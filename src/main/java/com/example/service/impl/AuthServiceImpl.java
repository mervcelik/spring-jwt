package com.example.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dto.DtoUser;
import com.example.jwt.AuthRequest;
import com.example.jwt.AuthResponse;
import com.example.jwt.JwtService;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.IAuthService;

@Service
public class AuthServiceImpl implements IAuthService {

    private final AuthenticationProvider authenticationProvider;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;

    AuthServiceImpl(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }
	
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

	@Override
	public AuthResponse authenticate(AuthRequest request) {
	
		try {
			UsernamePasswordAuthenticationToken auth= new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword());

			 authenticationProvider.authenticate(auth);
			Optional<User> optional = userRepository.findByUsername(request.getUsername());
			 String token = jwtService.generateToken(optional.get());
			 
			 return new AuthResponse(token);
		} catch (Exception e) {
			System.out.println("Kullanıcı adı veya şifre hatalı");
		}
		return null;
	}

}
