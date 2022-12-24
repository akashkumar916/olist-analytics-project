package com.dbms.dto.response;

/*
 * @author ayushkumar
 * @created on 04/12/22
 */


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericDataResponse<T> {

    public GenericDataResponse() {
    }

    public GenericDataResponse(String status, T data) {
        this.status = status;
        this.data = data;
    }

    @Override
    public String toString() {
        return "GenericDataResponse{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    @JsonInclude(JsonInclude.Include.ALWAYS)
    private String status;

    private T data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

