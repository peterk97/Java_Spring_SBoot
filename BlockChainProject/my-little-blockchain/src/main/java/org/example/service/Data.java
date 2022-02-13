package org.example.service;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;

@lombok.Data
public class Data {

    //== Fields ==
    private String from;
    private String to;
    private int amount;

    //== Constructor ==
    public Data (){
        this.from = "0";
        this.to = "0";
        this.amount = 0;
    }
    public Data(String from, String to, int amount){
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    //== Methods ==


    @Override
    public String toString() {
        return "Data{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}


