package kr.co.bnksys.querydsl.service;

import java.util.List;

import kr.co.bnksys.querydsl.model.second.Code;

public interface CodeService {

    List<Code> findByCode(String codeText);

}
