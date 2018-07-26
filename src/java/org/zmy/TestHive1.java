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
		Question q1 = new Question("@SECTION:各准驾车型通用试题","@1.1|B|SEL");
		q1.qStatement = "《中华人民共和国道路交通安全法》中所称的“道路”是指：";
		q1.loadSelection("A.高速公路、普通公路");
		q1.loadSelection("B.公路、城市道路和虽在单位管辖范围但允许社会机动车通行的地方，包括广场、公共停车场等用于公众通行的场所");
		q1.loadSelection("C.沥青公路、水泥公路、砂石公路");
		
		Question q2 = new Question("@SECTION:各准驾车型通用试题","@1.98|T|TOF");
		q2.qStatement = "道路交通标志分为警告标志、指示标志、禁令标志、指路标志、旅游区标志、道路施工安全标志和辅助标志。";
		
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
