package com.tatienduat.springboot.thymeafdemo.service;

import java.util.List;

import com.tatienduat.springboot.thymeafdemo.entity.Role;

public interface RoleSevice {

	public List<Role> findAll();

	public void save(Role theRole);
}
