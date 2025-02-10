package com.loan.loan.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
// entity조회를 할때 기본적으로 false인거는 제외되서 조회되게가능
@Where(clause = "is_deleted=false")
public class Judgment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long judgmentId;

    @Column(columnDefinition = "bigint NOT NULL COMMENT '신청 ID'")
    private Long applicationId;


    @Column(columnDefinition = "varchar(12) NOT NULL COMMENT '심사자'")
    private String name;


    @Column(columnDefinition = "decimal(15, 2) NOT NULL COMMENT '승인금액'")
    private BigDecimal approvalAmount;
}
