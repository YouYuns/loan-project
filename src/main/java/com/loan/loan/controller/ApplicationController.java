package com.loan.loan.controller;


import com.loan.loan.dto.ApplicationDto;
import com.loan.loan.dto.FileDto;
import com.loan.loan.dto.ResponseDto;
import com.loan.loan.service.ApplicationService;
import com.loan.loan.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/applications")
public class ApplicationController extends AbstractController{

    private final ApplicationService applicationService;

    private final FileStorageService fileStorageService;

    //대출신청
    @PostMapping
    public ResponseDto<ApplicationDto.Response> create(@RequestBody ApplicationDto.Request request){
        return ok(applicationService.create(request));
    }

    //대출조회
    @GetMapping("/{applicationId}")
    public ResponseDto<ApplicationDto.Response> get(@PathVariable Long applicationId){
        return ok(applicationService.get(applicationId));
    }

    //대출수정
    @PutMapping("/{applicationId}")
    public ResponseDto<ApplicationDto.Response> update(@PathVariable Long applicationId, @RequestBody ApplicationDto.Request request){
        return ok(applicationService.update(applicationId, request));

    }

    @DeleteMapping("/{applicationId}")
    public ResponseDto<Void> delete(@PathVariable Long applicationId){
        applicationService.delete(applicationId);
        return ok();
    }

    @PostMapping("/{applicationId}/terms")
    public ResponseDto<Boolean> acceptTerms(@PathVariable Long applicationId,
                                            @RequestBody ApplicationDto.AcceptTerms request){
        return ok(applicationService.acceptTemrs(applicationId, request));
    }


    @PostMapping("/files")
    public ResponseDto<Void> upload(MultipartFile file){
        fileStorageService.save(file);
        return ok();
    }

    @GetMapping("/files")
    public ResponseEntity<Resource> download(@RequestParam(value = "fileName") String fileName){
        Resource file = fileStorageService.load(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = \"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/files/info")
    public ResponseDto<List<FileDto>> getFileInfos() {

        fileStorageService.loadAll().map(path -> {
            String fileName = path.getFileName().toString();
            return FileDto.builder()
                    .name(fileName)
                    // MvcUriComponentsBuilder 사용해서 실제로 다운로드할 파일을제공하는 url을 준비
                    .url(MvcUriComponentsBuilder.fromMethodName(ApplicationController.class, "download", fileName).build().toString())
                    .build();
        }).toList();
        return ok();
    }

}
