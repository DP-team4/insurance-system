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
			System.out.println("청구 내용: " + b.getId());
			System.out.println("청구 내용: " + b.getAccidentContent());
			System.out.println("사고 일시: " + b.getAccidentDate());
			System.out.println("신청 일시: " + b.getRequestDate());
			System.out.println("재산 피해: " + b.getTotalPropertyLoss());
			System.out.println("인적 피해: " + b.getTotalPersonLoss());
			System.out.println("신청 상태: " + b.getState());
			System.out.println();
		}
	}

}
