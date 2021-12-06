package com.masters.ucin.cc.model;

import lombok.*;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class IncomeSpendObj {
    @Column("SPEND")
    private int spend;
    @Column("INCOME_RANGE")
    private String incomeRange;
}
