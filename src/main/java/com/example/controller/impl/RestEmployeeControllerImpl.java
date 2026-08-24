package com.example.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controller.IRestEmployeeController;
import com.example.dto.DtoEmployee;
import com.example.service.IEmployeeService;



@RestController
@RequestMapping("/employee")
public class RestEmployeeControllerImpl implements IRestEmployeeController {

	@Autowired
	private IEmployeeService employeeService;
	
	
	@GetMapping("/{id}")
	@Override
	public DtoEmployee finDtoEmployeeById(@PathVariable Long id) {

		return employeeService.finDtoEmployeeById(id);
	}
	

}
