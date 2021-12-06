package com.masters.ucin.cc.model;

import lombok.*;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class YearSpendObj {
    @Column("YEAR")
    private int year;
    @Column("SPEND")
    private int spend;
}
