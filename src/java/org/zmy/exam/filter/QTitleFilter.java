package org.zmy.exam.filter;

import org.zmy.exam.bean.Question;

public class QTitleFilter implements QuestionFilter {
	
	private String[] regex = null;
	
	public QTitleFilter() {
		
	}
	
	public QTitleFilter(String[] regex) {
		this.regex = regex;
	}

	public boolean doFilter(Question q) {
		
		for (int i = 0; i < regex.length; i++) {
			if (q.qStatement.matches(regex[i])) {
				return true;
			}
		}
		
		return false;
	}

}
