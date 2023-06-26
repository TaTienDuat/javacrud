package com.tatienduat.springboot.thymeafdemo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tatienduat.springboot.thymeafdemo.dao.jpa.RoleRepostory;
import com.tatienduat.springboot.thymeafdemo.entity.Role;

@Service
public class RoleSeviceImpl implements RoleSevice {

	private RoleRepostory roleReposttory;


	public RoleSeviceImpl(RoleRepostory roleReposttory) {
		this.roleReposttory = roleReposttory;
	}


	@Override
	public List<Role> findAll() {
		return roleReposttory.findAll();
	}


	@Override
	public void save(Role theRole) {
		roleReposttory.save(theRole);

	}

}
