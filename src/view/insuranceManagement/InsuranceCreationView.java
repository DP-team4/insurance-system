package view.insuranceManagement;

import domain.insurance.*;
import domain.insurance.test.CarInsuranceTest;
import domain.insurance.test.DriverInsuranceTest;
import domain.insurance.test.FireInsuranceTest;
import domain.insurance.test.MarineInsuranceTest;
import service.InsuranceManagementService;
import service.InsuranceManagementServiceImpl;
import view.viewUtility.ScannerUtility;
import view.viewUtility.View;

import java.util.ArrayList;
import java.util.Scanner;

public class InsuranceCreationView extends View {
    private final Scanner scanner = ScannerUtility.getScanner();
    private final InsuranceManagementService insuranceManagementService = InsuranceManagementServiceImpl.getInstance();


    @Override
    public void show() {
        System.out.println("///// ���ο� ������ �����մϴ�. /////");
        System.out.print("���� ȭ��(1), ������(2), �ػ�(3), �ڵ���(4) >> ");
        String category = scanner.nextLine().trim();
        switch (category) {
            case "1":
                showCreation(new FireInsurance());
                break;
            case "2":
                showCreation(new DriverInsurance());
                break;
            case "3":
                showCreation(new MarineInsurance());
                break;
            case "4":
                showCreation(new CarInsurance());
                break;
            default:
                System.out.println("������ ���� �Է��� �ùٸ��� �ʽ��ϴ�. �Է�: " + category);
                break;
        }
        return;
    }

    private void showCreation(Insurance insurance) {
        System.out.print("����� >> ");
        String name = scanner.nextLine().trim();

        insurance.setName(name);
        while(true) {
            System.out.println("����� �߰��Ͻðڽ��ϱ�? ��(1) �ƴϿ�(�� ��)");
            String input = scanner.nextLine().trim();
            if (!input.equals("1")) break;
            System.out.print("����� >> ");
            String clauseName = scanner.nextLine().trim();
            System.out.print("���Աݾ� >> ");
            String insuredAmount = scanner.nextLine().trim();
            System.out.print("�� ����� >> ");
            String premium = scanner.nextLine().trim();
            System.out.print("�Ϲݾ��(1), Ư�����(�� ��) >> ");
            String category = scanner.nextLine().trim();
            ClauseCategory clauseCategory = category.equals("1") ? ClauseCategory.NORMAL : ClauseCategory.SPECIAL;
            try {
                Clause clause = new Clause();
                clause.setName(clauseName);
                clause.setInsuredAmount(Long.parseLong(insuredAmount));
                clause.setPremium(Long.parseLong(premium));
                clause.setClauseCategory(clauseCategory);
                insurance.addClause(clause);
            } catch (NumberFormatException e) {
                System.out.println("�߸��� �Է��Դϴ�.");
            }
        }
        insuranceManagementService.addInsurance(insurance);
    }

}
