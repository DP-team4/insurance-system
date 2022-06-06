package view.customer.benefitPaymentManagement;

import java.util.ArrayList;

import domain.benefitPayment.BenefitPayment;
import domain.customer.Customer;
import service.customer.CustomerBenefitPaymentService;
import service.customer.CustomerBenefitPaymentServiceImpl;

public class CustomerBenefitPaymentListView {
	
	// Service
	private final CustomerBenefitPaymentService customerBenefitPaymentService = CustomerBenefitPaymentServiceImpl.getInstance();

	public void show(Customer customer) {
		System.out.println("\n[ 보험금 청구 목록 ]");
		ArrayList<BenefitPayment> benefitPayments = customerBenefitPaymentService.getByCustomerId(customer.getId());
		// A. 보험금 청구가 0개인 경우
		if(benefitPayments.size() == 0) { System.out.println("보험금 청구가 없습니다. 이전화면으로 돌아갑니다."); return; }
		for(BenefitPayment b : benefitPayments) {
			System.out.println(b.toString());
			System.out.println();
		}
	}

}
