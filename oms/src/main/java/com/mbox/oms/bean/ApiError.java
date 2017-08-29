package com.mbox.oms.bean;

import java.util.ArrayList;
import java.util.List;

public class ApiError {
    protected String cust_ErrCode;
    protected List<String> errMessageList = new ArrayList<String>();

    public ApiError() {
    }

    public String getCust_ErrCode() {
        return cust_ErrCode;
    }

    public void setCust_ErrCode(String cust_ErrCode) {
        this.cust_ErrCode = cust_ErrCode;
    }
    public void addErrorDescription(String errorDescription){
        this.errMessageList.add(errorDescription);
    }

    public List<String> getErrMessageList() {
        return errMessageList;
    }


}
