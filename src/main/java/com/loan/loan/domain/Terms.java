package com.loan.loan.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Terms extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    @EqualsAndHashCode.Include
    private Long termsId;

    @Column(columnDefinition = "varchar(255) NOT NULL COMMENT '약관'")
    private String name;

    @Column(columnDefinition = "varchar(255) NOT NULL COMMENT '약관상세 URL'")
    private String termsDetailUrl;
}
