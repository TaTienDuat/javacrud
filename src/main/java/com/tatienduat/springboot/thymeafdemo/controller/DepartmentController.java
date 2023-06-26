package com.tatienduat.springboot.thymeafdemo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tatienduat.springboot.thymeafdemo.document.DepartmentMongo;
import com.tatienduat.springboot.thymeafdemo.entity.Department;
import com.tatienduat.springboot.thymeafdemo.service.DepartmentSevice;
import com.tatienduat.springboot.thymeafdemo.service.mongo.DepartmentMongoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/department")
public class DepartmentController {

	private DepartmentSevice departmentSevice;

	private DepartmentMongoService departmentMongoService;

	// add mapping for "/list"

	public DepartmentController(DepartmentSevice departmentSevice, DepartmentMongoService departmentMongoService) {
		this.departmentSevice = departmentSevice;
		this.departmentMongoService = departmentMongoService;
	}

	@GetMapping("/list")
	public String listDepartment(Model theModel) {

		// get department form database
		List<Department> theDepartments = departmentSevice.findAll();

		theModel.addAttribute("department", theDepartments);

		return "department/list-department";

	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data

		Department theDepartment = new Department();

		theModel.addAttribute("department", theDepartment);

		return "department/department-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("departmentId") int theId, Model theModel) {

		// get the department from the service
		Department theDepartment = departmentSevice.findById(theId);
//		DepartmentMongo theDepartmentMongo = departmentMongoService.findById(theId);

		// set department as a model attribute to pre-populate the form

		theModel.addAttribute("department", theDepartment);
//		theModel.addAttribute("department", theDepartmentMongo);

		// send over to our
		return "department/department-form";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("departmentId") int theId) {

		// delete the department
		departmentSevice.deleteId(theId);
		departmentMongoService.deleteId(theId);

		// redirect to /department/list
		return "redirect:/department/list";

	}

	@PostMapping("/save")
	public String saveDepartment(@Valid Department theDepartment, DepartmentMongo thedepartmentMongo,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "department/department-form";
		}

		// save the department

		departmentMongoService.save(thedepartmentMongo);

		departmentSevice.save(theDepartment);

		// use a redirect to prevent duplicate submissions
		return "redirect:/department/list";

	}

}
