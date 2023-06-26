package com.tatienduat.springboot.thymeafdemo.entity;

import java.util.Collection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "user")
public class User {

	// define fields

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotEmpty
	@Column(name = "name")
	private String name;

	@NotEmpty
	// Regular Expression
	@Pattern(regexp = "^[0-9]*$", message = "only number")
	@Column(name = "phone")
	private String phone;

	@NotEmpty
	@Email(message = "Email needs correct format e.g: test@gmail.com ")
	@Column(name = "email", unique = true)
	private String email;


	@NotEmpty
	@Column(name = "username", unique = true)
	private String userName;

	@NotEmpty
	@Column(name = "password")
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "department_id")
	private Department departmentId;

	// define constructor
	public User() {

	}





	// constructor
	public User(int id, String name, String phone, String email,String userName, String passWord, Department departmentId) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.userName = userName;
		this.password = passWord;
		this.departmentId = departmentId;
	}

	public User(int id, String name, String phone, String email,String userName, String password, Collection<Role> roles,
			Department departmentId) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.roles = roles;
		this.departmentId = departmentId;
	}

	// getter and setter

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public Department getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Department departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", userName=" + userName
				+ ", password=" + password + ", roles=" + roles + ", departmentId=" + departmentId + "]";
	}



}
