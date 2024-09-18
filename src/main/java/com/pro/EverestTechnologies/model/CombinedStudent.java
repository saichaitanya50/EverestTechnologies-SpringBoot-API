package com.pro.EverestTechnologies.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CombinedStudent {

    private Student mysqlStudent;
    private StudentMongo mongoStudent;

    @Override
    public String toString() {
        return "CombinedStudent{" +
                "mysqlStudent=" + mysqlStudent +
                ", mongoStudent=" + mongoStudent +
                '}';
    }
}