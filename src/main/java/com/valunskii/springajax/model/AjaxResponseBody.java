package com.valunskii.springajax.model;

import java.util.List;

public class AjaxResponseBody {
    String message;
    List<User> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<User> getResult() {
        return result;
    }

    public void setResult(List<User> result) {
        this.result = result;
    }
}
