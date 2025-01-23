package com.loan.loan.controller;

import com.loan.loan.dto.ResponseDTO;
import com.loan.loan.dto.TermsDto;
import com.loan.loan.service.TermsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/terms")
public class TermsController extends AbstractController {

    private final TermsService termsService;

    @PostMapping
    public ResponseDTO<TermsDto.Response> create(@RequestBody TermsDto.Request request) {
        return ok(termsService.create(request));
    }

    @GetMapping()
    public ResponseDTO<List<TermsDto.Response>> getAll() {
        return ok(termsService.getAll());
    }
}