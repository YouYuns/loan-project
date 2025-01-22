package com.loan.loan.service;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import com.loan.loan.domain.Counsel;
import com.loan.loan.dto.CounselDto;
import com.loan.loan.exception.BaseException;
import com.loan.loan.exception.ResultType;
import com.loan.loan.repository.CounselRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class CounselServiceTest {

    @InjectMocks
    CounselServiceImpl counselService;

    @Mock
    private CounselRepository counselRepository;

    //Mock처리보다는 각각 다른 오브젝트를 매핑해주는 유틸성이라서 Spy사용
    @Spy
    private ModelMapper modelMapper;


    @Test
    void Should_ReturnResponseOfNewCounselEntity_When_RequestCounel() {
        Counsel entity = Counsel.builder()
                .name("Member yun")
                .cellPhone("010-1111-2222")
                .email("abc@def.g")
                .meno("대출 받고싶습니다")
                .zipCode("12345")
                .address("서울특별시 노원구 상계동")
                .addressDetail("1동 1호")
                .build();

        CounselDto.Request request = CounselDto.Request.builder()
                .name("Member yun")
                .cellPhone("010-1111-2222")
                .email("abc@def.g")
                .memo("대출 받고싶습니다")
                .zipCode("12345")
                .address("서울특별시 노원구 상계동")
                .addressDetail("1동 1호")
                .build();

        when(counselRepository.save(ArgumentMatchers.any(Counsel.class))).thenReturn(entity);

        CounselDto.Response actual = counselService.create(request);

        assertThat(actual.getName()).isSameAs(entity.getName());
    }

    @Test
    void Should_ThrowException_When_RequestNotExistCounselId(){
        Long findId = 2L;
        when(counselRepository.findById(findId)).thenThrow(new BaseException((ResultType.SYSTEM_ERROR)));

        Assertions.assertThrows(BaseException.class, () -> counselService.get(findId));
    }

}
