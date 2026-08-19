package com.example.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dto.DtoUser;
import com.example.jwt.AuthRequest;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.IAuthService;

@Service
public class AuthServiceImpl implements IAuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public DtoUser register(AuthRequest request) {
		DtoUser dtoUser= new DtoUser();
		User user = new User();
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		
		User savedUser = userRepository.save(user);
		BeanUtils.copyProperties(savedUser, dtoUser);
		return dtoUser;
	}

}
