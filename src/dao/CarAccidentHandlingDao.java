package dao;

import domain.benefitPayment.BenefitPayment;
import domain.carAccidentHandling.CarAccidentHandling;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarAccidentHandlingDao extends Dao {

	public CarAccidentHandlingDao()  {
		super.connect();
	}

	public boolean create(CarAccidentHandling bp){
//		String query = String.format(
//		""
//		)
		return super.create(null);
	}

	public boolean update(CarAccidentHandling bp) {
		return super.create(null);
	}


	public boolean delete(String id) {

		return super.delete(null);
	}

	public ArrayList<CarAccidentHandling> retrieveAll() {
		try{
			String query = "";
			System.out.println(query); //나중에 없앨 것
			ResultSet resultSet = super.retrieve(query);
			if(resultSet==null) return null;
			ArrayList<CarAccidentHandling> carAccidentHandlings = new ArrayList<>();
			while(resultSet.next()){
//				BenefitPayment benefitPayment = getCurrentBenefitPayment(resultSet);
//				carAccidentHandlings.add(benefitPayment);
			}
			return carAccidentHandlings;
		}catch (SQLException e) {
//			e.printStackTrace();
			return null;
		}
	}

	public CarAccidentHandling retrieveById(String id){
		try{
			String query = String.format("");
			System.out.println(query);
			ResultSet resultSet = super.retrieve(query);
			if(resultSet==null || !resultSet.next()) return null;
			CarAccidentHandling carAccidentHandling = this.getCurrentEntity(resultSet);
			return carAccidentHandling;
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	private CarAccidentHandling getCurrentEntity(ResultSet resultSet) throws SQLException { //내부에서 처리되므로 상관X
//		String id = resultSet.getString("id");
//		String name = resultSet.getString("name");

		return null;
	}


}
