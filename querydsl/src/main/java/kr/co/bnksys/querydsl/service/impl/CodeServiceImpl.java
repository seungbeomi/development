package kr.co.bnksys.querydsl.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.bnksys.querydsl.data.repository.second.CodeRepository;
import kr.co.bnksys.querydsl.model.second.Code;
import kr.co.bnksys.querydsl.service.CodeService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CodeServiceImpl implements CodeService {

    private final CodeRepository codeRepository;

    @Override
    public List<Code> findByCode(String codeText) {
        return codeRepository.findByCode(codeText);
    }

}
