package com.irisflowers.relation;

import java.io.Serializable;

import com.irisflowers.domain.EvoPath;
import com.irisflowers.math.SD;

public class MTopic implements Comparable<MTopic>{
	
	private String label;
	private int oldid;
	private int id;
	private String color;
	private String textcolor;
	private double size;
	private String desc;
	
	public MTopic(String label, int id, String color, String textcolor,
			double size, String desc) {
		super();
		this.label = label;
		this.oldid = id;
		this.color = color;
		this.textcolor = textcolor;
		this.size = size;
		this.desc = desc;
	}
	
	public MTopic() {
		super();
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getTextcolor() {
		return textcolor;
	}
	public void setTextcolor(String textcolor) {
		this.textcolor = textcolor;
	}
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int compareTo(MTopic o) {
		return label.compareTo(o.label);			//注意是不是比反了==
	}

	public void setOldid(int oldid) {
		this.oldid = oldid;
	}

	public int getOldid() {
		return oldid;
	}
	
}
