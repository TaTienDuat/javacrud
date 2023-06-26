package com.tatienduat.springboot.thymeafdemo.service;

import java.util.List;

import com.tatienduat.springboot.thymeafdemo.entity.Department;

public interface DepartmentSevice {


	public List<Department> findAll();

	public Department findById(int theId);

	public void save (Department theDepartment);

	public void deleteId(int theId);




}
