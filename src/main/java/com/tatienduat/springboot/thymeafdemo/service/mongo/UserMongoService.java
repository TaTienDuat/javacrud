package com.tatienduat.springboot.thymeafdemo.service.mongo;

import java.util.List;

import com.tatienduat.springboot.thymeafdemo.document.UserMongo;


public interface UserMongoService {

	public List<UserMongo> findAll();

	public UserMongo findById(int theId);

	public void save(UserMongo theUser);

	public void deleteId(int theId);




}
