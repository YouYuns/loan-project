package com.loan.loan.service;

import com.loan.loan.dto.JudgmentDto;

public interface JudgmentService {

    JudgmentDto.Response create(JudgmentDto.Request request);
}
