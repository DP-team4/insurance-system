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
		System.out.println("\n[ ����� û�� ��� ]");
		ArrayList<BenefitPayment> benefitPayments = customerBenefitPaymentService.getByCustomerId(customer.getId());
		// A. ����� û���� 0���� ���
		if(benefitPayments.size() == 0) { System.out.println("����� û���� �����ϴ�. ����ȭ������ ���ư��ϴ�."); return; }
		for(BenefitPayment b : benefitPayments) {
			System.out.println(b.toString());
			System.out.println();
		}
	}

}
