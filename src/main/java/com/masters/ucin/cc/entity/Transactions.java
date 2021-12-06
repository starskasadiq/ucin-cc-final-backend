package com.masters.ucin.cc.entity;

import lombok.*;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Transactions {

    @Column("BASKET_NUM")
    private String basketNum;
    @Column("HSHD_NUM")
    private String hshdNum;
    @Column("PURCHASE")
    private String purchase;
    @Column("PRODUCT_NUM")
    private String productNum;
    @Column("SPEND")
    private String spend;
    @Column("UNITS")
    private String units;
    @Column("STORE_R")
    private String storeR;
    @Column("WEEK_NUM")
    private String weekNum;
    @Column("YEAR")
    private String year;
}
