package com.example.controller;

import org.springframework.data.domain.Page;

import com.example.dto.DtoEmployee;
import com.example.model.Employee;
import com.example.utils.RestPageableRequest;

public interface IRestEmployeeController {
	public DtoEmployee finDtoEmployeeById(Long id);
	Page<Employee> findAllPageable(RestPageableRequest pageable ); 
}
