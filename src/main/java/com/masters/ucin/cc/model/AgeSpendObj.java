package com.masters.ucin.cc.model;

import lombok.*;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AgeSpendObj {
    @Column("SPEND")
    private int spend;
    @Column("AGE_RANGE")
    private String ageRange;
}
