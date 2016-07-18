package com.irisflowers.relation;

import java.io.Serializable;

public class MTopicRelation implements Serializable{
	
	public MTopicRelation(String desc, int source, int target, double weight,
			String color) {
		super();
		this.desc = desc;
		this.source = source;
		this.target = target;
		this.weight = weight;
		this.color = color;
	}
	
	public MTopicRelation() {
		super();
	}
	
	private String desc;
	private int source;
	private int target;
	private double weight;
	private String color;

	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getColor() {
		return color;
	}
	
}
