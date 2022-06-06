package dao;

import domain.benefitPayment.BenefitPayment;
import domain.benefitPayment.EBenefitPaymentState;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class BenefitPaymentDao extends Dao{
	//desc benefit_payment
	private final String tableName = "benefit_payment";

	public BenefitPaymentDao() {
		super.connect();
	}

	public boolean create(BenefitPayment benefitPayment){
		String query = String.format(
				"insert into %s values (" +
						"0, '%d', '%s', '%s', '%s', " +
						"'%d', '%d', '%d', " +
						"'%d')",
				this.tableName,  Integer.parseInt(benefitPayment.getContractId()), Timestamp.valueOf(benefitPayment.getRequestDate()), Timestamp.valueOf(benefitPayment.getAccidentDate()), benefitPayment.getAccidentContent(),
				benefitPayment.getTotalPropertyLoss(), benefitPayment.getTotalPersonLoss(), benefitPayment.getTotalBenefit(),
				benefitPayment.getState().ordinal()
		);
		return super.create(query);
	}

	public boolean update(BenefitPayment benefitPayment) {
		String query = String.format(
				"update %s set " +
						"request_date=%s, accident_date=%s, accident_content=%s, accident_location=%s, " +
						"total_property_loss=%d, total_person_loss=%d, total_benefit=%d" +
						"state=%d",
				this.tableName, Timestamp.valueOf(benefitPayment.getRequestDate()), Timestamp.valueOf(benefitPayment.getAccidentDate()),
				benefitPayment.getTotalPropertyLoss(), benefitPayment.getTotalPersonLoss(), benefitPayment.getTotalBenefit(),
				benefitPayment.getState().ordinal()
		);
		System.out.println(query);
		return super.create(query);
	}


	public boolean delete(String id) {
		String query = String.format("delete from %s where id=%s", this.tableName, id);
		System.out.println(query);
		return super.delete(query);
	}

	public ArrayList<BenefitPayment> retrieveAll() {
		try{
			String query = "select * from "+this.tableName;
			System.out.println(query); //나중에 없앨 것
			ResultSet resultSet = super.retrieve(query);
			if(resultSet==null) return null;
			ArrayList<BenefitPayment> benefitPayments = new ArrayList<>();
			while(resultSet.next()){
				BenefitPayment benefitPayment = getCurrentRecord(resultSet);
				benefitPayments.add(benefitPayment);
			}
			return benefitPayments;
		}catch (SQLException e) {
//			e.printStackTrace();
			return null;
		}
	}

	public BenefitPayment retrieveById(String id){
		try{
			String query = String.format("select * from "+this.tableName+" where id=%s", id);
			System.out.println(query);
			ResultSet resultSet = super.retrieve(query);
			if(resultSet==null || !resultSet.next()) return null;
			BenefitPayment benefitPayment = this.getCurrentRecord(resultSet);
			return benefitPayment;
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	private BenefitPayment getCurrentRecord(ResultSet resultSet) throws SQLException { //내부에서 처리되므로 상관X
		String id = resultSet.getString("id");
		String contractId = resultSet.getString("contract_id");
		LocalDateTime requestDate = resultSet.getTimestamp("request_date").toLocalDateTime();
		LocalDateTime accidentDate = resultSet.getTimestamp("accident_date").toLocalDateTime();
		String accidentContent = resultSet.getString("accident_content");
		long totalPropertyLoss = Long.parseLong(resultSet.getString("total_property_loss"));
		long totalPersonLoss = Long.parseLong(resultSet.getString("total_person_loss"));
		long totalBenefit = Long.parseLong(resultSet.getString("total_benefit"));

		EBenefitPaymentState state = EBenefitPaymentState.values()[resultSet.getInt("state")];

		BenefitPayment benefitPayment = new BenefitPayment();
		benefitPayment.setId(id);
		benefitPayment.setContractId(contractId);
		benefitPayment.setRequestDate(requestDate);
		benefitPayment.setAccidentDate(accidentDate);
		benefitPayment.setAccidentContent(accidentContent);
		benefitPayment.setTotalPropertyLoss(totalPropertyLoss);
		benefitPayment.setTotalPersonLoss(totalPersonLoss);
		benefitPayment.setTotalBenefit(totalBenefit);
		benefitPayment.setState(state);
		return benefitPayment;
	}

}
