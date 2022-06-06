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
            System.out.println("\n================����� û�� ��ü ��Ȳ ��ȸ ȭ��================");
            if (!showOnReviewCarAccidentHandlingList())
                break;
            System.out.println("�ٽ� ��ȸ�Ϸ��� Y Ȥ�� y, ���ư��÷��� �� �� �ƹ�Ű�� �Է��ϼ���.");
            String input = scanner.nextLine().trim();
            if (!(input.equals("Y") && input.equals("y"))) break;
        }
    }

    private boolean showOnReviewCarAccidentHandlingList() {        
        System.out.printf(" %s %s %s %s %s %s", "����� û�� ��ȣ", "����", "��û �ð�","��� �߻� �ð�","��� ����","������Ȳ");
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
