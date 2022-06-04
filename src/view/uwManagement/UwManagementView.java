package view.uwManagement;

import domain.contract.Contract;
import domain.uw.UW;
import domain.uw.UWDocument;
import domain.uw.UWState;
import service.UwManagementService;
import service.UwManagementServiceImpl;
import view.insuranceManagement.ClauseIntegratedManagementView;
import view.viewUtility.View;

public class UwManagementView extends View {
    private final UwManagementService uwManagementService = UwManagementServiceImpl.getInstance();
    private UW uw;

    public UwManagementView(String id) {
        uw = uwManagementService.getById(id);
    }

    @Override
    public void show() {
        while (true) {
            System.out.println("///// UW ���� /////");
            loadUw();
            System.out.println(uw);

            System.out.println("�㰡(1), �ݷ�(2), ���� ��û(3), �ڷΰ���(exit)");
            System.out.print(">>");
            String input = mScanner.getString();
            if(input.equals("exit")) break;
            switch (input) {
                case "1":
                    showAcceptView();
                    break;
                case "2":
                    showRejectView();
                    break;
                case "3":
                    showRequestDocumentView();
                    break;
                case "delete":
                    return;
                default:
                    System.out.println("�߸��� �Է��Դϴ�. �Է�: " + input);
                    break;
            }
        }
    }

    private void showRequestDocumentView() {
        System.out.println("������ ��û�� ���� �̸� �Է����ּ��� >> ");
        String documentName = mScanner.getString();
        uwManagementService.requestAdditionalDocument(uw.getId(), documentName);
    }

    private void showRejectView() {
        if(uwManagementService.rejectUw(uw)) System.out.println("�ش� UW�� �ݷ��Ͽ����ϴ�.");
        else System.out.println("UW �ݷ��� �����Ͽ����ϴ�.");
        loadUw();
    }

    private void showAcceptView() {
        if(uwManagementService.acceptUw(uw)) System.out.println("�ش� UW�� �㰡�Ͽ����ϴ�.");
        else System.out.println("UW �㰡�� �����Ͽ����ϴ�.");
        loadUw();
    }

    private void loadUw() {
        uw = uwManagementService.getById(uw.getId());
    }

}
