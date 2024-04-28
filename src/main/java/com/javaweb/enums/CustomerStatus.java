package com.javaweb.enums;

import java.util.Map;
import java.util.TreeMap;

public enum CustomerStatus {
    DANG_XU_LI("Đang xử lí"),
    DA_XU_LI("Đã xử lí"),
    CHUA_XU_LI("Chưa xử lí");
    private final String name;

    CustomerStatus(String name){
        this.name=name;
    }
    public static Map<String,String> type(){
        Map<String,String> customerStatus=new TreeMap<>();
        for(CustomerStatus it: CustomerStatus.values()){
            customerStatus.put(it.toString(),it.name);
        }
        return customerStatus;
    }

}
