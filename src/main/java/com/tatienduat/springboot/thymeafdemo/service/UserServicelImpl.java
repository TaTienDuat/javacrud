package com.tatienduat.springboot.thymeafdemo.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tatienduat.springboot.thymeafdemo.dao.jpa.UserRepository;
import com.tatienduat.springboot.thymeafdemo.entity.Role;
import com.tatienduat.springboot.thymeafdemo.entity.User;


@Service
public class UserServicelImpl implements UserService {




	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	private UserRepository userRepository;

	@Override
	@Transactional
	public User findByUserName(String userName) {
		// check the database if the user already exists

		return userRepository.findByUserName(userName);
	}



	@Autowired
	public UserServicelImpl(UserRepository theUserRepository) {
		this.userRepository = theUserRepository;
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAllByOrderByNameAsc();
	}

	@Override
	public User findById(int theId) {
		Optional<User> result = userRepository.findById(theId);

		User theUser = null;

		if (result.isPresent()) {
			theUser = result.get();
		} else {
			// we didn't find the user
			throw new RuntimeException("Did not find user id - " + theId);
		}

		return theUser;
	}

	@Override
	public void save(User theUser) {

		theUser.setPassword(passwordEncoder.encode(theUser.getPassword()));


		userRepository.save(theUser);
	}

	@Override
	public void deleteId(int theId) {
		userRepository.deleteById(theId);
	}

	@Override
	public Page<User> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(	pageNo-1, pageSize);
		return this.userRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUserName(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}




















}
