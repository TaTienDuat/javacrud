package com.tatienduat.springboot.thymeafdemo.dao.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tatienduat.springboot.thymeafdemo.entity.Role;

public interface RoleRepostory extends JpaRepository<Role, Integer> {

	@Override
	public List<Role> findAll();
}
