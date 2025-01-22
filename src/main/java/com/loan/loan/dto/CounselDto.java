package com.loan.loan.dto;

import lombok.*;

import java.time.LocalDateTime;

public class CounselDto {

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    public static class Request {

        private Long counselId;
        //요청자
        private String name;

        private String cellPhone;

        private String email;

        private String memo;

        private String address;

        private String addressDetail;

        private String zipCode;

        private LocalDateTime appliedAt;

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Response {

        private Long counselId;

        private String name;

        private String cellPhone;

        private String email;

        private String memo;

        private String address;

        private String addressDetail;

        private String zipCode;

        private LocalDateTime appliedAt;

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;
    }
}
