package com.loan.loan.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Application extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    @Column(columnDefinition = "varchar(12) DEFAULT NULL  COMMENT '신청자'")
    private String name;

    @Column(columnDefinition = "varchar(13) DEFAULT NULL COMMENT '전화번호'")
    private String cellPhone;

    @Column(columnDefinition = "varchar(50) DEFAULT NULL COMMENT '신청자 이메일'")
    private String email;

    @Column(columnDefinition = "decimal(5,4) DEFAULT NULL COMMENT '금리'")
    private BigDecimal interestRate; //금리

    @Column(columnDefinition = "decimal(5,4) DEFAULT NULL COMMENT '취급수수료'")
    private BigDecimal fee; //수수료

    @Column(columnDefinition = "datetime DEFAULT NULL COMMENT '만기'")
    private LocalDateTime maturity; //만기날짜

    @Column(columnDefinition = "decimal(15,2) DEFAULT NULL COMMENT '대출 신청 금액'")
    private BigDecimal hopeAmount; //희망금액

    @Column(columnDefinition = "datetime DEFAULT NULL COMMENT '신청일자'")
    private LocalDateTime appliedAt; //신청일자

    @Column(columnDefinition = "decimal(15, 2) DEFAULT NULL COMMENT '승인 금액'")
    private BigDecimal approvalAmount;
}
