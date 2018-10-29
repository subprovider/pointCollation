package org.ktds.pointCollation.repositories;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pointCollationData")
public class PointCollationData {

	@Id
	private String id;

	@NotEmpty(message="companyId 는 핑수 입력 입니다.")	
	private String companyId;
	private String companyNm;
	
	@NotEmpty(message="memberId 는 핑수 입력 입니다.")	
	private String memberId;
	
	@Indexed(unique=true)
	@Digits(fraction = 0, integer = 10, message ="approvalNum 는 숫자만 이력 가능합니다.")	
	private int approvalNum;
	
	private String approvalDate;
	
	@Min(value=0, message="0 이상")
	@Max(value=50000, message="50,000 점 이하")		
	private int useAmt;
	
	@Indexed	
	private String useDate;
	
	@Indexed	
	private String transactionId;	
	
	private Date insertDate;
	private Date updateDate;
	
	@Indexed
	private String hashNum;
	
	public PointCollationData() {}
	
	public PointCollationData(String companyId, String companyNm, String memberId, int approvalNum, String approvalDate,int useAmt, String useDate) {
		super();
		this.companyId = companyId;
		this.companyNm = companyNm;
		this.memberId = memberId;
		this.approvalNum = approvalNum;
		this.approvalDate = approvalDate;
		this.useAmt = useAmt;
		this.useDate = useDate;
		this.insertDate = new Date();
		this.updateDate = new Date();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyNm() {
		return companyNm;
	}

	public void setCompanyNm(String companyNm) {
		this.companyNm = companyNm;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getApprovalNum() {
		return approvalNum;
	}

	public void setApprovalNum(int approvalNum) {
		this.approvalNum = approvalNum;
	}

	public String getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(String approvalDate) {
		this.approvalDate = approvalDate;
	}

	public int getUseAmt() {
		return useAmt;
	}

	public void setUseAmt(int useAmt) {
		this.useAmt = useAmt;
	}

	public String getUseDate() {
		return useDate;
	}

	public void setUseDate(String useDate) {
		this.useDate = useDate;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getHashNum() {
		return hashNum;
	}

	public void setHashNum(String hashNum) {
		this.hashNum = hashNum;
	}
	
	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	
}
