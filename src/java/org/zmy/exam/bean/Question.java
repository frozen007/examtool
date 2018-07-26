package org.zmy.exam.bean;

import org.zmy.util.StringUtil;

public class Question {
	public static final String Q_TYPE_SELECT = "SEL";
	public static final String Q_TYPE_TRUE_FALSE = "TOF";
	public static final char SELECT_START_INDEX_CHAR = 'A';
	public static final String Q_COMMENT_PATTERN = "#.+";
	public static final String Q_HEADER_PATTERN = "@[0-9]+\\.[0-9]+\\|[A-Z](\\|.+)?";
	public static final String Q_PROMPT_PATTERN = "%.+";
	public static final String Q_SECTION_HEADER_PATTERN = "@SECTION:.+";
	public static final String Q_SELECTION_PATTERN = "[a-zA-Z]\\..+";
	public static final String Q_SELECTION_LOAD_ADD = "ADD";
	public static final String Q_SELECTION_LOAD_UPD = "UPD";
	public static final String Q_SELECTION_LOAD_ERR = "ERR";
	
	public static final int SELECTION_NUM = 3;

	public String qNo = null;
	public String qType = null;
	public String qSection = null;
	public String qStatement = null;
	public String qRawAnswer = null;
	public String qPrompt = null;
	public String[] selection = null;
	
	public Question() {
		selection = new String[SELECTION_NUM];
	}

	public Question(String sectionHeader, String questionHeader) {
		this();
		if (sectionHeader != null
				&& sectionHeader.matches(Q_SECTION_HEADER_PATTERN)) {
			this.qSection = sectionHeader
					.substring(sectionHeader.indexOf(":") + 1);
		}
		if (questionHeader != null 
				&& questionHeader.matches(Q_HEADER_PATTERN)) {
			String[] arr = questionHeader.substring(1).split("\\|");
			if (arr.length == 2) {
				this.qNo = arr[0];
				this.qRawAnswer = arr[1];
			} else if (arr.length == 3) {
				this.qNo = arr[0];
				this.qRawAnswer = arr[1];
				this.qType = arr[2];
			}
		}
	}
	
	public Question(String no, String type, String section, String answer) {
		this();
		this.qNo = no;
		this.qType = type;
		this.qSection = section;
		this.qRawAnswer = answer;
	}
	
	private static int mapBySequence(String sel) {
		return sel.toUpperCase().charAt(0) - SELECT_START_INDEX_CHAR;
	}
	
	private static char unmapBySequence(int idx) {
		return (char)(SELECT_START_INDEX_CHAR + idx);
	}
	
	private static String getTypeName(String type) {
		if(Q_TYPE_SELECT.equals(type)) {
			return "Ñ¡Ôñ";
		} else if(Q_TYPE_TRUE_FALSE.equals(type)) {
			return "ÅÐ¶Ï";
		} else {
			return "unknown";
		}
	}
	
	public String getSelection(String sel) {
		if (this.selection == null || this.selection.length == 0) {
			return "null selection";
		}
		int i = Question.mapBySequence(sel) % this.selection.length;
		return this.selection[i];
	}
	
	public String getAnswer() {
		String rec = "unknown answer";
		if(Q_TYPE_SELECT.equals(this.qType)) {
			rec = this.qRawAnswer;
		} else if (Q_TYPE_TRUE_FALSE.equals(this.qType)) {
			if("F".equals(this.qRawAnswer)) {
				rec = "´í";
			} else if("T".equals(this.qRawAnswer)) {
				rec = "¶Ô";
			}
		}
		return rec;
	}
	
	public String loadSelection(String sel) {
		String rec = null;
		if (sel.matches(Q_SELECTION_PATTERN)) {
			int pointLoc = sel.indexOf(".");
			int i = Question.mapBySequence(sel.substring(0, pointLoc));
			if (this.selection[i] == null) {
				rec = Q_SELECTION_LOAD_ADD;
			} else {
				rec = Q_SELECTION_LOAD_UPD;
			}
			this.selection[i] = sel.substring(pointLoc + 1);
		} else {
			rec = Q_SELECTION_LOAD_ERR;
		}

		return rec;
	}
	
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append(this.qNo);
		result.append("\t[" + Question.getTypeName(this.qType) + "]");
		result.append("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + this.getAnswer());
		result.append(StringUtil.LINE_SEP);
		result.append("\t" + this.qStatement);
		result.append(StringUtil.LINE_SEP);
		if (Q_TYPE_SELECT.equals(this.qType)) {
			for (int i = 0; i < this.selection.length; i++) {
				result.append("\t" + Question.unmapBySequence(i) + "."
						+ this.selection[i]);
				result.append(StringUtil.LINE_SEP);
			}
		}
		return result.toString();
	}
	
	public String toString(String idx) {
		StringBuffer result = new StringBuffer();
		result.append(this.qNo);
		result.append("\t[" + Question.getTypeName(this.qType) + "]");
		result.append("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + this.getAnswer());
		result.append(StringUtil.LINE_SEP);
		result.append("\t" + this.qStatement + "\t" + idx);
		result.append(StringUtil.LINE_SEP);
		if (Q_TYPE_SELECT.equals(this.qType)) {
			for (int i = 0; i < this.selection.length; i++) {
				result.append("\t" + Question.unmapBySequence(i) + "."
						+ this.selection[i]);
				result.append(StringUtil.LINE_SEP);
			}
		}
		return result.toString();
	}
	
	

}
