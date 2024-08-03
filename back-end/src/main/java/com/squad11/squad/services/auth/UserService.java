package com.squad11.squad.services.auth;

import com.squad11.squad.Utils.Dto.Auth.AuthRequest;

import java.util.Map;

public interface UserService {
    public Map<String, String> authentication(AuthRequest authRequest);

    public Map<String, String> register(AuthRequest authRequest);
}
