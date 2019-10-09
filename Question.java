import java.io.Serializable;

public class Question implements Serializable {

	private StringBuffer question;
	private StringBuffer[] options;
	private int correctChoice;

	// Default Constructor
	public Question() {
	}

	// Constructor accepting question and with custom option
	public Question(String question, String[] options, int correctChoice) {
		this.question = new StringBuffer(question);
		this.options = new StringBuffer[options.length];
		for (int i = 0; i < options.length; i++) {
			this.options[i] = new StringBuffer(options[i]);
		}
		this.correctChoice = correctChoice;
	}

	// Prints Question directly by object
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer(question + "\n");
		for (int i = 0; i < options.length; i++) {
			str.append((i + 1) + ") " + options[i] + "\n");
		}
		return str.toString();
	}

	// to get correct choice of an question
	int getCorrectChoice() {
		return correctChoice;
	}

	// Modify Question by passed parameters with custom choice
	public void modifyQuestion(String question, String[] options, int correctChoice) {
		this.question = new StringBuffer(question);
		for (int i = 0; i < options.length; i++) {
			this.options[i] = new StringBuffer(options[i]);
		}
		this.correctChoice = correctChoice;
	}

	public static void main(String[] args) {

		String[] options = { "Charles Babbage", "Dennis Ritchie", "James Gosling" };
		Question obj1 = new Question("Who was the founder of JAVA language ?", options, 3);

		System.out.println(obj1);
	}

}
