package domain.consultApplication.test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import dao.ConsultApplicationDao;
import dao.CustomerDao;
import domain.consultApplication.ConsultApplication;

public class ConsultApplicationTest {
    private static final ConsultApplicationDao consultApplicationDao = new ConsultApplicationDao();
    private static final CustomerDao customerDao = new CustomerDao();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        test(scanner);
        scanner.close();
    }
    
	public static void test(Scanner scanner) {
		System.out.println("///// Test for ConsultApplication /////"); 
	    while(true) {
	        System.out.println("����(1), ����(2), ��� ��û ���̵�� ��ȸ(3), �� ���̵�� ��ȸ(4), ��ü ��ȸ(5), �ڷΰ���(0)");
	        System.out.print(">> "); String input = scanner.nextLine().trim();
	        if(input.equals("0")) break;
	        switch (input) {
	        	case "1":
		        	testCreation(scanner);
	                break;
	            case "2":
	            	testDeletion(scanner);
	                break;
	            case "3":
	            	testRetrieve(scanner);
	                break;
	            case "4":
	            	testRetrieveByCustomerId(scanner);
	                break;
	            case "5":
	            	testRetrieveAll();
	                break;
	            default:
	                System.out.println("�߸��� �Է��Դϴ�.");
	                break;
	        }
	        System.out.println("�׽�Ʈ�� �ݺ��մϴ�. ����Ͻðڽ��ϱ�? ���(1) �ڷΰ���(�� ��)");
	        input = scanner.nextLine().trim();
	        if(!input.equals("1")) break;
	    }
	}

	private static void testCreation(Scanner scanner) {
        while (true) {
        	ConsultApplication consultApplication = new ConsultApplication();
            System.out.println("///// Test for ConsultApplication, Creation /////");
            /////////////////////////// ���� �ʿ� >> ���� �α��ε� ���� ID�� �����;� ��
            customerDao.retrieveAll().forEach(c -> {
                System.out.println(c);
                System.out.println();
            });
            System.out.print("�� ���̵� >> ");
            consultApplication.setCustomerId(scanner.nextLine().trim());
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            System.out.print("��� ���� >> "); consultApplication.setContent(scanner.nextLine().trim());
            System.out.println("��� ��¥ >> ");
            System.out.println("�� : "); int year = Integer.parseInt(scanner.nextLine().trim());
            System.out.println("�� : "); int month = Integer.parseInt(scanner.nextLine().trim());
            System.out.println("�� : "); int dayOfMonth = Integer.parseInt(scanner.nextLine().trim());
            System.out.println("�� : "); int hour = Integer.parseInt(scanner.nextLine().trim());
            System.out.println("�� : "); int minute = Integer.parseInt(scanner.nextLine().trim());
            LocalDateTime consultDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
            consultApplication.setConsultationDate(consultDateTime);
    		if(consultApplicationDao.create(consultApplication))System.out.println("�������丮�� �߰��Ǿ����ϴ�.");
            else System.out.println("�������丮�� �߰����� �ʾҽ��ϴ�.");
            System.out.print("��� ��û ���� �׽�Ʈ�� ��� �Ͻðڽ��ϱ�? ��(1), �ƴϿ�(�� ��) >> ");
            String continueInput = scanner.nextLine().trim();
            if(continueInput.equals("1")) continue;
            else break;
        }
	}

	private static void testDeletion(Scanner scanner) {
        while (true) {
            System.out.println("///// Test for ConsultApplication, Deletion /////");
            System.out.print("���̵� >> ");
            String id = scanner.nextLine().trim();
            if(consultApplicationDao.delete(id)) System.out.println("���� �����ῴ���ϴ�.");
            else System.out.println("���� �����Ͽ����ϴ�.");

            System.out.print("��� ��û ���� �׽�Ʈ�� ��� �Ͻðڽ��ϱ�? ��(1), �ƴϿ�(�� ��) >> ");
            String continueInput = scanner.nextLine().trim();
            if(continueInput.equals("1")) continue;
            else break;
        }
	}

	private static void testRetrieve(Scanner scanner) {
        while (true) {
            System.out.println("///// Test for ConsultApplication, Retrieve /////");
            System.out.print("���̵� >> ");
            String id = scanner.nextLine().trim();
            ConsultApplication consultApplication = consultApplicationDao.retrieveById(id);
            if(consultApplication == null) System.out.println("�ش��ϴ� ID�� ��� ��û�� ã�� ���߽��ϴ�.");
            else System.out.println(consultApplication);

            System.out.print("��� ��û ��ȸ �׽�Ʈ�� ��� �Ͻðڽ��ϱ�? ��(1), �ƴϿ�(�� ��) >> ");
            String continueInput = scanner.nextLine().trim();
            if(continueInput.equals("1")) continue;
            else break;
        }
	}

	private static void testRetrieveByCustomerId(Scanner scanner) {
        while (true) {
            System.out.println("///// Test for ConsultApplication, Retrieve By Customer Id/////");
            System.out.print("�� ���̵� >> ");
            String customerId = scanner.nextLine().trim();
            ArrayList<ConsultApplication> consultApplications = consultApplicationDao.retrieveByCustomerId(customerId);
            if(consultApplications.size() == 0) System.out.println("�ش��ϴ� �̸��� ��� ��û�� ã�� ���߽��ϴ�.");
            else {
            	System.out.println();
            	System.out.println(consultApplications.size() + "���� ��� ��û�� ã�ҽ��ϴ�!!");
            	System.out.println();
            	consultApplications.forEach(c -> {
                    System.out.println(c);
                    System.out.println();
                });
            }
            System.out.print("��� ��û ��ȸ �׽�Ʈ�� ��� �Ͻðڽ��ϱ�? ��(1), �ƴϿ�(�� ��) >> ");
            String continueInput = scanner.nextLine().trim();
            if(continueInput.equals("1")) continue;
            else break;
        }
	}

	private static void testRetrieveAll() {
		System.out.println("///// Test for ConsultApplication, RetrieveAll /////");
        consultApplicationDao.retrieveAll().forEach(c -> {
            System.out.println(c);
            System.out.println();
        });
	}
}
