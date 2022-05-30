package domain.insurance.test;

import domain.insurance.FireInsurance;

public class FireInsuranceTest {
    public static FireInsurance testCreation(String name) {
        System.out.println("///// Test for Insurance, Creation, FireInsurance");
        FireInsurance fireInsurance = new FireInsurance();
        fireInsurance.setName(name);
        InsuranceTest.testAddition(fireInsurance);
        System.out.println(fireInsurance);
        return fireInsurance;
    }
}
