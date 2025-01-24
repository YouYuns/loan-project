package com.loan.loan.service;

import com.loan.loan.domain.AcceptTerms;
import com.loan.loan.domain.Application;
import com.loan.loan.domain.Terms;
import com.loan.loan.dto.ApplicationDto;
import com.loan.loan.exception.BaseException;
import com.loan.loan.exception.ResultType;
import com.loan.loan.repository.AcceptTermsRepository;
import com.loan.loan.repository.ApplicationRepository;
import com.loan.loan.repository.TermsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService{

    private final ApplicationRepository applicationRepository;

    private final TermsRepository termsRepository;

    private final AcceptTermsRepository acceptTermsRepository;

    private final ModelMapper  modelMapper;
    @Override
    public ApplicationDto.Response create(ApplicationDto.Request request) {
        Application application = modelMapper.map(request, Application.class);
        application.setAppliedAt(LocalDateTime.now());

        Application applied = applicationRepository.save(application);
        return modelMapper.map(applied, ApplicationDto.Response.class);
    }

    @Override
    public ApplicationDto.Response get(Long applicationId) {
        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        });


        return modelMapper.map(application, ApplicationDto.Response.class);
    }

    @Override
    public ApplicationDto.Response update(Long applicationId, ApplicationDto.Request request) {
        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        });

        application.setName(request.getName());
        application.setEmail(request.getEmail());
        application.setCellPhone(request.getCellPhone());
        application.setHopeAmount(request.getHopeAmount());

        applicationRepository.save(application);

        return modelMapper.map(application, ApplicationDto.Response.class);
    }

    @Override
    public void delete(Long applicationId) {
        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        });

        applicationRepository.deleteById(applicationId);
    }

    @Override
    public Boolean acceptTemrs(Long applicationId, ApplicationDto.AcceptTerms request) {
        //1. validation 대출신청정보가 존재해야된다.
        applicationRepository.findById(applicationId).orElseThrow(() -> {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        });

        //2. validation 저장된 약관이 1개라도 있어야된다.
        //저장되어있는 약관을 먼저 조회한다 termsId를 기준으로 정렬까지
        List<Terms> termsList = termsRepository.findAll(Sort.by(Sort.Direction.ASC, "termsId"));
        if(termsList.isEmpty()){
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }

        //3. validation 대출회사가 게시해놓은 약관수와 고객이 동의한 약관수가 동일해야된다.
        //요청 약관 여러개일수있으니 요청약관과 저장되어있는 약관 validation
        List<Long> acceptTermsIds = request.getAcceptTermsIds();
        if (termsList.size() != acceptTermsIds.size()) {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }


        List<Long> termsIds = termsList.stream().map(Terms::getTermsId).collect(Collectors.toList());
        //요청 약관을 id에 대해서 정렬
        Collections.sort(acceptTermsIds);


        //4. validation 고객이 동의한 약관과 게시해놓은 약관이 같은 약관이 아닌 값이 들어왔을때
        if(termsIds.containsAll(acceptTermsIds)){
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }


        //validation이 끝나면 모든 고객이 동의한 약관에 대해서 AcceptTerms 저장을한다.
        for(Long termsId : acceptTermsIds){
            AcceptTerms accepted = AcceptTerms.builder()
                    .termsId(termsId)
                    .applicationId(applicationId)
                    .build();
            acceptTermsRepository.save(accepted);
        }

        return true;
    }
}
