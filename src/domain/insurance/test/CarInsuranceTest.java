package domain.insurance.test;

import domain.insurance.CarInsurance;

public class CarInsuranceTest {
    public static CarInsurance testCreation(String name) {
        System.out.println("///// Test for Insurance, Creation, FireInsurance");
        CarInsurance insurance = new CarInsurance();
        insurance.setName(name);
        InsuranceTest.testAddition(insurance);
        System.out.println(insurance);
        return insurance;
    }
}
