package com.tatienduat.springboot.thymeafdemo.dao.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tatienduat.springboot.thymeafdemo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{


	// add a method to sort by name
	public List<User> findAllByOrderByNameAsc();

	public User findByUserName(String userName);



}
