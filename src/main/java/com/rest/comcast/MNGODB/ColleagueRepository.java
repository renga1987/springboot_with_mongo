package com.rest.comcast.MNGODB;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface ColleagueRepository extends MongoRepository<Colleague, String> {

    
    public List<Colleague> findByName(String name);

}
