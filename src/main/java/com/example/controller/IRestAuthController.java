package com.example.controller;

import com.example.dto.DtoUser;
import com.example.jwt.AuthRequest;
import com.example.jwt.AuthResponse;

public interface IRestAuthController {
	public DtoUser register(AuthRequest request);
	public AuthResponse authenticate(AuthRequest request);
}
