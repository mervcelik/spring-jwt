package com.example.service;

import com.example.dto.DtoUser;
import com.example.jwt.AuthRequest;

public interface IAuthService {
	public DtoUser register(AuthRequest request);
}
