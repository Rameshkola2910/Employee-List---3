/*
 * You can use the following import statements
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 */
package com.example.employee.service;
import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.repository.EmployeeJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@Service
public class EmployeeJpaService implements EmployeeRepository{

    @Autowired
    private  EmployeeJpaRepository employeeJpaRepository;

	@Override
	public ArrayList<Employee> getEmployees() {
		return (ArrayList<Employee>) employeeJpaRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		try{
            return employeeJpaRepository.findById(employeeId).get();
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
	}

	@Override
	public Employee addEmployee(Employee employee) {
		employeeJpaRepository.save(employee);
		return employee;
	}

	@Override
	public Employee updateEmployee(int employeeId, Employee employee) {
		try{
            Employee newemployee = employeeJpaRepository.findById(employeeId).get();
            if(employee.getEmployeeName() != null){
                newemployee.setEmployeeName(employee.getEmployeeName());
            }
            if(employee.getEmail() != null){
                newemployee.setEmail(employee.getEmail());
            }
            if(employee.getDepartment() != null){
                newemployee.setDepartment(employee.getDepartment());
            }
            employeeJpaRepository.save(newemployee);
            return newemployee;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
	}

	@Override
	public void deleteEmployee(int employeeId) {
		try{
            employeeJpaRepository.deleteById(employeeId);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
	}
    
}