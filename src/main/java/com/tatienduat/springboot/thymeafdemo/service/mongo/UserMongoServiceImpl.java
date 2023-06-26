package com.tatienduat.springboot.thymeafdemo.service.mongo;

import java.util.List;
import java.util.Optional;

import com.tatienduat.springboot.thymeafdemo.dao.mongo.UserMongoRepository;
import com.tatienduat.springboot.thymeafdemo.document.UserMongo;

public class UserMongoServiceImpl implements UserMongoService {

	private UserMongoRepository userMongoRepository;

	public UserMongoServiceImpl(UserMongoRepository userMongoRepository) {
		this.userMongoRepository = userMongoRepository;
	}

	@Override
	public List<UserMongo> findAll() {

		return userMongoRepository.findAll();

	}

	@Override
	public UserMongo findById(int theId) {

		Optional<UserMongo> result = userMongoRepository.findById(theId);

		UserMongo theUserMongo = null;

		if(result.isPresent()) {
			theUserMongo = result.get();
		} else {
			throw new RuntimeException("Did not find user id - " + theId);
		}

		return theUserMongo;
	}

	@Override
	public void save(UserMongo theUser) {
		userMongoRepository.save(theUser);

	}

	@Override
	public void deleteId(int theId) {
		userMongoRepository.deleteById(theId);

	}



}
