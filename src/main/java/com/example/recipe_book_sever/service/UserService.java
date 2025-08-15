package com.example.recipe_book_sever.service;

import com.example.recipe_book_sever.model.User;
import com.example.recipe_book_sever.repository.UserRepository;
import com.example.recipe_book_sever.service.dto.LoginRequest;
import com.example.recipe_book_sever.service.dto.LoginResponse;
import com.example.recipe_book_sever.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public User createUser(User user) {
        try {
            // 이메일 중복 체크
            if (userRepository.existsByEmail(user.getEmail())) {
                throw new RuntimeException("이미 사용 중인 이메일입니다.");
            }

            // 닉네임 중복 체크
            if (userRepository.existsByNickname(user.getNickname())) {
                throw new RuntimeException("이미 사용 중인 닉네임입니다.");
            }

            // 비밀번호 암호화
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            // 사용자 저장
            return userRepository.save(user);

        } catch (DataIntegrityViolationException e) {
            // DB 제약조건 위반 (중복 등)
            throw new RuntimeException("이미 존재하는 사용자입니다.", e);

        } catch (Exception e) {
            throw new RuntimeException("사용자 생성 중 오류가 발생했습니다.", e);
        }
    }


    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("비밀번호가 올바르지 않습니다.");
        }

        String token = JwtUtil.generateToken(user.getEmail());

        // 콘솔 출력
        System.out.println("로그인 성공!");
        System.out.println("사용자 ID: " + user.getId());
        System.out.println("이메일: " + user.getEmail());
        System.out.println("닉네임: " + user.getNickname());
        System.out.println("발급된 토큰: " + token);

        return new LoginResponse(token, user.getId(), user.getEmail(), user.getNickname());
    }
}
