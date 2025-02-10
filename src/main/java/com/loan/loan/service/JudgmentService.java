package com.loan.loan.service;

import com.loan.loan.dto.ApplicationDto;
import com.loan.loan.dto.JudgmentDto;

public interface JudgmentService {

    JudgmentDto.Response create(JudgmentDto.Request request);

    JudgmentDto.Response get(Long judgementId);

    JudgmentDto.Response getJudgmentOfApplication(Long applicationId);

    JudgmentDto.Response update(Long judgementId, JudgmentDto.Request request);

    void delete(Long judgementId);

    ApplicationDto.GrantAmount grant(Long judgementId);
}

