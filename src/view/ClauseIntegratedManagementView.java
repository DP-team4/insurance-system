package view;

import domain.insurance.Clause;
import domain.insurance.Insurance;
import service.InsuranceManagementService;
import service.InsuranceManagementServiceImpl;
import view.viewUtility.ScannerUtility;
import view.viewUtility.View;

import java.util.Scanner;

public class ClauseIntegratedManagementView extends View {
    private final Scanner scanner = ScannerUtility.getScanner();
    private final InsuranceManagementService insuranceManagementService = InsuranceManagementServiceImpl.getInstance();
    private Insurance insurance;

    public ClauseIntegratedManagementView(String insuranceId) {
        this.insurance = insuranceManagementService.getById(insuranceId);
    }

    @Override
    public void show() {
        while(true){
            refreshInsurance();
            System.out.println(insurance.getName() + " �� �����");
            showClauses();
            System.out.println("�۾��� ����� ID�� �Է��ϼ���. ��� �߰�(new) �ڷΰ���(exit)");
            System.out.print(">> ");
            String input = scanner.nextLine().trim();
            if(input.equals("exit")) break;
            else if(input.equals("new")) new ClauseCreationView(insurance.getId()).show();
            else if(insurance.getClauses().stream().noneMatch(c -> c.getId().equals(input))){
                System.out.println("�߸��� �Է��Դϴ�.");
                continue;
            }
            else {
                for (Clause clause : insurance.getClauses()) {
                    if(clause.getId().equals(input)) {
                        new ClauseManagementView(clause.getId()).show();
                        break;
                    }
                }
            }
        }

    }

    private void showClauses() {
        if (insurance.getClauses().size() < 1) {
            System.out.println("����� �����ϴ�.");
            return;
        }
        insurance.getClauses().forEach(c -> {
            System.out.println(c);
            System.out.println();
        });
    }

    private void refreshInsurance() {
        insurance = insuranceManagementService.getById(insurance.getId());
    }

}
