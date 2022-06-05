package service;

import domain.benefitPayment.BenefitPayment;

import java.util.ArrayList;

public interface BenefitPaymentService {
    public ArrayList<BenefitPayment> getAll();
    public BenefitPayment getById(String id);
    public boolean createCarAccidentHandling(BenefitPayment benefitPayment);
    public boolean updateCarAccidentHandling(BenefitPayment benefitPayment);
    public boolean deleteCarAccidentHandling(String id);
}
