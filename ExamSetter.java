import java.util.Scanner;
import java.util.Vector;

public class ExamSetter {

	private Vector<ExamPaper> setOfPapers = new Vector<ExamPaper>();

	public ExamSetter() {
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer("");
		for (int i = 0; i < setOfPapers.size(); i++) {
			str.append("Paper " + (i + 1) + ": " + setOfPapers.get(i).getQuestionPaperName() + "\n");
		}
		return str.toString();
	}

	// to view particular Question Paper
	public void viewPaper(int index) {
		System.out.println(setOfPapers.get(index - 1));
	}

	// Main Serving Method
	public void serve() {
		Scanner sc = new Scanner(System.in);
		int choice = 1;
		int removeIndex;
		int viewIndex;

		do {
			System.out.println("Press 1 to add Question Paper");
			System.out.println("Press 2 to remove Question Paper");
			System.out.println("Press 3 to view list Question Papers");
			System.out.println("Press 4 to view full Question Paper");
//			System.out.println("Press 5 to edit Question Paper");

			System.out.println("Press 0 to exit !");
			System.out.println("Enter your choice: ");

			choice = sc.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Enter Exam Paper Name: ");
				sc.skip("[\\r\\n]+");
				setOfPapers.add(new ExamPaper(sc.nextLine()));
				break;
			case 2:
				System.out.println(this);
				System.out.println("Enter which question Paper to remove: ");
				removeIndex = sc.nextInt();
				setOfPapers.remove(removeIndex - 1);
				System.out.println("Paper Removed Sucessfully !");
				break;
			case 3:
				System.out.println(this);
				break;
			case 4:
				System.out.println(this);
				System.out.println("Enter which question Paper to view: ");
				viewIndex = sc.nextInt();
				viewPaper(viewIndex);
				break;
			case 5:
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

	// to choose which exam paper to attend
	public void chooseExamPaper() {
		Scanner sc = new Scanner(System.in);
		int choosePaper;

		for (int i = 0; i < setOfPapers.size(); i++) {
			if (setOfPapers.get(i).isActive())
				System.out.println("Paper " + (setOfPapers.indexOf(setOfPapers.get(i)) + 1) + ": "
						+ setOfPapers.get(i).getQuestionPaperName() + "\n");
		}
		System.out.println("Choose from available Papers: ");
		choosePaper = sc.nextInt();
		attendExam(choosePaper - 1);
	}

	// to take exam of particular paper by One-by-One questions
	public void attendExam(int paperNumber) {
		if (setOfPapers.get(paperNumber).isActive()) {
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
		} else {
			System.out.println("Invalid Choice !");
		}
	}

	public static void main(String[] args) {

		ExamSetter obj = new ExamSetter();
		obj.serve();
	}
}