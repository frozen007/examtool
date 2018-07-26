package org.zmy.exam.filter;

import org.zmy.exam.bean.Question;

public class QSelectionFilter implements QuestionFilter {
	
	private String[] regex = null;
	
	public QSelectionFilter() {
		
	}
	
	public QSelectionFilter(String[] regex) {
		this.regex = regex;
	}

	public boolean doFilter(Question q) {
		if(Question.Q_TYPE_SELECT.equals(q.qType)) {
			String tmp = q.selection[0] + q.selection[1] + q.selection[2];
			for (int i = 0; i < regex.length; i++) {
				if(tmp.matches(regex[i])) {
					return true;
				}
			}
		}
		return false;
	}

}
