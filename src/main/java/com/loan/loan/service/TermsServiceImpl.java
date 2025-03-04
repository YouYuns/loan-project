package com.loan.loan.service;

import com.loan.loan.domain.Terms;
import com.loan.loan.dto.TermsDto;
import com.loan.loan.repository.TermsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TermsServiceImpl implements TermsService {

    private final TermsRepository termsRepository;

    private final ModelMapper modelMapper;

    @Override
    public TermsDto.Response create(TermsDto.Request request) {
        Terms terms = modelMapper.map(request, Terms.class);
        Terms created = termsRepository.save(terms);

        return modelMapper.map(created, TermsDto.Response.class);
    }

    @Override
    public List<TermsDto.Response> getAll() {
        List<Terms> termsList = termsRepository.findAll();

        return termsList.stream().map(t -> modelMapper.map(t, TermsDto.Response.class)).collect(Collectors.toList());
    }
}