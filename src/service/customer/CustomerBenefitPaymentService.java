package service.customer;

import java.util.ArrayList;

import domain.benefitPayment.BenefitPayment;

public interface CustomerBenefitPaymentService {

	boolean applyBenefitPayment(BenefitPayment benefitPayment);
	boolean revokeMyBenefitPayment(String id, String customerId);
	ArrayList<BenefitPayment> getByCustomerId(String customerId);

}
