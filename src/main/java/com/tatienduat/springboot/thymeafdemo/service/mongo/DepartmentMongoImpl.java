package com.tatienduat.springboot.thymeafdemo.service.mongo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tatienduat.springboot.thymeafdemo.dao.mongo.DepartmentMongoRepository;
import com.tatienduat.springboot.thymeafdemo.document.DepartmentMongo;


@Service
public class DepartmentMongoImpl implements DepartmentMongoService {

	private DepartmentMongoRepository departmentMongoRepository;


	
	public DepartmentMongoRepository getDepartmentMongoRepository() {
		return departmentMongoRepository;
	}



	public void setDepartmentMongoRepository(DepartmentMongoRepository departmentMongoRepository) {
		this.departmentMongoRepository = departmentMongoRepository;
	}



	public DepartmentMongoImpl(DepartmentMongoRepository departmentMongoRepository) {
		this.departmentMongoRepository = departmentMongoRepository;
	}



	@Override
	public List<DepartmentMongo> findAll() {
		return departmentMongoRepository.findAll();
	}

	@Override
	public DepartmentMongo findById(int theId) {
		Optional<DepartmentMongo> result = departmentMongoRepository.findById(theId);

		DepartmentMongo theDepartmentMongo = null;

		if (result.isPresent()) {
			theDepartmentMongo = result.get();
		} else {
			throw new RuntimeException("Did not find department id - " + theId);
		}
		return theDepartmentMongo;
	}

	@Override
	public void save(DepartmentMongo theDepartmentMongo) {

		
		departmentMongoRepository.save(theDepartmentMongo);

	}

	@Override
	public void deleteId(int theId) {
		departmentMongoRepository.deleteById(theId);
	}

}
