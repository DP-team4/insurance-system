package service.customer;

import java.util.ArrayList;

import dao.ContractDao;
import domain.carAccidentHandling.CarAccidentHandling;
import domain.contract.Contract;
import repository.carAccidentHandling.CarAccidentHandlingRepository;

public class CustomerCarAccidentHandlingServiceImpl implements CustomerCarAccidentHandlingService {
    private static final CustomerCarAccidentHandlingService instance = new CustomerCarAccidentHandlingServiceImpl();
    private final CarAccidentHandlingRepository carAccidentHandlingRepository = CarAccidentHandlingRepository.getInstance();
    private static final ContractDao contractDao = new ContractDao();

    //singleton
    private CustomerCarAccidentHandlingServiceImpl(){}
    public static CustomerCarAccidentHandlingService getInstance() {
        return instance;
    }
    
    @Override
    public boolean applyCarAccidentHandling(CarAccidentHandling carAccidentHandling) {
        return carAccidentHandlingRepository.create(carAccidentHandling);
    }

	@Override
	public boolean revokeMyCarAccidentHandling(String id, String customerId) {
		CarAccidentHandling carAccidentHandling = carAccidentHandlingRepository.getById(id);
		if(carAccidentHandling == null) return false;
		Contract contract = contractDao.retrieveById(carAccidentHandling.getContractId());
		if(contract.getCustomerId().equals(customerId)) return carAccidentHandlingRepository.delete(id);
		return false;
	}
    
	@Override
	public ArrayList<CarAccidentHandling> getByCustomerId(String customerId) {
		ArrayList<CarAccidentHandling> carAccidentHandlings = new ArrayList<>();
		for(CarAccidentHandling c : carAccidentHandlingRepository.getAll()) {
			Contract contract = contractDao.retrieveById(c.getContractId());
			if(contract.getCustomerId().equals(customerId)) {
				carAccidentHandlings.add(c);
			}
		}
		return carAccidentHandlings;
	}
}
