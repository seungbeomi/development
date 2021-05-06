package kr.co.bnksys.querydsl.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import kr.co.bnksys.querydsl.data.mapper.UserMapper;
import kr.co.bnksys.querydsl.data.repository.UserRepository;
import kr.co.bnksys.querydsl.data.repository.output.UserOutput;
import kr.co.bnksys.querydsl.model.User;
import kr.co.bnksys.querydsl.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Transactional
    @Override
    public User save(User user) {

        UserOutput output = userRepository.findDtoByName("44444");
        userMapper.updateUser(user);
        User result = userRepository.save(user);

        //throw new RuntimeException("ERRORRRRRRRRR!!!!");
        return result;
    }

}
