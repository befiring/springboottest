package com.befiring.entity;

import java.util.HashMap;

public class ReturnValue {

    private final boolean success;
    private final int code;
    private final String message;
    private final HashMap<Object,Object> data;

    private ReturnValue(Builder builder){
        this.success=builder.success;
        this.code=builder.code;
        this.message=builder.message;
        this.data=builder.data;
    }

    public static class Builder{
        private boolean success;
        private int code;
        private String message;
        private HashMap<Object,Object> data;

        public Builder(boolean success){
            this.success=success;
        }
        public Builder message(String message){
            this.message=message;
            return this;
        }
        public Builder code(int code){
            this.code=code;
            return this;
        }
        public Builder data(HashMap<Object,Object> data){
            this.data=data;
            return this;
        }

        public ReturnValue build(){
            return new ReturnValue(this);
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HashMap<Object, Object> getData() {
        return data;
    }
}
