package repository.benefitPayment;

import dao.BenefitPaymentDao;
import domain.benefitPayment.BenefitPayment;
import domain.carAccidentHandling.AccidentCar;
import domain.carAccidentHandling.AccidentPerson;
import domain.carAccidentHandling.CarAccidentHandling;

import java.util.ArrayList;

public class BenefitPaymentRepository {

    private static final BenefitPaymentRepository instance = new BenefitPaymentRepository();
    private final BenefitPaymentDao benefitPaymentDao = new BenefitPaymentDao();

    // Singleton
    private BenefitPaymentRepository(){}
    public static BenefitPaymentRepository getInstance() { return instance; }

    public boolean create(BenefitPayment benefitPayment) {
        return benefitPaymentDao.create(benefitPayment);
    }
    public boolean delete(String id) {
        return benefitPaymentDao.delete(id);
    }
    public BenefitPayment getById(String id) {
        return benefitPaymentDao.retrieveById(id);
    }
    public ArrayList<BenefitPayment> getAll() {
        return benefitPaymentDao.retrieveAll();
    }
    public boolean update(BenefitPayment benefitPayment) {
            BenefitPayment before = this.getById(benefitPayment.getId());
            if(!before.equalsAttributes(benefitPayment))
                if(!benefitPaymentDao.update(benefitPayment))
                    return false;
        return true;
    }
}
