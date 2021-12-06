package com.masters.ucin.cc.entity;


import lombok.*;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DataObject {
    @Column("HSHD_NUM")
    private String hshdNum;
    @Column("L")
    private String l;
    @Column("AGE_RANGE")
    private String ageRange;
    @Column("MARITAL")
    private String marital;
    @Column("INCOME_RANGE")
    private String incomeRange;
    @Column("HOMEOWNER")
    private String homeOwner;
    @Column("HSHD_COMPOSITION")
    private String hshdComposition;
    @Column("HH_SIZE")
    private String hhSize;
    @Column("CHILDREN")
    private String children;
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
    @Column("BASKET_NUM")
    private String basketNum;
    @Column("PURCHASE_DATE")
    private String purchaseDate;
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
