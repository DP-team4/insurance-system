package view.customer.consultationManagement;

import java.util.ArrayList;

import domain.consultApplication.ConsultApplication;
import domain.customer.Customer;
import service.customer.MyConsultApplicationService;
import service.customer.MyConsultApplicationServiceImpl;

public class ConsultationListView {
	
	// Service
	private MyConsultApplicationService consultApplicationService = MyConsultApplicationServiceImpl.getInstance();

	public void show(Customer customer) {
		System.out.println("\n[ ���� ��� ��û ��� ]");
		ArrayList<ConsultApplication> consultApplications = consultApplicationService.getByCustomerId(customer.getId());
		// A. ���� ��� ��û�� 0���� ���
		if(consultApplications.size() == 0) { System.out.println("���� ��� ��û�� �����ϴ�. �޴�ȭ������ ���ư��ϴ�."); return; }
		for(ConsultApplication c : consultApplications) {
			System.out.println(c.toStringForCustomer());
			System.out.println();
		}
	}
}
