package domain.cancelApplication.test;

import java.util.Scanner;

import dao.CancelApplicationDao;
import domain.cancelApplication.CancelApplication;

public class CancelApplicationTest {
    private static final CancelApplicationDao cancelApplicationDao = new CancelApplicationDao();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        test(scanner);
        scanner.close();
    }
    
	public static void test(Scanner scanner) {
		System.out.println("///// Test for CancelApplication /////"); 
	    while(true) {
	        System.out.println("����(1), ����(2), ���� ��û ��û ���̵�� ��ȸ(3), �� ���̵�� ��ȸ(4), ��ü ��ȸ(5), �ڷΰ���(0)");
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
	            	testRetrieveByContractId(scanner);
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
        	CancelApplication cancelApplication = new CancelApplication();
            System.out.println("///// Test for CancelApplication, Creation /////");
            /////////////////////////// ���� �ʿ� >> ���� �α��ε� ���� ��� ����� �����;� ��
            System.out.print("������ ��� ���̵� >> "); cancelApplication.setContractId(scanner.nextLine().trim());
    		if(cancelApplicationDao.create(cancelApplication))System.out.println("�������丮�� �߰��Ǿ����ϴ�.");
            else System.out.println("�������丮�� �߰����� �ʾҽ��ϴ�.");
            System.out.print("���� ��û ���� �׽�Ʈ�� ��� �Ͻðڽ��ϱ�? ��(1), �ƴϿ�(�� ��) >> ");
            String continueInput = scanner.nextLine().trim();
            if(continueInput.equals("1")) continue;
            else break;
        }
	}

	private static void testDeletion(Scanner scanner) {
        while (true) {
            System.out.println("///// Test for CancelApplication, Deletion /////");
            System.out.print("���̵� >> ");
            String id = scanner.nextLine().trim();
            if(cancelApplicationDao.delete(id)) System.out.println("���� �����ῴ���ϴ�.");
            else System.out.println("���� �����Ͽ����ϴ�.");

            System.out.print("���� ��û ���� �׽�Ʈ�� ��� �Ͻðڽ��ϱ�? ��(1), �ƴϿ�(�� ��) >> ");
            String continueInput = scanner.nextLine().trim();
            if(continueInput.equals("1")) continue;
            else break;
        }
	}

	private static void testRetrieve(Scanner scanner) {
        while (true) {
            System.out.println("///// Test for CancelApplication, Retrieve /////");
            System.out.print("���̵� >> ");
            String id = scanner.nextLine().trim();
            CancelApplication cancelApplication = cancelApplicationDao.retrieveById(id);
            if(cancelApplication == null) System.out.println("�ش��ϴ� ID�� ���� ��û�� ã�� ���߽��ϴ�.");
            else System.out.println(cancelApplication);

            System.out.print("���� ��û ��ȸ �׽�Ʈ�� ��� �Ͻðڽ��ϱ�? ��(1), �ƴϿ�(�� ��) >> ");
            String continueInput = scanner.nextLine().trim();
            if(continueInput.equals("1")) continue;
            else break;
        }
	}

	private static void testRetrieveByContractId(Scanner scanner) {
        while (true) {
            System.out.println("///// Test for CancelApplication, Retrieve By Customer Id/////");
            System.out.print("��� ���̵� >> ");
            String contractId = scanner.nextLine().trim();
            CancelApplication cancelApplication = cancelApplicationDao.retrieveByContractId(contractId);
            if(cancelApplication == null) System.out.println("�ش��ϴ� ��� ID�� ���� ��û�� ã�� ���߽��ϴ�.");
            else System.out.println(cancelApplication);
            System.out.print("���� ��û ��ȸ �׽�Ʈ�� ��� �Ͻðڽ��ϱ�? ��(1), �ƴϿ�(�� ��) >> ");
            String continueInput = scanner.nextLine().trim();
            if(continueInput.equals("1")) continue;
            else break;
        }
	}

	private static void testRetrieveAll() {
		System.out.println("///// Test for CancelApplication, RetrieveAll /////");
        cancelApplicationDao.retrieveAll().forEach(c -> {
            System.out.println(c);
            System.out.println();
        });
	}
}
