package com.example.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.dto.DtoEmployee;
import com.example.model.Employee;

public interface IEmployeeService {

	DtoEmployee finDtoEmployeeById(Long Id);
	
	Page<Employee> findAllPageable(Pageable pageable); 
	
	List<DtoEmployee> toDTOList(List<Employee> employees);
}
