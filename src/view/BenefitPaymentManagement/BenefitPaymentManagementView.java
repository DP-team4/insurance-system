package view.BenefitPaymentManagement;

import view.viewUtility.ScannerUtility;
import view.viewUtility.View;

import java.util.Scanner;

public class BenefitPaymentManagementView  extends View {

    private final Scanner scanner = ScannerUtility.getScanner();

    @Override
    public void show() {
        while (true) {
            System.out.println();
            System.out.println("================����� û�� ���� ȭ��================");
            System.out.println("����� û�� ó��(1), ����� ����(2), ����� û�� ��ü ��Ȳ Ȯ��(3), �ڷΰ���(0)");
            System.out.print(">> ");
            String input = this.scanner.nextLine().trim();
            if (input.equals("0")) break;
            switch (input) {
                case "1":
                    new BenefitPaymentRequestedProcessView().show();
                    break;
                case "2":
                    new BenefitPaymentProcessView().show();
                    break;
                case "3":
                    new BenefitPaymentListView().show();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("�߸��� �Է��Դϴ�.");
                    break;
            }
            System.out.println("�ݺ��˴ϴ�. ����Ͻðڽ��ϰ�? ���(1) �ڷΰ���(�� ��)");
            input = scanner.nextLine().trim();
            if (!input.equals("1")) break;
        }
    }
}
