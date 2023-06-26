package com.ss.oa.transaction.payment;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.transaction.vo.Payment;

@Scope("prototype")
@Component
public class PaymentService {
	
	@Autowired
	PaymentDao dao;
	public List<Payment> getAllPayments(Map<String, String> criteria) {
		// TODO Auto-generated method stub
		return dao.getAllPayments(criteria);
	}

	
	public Payment getPaymentById(String id) {
		// TODO Auto-generated method stub
		return dao.getPaymentById(id);
	}

	
	public String addPayment(Payment payment) {
		System.out.println(payment);
		// TODO Auto-generated method stub
		return dao.addPayment(payment);
	}

	
	public String updatePayment(String id, Payment payment) {
		// TODO Auto-generated method stub
		return dao.updatePayment(id, payment);
	}


}
