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
            System.out.println(insurance.getName() + " 의 약관들");
            showClauses();
            System.out.println("작업할 약관의 ID를 입력하세요. 약관 추가(new) 뒤로가기(exit)");
            System.out.print(">> ");
            String input = scanner.nextLine().trim();
            if(input.equals("exit")) break;
            else if(input.equals("new")) new ClauseCreationView(insurance.getId()).show();
            else if(insurance.getClauses().stream().noneMatch(c -> c.getId().equals(input))){
                System.out.println("잘못된 입력입니다.");
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
            System.out.println("약관이 없습니다.");
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
