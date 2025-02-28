package com.invest.recommendation.dto;

public class IdPhoneNumber {
    public Long id;
    public String phone_number;

    public IdPhoneNumber(Long id, String phone_number){
        this.id = id;
        this.phone_number = phone_number;
    }

    public Long getId(){
        return id;
    }

    public String getPhone_number(){
        return phone_number;
    }
}
