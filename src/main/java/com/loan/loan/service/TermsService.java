package com.loan.loan.service;


import com.loan.loan.dto.TermsDto;

import java.util.List;

public interface TermsService {

    TermsDto.Response create(TermsDto.Request request);

    List<TermsDto.Response> getAll();
}
