package domain.cancelApplication.test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import domain.customer.Customer;
import domain.insurance.FireInsurance;
import domain.insurance.Insurance;
import domain.cancelApplication.CancelApplication;
import domain.cancelApplication.CancelApplicationState;
import domain.contract.Contract;
import repository.cancelApplication.CancelApplicationListImpl;

public class CancelApplicationTest {
	private static final ArrayList<Customer> customers = new ArrayList<>();
    private static final ArrayList<Insurance> insurances = new ArrayList<>();
    private static final ArrayList<Contract> contracts = new ArrayList<>();
	private static final CancelApplicationListImpl cancelRepository = CancelApplicationListImpl.getInstance();
	
	public static void test(Scanner scanner) {
		System.out.println("///// Test for CancelApplication /////");

        // �׽�Ʈ ������ ����
		int numTestData = 2; // �׽�Ʈ ������ ����
        // Customer
        for(int i = 0; i < numTestData; i++) {
        	Customer customer = new Customer();
        	customer.setCustomerName("Customer" + i+1);
            customer.setAge(24 + (i * 3 + 1));
            customers.add(customer);
        }
        // Insurance
        for (int i = 0; i < numTestData; i++) {
            FireInsurance insurance = new FireInsurance();
            insurance.setName("Insurance" + i);
            insurances.add(insurance);
        }
        // Contract
        for(int i = 0; i < numTestData; i++) {
        	Contract contract = new Contract(customers.get(i), insurances.get(i), LocalDateTime.now(), LocalDateTime.now().plusDays(i * 3));
        	contracts.add(contract);
        }
        // CancelApplication
        for(int i = 0; i < numTestData; i++) {
        	CancelApplication cancelApplication = new CancelApplication();
        	cancelApplication.setContract(contracts.get(i));
            cancelRepository.add(cancelApplication);
        }
        System.out.println("CancelApplication ���:");
        cancelRepository.printAll();
        System.out.print("�۾��� CancelApplication ID�� �Է��ϼ��� >> ");
        String cancelApplicationId = scanner.nextLine().trim();

        CancelApplication cancelApplication = cancelRepository.get(cancelApplicationId);
        if(cancelApplication == null){
            System.out.println("�߸��� CancelApplication ID�Դϴ�.");
            return;
        }

        while(true) {
            System.out.println("////// �ش� CancelApplication�� ���� �۾��� �����մϴ�. //////");
            System.out.println("����(1), ����(2), �ڷΰ���(0)");
            String input = scanner.nextLine().trim();
            if(input.equals("0")) break;
            switch (input) {
                case "1":
                    testAccept(cancelApplication);
                    break;
                case "2":
                    testReject(cancelApplication);
                    break;
                default:
                    System.out.println("�߸��� �Է��Դϴ�.");
                    break;
            }
            System.out.println(cancelApplication);

            System.out.println("�׽�Ʈ�� �ݺ��մϴ�. ����Ͻðڽ��ϰ�? ���(1) �ڷΰ���(�� ��)");
            input = scanner.nextLine().trim();
            if(!input.equals("1")) break;
        }
	}

	private static void testAccept(CancelApplication cancelApplication) {
        System.out.println("///// Test for CancelApplication, Accept /////");
        cancelApplication.setState(CancelApplicationState.ACCEPTED);
        System.out.println("���� ������ �Ϸ��Ͽ����ϴ�.");
	}

	private static void testReject(CancelApplication cancelApplication) {
        System.out.println("///// Test for CancelApplication, Reject /////");
        cancelApplication.setState(CancelApplicationState.REJECTED);
        System.out.println("���� ��û ������ �Ϸ��Ͽ����ϴ�.");
	}
}
