package view.customer.contractManagement;

import java.util.ArrayList;

import domain.cancelApplication.CancelApplication;
import domain.customer.Customer;
import service.customer.CustomerCancelApplicationService;
import service.customer.CustomerCancelApplicationServiceImpl;

public class CancelApplicationListView {
	
	// Service
	private CustomerCancelApplicationService customerCancelApplicationService = CustomerCancelApplicationServiceImpl.getInstance();

	public void show(Customer customer) {
		System.out.println("\n[ ��� ���� ��û ��� ]");
		ArrayList<CancelApplication> cancelApplications = customerCancelApplicationService.getByCustomerId(customer.getId());
		// A. ��� ���� ��û�� 0���� ���
		if(cancelApplications.size() == 0) { System.out.println("��� ���� ��û�� �����ϴ�. �޴�ȭ������ ���ư��ϴ�."); return; }
		for(CancelApplication c : cancelApplications) {
			System.out.println();
			System.out.println(c.toString());
		}
	}
}
