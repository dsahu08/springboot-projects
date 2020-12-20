package com.projects.springboot.demo1.springbootdemo1.dao;

import java.util.List;

import com.projects.springboot.demo1.springbootdemo1.entity.Employee;

public interface EmployeeDAO {
	
	public List<Employee> findAll();
	public Employee findById(int theId);
	public void addEmployee(Employee theEmployee);
	public void deleteEmployee(int theId);

}
