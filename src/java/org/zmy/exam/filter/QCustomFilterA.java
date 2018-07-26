package org.zmy.exam.filter;

import org.zmy.exam.bean.Question;

public class QCustomFilterA implements QuestionFilter {

	public boolean doFilter(Question q) {
		String[] tmp = q.qNo.split("\\.");
		if (Integer.parseInt(tmp[0]) >= 6) {
			return true;
		}
		return false;
	}

}
