//package com.store.storereservation.security;
//
//import lombok.AllArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class PasswordEncoderImpl implements PasswordEncoder {
//
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public String encode(CharSequence rawPassword) {
//        return passwordEncoder.encode(rawPassword);
//    }
//
//    @Override
//    public boolean matches(CharSequence rawPassword, String encodedPassword) {
//        return passwordEncoder.matches(rawPassword, encodedPassword);
//    }
//}
