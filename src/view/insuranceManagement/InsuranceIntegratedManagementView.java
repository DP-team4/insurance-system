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
        System.out.println("///// 보험 통합관리 /////");
        while(true){
            ArrayList<Insurance> insurances = insuranceManagementService.getAll();
            showInsuranceListView();
            System.out.println("작업할 보험의 ID 또는 이름을 입력하세요. 보험 개발(new) 뒤로가기(exit)");
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
                System.out.println("해당하는 보험을 찾지 못했습니다. 입력을 확인해주세요.");
            }
        }
    }

    public void showInsuranceListView() {
        ArrayList<Insurance> insurances = insuranceManagementService.getAll();
        if(insurances.size() < 1) System.out.println("현재 저장된 보험상품이 없습니다.");
        else insurances.forEach(i -> {
            System.out.println(i);
            System.out.println();
        });
    }

    public static void main(String[] args) {
        new InsuranceIntegratedManagementView().show();
    }
}
