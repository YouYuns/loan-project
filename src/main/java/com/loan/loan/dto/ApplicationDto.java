package com.loan.loan.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ApplicationDto implements Serializable {

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Request {
        private String name;

        private String cellPhone;

        private String email;

        private BigDecimal hopeAmount;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Response {

        private Long applicationId;

        private String name;

        private String cellPhone;

        private String email;

        private BigDecimal hopeAmount;

        private LocalDateTime appliedAt;

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;


    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    public static class AcceptTerms {
        List<Long> acceptTermsIds;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class GrantAmount {
        private Long applicationId;
        private BigDecimal approvalAmount;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
