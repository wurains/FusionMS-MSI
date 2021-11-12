package com.common.model;

import lombok.Data;

@Data
public class ResultModel {
    private boolean status;
    private int code;
    private String message;
    private Object data;

    public void setSuccessResult(String aMessage, Object aData) {
        this.message = aMessage;
        this.data = aData;
    }

    public void setSuccessResult(Object aData) {
        this.message = "Successful";
        this.data = aData;
    }

    public ResultModel(Object aData) {
        setSuccessResult(aData);
    }

    public ResultModel() {
    }
}
