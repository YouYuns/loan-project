package com.loan.loan.service;

import com.loan.loan.dto.ApplicationDto;

public interface ApplicationService {

    ApplicationDto.Response create(ApplicationDto.Request request);


    ApplicationDto.Response get(Long applicationId);

    ApplicationDto.Response update(Long applicationId, ApplicationDto.Request request);

    void delete(Long applicationId);

    Boolean acceptTemrs(Long applicationId, ApplicationDto.AcceptTerms request);
}
