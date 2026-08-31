package com.example.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controller.IRestEmployeeController;
import com.example.dto.DtoEmployee;
import com.example.model.Employee;
import com.example.service.IEmployeeService;
import com.example.utils.RestPageableEntity;
import com.example.utils.RestPageableRequest;
import com.example.utils.RestRootEntity;

@RestController
@RequestMapping("/employee")
public class RestEmployeeControllerImpl extends RestBaseController implements IRestEmployeeController {

	@Autowired
	private IEmployeeService employeeService;

	@GetMapping("/{id}")
	@Override
	public DtoEmployee finDtoEmployeeById(@PathVariable Long id) {

		return employeeService.finDtoEmployeeById(id);
	}

	@GetMapping("list/pageable")
	@Override
	public RestRootEntity<RestPageableEntity<DtoEmployee>> findAllPageable(@ModelAttribute RestPageableRequest pageable) {
		
		Page<Employee> page= employeeService.findAllPageable(toPageable(pageable));
		RestPageableEntity<DtoEmployee> pageableResponse = toPageableResponse(page, employeeService.toDTOList(page.getContent()));
		
		return ok(pageableResponse);
	}

}
