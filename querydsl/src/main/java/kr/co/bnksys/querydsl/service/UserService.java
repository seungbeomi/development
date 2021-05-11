package kr.co.bnksys.querydsl.service;

import kr.co.bnksys.querydsl.model.first.User;

public interface UserService {

    User findByName(String name);

    User save(User user);

}
