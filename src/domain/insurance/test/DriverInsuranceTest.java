package domain.insurance.test;

import domain.insurance.DriverInsurance;

public class DriverInsuranceTest {
    public static DriverInsurance testCreation(String name) {
        System.out.println("///// Test for Insurance, Creation, DriverInsurance");
        DriverInsurance insurance = new DriverInsurance();
        insurance.setName(name);
        InsuranceTest.testAddition(insurance);
        System.out.println(insurance);
        return insurance;
    }
}
