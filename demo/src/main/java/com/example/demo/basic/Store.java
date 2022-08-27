package com.example.demo.basic;

import org.springframework.beans.factory.annotation.Autowired;

public class Store {
    @Autowired
    Payment payment;

    public Store(Payment cashProc) {
    }

    public void buySomething(int amount) {
        if(amount > 1000)
            payment.creditPay(amount);
        else
            payment.cashPay(amount);    
    }
}
