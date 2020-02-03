package com.bigdata.coreweb.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class StatisticsData implements Serializable {
	private String area;
	private int phoneNum;
	private Integer whNum;
	private Integer hbNum;
	private Integer oneNum;
	private Integer twoNum;
	private Integer threeNum;
}
