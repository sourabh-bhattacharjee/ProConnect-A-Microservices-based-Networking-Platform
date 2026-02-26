package com.sourabh.projects.linkedin.userserice.service;

import com.sourabh.projects.linkedin.userserice.dto.LoginRequestDto;
import com.sourabh.projects.linkedin.userserice.dto.SignupRequestDto;
import com.sourabh.projects.linkedin.userserice.dto.UserDto;
import com.sourabh.projects.linkedin.userserice.entity.User;
import com.sourabh.projects.linkedin.userserice.exception.BadRequestException;
import com.sourabh.projects.linkedin.userserice.exception.ResourceNotFoundException;
import com.sourabh.projects.linkedin.userserice.repository.UserRepository;
import com.sourabh.projects.linkedin.userserice.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JWTService jwtService;


    public UserDto signUp(SignupRequestDto signupRequestDto) {

        boolean exists = userRepository.existsByEmail(signupRequestDto.getEmail());

        if(exists){
           throw new BadRequestException("User already exists, cannot signup again");
        }

        User user  = modelMapper.map(signupRequestDto, User.class);
        user.setPassword(PasswordUtil.hashPassword(signupRequestDto.getPassword()));
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    public String login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User with email "+ loginRequestDto.getEmail()+ " not found"));

        boolean isPasswordMatch = PasswordUtil.checkPassword(loginRequestDto.getPassword(),user.getPassword());

        if(!isPasswordMatch){
            throw new BadRequestException("Incorrect password");
        }

        return jwtService.generateAccessToken(user);
    }
}
