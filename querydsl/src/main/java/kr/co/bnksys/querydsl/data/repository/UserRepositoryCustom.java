package kr.co.bnksys.querydsl.data.repository;

import kr.co.bnksys.querydsl.data.repository.output.UserOutput;
import kr.co.bnksys.querydsl.model.User;

public interface UserRepositoryCustom {

    User findByName(String name);

    UserOutput findDtoByName(String name);

}
