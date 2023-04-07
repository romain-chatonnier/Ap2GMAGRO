package com.example.ap2gmagro.beans;

public class Machine {
    public String code;


    public Machine(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return  code ;
    }
}
