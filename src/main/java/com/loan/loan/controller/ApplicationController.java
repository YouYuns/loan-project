package com.loan.loan.controller;


import com.loan.loan.dto.ApplicationDto;
import com.loan.loan.dto.ResponseDTO;
import com.loan.loan.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/applications")
public class ApplicationController extends AbstractController{

    private final ApplicationService applicationService;


    //대출신청
    @PostMapping
    public ResponseDTO<ApplicationDto.Response> create(@RequestBody ApplicationDto.Request request){
        return ok(applicationService.create(request));
    }

    //대출조회
    @GetMapping("/{applicationId}")
    public ResponseDTO<ApplicationDto.Response> get(@PathVariable Long applicationId){
        return ok(applicationService.get(applicationId));
    }

    //대출수정
    @PutMapping("/{applicationId}")
    public ResponseDTO<ApplicationDto.Response> update(@PathVariable Long applicationId, @RequestBody ApplicationDto.Request request){
        return ok(applicationService.update(applicationId, request));

    }

    @DeleteMapping("/{applicationId}")
    public ResponseDTO<Void> delete(@PathVariable Long applicationId){
        applicationService.delete(applicationId);
        return ok();
    }

    @PostMapping("/{applicationId}/terms")
    public ResponseDTO<Boolean> acceptTerms(@PathVariable Long applicationId,
                                            @RequestBody ApplicationDto.AcceptTerms request){
        return ok(applicationService.acceptTemrs(applicationId, request));
    }
}
