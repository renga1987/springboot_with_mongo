package com.rest.comcast.MNGODB;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;




@JsonIgnoreProperties(ignoreUnknown = true)
public class Recognition {

    @Id
    public String skill_id;

    public String skill_name;
    
    public Recognition() {
        
    }

    public Recognition(String name) {
        this.skill_name = name;
       
    }

    public Recognition(String skill_id,String skill_name) {
        this.skill_id = skill_id;
        this.skill_name = skill_name;
    }

   
    }

