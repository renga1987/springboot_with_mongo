package com.rest.comcast.MNGODB;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


import java.util.*;

@Service
@EnableConfigurationProperties
@RestController
public class RecognitionController {

    @Autowired
    private ColleagueRepository repository;
    public Colleague coll;
    
    private final Logger logger = LoggerFactory.getLogger(RecognitionController.class);
    
    @PostMapping("${st1}")
   
    public ResponseEntity<String> addColleague(@RequestBody Colleague colleague){
        logger.debug("AddColleague started");
    	repository.save(colleague);
    	logger.debug("Colleague added successfully");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @RequestMapping(value="/colleagues",method=RequestMethod.GET)
    public List<Colleague> getColleagues(){
    	logger.debug("GetColleague started");
    	logger.debug("Get Colleague done successfully");
    	return repository.findAll();
    	
    }

    @GetMapping(value="/colleagues/{name}")
    
    public List<Colleague> getColleague(@PathVariable String name){
    	logger.debug("GetColleague by name started");
    	logger.debug("Get Colleague by name done successfully");
        return repository.findByName(name);
    }

    //This is of course a very naive implementation! We are assuming unique names...
    @DeleteMapping("/colleagues/{name}")
    @HystrixCommand(fallbackMethod = "deleteColleagueFallBack", commandKey="customCommandKey")
    public ResponseEntity<String> deleteColleague(@PathVariable  String name){
    		List<Colleague> colleagues = repository.findByName(name);
    		logger.debug("findAll method started to deleted specific colleague");
    		int j=0;
    		for(int i=0;i<colleagues.size();i++)
    			{
    			logger.debug("Iteration started to delete the colleague in index"+i);
    			Colleague colleague = colleagues.get(i);
        		repository.delete(colleague);
        		logger.debug("Deleted the colleague in index"+i);
        		j++;
        	}    		
    	
    	if((j==colleagues.size()) && (j!=0))
    	{
    		logger.info("Successfully deleted the specified colleague from repository");
    		return new ResponseEntity<>(HttpStatus.ACCEPTED);
    	}
    	else
    	{
    		logger.warn("something went wrong");
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	
    	
}
    public ResponseEntity<String> deleteColleagueFallBack(@PathVariable String name){
    	
       logger.debug("Fallback method entered");
    return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        
    
    }
    
    
    
    
    
    
    
}






/**/
