package com.tatienduat.springboot.thymeafdemo.service.mongo;

import java.util.List;

import com.tatienduat.springboot.thymeafdemo.document.DepartmentMongo;

public interface DepartmentMongoService {

	public List<DepartmentMongo> findAll();

	public DepartmentMongo findById(int theId);

	public void save (DepartmentMongo theDepartmentMongo);

	public void deleteId(int theId);


}
