package com.masters.ucin.cc.entity;

import lombok.*;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Products {

    @Column("PRODUCT_NUM")
    private String productNum;
    @Column("DEPARTMENT")
    private String department;
    @Column("COMMODITY")
    private String commodity;
    @Column("BRAND_TY")
    private String brandTy;
    @Column("NATURAL_ORGANIC_FLAG")
    private String naturalOrganicFlag;
}
