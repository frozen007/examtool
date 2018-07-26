package org.zmy.exam.filter;

import java.util.ArrayList;

import org.zmy.exam.bean.Question;


public class QListFilter implements QuestionFilter {
	
	private ArrayList<String> listTable = new ArrayList<String>();
	
	public QListFilter() {
		
	}
	
	public QListFilter(String[] qNoList) {
		for (int i = 0; i < qNoList.length; i++) {
			if(!listTable.contains(qNoList[i])) {
				listTable.add(qNoList[i]);
			}
		}
	}
	
	

	public boolean doFilter(Question q) {
		if (listTable.isEmpty()) {
			return true;
		}
		if (listTable.contains(q.qNo)) {
			return true;
		}
		return false;
	}

}
