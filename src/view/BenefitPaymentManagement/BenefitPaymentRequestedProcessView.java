package view.BenefitPaymentManagement;

import domain.benefitPayment.BenefitPayment;
import domain.benefitPayment.EBenefitPaymentState;
import exception.InvalidInputException;
import service.BenefitPaymentService;
import service.BenefitPaymentServiceImpl;
import service.contractManagement.ContractManagementService;
import service.contractManagement.ContractManagementServiceImpl;
import view.viewUtility.ScannerUtility;
import view.viewUtility.View;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class BenefitPaymentRequestedProcessView extends View {
    private final Scanner scanner = ScannerUtility.getScanner();
    private BenefitPaymentService benefitPayManagementService = BenefitPaymentServiceImpl.getInstance();
    private ContractManagementService contractManagementService = ContractManagementServiceImpl.getInstance();

    @Override
    public void show() {
        while(true) {
            try {
                System.out.println("\n================보험금 청구 처리 화면================");
                if(!showOnReviewCarAccidentHandlingList())
                    break;
                showProcessRequestedCarAccidentHandling();
                System.out.println("보험금 청구 처리  화면입니다. 계속하시려면 Y/y, 돌아가시려면 N/n을 입력해주세요.");
                String input = scanner.nextLine().trim();
                if(!(input.equals("Y")&&input.equals("y"))) break;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void showProcessRequestedCarAccidentHandling() throws InvalidInputException {
        BenefitPayment benefitPayment = null;

        System.out.print("처리할 보험금 청구 건의 ID를 입력하세요 >> ");
        String input = scanner.nextLine().trim();
        benefitPayment = benefitPayManagementService.getById(input);
        if(benefitPayment == null)
            throw new InvalidInputException("해당 보험금 청구 ID는 유효하지 않습니다.");

        while(true) {
            System.out.println("////// 해당 보험금 청구를 처리합니다. //////");
            System.out.println("'승인'처리(1), '거절'처리(2), 뒤로가기 (0)");
            input = scanner.nextLine().trim();
            boolean escape = false;
            if(input.equals("0")) break;
            switch (input) {
                case "1":
                    benefitPayment.setState(EBenefitPaymentState.READY);
                    break;
                case "2":
                    benefitPayment.setState(EBenefitPaymentState.REFUSED);
                    break;
                case "0":
                    break;
                default:
                    System.out.println("1, 2, 0 중에서 입력해주시기 바랍니다.");
                    break;
            }
            if(escape) break;
        }
    }

    private boolean showOnReviewCarAccidentHandlingList() {
        System.out.printf(" %s %s %s %s %s", "보험금 청구 번호", "고객명", "요청 시각","사고 발생 시각","사고 내용");
        ArrayList<BenefitPayment> benefitPayments = this.benefitPayManagementService.getAll();
        if(benefitPayments.isEmpty()) return false;
        for(BenefitPayment e : benefitPayments){
            if(!e.getState().equals(EBenefitPaymentState.ONREVIEW)) continue;
            String id = e.getId();
            String customerName = this.contractManagementService.getCustomer(this.contractManagementService.getContract(e.getContractId()).getCustomerId()).getName();
            LocalDateTime requestDate = e.getRequestDate();
            LocalDateTime accidentDate = e.getAccidentDate();
            String accidentContent = e.getAccidentContent();

            System.out.printf("%s %s %s %s %s", id, customerName, requestDate, accidentDate, accidentContent);
            System.out.println();
        }
        return true;
    }
}
