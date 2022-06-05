package service;

import domain.benefitPayment.BenefitPayment;
import repository.benefitPayment.BenefitPaymentRepository;

import java.util.ArrayList;

public class BenefitPaymentServiceImpl implements BenefitPaymentService {
    private static final BenefitPaymentService instance = new BenefitPaymentServiceImpl();
    private final BenefitPaymentRepository benefitPaymentRepository = BenefitPaymentRepository.getInstance();

    //singleton
    private BenefitPaymentServiceImpl(){}
    public static BenefitPaymentService getInstance() {
        return instance;
    }

    @Override
    public ArrayList<BenefitPayment> getAll() {
        return benefitPaymentRepository.getAll();
    }
    @Override
    public BenefitPayment getById(String id) {
        return benefitPaymentRepository.getById(id);
    }
    @Override
    public boolean createCarAccidentHandling(BenefitPayment benefitPayment) {
        return benefitPaymentRepository.create(benefitPayment);
    }
    @Override
    public boolean updateCarAccidentHandling(BenefitPayment benefitPayment) {
        return benefitPaymentRepository.update(benefitPayment);
    }
    @Override
    public boolean deleteCarAccidentHandling(String id) {
        return benefitPaymentRepository.delete(id);
    }
}
