package com.loan.loan.service;

import com.loan.loan.exception.BaseException;
import com.loan.loan.exception.ResultType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService{

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    @Override
    public void save(MultipartFile file) {
        try {
            //받은 파일을 지정된 경로로 복사를 한다 이미 있는 파일명일 경우 덮어쓰게 만든다.
            Files.copy(file.getInputStream(), Paths.get(uploadPath).resolve(file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }
    }


    //대출신청을 조회
    @Override
    public Resource load(String fileName) {
        try {
            Path file = Paths.get(uploadPath).resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if(resource.isReadable() || resource.exists()){
                return resource;
            }else{
                throw new BaseException(ResultType.NOT_EXIST);
            }
        } catch (MalformedURLException e) {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
        //walk에 Path를 넣어주면 이 경로에 해당하는 모든 경로를 탐색해서 인자로 전달받은 Depth에 해당하는 파일들은 탐색해서 보여준다.
        //file만 반환하기위해서 해당 uploadPath에있는것만 반환한다.
          return  Files.walk(Paths.get(uploadPath), 1).filter(path -> !path.equals(Paths.get(uploadPath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
