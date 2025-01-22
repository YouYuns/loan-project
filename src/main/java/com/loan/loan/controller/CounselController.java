package com.loan.loan.controller;


import com.loan.loan.dto.CounselDto;
import com.loan.loan.dto.ResponseDTO;
import com.loan.loan.service.CounselService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/counsels")
public class CounselController extends AbstractController{
    private final CounselService counselService;

    @PostMapping
    public ResponseDTO<CounselDto.Response> create(@RequestBody CounselDto.Request request){
        return ok(counselService.create(request));
    }

    @GetMapping("/{counselId}")
    public ResponseDTO<CounselDto.Response> get(@PathVariable Long counselId){
        return ok(counselService.get(counselId));
    }

    @PutMapping("/{counselId}")
    public ResponseDTO<CounselDto.Response> update(@PathVariable Long counselId, @RequestBody CounselDto.Request request){
        return ok(counselService.update(counselId, request));
    }

    @DeleteMapping("/{counselId}")
    public ResponseDTO<CounselDto.Response> delete(@PathVariable Long counselId){
        counselService.delete(counselId);
        return ok();
    }

}
