package view.sales;

import java.util.Scanner;

import domain.consultApplication.ConsultApplication;

public class ConsultApplicationListView {
	
	Scanner scanner;

	public ConsultApplicationListView(Scanner scanner) {
		this.scanner = scanner;
	}

	public void showView() {
		while(true) {
			System.out.println("\n================����û ��� ȭ��================");
			this.showConsultApplicationList();
			this.showConsultApplicationManage();
			
			
			 System.out.println("�ݺ��մϴ�. ����Ͻðڽ��ϰ�? ���(1) �ڷΰ���(�� ��)");
			 String input = scanner.nextLine().trim();
			 if(!input.equals("1")) break;
		}
	}

	private void showConsultApplicationList() {
		// ����û ��� ��������
	}
	
	private void showConsultApplicationManage() {
		System.out.print("�۾��� ����û�� ID�� �Է��ϼ��� >> ");
		String consultApplicationId = scanner.nextLine().trim();

//		ConsultApplication consultApplication
//		if(consultApplication == null){
//		    System.out.println("�߸��� ����û ID�Դϴ�.");
//		    return;
//		}
		
		while(true) {
		    System.out.println("////// �ش� ����û�� ���� �۾��� �����մϴ�. //////");
		    System.out.println("����(1), ����(2), �� ���� ����(3), �ڷΰ��� (0)");
		    String input = scanner.nextLine().trim();
	        if(input.equals("0")) break;
	        switch (input) {
	            case "1":
	                
	                break;
	            case "2":
	               
	                break;
	            case "3":
	         
	                break;
	            case "0":
	            	break;
	            default:
	                System.out.println("�߸��� �Է��Դϴ�.");
	                break;
	        }
		}
	}

}
