package com.rest.comcast.MNGODB;



import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.ArrayList;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MngodbApplicationTests {

	@Test
	public void contextLoads() {
	}
	@Autowired
    private  RecognitionController mrc;
	public Colleague c1;
	public Colleague c2;
	public Recognition r1;
	public Recognition r2;
	public Recognition r3;
	 ArrayList<Colleague> lst=new ArrayList<Colleague>();
	 RecognitionController rcmock=mock(RecognitionController.class);
	 ColleagueRepository mcc=mock(ColleagueRepository.class);
	 
	@Before
	public  void setUp()  {
		 mrc=mock(RecognitionController.class);
		
		 r1=new Recognition("001","Java");
		 r2=new Recognition("002","SQL");
		 r3=new Recognition("003","MongoDb");
		 List<Recognition> al1=new ArrayList<Recognition>();
		 al1.add(r1);
		 al1.add(r2);
		 List<Recognition> al2=new ArrayList<Recognition>();
		 al2.add(r2);
		 al2.add(r3);
		 c1=new Colleague("1","Pearlin",al1);
		 c2=new Colleague("2","Priya",al2);
		 lst.add(c1);
		 lst.add(c2);
		 when(mcc.findAll()).thenReturn(lst);
		 when(rcmock.getColleagues()).thenReturn(lst);
	}
	
	@Test 
	public void addColleague() throws Exception{
	 when(rcmock.addColleague(c1)).thenReturn(new ResponseEntity<>(HttpStatus.CREATED));
	/* when(mrc.addColleague(c2)).thenReturn(new ResponseEntity<>(HttpStatus.CREATED));
	 List<Colleague> li=mcc.findAll();
	 	assertEquals(c1,li.get(0));
		assertEquals(c2,li.get(1));
		assertNotEquals(c2,li.get(0));
		assertNotEquals(c1,li.get(1));*/
	 ResponseEntity re=new ResponseEntity<>(HttpStatus.CREATED);
	// re=mrc.addColleague(c1);
	 assertThat(rcmock.addColleague(c1),is(HttpStatus.CREATED));
	 }
	
	@Test
	public void getColleague() throws Exception{
		boolean flag=false;
		List<Colleague> li=rcmock.getColleagues();
		assertEquals(lst,li);
		int size=rcmock.getColleagues().size();
		assertEquals(2, size);
	 	if(size==2)
	 	{
	 		flag=true;
	 	}
	 	assertFalse(!flag);
	 }
	
	@Test
	public void deleteColleague() throws Exception{
		when(rcmock.deleteColleague("Priya")).thenReturn(new ResponseEntity<>(HttpStatus.ACCEPTED));
		rcmock.deleteColleague("Priya");
	int size=rcmock.getColleagues().size();
	
	assertEquals(1,size);
	when(rcmock.deleteColleague("")).thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}
	
	
	
	
}




















