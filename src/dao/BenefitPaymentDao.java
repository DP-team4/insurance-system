package dao;

import domain.benefitPayment.BenefitPayment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BenefitPaymentDao extends Dao{

	public BenefitPaymentDao() {
		super.connect();
	}

	public boolean create(BenefitPayment bp){
//		String query = String.format(
//		""
//		)
		return super.create(null);
	}

	public boolean update(BenefitPayment bp) {
		return super.create(null);
	}


	public boolean delete(String id) {

		return super.delete(null);
	}

	public ArrayList<BenefitPayment> retrieveAll() {
		try{
		String query = "";
			System.out.println(query); //나중에 없앨 것
			ResultSet resultSet = super.retrieve(query);
			if(resultSet==null) return null;
			ArrayList<BenefitPayment> benefitPayments = new ArrayList<>();
			while(resultSet.next()){
//				BenefitPayment benefitPayment = getCurrentBenefitPayment(resultSet);
//				benefitPayments.add(benefitPayment);
			}
			return benefitPayments;
		}catch (SQLException e) {
//			e.printStackTrace();
			return null;
		}
	}

	public BenefitPayment retrieveById(String id){
		try{
			String query = String.format("");
			System.out.println(query);
			ResultSet resultSet = super.retrieve(query);
			if(resultSet==null || !resultSet.next()) return null;
			BenefitPayment benefitPayment = this.getCurrentEntity(resultSet);
			return benefitPayment;
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	private BenefitPayment getCurrentEntity(ResultSet resultSet) throws SQLException { //내부에서 처리되므로 상관X
//		String id = resultSet.getString("id");
//		String name = resultSet.getString("name");


		return null;
	}

}
