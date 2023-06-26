package com.tatienduat.springboot.thymeafdemo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Document(collection = "user")
public class UserMongo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	private String phone;
	private String email;
	private String userName;
	private DepartmentMongo departmentId;

	public UserMongo() {

	}



	public UserMongo(int id, String name, String email, String userName, DepartmentMongo departmentId) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.userName = userName;
		this.departmentId = departmentId;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public DepartmentMongo getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(DepartmentMongo departmentId) {
		this.departmentId = departmentId;
	}



	@Override
	public String toString() {
		return "UserMongo [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", userName="
				+ userName + ", departmentId=" + departmentId + "]";
	}





}
