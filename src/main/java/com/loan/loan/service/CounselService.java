package com.loan.loan.service;


import com.loan.loan.dto.CounselDto;

public interface CounselService {

    CounselDto.Response create(CounselDto.Request request);
}
