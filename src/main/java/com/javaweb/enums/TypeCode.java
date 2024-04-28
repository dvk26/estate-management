package com.javaweb.enums;

import java.lang.reflect.Type;
import java.util.*;
public enum TypeCode {
    TANG_TRET("Tầng Trệt"),
    NGUYEN_CAN("Nguyên Căn"),
    NOI_THAT("Nội Thất");

    private final String name;
    TypeCode(String name){
        this.name=name;
    }
    public static Map<String,String> type(){
        Map<String,String> typeCodes=new HashMap<>();
        for(TypeCode it: TypeCode.values()){
            typeCodes.put(it.toString(),it.name);
        }
        return typeCodes;
    }

}
