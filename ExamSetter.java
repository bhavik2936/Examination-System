import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;
import java.util.Vector;

public class ExamSetter implements Serializable {

	private static final long serialVersionUID = 5940166290730324489L;
	private Vector<ExamPaper> setOfPapers = new Vector<ExamPaper>();

	// Defalut Constructor
	public ExamSetter() {
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

	// to print total number of Question Papes
	int getCountOfPapers() {
		return setOfPapers.size();
	}
	
	// add Paper as an Object
	void addPaper(ExamPaper examPaper) {
		setOfPapers.add(examPaper);
	}

	// to view particular Question Paper
	public void viewPaper(int index) {
		System.out.println(setOfPapers.get(index - 1));
	}

	// reset papers while loading previous changes
	private void resetPapers(ExamSetter examPapers) {
		this.setOfPapers = examPapers.setOfPapers;
	}
	
	// get different paper for client
	ExamSetter getClientPaper() {
		ExamSetter clientPaper = new ExamSetter();
		for (int i=0; i<setOfPapers.size(); i++) {
			if (setOfPapers.get(i).isActive())
				clientPaper.addPaper(setOfPapers.get(i));
		}
		return clientPaper;
	}

	// load previous changes
	boolean loadChanges() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Are you sure want to Load Previous Changes ?");
		System.out.println("All the current changes will be delted !");
		System.out.println("Please enter 'Y' to proceed and 'N' to abort.");
		char ch = sc.next().charAt(0);

		if (ch == 'Y' || ch == 'y') {
			ObjectInputStream objectInputStream = null;
			FileInputStream fileInputStream = null;
			String fileName = "./AdminPapers.dat";
			ExamSetter examPapers;

			try {
				fileInputStream = new FileInputStream(fileName);
				objectInputStream = new ObjectInputStream(fileInputStream);
				examPapers = (ExamSetter) objectInputStream.readObject();
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
		} else {
			return false;
		}
	}

	// save changes permanently
	boolean commitChanges() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Are you sure want to Commit ?");
		System.out.println("Any Saved data will be Overrided !");
		System.out.println("Please enter 'Y' to proceed and 'N' to abort.");
		char ch = sc.next().charAt(0);

		if (ch == 'Y' || ch == 'y') {
			FileOutputStream fileOutputStream = null;
			ObjectOutputStream objectOutputStream = null;
			String adminFileName = "./AdminPapers.dat";
			String clientFileName = "./QuestionPapers.dat";

			try {
				fileOutputStream = new FileOutputStream(adminFileName);
				objectOutputStream = new ObjectOutputStream(fileOutputStream);
				objectOutputStream.writeObject(this);
				fileOutputStream = new FileOutputStream(clientFileName);
				objectOutputStream = new ObjectOutputStream(fileOutputStream);
				objectOutputStream.writeObject(getClientPaper());
				return true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		} else {
			return false;
		}
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
			System.out.println("Press 6 to Load Previous Changes");
			System.out.println("Press 7 to Commit Changes");

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
				if (getCountOfPapers() > 0) {
					System.out.println(this);
				} else {
					System.out.println("No Question Paper Exists till now !");
				}
				break;
			case 4:
				if (getCountOfPapers() > 0) {
					System.out.println(this);
					System.out.println("Enter which question Paper to view: ");
					viewIndex = sc.nextInt();
					viewPaper(viewIndex);
				} else {
					System.out.println("No Question Paper Exists till now !");
				}
				break;
			case 5:
				break;
			case 6:
				if (loadChanges())
					System.out.println("Loaded Previous changes Sucessfully !");
				else
					System.out.println("Loading Failed !");
				break;
			case 7:
				if (commitChanges())
					System.out.println("Committed changes Sucessfully !");
				else
					System.out.println("Commit Failed !");
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

		ExamSetter obj = new ExamSetter();
		obj.serve();
	}
}