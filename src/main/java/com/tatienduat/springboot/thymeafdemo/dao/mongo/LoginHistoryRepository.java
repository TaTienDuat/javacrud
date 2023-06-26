package com.tatienduat.springboot.thymeafdemo.dao.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tatienduat.springboot.thymeafdemo.document.LoginHistory;

@Repository
public interface LoginHistoryRepository extends MongoRepository<LoginHistory, Integer> {

}
