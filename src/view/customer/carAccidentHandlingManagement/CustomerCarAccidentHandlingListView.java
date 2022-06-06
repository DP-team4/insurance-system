package view.customer.carAccidentHandlingManagement;

import java.util.ArrayList;

import domain.carAccidentHandling.CarAccidentHandling;
import domain.customer.Customer;
import service.customer.CustomerCarAccidentHandlingService;
import service.customer.CustomerCarAccidentHandlingServiceImpl;

public class CustomerCarAccidentHandlingListView {

	// Service
	private final CustomerCarAccidentHandlingService customerCarAccidentHandlingService = CustomerCarAccidentHandlingServiceImpl.getInstance();
	
	public void show(Customer customer) {
		System.out.println("\n[ ���ó�� ��û ��� ]");
		ArrayList<CarAccidentHandling> carAccidentHandlings = customerCarAccidentHandlingService.getByCustomerId(customer.getId());
		// A. ���ó�� ��û�� 0���� ���
		if(carAccidentHandlings.size() == 0) { System.out.println("���ó�� ��û�� �����ϴ�. ����ȭ������ ���ư��ϴ�."); return; }
		for(CarAccidentHandling c : carAccidentHandlings) {
			System.out.println(c.toString());
			System.out.println();
		}
	}

}
