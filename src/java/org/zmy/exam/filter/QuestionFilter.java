package org.zmy.exam.filter;

import org.zmy.exam.bean.Question;

public interface QuestionFilter {
	
	boolean doFilter(Question q);

}
