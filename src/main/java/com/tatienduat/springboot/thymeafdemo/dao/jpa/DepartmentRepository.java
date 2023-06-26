package com.tatienduat.springboot.thymeafdemo.dao.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tatienduat.springboot.thymeafdemo.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	public List<Department> findAllByOrderByNameAsc();
}
