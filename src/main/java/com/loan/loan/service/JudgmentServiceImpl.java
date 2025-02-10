package com.loan.loan.service;

import com.loan.loan.dto.JudgmentDto;
import com.loan.loan.repository.ApplicationRepository;
import com.loan.loan.repository.JudgmentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JudgmentServiceImpl implements JudgmentService {

    private final JudgmentRepository judgmentRepository;

    private final ApplicationRepository applicationRepository;

    private final ModelMapper modelMapper;

    @Override
    public JudgmentDto.Response create(JudgmentDto.Request request) {
        // 신청 정보 검증
        //request dto -> entity -> save
        //save -> response dto



        return null;
    }

    private boolean isPresnetApplication(Long applicationId){
        return applicationRepository.findById(applicationId).isPresent();
    }
}
