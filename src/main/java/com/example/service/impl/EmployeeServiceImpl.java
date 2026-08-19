package com.example.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.DtoDepartment;
import com.example.dto.DtoEmployee;
import com.example.model.Department;
import com.example.model.Employee;
import com.example.repository.EmployeeRepository;
import com.example.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public DtoEmployee finDtoEmployeeById(Long Id) {
		
		DtoEmployee dtoEmployee= new DtoEmployee();
		DtoDepartment dtoDepartment= new  DtoDepartment();
		
		Optional<Employee> optional = employeeRepository.findById(Id);
		
		if(optional.isEmpty()) {
			//exception
			return null;
		}
		
		Employee employee=optional.get();
		Department department=optional.get().getDepartment();
		
		BeanUtils.copyProperties(employee, dtoEmployee);
		BeanUtils.copyProperties(department, dtoDepartment);
		dtoEmployee.setDepartment(dtoDepartment);
		return dtoEmployee;
	}

}
