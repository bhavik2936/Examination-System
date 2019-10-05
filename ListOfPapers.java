import java.io.Serializable;
import java.util.Vector;

public class ListOfPapers extends ExamPaper implements Serializable {

	private static final long serialVersionUID = 5940166290730324489L;
	private Vector<ExamPaper> setOfPapers = new Vector<ExamPaper>();

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

	// add Paper as an Object
	void addPaper(ExamPaper examPaper) {
		setOfPapers.add(examPaper);
	}

	// remove Paper at particular index
	void removePaper(int index) {
		setOfPapers.remove(index);
	}

	// to get particular Question Paper
	public ExamPaper getPaper(int index) {
		return (setOfPapers.get(index));
	}

	// reset papers while loading previous changes
	protected void resetPapers(ListOfPapers examPapers) {
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
