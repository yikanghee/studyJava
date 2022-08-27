package com.example.demo.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary // client에 payment를 호출하면 ProxyPaymentService가 먼저 호출된다.
@Service
public class ProxyPaymentService implements Payment {

    @Autowired
    PaymentService paymentService;

    @Override
    public void cashPay(int amount) {
        long begin = System.currentTimeMillis();  // 기능 1
        paymentService.cashPay(1000); // 원래 객체에 위임
        System.out.println(System.currentTimeMillis() - begin); // 기능 2
    }

    @Override
    public void creditPay(int amount) {
        long begin = System.currentTimeMillis();  // 기능 1
        paymentService.creditPay(1000); // 원래 객체에 위임
        System.out.println(System.currentTimeMillis() - begin); // 기능 2
    }
}
