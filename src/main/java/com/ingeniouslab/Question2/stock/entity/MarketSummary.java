package com.ingeniouslab.Question2.stock.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="market_summary")
public class MarketSummary {

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@GeneratedValue(generator = "system-uuid")
	@Column(length=60)
    private String id;
	
	@Column(name="record_datetime", unique=true)
	private Date recordDatetime;
	
	@Column(name="num_gainers")
	private String numGainers;
	
	@Column(name="num_losers")
	private String numLosers;

	public Date getRecordDatetime() {
		return recordDatetime;
	}

	public void setRecordDatetime(Date recordDatetime) {
		this.recordDatetime = recordDatetime;
	}

	public String getNumGainers() {
		return numGainers;
	}

	public void setNumGainers(String numGainers) {
		this.numGainers = numGainers;
	}

	public String getNumLosers() {
		return numLosers;
	}

	public void setNumLosers(String numLosers) {
		this.numLosers = numLosers;
	}
	
}
