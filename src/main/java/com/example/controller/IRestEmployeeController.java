package com.example.controller;


import com.example.dto.DtoEmployee;
import com.example.utils.RestPageableEntity;
import com.example.utils.RestPageableRequest;
import com.example.utils.RestRootEntity;

public interface IRestEmployeeController {
	public DtoEmployee finDtoEmployeeById(Long id);
	RestRootEntity<RestPageableEntity<DtoEmployee>> findAllPageable(RestPageableRequest pageable ); 
}
