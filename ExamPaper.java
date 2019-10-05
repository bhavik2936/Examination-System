import java.io.Serializable;
import java.util.Scanner;
import java.util.Vector;

public class ExamPaper extends Question implements Serializable {

	private boolean isActive = true;
	private StringBuffer paperName;
	private Vector<Question> setOfQuestions = new Vector<Question>();

	// Default Constructor
	public ExamPaper() {
	}
	
	// set Paper using Interactive way
	public ExamPaper(String paperName) {
		this.paperName = new StringBuffer(paperName);
		setQuestion();
	}

	// Parameterized Constructor accepting array of Questions
	public ExamPaper(String paperName, Question[] questions) {
		this.paperName = new StringBuffer(paperName);
		for (Question question : questions) {
			setOfQuestions.add(question);
		}
	}

	// Prints Question Paper Directly
	// Under Construction
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer(paperName + "\n\n");
		for (int i = 0; i < setOfQuestions.size(); i++) {
			str.append("Que " + (i + 1) + ". " + setOfQuestions.get(i) + "\n");
		}
		return str.toString();
	}
	
	// gets Question Paper Name
	public String getQuestionPaperName() {
		return paperName.toString();
	}
	
	// is Paper to be shown or not
	public boolean isActive() {
		return isActive;
	}
	
	// gets size of Paper in form of total Questions
	public int getQuestionPaperSize() {
		return setOfQuestions.size();
	}

	// Sets Question manually with method
	void setQuestion() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the number of questions you want to add: ");
		int size = sc.nextInt();
		
		for (int i = 0; i < size; i++) {
			System.out.println("Question: " + (i+1));
			addQuestion();
		}
	}
	
	// gets particular Question of Paper
	Question getQuestion(int index) {
		return setOfQuestions.get(index);
	}

	// Adds Question by array of question
	void addQuestions(Question[] questions) {
		for (Question question : questions)
			setOfQuestions.add(question);
	}

	// Adds Question individually
	void addQuestion(Question question) {
		setOfQuestions.add(question);
	}

	// Adds Question interactively
	void addQuestion() {
		Scanner sc = new Scanner(System.in);

		String question;
		int sizeOfOptions;
		String[] options;
		int correctChoice;

		System.out.println("Enter question: ");
		question = new String(sc.nextLine());

		System.out.println("Enter number of options for question: ");
		sizeOfOptions = sc.nextInt();
		options = new String[sizeOfOptions];

		System.out.println("Enter options: ");
		sc.skip("[\\r\\n]+");
		for (int j = 0; j < sizeOfOptions; j++) {
			options[j] = new String(sc.nextLine());
		}

		System.out.println("Enter which option is correct: ");
		correctChoice = sc.nextInt();

		addQuestion(new Question(question, options, correctChoice));
	}

	// Removes Question by index
	// Under Construction
	void removeQuestion(int index) {
		setOfQuestions.remove(index - 1);
	}

	// Changes question by user requirement
	// Under Construction
	void changeQuestion(Question question) {
		String ques = null;
		String[] options = null;
		int correctChoice = 0;
		question.modifyQuestion(ques, options, correctChoice);
	}
	
	// to take and verify the answers
	int checkPaper(Vector<Integer> answers) {
		int marks = 0;
		for (int i=0; i<setOfQuestions.size(); i++) {
			if (setOfQuestions.get(i).getCorrectChoice() == answers.get(i))
				marks ++;
		}
		return marks;
	}

	public static void main(String[] args) {

//		String[] options = { "Charles Babbage", "Dennis Ritchie", "James Gosling" };
//
//		Question[] obj = new Question[2];
//		obj[0] = new Question("Who was the founder of JAVA1 language ?", options);
//		obj[1] = new Question("Who was the founder of JAVA2 language ?", options);
//
//		ExamPaper exm = new ExamPaper(obj);

		ExamPaper exm = new ExamPaper("General Questions!");

		System.out.println(exm);
	}

}