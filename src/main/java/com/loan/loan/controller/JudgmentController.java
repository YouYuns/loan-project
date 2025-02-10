package com.loan.loan.controller;

import com.loan.loan.dto.ApplicationDto;
import com.loan.loan.dto.JudgmentDto;
import com.loan.loan.dto.ResponseDto;
import com.loan.loan.service.JudgmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor

@RestController
@RequestMapping("/judgments")
public class JudgmentController extends AbstractController{

    private final JudgmentService judgmentService;

    @PostMapping
    public ResponseDto<JudgmentDto.Response> create(@RequestBody JudgmentDto.Request request){
        return ok(judgmentService.create(request));
    }


    @GetMapping("/{judgementId}")
    public ResponseDto<JudgmentDto.Response> get(@PathVariable Long judgmentId){
        return ok(judgmentService.get(judgmentId));
    }

    @GetMapping("/applications/{applicationId}")
    public ResponseDto<JudgmentDto.Response> getJudgmentOfApplication(Long applicationId){
        return ok(judgmentService.getJudgmentOfApplication(applicationId));
    }

    @PutMapping
    public ResponseDto<JudgmentDto.Response> update(@PathVariable Long judgmentId, @RequestBody JudgmentDto.Request request){
        return ok(judgmentService.update(judgmentId, request));
    }

    @DeleteMapping("/{judgmentId}")
    public ResponseDto<Void> delete(@PathVariable Long judgmentId){
        judgmentService.delete(judgmentId);
        return ok();
    }

    @PatchMapping("/{judgmentId}/grants")
    public ResponseDto<ApplicationDto.GrantAmount> grant(@PathVariable Long judgmentId){
        return ok(judgmentService.grant(judgmentId));
    }

}
