package domain.cancelApplication.test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import domain.Customer.Customer;
import domain.Insurance.FireInsurance;
import domain.Insurance.Insurance;
import domain.cancelApplication.CancelApplication;
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
        	customers.add(new Customer("Customer" + i, 30 + i));
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
        cancelApplication.accept();
        System.out.println("���� ������ �Ϸ��Ͽ����ϴ�.");
	}

	private static void testReject(CancelApplication cancelApplication) {
        System.out.println("///// Test for CancelApplication, Reject /////");
        cancelApplication.reject();
        System.out.println("���� ��û ������ �Ϸ��Ͽ����ϴ�.");
	}
}