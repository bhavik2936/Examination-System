import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;
import java.util.Vector;

public class AttendExam {

	private static final long serialVersionUID = 5940166290730324489L;
	private Vector<ExamPaper> setOfPapers = new Vector<ExamPaper>();

	// To print Names of all Question Papers
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer("");
		for (int i = 0; i < setOfPapers.size(); i++) {
			str.append("Paper " + (i + 1) + ": " + setOfPapers.get(i).getQuestionPaperName() + "\n");
		}
		return str.toString();
	}

	// assigns papers to current class
	private void resetPapers(AttendExam examPapers) {
		this.setOfPapers = examPapers.setOfPapers;
	}

	// to choose which exam paper to attend
	public void chooseExamPaper() {
		Scanner sc = new Scanner(System.in);
		int choosePaper;

		System.out.println(this);
		System.out.println("Choose from available Papers: ");
		choosePaper = sc.nextInt();
		giveExam(choosePaper - 1);
	}

	// to attend exam of particular paper by One-by-One questions
	public void giveExam(int paperNumber) {
		ExamPaper paper = setOfPapers.get(paperNumber);
		Vector<Integer> answers = new Vector<Integer>();
		Scanner sc = new Scanner(System.in);
		int answer;

		for (int i = 0; i < paper.getQuestionPaperSize(); i++) {
			System.out.println("Que " + (i + 1) + ". " + paper.getQuestion(i));
			System.out.println("Enter your Answer: ");
			answer = sc.nextInt();
			answers.add(answer);
		}
		answer = paper.checkPaper(answers);
		System.out.println("Your score is: " + answer);
	}

	// loads the list of exam papers
	boolean loadPapers() {
		ObjectInputStream objectInputStream = null;
		FileInputStream fileInputStream = null;
		String fileName = "./QuestionPapers.dat";
		AttendExam examPapers;

		try {
			fileInputStream = new FileInputStream(fileName);
			objectInputStream = new ObjectInputStream(fileInputStream);
			examPapers = (AttendExam) objectInputStream.readObject();
			resetPapers(examPapers);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// Main Serving Method
	public void serve() {
		Scanner sc = new Scanner(System.in);
		int choice = 1;

		do {
			System.out.println("Press 1 to load list Question Papers");
			System.out.println("Press 2 to attend Exam");

			System.out.println("Press 0 to exit !");
			System.out.println("Enter your choice: ");
			choice = sc.nextInt();

			switch (choice) {
			case 1:
				loadPapers();
				break;
			case 2:
				chooseExamPaper();
				break;
			default:
				System.out.println("You've entered invalid choice !");
				break;
			}
		} while (true);
	}

	public static void main(String[] args) {

	}

}
