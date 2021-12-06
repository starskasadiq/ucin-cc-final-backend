package com.masters.ucin.cc.entity;

import lombok.*;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HouseHolds {

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
}
