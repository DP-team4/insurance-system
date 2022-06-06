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
            System.out.println("================사고처리 요청 관리 화면================");
            System.out.println("보험금 청구 처리(1), 보험금 지급(2), 보험금 청구 전체 현황 확인(3), 뒤로가기(0)");
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
                    System.out.println("잘못된 입력입니다.");
                    break;
            }
            System.out.println("반복됩니다. 계속하시겠습니가? 계속(1) 뒤로가기(그 외)");
            input = scanner.nextLine().trim();
            if (!input.equals("1")) break;
        }
    }
}
