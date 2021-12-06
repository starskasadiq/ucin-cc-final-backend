package com.masters.ucin.cc.model;

import lombok.*;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommoditySpendObj {
    @Column("YEAR")
    private int year;
    @Column("COMMODITY")
    private String commodity;
    @Column("SPEND")
    private int spend;
}
