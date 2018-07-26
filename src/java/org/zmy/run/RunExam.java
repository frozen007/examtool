package org.zmy.run;

import java.io.BufferedWriter;
import java.io.FileWriter;

import org.zmy.exam.bean.Question;
import org.zmy.exam.bean.QuestionStorage;
import org.zmy.exam.filter.QCustomFilterA;
import org.zmy.exam.filter.QListFilter;
import org.zmy.exam.filter.QSelectionFilter;
import org.zmy.exam.filter.QTitleFilter;
import org.zmy.util.DataFileReader;

public class RunExam {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String qFile = "D:/eclipse/workspace/ExamTool/data/traffic_quiz.dat";
		String qOut = "D:/eclipse/workspace/ExamTool/data/quiz_random.txt";
		String aOut = "D:/eclipse/workspace/ExamTool/data/answer.txt";
		String yours = "D:/eclipse/workspace/ExamTool/data/mine.txt";
		String includeFile = "D:/eclipse/workspace/ExamTool/data/filter/include.txt";
		String titleFilterFile = "D:/eclipse/workspace/ExamTool/data/filter/titleFilter.txt";
		String selectionFilterFile = "D:/eclipse/workspace/ExamTool/data/filter/selectionFilter.txt";
		QuestionStorage qs = new QuestionStorage(qFile);
		String[] includes = DataFileReader.getLines(includeFile);
		String[] titleFilters =  DataFileReader.getLines(titleFilterFile);
		String[] selectionFilters = DataFileReader.getLines(selectionFilterFile);
		if (includes.length != 0) {
			qs.addQFilter(new QListFilter(includes));
		}
		
		if (titleFilters.length != 0) {
			qs.addQFilter(new QTitleFilter(titleFilters));
		}
		
		if (selectionFilters.length != 0) {
			qs.addQFilter(new QSelectionFilter(selectionFilters));
		}
		
		//qs.addQFilter(new QCustomFilterA());
		
		Question[] rqs = qs.getRandom(1000);
		
		BufferedWriter qWriter = new BufferedWriter(new FileWriter(qOut));
		BufferedWriter aWriter = new BufferedWriter(new FileWriter(aOut));
		BufferedWriter myWriter = new BufferedWriter(new FileWriter(yours));
		
		for (int i = 0; i < rqs.length; i++) {
			qWriter.write(rqs[i].toString(String.valueOf(i + 1)));
			qWriter.newLine();
			
			aWriter.write(rqs[i].getAnswer() + "\t" + rqs[i].qNo);
			aWriter.newLine();
			
			myWriter.write(rqs[i].qNo+"\t");
			myWriter.newLine();
		}
		
		qWriter.close();
		aWriter.close();
		myWriter.close();
		
		System.out.println("Questions:[" + rqs.length + "]");

	}

}
