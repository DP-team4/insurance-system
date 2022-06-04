package view.insuranceManagement;

import domain.insurance.Insurance;
import domain.insurance.InsuranceState;
import service.InsuranceManagementService;
import service.InsuranceManagementServiceImpl;
import view.viewUtility.ScannerUtility;
import view.viewUtility.View;

import java.util.Scanner;

public class InsuranceManagementView extends View {
    private final Scanner scanner = ScannerUtility.getScanner();
    private final InsuranceManagementService insuranceManagementService = InsuranceManagementServiceImpl.getInstance();
    private Insurance insurance;

    public InsuranceManagementView(String insuranceId) {
        this.insurance = insuranceManagementService.getById(insuranceId);
    }

    @Override
    public void show() {
        while (true) {
            System.out.println("///// 보험 관리 /////");
            refreshInsurance();
            System.out.println(insurance);

            System.out.println("이름 변경(1), 상태 변경(2), 약관 관리(3), 상품심사 요청(4) 삭제(delete), 뒤로가기(exit)");
            System.out.print(">>");
            String input = scanner.nextLine().trim();
            if(input.equals("exit")) break;
            switch (input) {
                case "1":
                    showRenameView();
                    break;
                case "2":
                    showChangeStateView();
                    break;
                case "3":
                    new ClauseIntegratedManagementView(insurance.getId()).show();
                    break;
                case "4":
                    showRequestAuditView();
                    break;
                case "delete":
                    showDeletionView();
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 입력: " + input);
                    break;
            }
        }
    }

    private void showRequestAuditView() {
        System.out.println(insurance.getName() + "에 대한 심사를 요청합니다.");
    }

    private void showDeletionView() {
        System.out.println("정말로 삭제하시겠습니까? yes/no");
        String input = scanner.nextLine().trim();
        switch (input) {
            case "yes":
                insuranceManagementService.deleteInsurance(insurance.getId());
                break;
            case "no":
                return;
            default:
                System.out.println("잘못된 입력입니다.");
                return;
        }
    }

    private void showChangeStateView() {
        while (true) {
            refreshInsurance();
            System.out.println(insurance.getName() + "의 상태를 변경합니다.");
            System.out.print("판매중(1), 판매종료(2), 심사 전(3), 심사 중(4), 심사 통과(5), 심사 반려(6) >> ");
            String input = scanner.nextLine().trim();
            InsuranceState insuranceState = null;
            switch (input) {
                case "1" : insuranceState = InsuranceState.ON_SALE; break;
                case "2" : insuranceState = InsuranceState.END_SALE; break;
                case "3" : insuranceState = InsuranceState.BEFORE_AUDIT; break;
                case "4" : insuranceState = InsuranceState.UNDER_AUDIT; break;
                case "5" : insuranceState = InsuranceState.ACCEPTED; break;
                case "6" : insuranceState = InsuranceState.REJECTED; break;
                default:
                    System.out.println("잘못된 입력입니다. 입력: " + input);
                    continue;
            }
            insurance.setInsuranceState(insuranceState);
            insuranceManagementService.updateInsurance(insurance);
            break;
        }
    }

    private void showRenameView() {
        while (true) {
            refreshInsurance();
            System.out.println(insurance.getName() + "의 이름을 변경합니다.");
            System.out.print("이름 >> ");
            String name = scanner.nextLine().trim();
            if(name.length() < 1) {
                System.out.println("공백은 이름이 될 수 없습니다.");
                continue;
            } else {
                insurance.setName(name);
                insuranceManagementService.updateInsurance(insurance);
                break;
            }
        }

    }

    private void refreshInsurance() {
        insurance = insuranceManagementService.getById(insurance.getId());
    }
}
