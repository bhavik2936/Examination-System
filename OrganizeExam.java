import java.util.Scanner;
import java.util.Vector;

public class OrganizeExam {

	// to choose which exam paper to attend
		public void chooseExamPaper() {
//			Scanner sc = new Scanner(System.in);
//			int choosePaper;
//
//			for (int i = 0; i < setOfPapers.size(); i++) {
//				if (setOfPapers.get(i).isActive())
//					System.out.println("Paper " + (setOfPapers.indexOf(setOfPapers.get(i)) + 1) + ": "
//							+ setOfPapers.get(i).getQuestionPaperName() + "\n");
//			}
//			System.out.println("Choose from available Papers: ");
//			choosePaper = sc.nextInt();
//			attendExam(choosePaper - 1);
		}

		// to attend exam of particular paper by One-by-One questions
		public void attendExam(int paperNumber) {
//			if (setOfPapers.get(paperNumber).isActive()) {
//				ExamPaper paper = setOfPapers.get(paperNumber);
//				Vector<Integer> answers = new Vector<Integer>();
//				Scanner sc = new Scanner(System.in);
//				int answer;
//
//				for (int i = 0; i < paper.getQuestionPaperSize(); i++) {
//					System.out.println("Que " + (i + 1) + ". " + paper.getQuestion(i));
//					System.out.println("Enter your Answer: ");
//					answer = sc.nextInt();
//					answers.add(answer);
//				}
//				answer = paper.checkPaper(answers);
//				System.out.println("Your score is: " + answer);
//			} else {
//				System.out.println("Invalid Choice !");
//			}
		}
	
	public static void main(String[] args) {

	}

}
