package com.ss.oa.transaction.payment;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.transaction.vo.Payment;

@Scope("prototype")
public interface PaymentDao {
	public abstract List<Payment> getAllPayments(Map<String,String> criteria);
	public abstract Payment getPaymentById(String id);
	public abstract String addPayment(Payment payment);
	public abstract String updatePayment(String id,Payment payment);
}
