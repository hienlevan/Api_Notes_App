package com.notes_api.controllers;

import com.notes_api.dtos.AccountDto;
import com.notes_api.dtos.TokenDetails;
import com.notes_api.exceptions.InvalidException;
import com.notes_api.exceptions.UserNotFoundAuthenticationException;
import com.notes_api.securities.CustomUserDetailsService;
import com.notes_api.securities.JwtTokenUtils;
import com.notes_api.securities.JwtUserDetails;
import com.notes_api.securities.UserAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/rest")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService customUserDetailsService;

    private final JwtTokenUtils jwtTokenUtils;

    private final RestTemplate restTemplate = new RestTemplate();

    public AuthenticationController(AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService,
                                    JwtTokenUtils jwtTokenUtils) {
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDetails> login(@RequestBody AccountDto dto) {
        UserAuthenticationToken authenticationToken = new UserAuthenticationToken(
                dto.getUsername(),
                dto.getPassword(),
                true
        );
        try {
            authenticationManager.authenticate(authenticationToken);
        } catch (UserNotFoundAuthenticationException | BadCredentialsException ex) {
            throw new InvalidException(ex.getMessage());
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        final JwtUserDetails userDetails = (JwtUserDetails) customUserDetailsService
                .loadUserByUsername(dto.getUsername());
        final TokenDetails result = jwtTokenUtils.getTokenDetails(userDetails, null);
        log.info(String.format("User %s login at %s", dto.getUsername(), new Date()));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
