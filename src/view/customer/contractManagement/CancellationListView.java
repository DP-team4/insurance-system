package view.customer.contractManagement;

import java.util.ArrayList;

import domain.cancelApplication.CancelApplication;
import domain.customer.Customer;
import service.customer.MyCancelApplicationService;
import service.customer.MyCancelApplicationServiceImpl;

public class CancellationListView {
	
	// Service
	private MyCancelApplicationService cancelApplicationService = MyCancelApplicationServiceImpl.getInstance();

	public void show(Customer customer) {
		System.out.println("\n[ ���� ��� ��û ��� ]");
		ArrayList<CancelApplication> cancelApplications = cancelApplicationService.getByCustomerId(customer.getId());
		// A. ���� ��� ��û�� 0���� ���
		if(cancelApplications.size() == 0) { System.out.println("���� ��� ��û�� �����ϴ�. �޴�ȭ������ ���ư��ϴ�."); return; }
		for(CancelApplication c : cancelApplications) {
			System.out.println(c.toString());
			System.out.println();
		}
	}
}
