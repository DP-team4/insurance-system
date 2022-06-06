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
			System.out.println("û�� ����: " + b.getId());
			System.out.println("û�� ����: " + b.getAccidentContent());
			System.out.println("��� �Ͻ�: " + b.getAccidentDate());
			System.out.println("��û �Ͻ�: " + b.getRequestDate());
			System.out.println("��� ����: " + b.getTotalPropertyLoss());
			System.out.println("���� ����: " + b.getTotalPersonLoss());
			System.out.println("��û ����: " + b.getState());
			System.out.println();
		}
	}

}
