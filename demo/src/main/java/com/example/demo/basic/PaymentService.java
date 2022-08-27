package com.example.demo.basic;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PaymentService implements Payment{

    @Override
    public void cashPay(int amount) {
        System.out.println("cashPay : " + amount);
    }

    @Override
    public void creditPay(int amount) {
        System.out.println("creditPay : " + amount);
    }



}
