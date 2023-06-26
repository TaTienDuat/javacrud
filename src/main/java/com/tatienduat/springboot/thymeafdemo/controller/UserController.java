package com.tatienduat.springboot.thymeafdemo.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tatienduat.springboot.thymeafdemo.entity.Department;
import com.tatienduat.springboot.thymeafdemo.entity.Role;
import com.tatienduat.springboot.thymeafdemo.entity.User;
import com.tatienduat.springboot.thymeafdemo.service.DepartmentSevice;
import com.tatienduat.springboot.thymeafdemo.service.RoleSevice;
import com.tatienduat.springboot.thymeafdemo.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

	private UserService userService;
	private DepartmentSevice departmentSevice;
	private RoleSevice roleSevice;




	// add mapping for "/list"

	public UserController(UserService userService, DepartmentSevice departmentSevice, RoleSevice roleSevice) {
		this.userService = userService;
		this.departmentSevice = departmentSevice;
		this.roleSevice = roleSevice;
	}


	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		return findPainated(1, theModel);
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("userId") int theId, Model theModel) {

		// get the user form the service
		User theUser = userService.findById(theId);
		theModel.addAttribute("user", theUser);

		List<Department> theDepartments = departmentSevice.findAll();
		theModel.addAttribute("department", theDepartments);

		List<Role> theRoles = roleSevice.findAll();
		theModel.addAttribute("role", theRoles);

		// add to the spring model

		// set user as a model attribute to pre-populate the form

		// send over to our form
		return "user/user-form";

	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		User theUser = new User();

		List<Department> theDepartments = departmentSevice.findAll();
		// add to the spring model
		theModel.addAttribute("department", theDepartments);

		theModel.addAttribute("user", theUser);

		List<Role> theRoles = roleSevice.findAll();
		theModel.addAttribute("role", theRoles);



		return "user/user-form";
	}

	@PostMapping("/save")
	public String saveUser(@Valid User theUser, BindingResult bindingResult, Model theModel) {

		List<Department> theDepartments = departmentSevice.findAll();
		theModel.addAttribute("department", theDepartments);

		List<Role> theRoles = roleSevice.findAll();
		theModel.addAttribute("role", theRoles);

		// check the database if user already exists
		String userName = theUser.getUserName();
		User existing = userService.findByUserName(userName);

		if (existing != null) {
			theModel.addAttribute("exists", "User name already exists.");
			return "user/user-form";
		}

		try {
			if (bindingResult.hasErrors()) {
				return "user/user-form";
			}
			// save the user
			userService.save(theUser);
			// use a redirect to prevent duplicate submissions
			return "redirect:/user/list";
		} catch (Exception e) {
			theModel.addAttribute("error", "Email already exists");
		}
		return "user/user-form";

	}

	@GetMapping("/delete")
	public String delete(@RequestParam("userId") int theId, Model theModel) {

		// delete the user
		userService.deleteId(theId);

		// redirect to /user/list
		return "redirect:/user/list";

	}

	@GetMapping("/page/{pageNo}")
	public String findPainated(@PathVariable(value = "pageNo") int pageNo, Model theModel) {
		int pageSize = 5;

		Page<User> page = userService.findPaginated(pageNo, pageSize);
		List<User> theUsers = page.getContent();

		theModel.addAttribute("currentPage", pageNo);
		theModel.addAttribute("totalPages", page.getTotalPages());
		theModel.addAttribute("totalItems", page.getTotalElements());
		theModel.addAttribute("user", theUsers);

		return "user/list-user";

	}

}
