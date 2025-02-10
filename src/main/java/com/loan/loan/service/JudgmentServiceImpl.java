package com.loan.loan.service;

import com.loan.loan.domain.Application;
import com.loan.loan.domain.Judgment;
import com.loan.loan.dto.ApplicationDto;
import com.loan.loan.dto.JudgmentDto;
import com.loan.loan.exception.BaseException;
import com.loan.loan.exception.ResultType;
import com.loan.loan.repository.ApplicationRepository;
import com.loan.loan.repository.JudgmentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class JudgmentServiceImpl implements JudgmentService {

    private final JudgmentRepository judgmentRepository;

    private final ApplicationRepository applicationRepository;

    private final ModelMapper modelMapper;

    @Override
    public JudgmentDto.Response create(JudgmentDto.Request request) {
        // 신청 정보 검증
        //신청정보가있는지 확인
        Long applicationId = request.getApplicationId();
        if(!isPresnetApplication(applicationId)){
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }


        //request dto -> entity -> save
        Judgment judgment = modelMapper.map(request, Judgment.class);
        Judgment saved = judgmentRepository.save(judgment);


        //save -> response dto
        return modelMapper.map(saved, JudgmentDto.Response.class);
    }

    @Override
    public JudgmentDto.Response get(Long judgementId) {
        Judgment judgment = judgmentRepository.findById(judgementId).orElseThrow(() -> {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        });
        return modelMapper.map(judgment, JudgmentDto.Response.class);
    }

    @Override
    public JudgmentDto.Response getJudgmentOfApplication(Long applicationId) {
        if(!isPresnetApplication(applicationId)){
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }

        Judgment judgment = judgmentRepository.findByApplicationId(applicationId).orElseThrow(()-> {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        });

        return modelMapper.map(judgment, JudgmentDto.Response.class);
    }

    @Override
    public void delete(Long judgementId) {
        Judgment judgment = judgmentRepository.findById(judgementId).orElseThrow(() ->{
            throw new BaseException(ResultType.SYSTEM_ERROR);
        });

        judgment.setIsDeleted(true);
        judgmentRepository.save(judgment);
    }

    @Override
    public JudgmentDto.Response update(Long judgementId, JudgmentDto.Request request) {
        Judgment judgment = judgmentRepository.findById(judgementId).orElseThrow(() ->{
            throw new BaseException(ResultType.SYSTEM_ERROR);
        });
        judgment.setName(request.getName());
        judgment.setApprovalAmount(request.getApprovalAmount());


        judgmentRepository.save(judgment);
        return modelMapper.map(judgment, JudgmentDto.Response.class);
    }


    @Override
    public ApplicationDto.GrantAmount grant(Long judgementId) {
        Judgment judgment = judgmentRepository.findById(judgementId).orElseThrow(() -> new BaseException(ResultType.SYSTEM_ERROR));

        Long applicationId = judgment.getApplicationId();
        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> new BaseException(ResultType.SYSTEM_ERROR));

        BigDecimal approvalAmount = judgment.getApprovalAmount();
        application.setApprovalAmount(approvalAmount);

        applicationRepository.save(application);

        return modelMapper.map(application, ApplicationDto.GrantAmount.class);
    }

    private boolean isPresnetApplication(Long applicationId){
        return applicationRepository.findById(applicationId).isPresent();
    }
}
