package view.insuranceManagement;

import domain.insurance.Insurance;
import service.InsuranceManagementService;
import service.InsuranceManagementServiceImpl;
import view.viewUtility.ScannerUtility;
import view.viewUtility.View;

import java.util.ArrayList;
import java.util.Scanner;

public class InsuranceIntegratedManagementView extends View {
    private final Scanner scanner = ScannerUtility.getScanner();
    private final InsuranceManagementService insuranceManagementService = InsuranceManagementServiceImpl.getInstance();

    public void show() {
        System.out.println("///// ���� ���հ��� /////");
        while(true){
            ArrayList<Insurance> insurances = insuranceManagementService.getAll();
            showInsuranceListView();
            System.out.println("�۾��� ������ ID �Ǵ� �̸��� �Է��ϼ���. ���� ����(new) �ڷΰ���(exit)");
            System.out.print(">> ");
            String input = scanner.nextLine().trim();
            if(input.equals("exit")) break;
            else if(input.equals("new")) new InsuranceCreationView().show();
            else {
                for (Insurance insurance : insurances) {
                    if(insurance.getId().equals(input) || insurance.getName().equals(input)) {
                        new InsuranceManagementView(insurance.getId()).show();
                        break;
                    }
                }
                System.out.println("�ش��ϴ� ������ ã�� ���߽��ϴ�. �Է��� Ȯ�����ּ���.");
            }
        }
    }

    public void showInsuranceListView() {
        ArrayList<Insurance> insurances = insuranceManagementService.getAll();
        if(insurances.size() < 1) System.out.println("���� ����� �����ǰ�� �����ϴ�.");
        else insurances.forEach(i -> {
            System.out.println(i);
            System.out.println();
        });
    }

    public static void main(String[] args) {
        new InsuranceIntegratedManagementView().show();
    }
}
