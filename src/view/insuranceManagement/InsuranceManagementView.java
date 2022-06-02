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
            System.out.println("///// ���� ���� /////");
            refreshInsurance();
            System.out.println(insurance);

            System.out.println("�̸� ����(1), ���� ����(2), ��� ����(3), ��ǰ�ɻ� ��û(4) ����(delete), �ڷΰ���(exit)");
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
                    System.out.println("�߸��� �Է��Դϴ�. �Է�: " + input);
                    break;
            }
        }
    }

    private void showRequestAuditView() {
        System.out.println(insurance.getName() + "�� ���� �ɻ縦 ��û�մϴ�.");
    }

    private void showDeletionView() {
        System.out.println("������ �����Ͻðڽ��ϱ�? yes/no");
        String input = scanner.nextLine().trim();
        switch (input) {
            case "yes":
                insuranceManagementService.deleteInsurance(insurance.getId());
                break;
            case "no":
                return;
            default:
                System.out.println("�߸��� �Է��Դϴ�.");
                return;
        }
    }

    private void showChangeStateView() {
        while (true) {
            refreshInsurance();
            System.out.println(insurance.getName() + "�� ���¸� �����մϴ�.");
            System.out.print("�Ǹ���(1), �Ǹ�����(2), �ɻ� ��(3), �ɻ� ��(4), �ɻ� ���(5), �ɻ� �ݷ�(6) >> ");
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
                    System.out.println("�߸��� �Է��Դϴ�. �Է�: " + input);
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
            System.out.println(insurance.getName() + "�� �̸��� �����մϴ�.");
            System.out.print("�̸� >> ");
            String name = scanner.nextLine().trim();
            if(name.length() < 1) {
                System.out.println("������ �̸��� �� �� �����ϴ�.");
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
