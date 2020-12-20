package com.projects.springboot.demo1.springbootdemo1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projects.springboot.demo1.springbootdemo1.dao.EmployeeDAO;
import com.projects.springboot.demo1.springbootdemo1.entity.Employee;

@RestController
public class EmployeeRestController {
	private EmployeeDAO employeeDAO;
	
	@Autowired
	public EmployeeRestController(EmployeeDAO theEmployeeDAO) {
		employeeDAO = theEmployeeDAO;
	}
	
	@GetMapping("/employees")
	public List<Employee> findAll(){
		return employeeDAO.findAll();
	}
	
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {
		Employee employee = employeeDAO.findById(employeeId);
		
		if(employee==null)
		{
			throw new RuntimeException("Employee with id:"+employeeId+" not found");
		}
		return employee;
	}
	
	@PostMapping("/employees")
	@Transactional
	public Employee addAEmployee(@RequestBody Employee theEmployee) {
		
		theEmployee.setId(0);
		employeeDAO.addEmployee(theEmployee);
		return theEmployee;
		
	}
	
	@PutMapping("/employees")
	@Transactional
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
		employeeDAO.addEmployee(theEmployee);
		return theEmployee;
	}
	
	@DeleteMapping("/employees/{employeeId}")
	@Transactional
	public Employee deleteEmployee(@PathVariable int employeeId) {
		Employee employee = employeeDAO.findById(employeeId);
		if(employee==null) {
			throw new RuntimeException("Employee with id:"+"employeeId+ has not been found");
		}
		employeeDAO.deleteEmployee(employeeId);
		//return "Employee deleted";
		return employee;
		
	}

}
