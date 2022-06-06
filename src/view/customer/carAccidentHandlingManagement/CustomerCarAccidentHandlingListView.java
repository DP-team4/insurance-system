package view.customer.carAccidentHandlingManagement;

import java.util.ArrayList;

import domain.carAccidentHandling.AccidentCar;
import domain.carAccidentHandling.AccidentPerson;
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
			System.out.println("��� ó�� ID: " + c.getId());
			System.out.println("��� ���: " + c.getAccidentLocation());
			System.out.println("��� ����: " + c.getAccidentContent());
			System.out.println("��� �Ͻ�: " + c.getAccidentDate());
			System.out.println("���� �Ͻ�: " + c.getRequestDate());
			System.out.println("��û ����: " + c.getState());
	        System.out.println("\n< ��� ���� ���� >");
			for(AccidentCar car : c.getAccidentCars()) {
				System.out.println("���� ��ȣ : " + car.getCarNo());
	        	System.out.println("���� �̸� : " + car.getOwnerName());
	        	System.out.println("���� ��ȣ : " + car.getOwnerPhoneNo());
	        	System.out.println("���� ������ : " + car.getVisitedShopName());
	        	System.out.println();
			}
	        System.out.println("< ��� ������ ���� >");
			for(AccidentPerson person : c.getAccidentPeople()) {
				System.out.print("�̸�: " + person.getName());
	        	System.out.print("��ȭ��ȣ: " + person.getPhoneNo());
	        	System.out.print("�湮����: " + person.getVisitedHospitalName());
	        	System.out.println();
			}
			System.out.println();
		}
	}

}
