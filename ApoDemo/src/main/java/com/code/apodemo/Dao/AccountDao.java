package com.code.apodemo.Dao;

import com.code.apodemo.Account;

import java.util.List;

public interface AccountDao {
    // add a new method find accounts
    List<Account> findAccounts(boolean tripWire);
    void addAccount(Account theAccount,boolean VipFlag);
    public String getName();

    public void setName(String name);

    public String getServiceCode();

    public void setServiceCode(String serviceCode);
    List<Account> findAccounts();
}
