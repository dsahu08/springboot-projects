package com.projects.springboot.demo1.springbootdemo1.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.projects.springboot.demo1.springbootdemo1.entity.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

	private EntityManager entityManager;
	
	public EmployeeDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	//List all the employees from Employee Table
	@Override
	public List<Employee> findAll() {
		
		Query theQuery = entityManager.createQuery("from Employee");
		List<Employee> employees = theQuery.getResultList();
		return employees;
	}

	//Find the Employee by employeeID
	@Override
	public Employee findById(int theId) {
		Employee theEmployee = entityManager.find(Employee.class, theId);
		return theEmployee;
	}

	// Add/update a new Employee to the Employee Table
	@Override
	public void addEmployee(Employee theEmployee) {
		Employee dbEmployee = entityManager.merge(theEmployee);
		dbEmployee.setId(dbEmployee.getId());
	
	}
	

//Delete an employee from the table
	@Override
	public void deleteEmployee(int theId) {
		Query query = entityManager.createQuery("delete from Employee where id=:employeeId ");
		query.setParameter("employeeId",theId);
		query.executeUpdate();
		

	}

}
