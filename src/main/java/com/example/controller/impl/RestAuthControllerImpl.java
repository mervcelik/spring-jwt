package com.example.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.controller.IRestAuthController;
import com.example.dto.DtoUser;
import com.example.jwt.AuthRequest;
import com.example.service.IAuthService;

import jakarta.validation.Valid;


@RestController
public class RestAuthControllerImpl  implements IRestAuthController{

	@Autowired
	private IAuthService authService;
	
	@PostMapping("/register")
	@Override
	public DtoUser register(@Valid @RequestBody AuthRequest request) {
		return authService.register(request);
	}


}
