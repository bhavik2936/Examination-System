import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Scanner;
import java.util.Vector;

public class AttendExam implements Serializable {

	private ListOfPapers papers;

	// Default Constructor
	public AttendExam() {
		papers = new ListOfPapers();
		loadPapers();
	}

	// loads the list of exam papers
	public boolean loadPapers() {
		ObjectInputStream objectInputStream = null;
		FileInputStream fileInputStream = null;
		String fileName = "./QuestionPapers.dat";
		ListOfPapers examPapers;

		try {
			fileInputStream = new FileInputStream(fileName);
			objectInputStream = new ObjectInputStream(fileInputStream);
			examPapers = (ListOfPapers) objectInputStream.readObject();
			papers.resetClientPapers(examPapers);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileInputStream.close();
				objectInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	// to choose which exam paper to attend
	public void chooseExamPaper() {
		Scanner sc = new Scanner(System.in);
		int choosePaper;

		System.out.println(papers);
		System.out.println("Press 0 NOT to choose any paper");
		System.out.println("Choose from available Papers: ");
		choosePaper = sc.nextInt();
		if (choosePaper > 0)
			giveExam(choosePaper - 1);
	}

	// to attend exam of particular paper by One-by-One questions
	public void giveExam(int paperNumber) {
		QuestionPaper paper = papers.getPaper(paperNumber);
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

	// Main Serving Method
	public void serve() {
		Scanner sc = new Scanner(System.in);
		int choice = 1;

		do {
//			System.out.println("Press 1 to load list Question Papers");
			System.out.println("Press 2 to attend Exam");

			System.out.println("Press 0 to exit !");
			System.out.println("Enter your choice: ");
			choice = sc.nextInt();

			switch (choice) {
			case 1:
				break;
			case 2:
				chooseExamPaper();
				break;
			case 0:
				System.exit(0);
			default:
				System.out.println("You've entered invalid choice !");
				break;
			}
		} while (true);
	}

	public static void main(String[] args) {
//		AttendExam obj = new AttendExam();
		new AttendExam().serve();
	}

}
