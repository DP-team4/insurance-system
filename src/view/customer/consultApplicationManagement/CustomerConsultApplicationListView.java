package view.customer.consultApplicationManagement;

import java.util.ArrayList;

import domain.consultApplication.ConsultApplication;
import domain.customer.Customer;
import service.customer.CustomerConsultApplicationService;
import service.customer.CustomerConsultApplicationServiceImpl;

public class CustomerConsultApplicationListView {
	
	// Service
	private CustomerConsultApplicationService customerConsultApplicationService = CustomerConsultApplicationServiceImpl.getInstance();

	public void show(Customer customer) {
		System.out.println("\n[ ���� ��� ��û ��� ]");
		ArrayList<ConsultApplication> consultApplications = customerConsultApplicationService.getByCustomerId(customer.getId());
		// A. ���� ��� ��û�� 0���� ���
		if(consultApplications.size() == 0) { System.out.println("���� ��� ��û�� �����ϴ�. ����ȭ������ ���ư��ϴ�."); return; }
		for(ConsultApplication c : consultApplications) {
			System.out.println(c.toStringForCustomer());
			System.out.println();
		}
	}
}
