 package com.tatienduat.springboot.thymeafdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tatienduat.springboot.thymeafdemo.dao.jpa.DepartmentRepository;
import com.tatienduat.springboot.thymeafdemo.entity.Department;


@Service
public class DepartmentImpl implements DepartmentSevice {

	private DepartmentRepository departmentRepository;


	@Autowired
	public DepartmentImpl(DepartmentRepository thedepartmentRepository) {
		this.departmentRepository = thedepartmentRepository;
	}



	@Override
	public List<Department> findAll() {
		return departmentRepository.findAllByOrderByNameAsc();
	}



	@Override
	public Department findById(int theId) {
		Optional<Department> result = departmentRepository.findById(theId);

		Department theDepartment =null;

		if(result.isPresent()) {
			theDepartment = result.get();
		} else {
			// we didn't find the department
			throw new RuntimeException("Did not find department id - "+ theId);
		}

		return theDepartment;
	}

	@Override
	public void save(Department theDepartment) {

		departmentRepository.save(theDepartment);
	}

	@Override
	public void deleteId(int theId) {
		departmentRepository.deleteById(theId);
	}



}
