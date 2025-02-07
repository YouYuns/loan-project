package com.loan.loan.dto;


import jdk.jfr.Name;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class FileDto implements Serializable {
    private String name;
    private String url;
}
