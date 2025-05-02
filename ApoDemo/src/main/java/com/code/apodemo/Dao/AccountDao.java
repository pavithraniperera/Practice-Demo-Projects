package com.code.apodemo.Dao;

import com.code.apodemo.Account;

public interface AccountDao {
    void addAccount(Account theAccount,boolean VipFlag);
    public String getName();

    public void setName(String name);

    public String getServiceCode();

    public void setServiceCode(String serviceCode);
}
