package com.loan.loan.service;


import com.loan.loan.dto.CounselDto;

public interface CounselService {

    CounselDto.Response create(CounselDto.Request request);

    CounselDto.Response get(Long counselId);

    CounselDto.Response update(Long counselId, CounselDto.Request request);

    void delete(Long counselId);
}
