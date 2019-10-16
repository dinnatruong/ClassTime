package com.example.classtime.data.model;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("result")
    private String result;

    public Result(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
