package com.loan.loan.dto;

import lombok.*;
import org.springframework.cglib.core.Local;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class JudgmentDto implements Serializable {


    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Request {
        private Long applicationId;

        private String name;

        private BigDecimal approvalAmount;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Response {

        private Long judgmentId;

        private Long applicationId;

        private String name;

        private BigDecimal approvalAmount;

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;

    }

}
