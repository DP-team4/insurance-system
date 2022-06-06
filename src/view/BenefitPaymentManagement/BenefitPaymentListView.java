package view.BenefitPaymentManagement;

import domain.benefitPayment.BenefitPayment;
import service.BenefitPaymentServiceImpl;
import service.BenefitPaymentService;
import service.contractManagement.ContractManagementService;
import service.contractManagement.ContractManagementServiceImpl;
import view.viewUtility.ScannerUtility;
import view.viewUtility.View;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class BenefitPaymentListView extends View {
    private final Scanner scanner = ScannerUtility.getScanner();
    private BenefitPaymentService benefitPayManagementService = BenefitPaymentServiceImpl.getInstance();
    private ContractManagementService contractManagementService = ContractManagementServiceImpl.getInstance();

    @Override
    public void show() {
        while (true) {
            System.out.println("\n================보험금 청구 전체 현황 조회 화면================");
            if (!showOnReviewCarAccidentHandlingList())
                break;
            System.out.println("다시 조회하려면 Y 혹은 y, 돌아가시려면 그 외 아무키나 입력하세요.");
            String input = scanner.nextLine().trim();
            if (!(input.equals("Y") && input.equals("y"))) break;
        }
    }

    private boolean showOnReviewCarAccidentHandlingList() {        
        System.out.printf(" %s %s %s %s %s %s", "보험금 청구 번호", "고객명", "요청 시각","사고 발생 시각","사고 내용","접수현황");
        ArrayList<BenefitPayment> benefitPayments = this.benefitPayManagementService.getAll();
        if(benefitPayments.isEmpty()) return false;
        for(BenefitPayment e : benefitPayments){
            String id = e.getId();
            String customerName = this.contractManagementService.getCustomer(this.contractManagementService.getContract(e.getContractId()).getCustomerId()).getName();
            LocalDateTime requestDate = e.getRequestDate();
            LocalDateTime accidentDate = e.getAccidentDate();
            String accidentContent = e.getAccidentContent();
            String state = e.getState().getTitle();
            System.out.printf("%s %s %s %s %s %s", id, customerName, requestDate, accidentDate, accidentContent, state);
            System.out.println();
        }
        return true;
    }
}
