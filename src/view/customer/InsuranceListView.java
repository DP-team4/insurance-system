package view.customer;

import java.util.ArrayList;
import java.util.Scanner;

import domain.insurance.Clause;
import domain.insurance.Insurance;
import domain.insurance.InsuranceCategory;
import service.customer.CustomerInsuranceRetrieveService;
import service.customer.CustomerInsuranceRetrieveServiceImpl;
import view.viewUtility.ScannerUtility;

public class InsuranceListView {
    private final Scanner scanner = ScannerUtility.getScanner();
    
    // Service
    private final CustomerInsuranceRetrieveService customerInsuranceRetrieveService = CustomerInsuranceRetrieveServiceImpl.getInstance();
	
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
	        ArrayList<Insurance> insurances = customerInsuranceRetrieveService.getInsurancesOnSale();
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
			insurances = customerInsuranceRetrieveService.filterInsuranceByCategory(insurances, insuranceCategory);
	        // ������(�����ǰ��, ��������, �����, ���Աݾ�, �����)�� �����ش�
	        // A2. �˻� ���ǿ� �ش��ϴ� �����ǰ�� 0���� ��� -> �޽��� '������ ���ǿ� �´� ��ǰ�� �����ϴ�'�� �����ش�
	        if(insurances.size() == 0) { System.out.println("������ ���ǿ� �´� ��ǰ�� �����ϴ�. �޴�ȭ������ ���ư��ϴ�."); return; }
            insurances.forEach(i -> {
            	ArrayList<Clause> clauses = i.getClauses();
                System.out.println("\n�����ǰ��: " + i.getName());
            	System.out.println("<��� ���>");
                for(Clause clause : clauses) {
                	System.out.print("�����: " + clause.getName() + ", ");
	                System.out.print("���Աݾ�: " + clause.getInsuredAmount() + ", ");
	                System.out.println("�Ѻ����: " + clause.getPremium());
                }
            });
		}
	}
}
