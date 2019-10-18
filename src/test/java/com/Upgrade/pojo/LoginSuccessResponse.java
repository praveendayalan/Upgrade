package com.Upgrade.pojo;

import java.util.List;

public class LoginSuccessResponse {
	
	private String firstName;
	private String userId;
	private String userUuid;
	private List<LoanApplications> loanApplications; 
	private List<LoansInReview>loansInReview;

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the userUuid
	 */
	public String getUserUuid() {
		return userUuid;
	}
	/**
	 * @param userUuid the userUuid to set
	 */
	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}
	/**
	 * @return the loanApplications
	 */
	public List<LoanApplications> getLoanApplications() {
		return loanApplications;
	}
	/**
	 * @param loanApplications the loanApplications to set
	 */
	public void setLoanApplications(List<LoanApplications> loanApplications) {
		this.loanApplications = loanApplications;
	}
	/**
	 * @return the loansInReview
	 */
	public List<LoansInReview> getLoansInReview() {
		return loansInReview;
	}
	/**
	 * @param loansInReview the loansInReview to set
	 */
	public void setLoansInReview(List<LoansInReview> loansInReview) {
		this.loansInReview = loansInReview;
	}
	
}
