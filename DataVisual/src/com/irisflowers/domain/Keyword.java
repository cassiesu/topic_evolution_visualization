package com.irisflowers.domain;

public class Keyword implements Comparable<Keyword> {

	private String text;
	private double size;
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}
	
	public Keyword(String word, double weight) {
		this.text = word;
		this.size = weight;
	}

	
	public int compareTo(Keyword kw) {
		Double sizeobj = Double.valueOf(size);
		return -sizeobj.compareTo(kw.size);			//这里可以破格写私有成员变量，加负号表示反方向排序
	}
	
	@Override
	public String toString() {
		return "Keyword [text=" + text + ", size=" + size + "]";
	}

}
