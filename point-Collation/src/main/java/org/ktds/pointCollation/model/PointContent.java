package org.ktds.pointCollation.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PointContent {

	private String id;
	private String companyId;
	private String companyNm;
	private String memberId;
	private int approvalNum;
	private Date approvalDate;
	private int useAmt;
	private Date useDate;	
}
