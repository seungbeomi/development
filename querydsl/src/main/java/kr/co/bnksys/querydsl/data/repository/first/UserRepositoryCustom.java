package kr.co.bnksys.querydsl.data.repository.first;

import kr.co.bnksys.querydsl.data.repository.first.output.UserOutput;
import kr.co.bnksys.querydsl.model.first.User;

public interface UserRepositoryCustom {

    User findByName(String name);

    UserOutput findDtoByName(String name);

}
