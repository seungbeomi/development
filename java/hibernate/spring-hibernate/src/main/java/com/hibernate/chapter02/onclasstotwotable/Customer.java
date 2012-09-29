package com.hibernate.chapter02.onclasstotwotable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name="Customer")
@SecondaryTable(name="CustomerDetail")
public class Customer {

	@Id
	@GeneratedValue
	private int customerId;
	private String customerName;

	@Column(table="CustomerDetail")
	private String customerAddress;
	@Column(table="CustomerDetail")
	private int creditScore;
	@Column(table="CustomerDetail")
	private int rewardPoints;
	
}
