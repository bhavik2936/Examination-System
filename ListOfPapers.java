import java.io.Serializable;
import java.util.Vector;

public class ListOfPapers extends QuestionPaper implements Serializable {

	private static final long serialVersionUID = 1L;
	private Vector<QuestionPaper> setOfPapers = new Vector<QuestionPaper>();

	// Default Constructor
	public ListOfPapers() {
	}

	// To print Names of all Question Papers
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer("");
		for (int i = 0; i < setOfPapers.size(); i++) {
			str.append("Paper " + (i + 1) + ": " + setOfPapers.get(i).getQuestionPaperName() + "\n");
		}
		return str.toString();
	}

	// to print total number of Question Papers
	int getCountOfPapers() {
		return setOfPapers.size();
	}

	// add multiple Papers
	void addPapers(ListOfPapers examPapers) {
		for (QuestionPaper examPaper : examPapers.setOfPapers) {
			setOfPapers.add(examPaper);
		}
	}

	// add Paper as an Object
	void addPaper(QuestionPaper examPaper) {
		setOfPapers.add(examPaper);
	}

	// remove Paper at particular index
	void removePaper(int index) {
		setOfPapers.remove(index);
	}

	// to get particular Question Paper
	public QuestionPaper getPaper(int index) {
		return (setOfPapers.get(index));
	}

	// to shuffle questions of every paper
	private void shufflePapers() {
		for (QuestionPaper questionPaper : setOfPapers) {
			questionPaper.shufflePaper();
		}
	}

	// reset papers while loading previous changes
	protected void resetExamSetterPapers(ListOfPapers examPapers) {
		this.setOfPapers = examPapers.setOfPapers;
	}

	// reset and shuffle clients' papers only while loading previous changes
	protected void resetClientPapers(ListOfPapers examPapers) {
		examPapers.shufflePapers();
		this.setOfPapers = examPapers.setOfPapers;
	}

	// get different paper for client
	ListOfPapers getClientPaper() {
		ListOfPapers clientPaper = new ListOfPapers();
		for (int i = 0; i < setOfPapers.size(); i++) {
			if (setOfPapers.get(i).isActive())
				clientPaper.addPaper(setOfPapers.get(i));
		}
		return clientPaper;
	}

	public static void main(String[] args) {
	}

}
