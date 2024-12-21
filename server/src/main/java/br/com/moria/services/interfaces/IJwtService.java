package br.com.moria.services.interfaces;

import jakarta.servlet.http.HttpServletRequest;

public interface IJwtService {
    void validateAndAuthenticate(String jwt, HttpServletRequest request);
}
