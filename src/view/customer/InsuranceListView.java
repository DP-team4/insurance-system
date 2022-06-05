package view.customer;

import java.util.ArrayList;
import java.util.Scanner;

import domain.insurance.Clause;
import domain.insurance.Insurance;
import domain.insurance.InsuranceCategory;
import service.customer.InsuranceRetrieveService;
import service.customer.InsuranceRetrieveServiceImpl;
import view.viewUtility.ScannerUtility;

public class InsuranceListView {
    private final Scanner scanner = ScannerUtility.getScanner();
    
    // Service
    private final InsuranceRetrieveService insuranceRetrieveService = InsuranceRetrieveServiceImpl.getInstance();
	
	public void show() {
		System.out.println("\n[ �����ǰ ��ȸ ]");
		// ������ �����ǰ(�ػ���, �ڵ�������, �����ں���, ȭ�纸��)�� �����ϰ� �˻���ư�� Ŭ���Ѵ�(A1, A2, E1, E2)
		System.out.println("��ȸ�� �����ǰ ������ �������ּ��� -");
		System.out.print("�ػ���(1), �ڵ�������(2), �����ں���(3), ȭ�纸��(4), ��ü��ȸ(5) >> ");
		String input = scanner.nextLine().trim();
        // �˻� ���� �Է� ���θ� üũ�Ѵ�
		// A1. �˻� ������ �� �Է��� ��� -> '�˻� ������ �Է����ּ���' �޽����� �����ش�
		if(input.equals("")) System.out.println("\n�˻� ������ �Է����ּ���");
		else {
	        // �˻� ���ǿ� �´� �Ǹ����� �����ǰ ������ ��û�Ѵ�
	        ArrayList<Insurance> insurances = insuranceRetrieveService.getInsurancesOnSale();
	        InsuranceCategory insuranceCategory = null;
			switch(input) {
				case "1": insuranceCategory = InsuranceCategory.MARINE; break;
				case "2": insuranceCategory = InsuranceCategory.CAR; break;
				case "3": insuranceCategory = InsuranceCategory.DRIVER; break;
				case "4": insuranceCategory = InsuranceCategory.FIRE; break;
				case "5": insuranceCategory = null; break;
				// A3. �ùٸ��� ���� �˻� ������ �Է��� ��� -> "�Է°��� ��ȿ���� �ʽ��ϴ�" �޽����� �����ش�
				default : System.out.println("\n�Է°��� ��ȿ���� �ʽ��ϴ�."); return;
			}
			insuranceRetrieveService.filterInsuranceByCategory(insurances, insuranceCategory);
	        // ������(�����ǰ��, ��������, �����, ���Աݾ�, �����)�� �����ش�
	        // A2. �˻� ���ǿ� �ش��ϴ� �����ǰ�� 0���� ��� -> �޽��� '������ ���ǿ� �´� ��ǰ�� �����ϴ�'�� �����ش�
	        if(insurances.size() == 0) { System.out.println("������ ���ǿ� �´� ��ǰ�� �����ϴ�. �޴�ȭ������ ���ư��ϴ�."); return; }
            insurances.forEach(i -> {
            	ArrayList<Clause> clauses = i.getClauses();
                System.out.print("�����ǰ��: " + i.getName());
                System.out.print("��������: " + i.getName());
                for(Clause clause : clauses) {
                	System.out.println("<��� ���>");
                	System.out.print("�����: " + clause.getName() + ", ");
	                System.out.print("���Աݾ�: " + clause.getInsuredAmount());
	                System.out.print("�����: " + clause.getPremium());
                }
            });
		}
	}
}
