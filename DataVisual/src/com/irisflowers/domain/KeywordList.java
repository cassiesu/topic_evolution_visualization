package com.irisflowers.domain;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KeywordList {
	
	private String year;
	private int num;
	private List<Keyword> list;
	
	public KeywordList(String year){
		list = new ArrayList<Keyword>();
		num = 0;
		this.year = year;
	}
	
	public void addKeyword(Keyword kw){
		list.add(kw);
		Collections.sort(list);
		num++;
	}

	public int getNum() {
		return num;
	}

	public List<Keyword> getList() {
		return list;
	}
	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	public void changeKwWeight(String word, double weight){
		for(int i=0; i<num; i++) {
			if(word.equals(list.get(i).getText())) {
				list.get(i).setSize(list.get(i).getSize()+weight);
				return;
			}
		}
		list.add(new Keyword(word, weight));
		num++;
		return;
	}

	@Override
	public String toString() {
		return "KeywordList [num=" + num + ", list=" + list + "]";
	}
	
}
