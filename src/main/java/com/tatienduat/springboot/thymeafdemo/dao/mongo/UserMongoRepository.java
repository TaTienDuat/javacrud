package com.tatienduat.springboot.thymeafdemo.dao.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tatienduat.springboot.thymeafdemo.document.UserMongo;

public interface UserMongoRepository extends MongoRepository<UserMongo, Integer> {

}


