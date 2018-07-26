package org.zmy;

import org.zmy.exam.bean.Question;
import org.zmy.exam.bean.QuestionStorage;
import org.zmy.util.DataFileReader;

public class TestHive1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestHive1 t1 = new TestHive1();
		t1.testMain();
	}
	
	public void testMain() {
		// testDataFileReader();
		testQuestionStorage();
	}
	
	public void testQuestion() {
		Question q1 = new Question("@SECTION:��׼�ݳ���ͨ������","@1.1|B|SEL");
		q1.qStatement = "���л����񹲺͹���·��ͨ��ȫ���������Ƶġ���·����ָ��";
		q1.loadSelection("A.���ٹ�·����ͨ��·");
		q1.loadSelection("B.��·�����е�·�����ڵ�λ��Ͻ��Χ��������������ͨ�еĵط��������㳡������ͣ���������ڹ���ͨ�еĳ���");
		q1.loadSelection("C.���๫·��ˮ�๫·��ɰʯ��·");
		
		Question q2 = new Question("@SECTION:��׼�ݳ���ͨ������","@1.98|T|TOF");
		q2.qStatement = "��·��ͨ��־��Ϊ�����־��ָʾ��־�������־��ָ·��־����������־����·ʩ����ȫ��־�͸�����־��";
		
		System.out.println(q1);
		System.out.println("q1.qAnswer:"+q1.getAnswer());
		
		System.out.println(q2);
		System.out.println("q2.qAnswer:"+q2.getAnswer());

	}
	
	public void testDataFileReader() {
		String[] out = DataFileReader.getLines("D:/eclipse/workspace/ExamTool/data/test.txt");
		for(int i=0;i<out.length;i++) {
			System.out.println(out[i]);
		}
	}
	
	public void testQuestionStorage() {
		QuestionStorage qs = new QuestionStorage("D:/eclipse/workspace/ExamTool/data/quiz.dat");
		Question[] rqs = qs.getRandom(1000);
		// Question[] rqs = qs.getQArray();
		for (int i = 0; i < rqs.length; i++) {
			System.out.println(rqs[i]);
			System.out.println(rqs[i].getAnswer());
			System.out.println();
		}
	}
}
