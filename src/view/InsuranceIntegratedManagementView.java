package view;

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
        System.out.println("///// 보험 통합관리 /////");
        while(true){
            ArrayList<Insurance> insurances = insuranceManagementService.getAll();
            showInsuranceListView();
            System.out.println("작업할 보험의 ID를 입력하세요. 보험 개발(new) 뒤로가기(exit)");
            System.out.print(">> ");
            String input = scanner.nextLine().trim();
            if(input.equals("exit")) break;
            else if(input.equals("new")) new InsuranceCreationView().show();
            else {
                for (Insurance insurance : insurances) {
                    if(insurance.getId().equals(input)) {
                        new InsuranceManagementView(insurance.getId()).show();
                        continue;
                    }
                }
                System.out.println("잘못된 입력입니다.");
            }
        }
    }

    public void showInsuranceListView() {
        ArrayList<Insurance> insurances = insuranceManagementService.getAll();
        insurances.forEach(i -> {
            System.out.println(i);
            System.out.println();
        });
    }

    public static void main(String[] args) {
        new InsuranceIntegratedManagementView().show();
    }
}
