package org.zmy.exam.bean;

import java.util.ArrayList;
import java.util.Random;

import org.zmy.exam.filter.QuestionFilter;
import org.zmy.util.DataFileReader;
import org.zmy.util.StringUtil;

public class QuestionStorage {
	
	private Question[] qArray = null;
	
	private ArrayList<QuestionFilter> qFilterList = new ArrayList<QuestionFilter>();
	
	public QuestionStorage() {
		
	}
	
	public QuestionStorage(String qFile) {
		qArray = QuestionStorage.loadQuestionsFile(qFile);
	}
	
	public static Question[] loadQuestionsFile(String qFile) {
		Question[] qs = null;
		String[] lines = null;
		lines = DataFileReader.getLines(qFile);
		
		String nowSectionHeader = null;
		String preQuestionType = null;
		StringBuilder nowStatement = new StringBuilder();
		
		ArrayList<Question> qList = new ArrayList<Question>();
		
		Question qTmp = new Question();
		
		for(int i=0;i<lines.length;i++) {
			String lineBuf = lines[i];
			if (lineBuf.matches(Question.Q_HEADER_PATTERN)) {
				qTmp.qStatement = nowStatement.toString();
				preQuestionType = qTmp.qType;
				qList.add(qTmp);
				qTmp = new Question(nowSectionHeader, lineBuf);
				nowStatement.delete(0, nowStatement.capacity() - 1);
				if (qTmp.qType == null) {
					qTmp.qType = preQuestionType;
				}
			} else if(lineBuf.matches(Question.Q_SELECTION_PATTERN)) {
				qTmp.loadSelection(lineBuf);
			} else if(lineBuf.matches(Question.Q_SECTION_HEADER_PATTERN)) {
				nowSectionHeader = lineBuf;
			} else if(lineBuf.matches(Question.Q_COMMENT_PATTERN)) {
				
			} else if (lineBuf.matches(Question.Q_PROMPT_PATTERN)) {
				
			} else {
				nowStatement.append(lineBuf);
			}
		}
		
		qTmp.qStatement = nowStatement.toString();
		qList.add(qTmp);
		qList.remove(0);
		
		qs = new Question[qList.size()];
		qs = qList.toArray(qs);
		
		return qs;
	}
	
	public Question[] getQArray() {
		return this.qArray;
	}
	
	public void addQFilter(QuestionFilter qf) {
		if (!qFilterList.contains(qf)) {
			this.qFilterList.add(qf);
		}
	}
	
	public Question[] getRandom(int count){
		int randsize = count;
		Question[] tmpArray = this.qArray;
		if (!this.qFilterList.isEmpty()) {
			tmpArray = this.getFilteredQuestions();
		}
		
		if (count > tmpArray.length) {
			randsize = tmpArray.length;
		}
		Question[] rec = new Question[randsize];
		ArrayList<String> tmpLst = new ArrayList<String>();
		Random rand = new Random(System.currentTimeMillis());
		for (int i = 0; i < tmpArray.length; i++) {
			tmpLst.add(String.valueOf(i));
		}
		for (int i = 0; i < randsize && i < tmpArray.length; i++) {
			int selected = Math.abs(rand.nextInt()) % tmpLst.size();
			rec[i] = tmpArray[Integer.parseInt(tmpLst.get(selected))];
			tmpLst.remove(selected);
		}
		
		return rec;
	}
	
	private Question[] getFilteredQuestions() {
		Question[] result = null;
		ArrayList<Question> list = new ArrayList<Question>();
		for (int i = 0; i < this.qArray.length; i++) {
			for (int j = 0; j < this.qFilterList.size(); j++) {
				QuestionFilter qf = this.qFilterList.get(j);
				if (qf.doFilter(this.qArray[i])) {
					list.add(this.qArray[i]);
					break;
				}
			}
		}
		result = new Question[list.size()];
		result = list.toArray(result);
		return result;
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		for(int i=0;i<this.qArray.length;i++) {
			buf.append(this.qArray[i].toString());
			buf.append(StringUtil.LINE_SEP);
		}
		return buf.toString();
		
	}

}
