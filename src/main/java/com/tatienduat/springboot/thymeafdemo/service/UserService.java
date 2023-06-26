package com.tatienduat.springboot.thymeafdemo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.tatienduat.springboot.thymeafdemo.entity.User;

public interface UserService extends UserDetailsService {

	public List<User> findAll();

	public User findById(int theId);

	public void save(User theUser);

	public void deleteId(int theId);

	public User findByUserName(String userName);

	Page<User> findPaginated(int pageNo, int pageSize);

}
