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
                System.out.println("\n================����� û�� ó�� ȭ��================");
                if(!showOnReviewCarAccidentHandlingList())
                    break;
                showProcessRequestedCarAccidentHandling();
                System.out.println("����� û�� ó��  ȭ���Դϴ�. ����Ͻ÷��� Y/y, ���ư��÷��� N/n�� �Է����ּ���.");
                String input = scanner.nextLine().trim();
                if(!(input.equals("Y")&&input.equals("y"))) break;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void showProcessRequestedCarAccidentHandling() throws InvalidInputException {
        BenefitPayment benefitPayment = null;

        System.out.print("ó���� ����� û�� ���� ID�� �Է��ϼ��� >> ");
        String input = scanner.nextLine().trim();
        benefitPayment = benefitPayManagementService.getById(input);
        if(benefitPayment == null)
            throw new InvalidInputException("�ش� ����� û�� ID�� ��ȿ���� �ʽ��ϴ�.");

        while(true) {
            System.out.println("////// �ش� ����� û���� ó���մϴ�. //////");
            System.out.println("'����'ó��(1), '����'ó��(2), �ڷΰ��� (0)");
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
                    System.out.println("1, 2, 0 �߿��� �Է����ֽñ� �ٶ��ϴ�.");
                    break;
            }
            if(escape) break;
        }
    }

    private boolean showOnReviewCarAccidentHandlingList() {
        System.out.printf(" %s %s %s %s %s", "����� û�� ��ȣ", "����", "��û �ð�","��� �߻� �ð�","��� ����");
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
