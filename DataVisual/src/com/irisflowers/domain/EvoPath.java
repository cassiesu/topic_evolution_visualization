package com.irisflowers.domain;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.irisflowers.math.SD;

public class EvoPath implements Comparable<EvoPath> {

	private int id;
	private String tag;
	private EvoGroup evogroup;
	
	private double weightperyear[];
	private double weightfull1[];
	private double weightfull2[];
	private double weightfull3[];
	private double yearfull[];
	private String topicidperyear[];
	private double totalweight = 0;
	
	private int convergence;				//敛散性记录
	
	private String firstyear;
	private String[] years = new String[]{"2007","2008","2009","2010","2011","2012","2013","2014"};
	public String[] getYears() {
		return years;
	}
	public void setYears(String[] years) {
		this.years = years;
	}

	private double a=1, b=0.04;		//path比较的权重有待调整，根据具体数据
	private static DecimalFormat f1 = new DecimalFormat("0.0000");

	private Map<String,KeywordList> map = new HashMap<String, KeywordList>();
	
	public EvoPath(int id, EvoGroup evogroup) {
		this.id = id;
		this.evogroup = evogroup;
	}

	public void addMap(KeywordList kwlist){
		map.put(kwlist.getYear(), kwlist);
	}

	public int getId() {
		return id;
	}

	public double[] getWeightperyear() {
		return weightperyear;
	}

	public void setWeightperyear(double[] weightperyear) {
		this.weightperyear = weightperyear;
		boolean flag = false;
		for(int i=0;i<years.length;i++){
			this.totalweight += weightperyear[i];
			if(!flag && weightperyear[i]!=0){
				firstyear = years[i];
				flag = true;
			}
		}
		totalweight = Double.parseDouble(f1.format(totalweight));
	}

	public double getTotalweight() {
		return totalweight;
	}

	public String getFirstyear() {
		return firstyear;
	}

	public void setFirstyear(String firstyear) {
		this.firstyear = firstyear;
	}

	public EvoGroup getEvogroup() {
		return evogroup;
	}

	public void setEvogroup(EvoGroup evogroup) {
		this.evogroup = evogroup;
	}

	public Map<String, KeywordList> getMap() {
		return map;
	}
	
	public String[] getTopicidperyear() {
		return topicidperyear;
	}

	public void setTopicidperyear(String[] topicidperyear) {
		this.topicidperyear = topicidperyear;
	}

	@Override
	public String toString() {
		return "EvoPath [id=" + id + ", evogroup=" + evogroup
				+ ", weightperyear=" + Arrays.toString(weightperyear)
				+ ", topicidperyear=" + Arrays.toString(topicidperyear)
				+ ", totalweight=" + totalweight + ", firstyear=" + firstyear
				+ ", map=" + map + "]";
	}


	public void setYearfull(double yearfull[]) {
		this.yearfull = yearfull;
	}

	public double[] getYearfull() {
		return yearfull;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTag() {
		return tag;
	}

	public void setWeightfull1(double weightfull1[]) {
		this.weightfull1 = weightfull1;
	}

	public double[] getWeightfull1() {
		return weightfull1;
	}

	public void setWeightfull2(double weightfull2[]) {
		this.weightfull2 = weightfull2;
	}

	public double[] getWeightfull2() {
		return weightfull2;
	}

	public void setWeightfull3(double weightfull3[]) {
		this.weightfull3 = weightfull3;
	}

	public double[] getWeightfull3() {
		return weightfull3;
	}
	
	public void setConvergence(int convergence) {
		this.convergence = convergence;
	}

	public int getConvergence() {
		return convergence;
	}

	public int compareTo(EvoPath p) {
		
		//0、只考虑波动程度
		//return -Double.valueOf(SD.getSD(weightperyear)).compareTo(SD.getSD(p.weightperyear));
		
		//1、不考虑收敛发散互补
		double myweight = (Double.parseDouble(firstyear)-2007)*b + SD.getSD(weightfull3)*a; 			//时间越靠前，标准差越小，顺序越靠前
		double oweight = (Double.parseDouble(p.firstyear)-2007)*b + SD.getSD(p.weightfull3)*a; 	
		return -Double.valueOf(myweight).compareTo(oweight);			//注意是不是比反了==
		
		//2、考虑收敛发散互补？？？这里还没有神马想法==
		
		//return 0;
	}

}
