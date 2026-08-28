package com.example.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.controller.IRestEmployeeController;
import com.example.dto.DtoEmployee;
import com.example.model.Employee;
import com.example.service.IEmployeeService;
import com.example.utils.RestPageableRequest;

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

	@GetMapping("list/pageable")
	@Override
	public Page<Employee> findAllPageable(@ModelAttribute RestPageableRequest pageable) {
		
		PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
		return employeeService.findAllPageable(pageRequest);
	}

}
