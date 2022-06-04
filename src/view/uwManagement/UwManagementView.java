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
            System.out.println("///// UW 관리 /////");
            loadUw();
            System.out.println(uw);

            System.out.println("허가(1), 반려(2), 서류 요청(3), 뒤로가기(exit)");
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
                    System.out.println("잘못된 입력입니다. 입력: " + input);
                    break;
            }
        }
    }

    private void showRequestDocumentView() {
        System.out.println("고객에게 요청할 서류 이름 입력해주세요 >> ");
        String documentName = mScanner.getString();
        uwManagementService.requestAdditionalDocument(uw.getId(), documentName);
    }

    private void showRejectView() {
        if(uwManagementService.rejectUw(uw)) System.out.println("해당 UW를 반려하였습니다.");
        else System.out.println("UW 반려를 실패하였습니다.");
        loadUw();
    }

    private void showAcceptView() {
        if(uwManagementService.acceptUw(uw)) System.out.println("해당 UW를 허가하였습니다.");
        else System.out.println("UW 허가를 실패하였습니다.");
        loadUw();
    }

    private void loadUw() {
        uw = uwManagementService.getById(uw.getId());
    }

}
