package com.loan.loan.service;

import com.loan.loan.exception.BaseException;
import com.loan.loan.exception.ResultType;
import com.loan.loan.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
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

    private final ApplicationRepository applicationRepository;

    @Override
    public void save(Long applicationId, MultipartFile file) {
        if(!isPresentApplication(applicationId)){
            throw new BaseException(ResultType.NOT_EXIST);
        }
        try {
            // ../dir/1
            // ../dir/2
           String applicationPath = uploadPath.concat("/" + applicationId);
           Path directoryPath = Path.of(applicationPath);

            //기존 directory 가 있는지 확인하고 없으면 해당 applicationId에 맞게 디렉토리 생성
           if(Files.exists(directoryPath)){
               Files.createDirectory(directoryPath);
           }
            //받은 파일을 지정된 경로로 복사를 한다 이미 있는 파일명일 경우 덮어쓰게 만든다.
            Files.copy(file.getInputStream(), Paths.get(uploadPath).resolve(file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }
    }


    //대출신청을 조회
    @Override
    public Resource load(Long applicationId, String fileName) {
        if(!isPresentApplication(applicationId)){
            throw new BaseException(ResultType.NOT_EXIST);
        }
        try {
            String applicationPath = uploadPath.concat("/" + applicationId);

            Path file = Paths.get(applicationPath).resolve(fileName);
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
    public Stream<Path> loadAll(Long applicationId) {
        if(!isPresentApplication(applicationId)){
            throw new BaseException(ResultType.NOT_EXIST);
        }
        try {
            String applicationPath = uploadPath.concat("/" + applicationId);
        //walk에 Path를 넣어주면 이 경로에 해당하는 모든 경로를 탐색해서 인자로 전달받은 Depth에 해당하는 파일들은 탐색해서 보여준다.
        //file만 반환하기위해서 해당 uploadPath에있는것만 반환한다.
          return  Files.walk(Paths.get(applicationPath), 1).filter(path -> !path.equals(Paths.get(uploadPath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteAll(Long applicationId) {
        if(!isPresentApplication(applicationId)){
            throw new BaseException(ResultType.NOT_EXIST);
        }

        String applicationPath = uploadPath.concat("/" + applicationId);
        //Spring framework에서 제공하는 FileSystemUtils에서 path에 해당하는걸 제공해준다.
        FileSystemUtils.deleteRecursively(Paths.get(applicationPath).toFile());
    }


    private boolean isPresentApplication(Long applicationId){
        return applicationRepository.findById(applicationId).isPresent();
    }
}
