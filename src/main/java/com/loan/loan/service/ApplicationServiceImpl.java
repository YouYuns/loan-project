package com.loan.loan.service;

import com.loan.loan.domain.Application;
import com.loan.loan.dto.ApplicationDto;
import com.loan.loan.exception.BaseException;
import com.loan.loan.exception.ResultType;
import com.loan.loan.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService{

    private final ApplicationRepository applicationRepository;

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
}
