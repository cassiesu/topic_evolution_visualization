package com.irisflowers.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EvoGroup {
	private String category;
	private String id;
	private List<EvoPath> list;
	private int pathNum;
	
	public EvoGroup(String category, String id){
		this.category = category;
		this.id = id;
		list = new ArrayList<EvoPath>();
		pathNum = 0;
	}
	
	public void addEvoPath(EvoPath path){
		list.add(path);
		Collections.sort(list);
		pathNum++;
	}

	public String getCategory() {
		return category;
	}

	public String getId() {
		return id;
	}

	public List<EvoPath> getList() {
		return list;
	}

	public int getPathNum() {
		return pathNum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EvoGroup other = (EvoGroup) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}
