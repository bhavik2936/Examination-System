import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;
import java.util.Vector;

public class ExamSetter implements Serializable{

	private static final long serialVersionUID = 5940166290730324489L;
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
	
	// reset papers while loading previous changes
	private void resetPapers(ExamSetter examPapers) {
		this.setOfPapers = examPapers.setOfPapers;
	}

	// load previous changes
	boolean loadChanges() {
		
		ObjectInputStream objectInputStream = null;
		FileInputStream fileInputStream = null;
		String fileName = "Papers.txt";
		ExamSetter examPapers;
		
		try {
			fileInputStream = new FileInputStream(fileName);
			objectInputStream = new ObjectInputStream(fileInputStream);
			examPapers = (ExamSetter)objectInputStream.readObject();
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
	
	// save changes permanently
	boolean commitChanges() {
		
		ObjectOutputStream objectOutputStream = null;
		FileOutputStream fileOutputStream = null;
		String fileName = "Papers.txt";
		
		try {
			fileOutputStream = new FileOutputStream(fileName);
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(this);
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
				System.out.println(this);
				break;
			case 4:
				System.out.println(this);
				System.out.println("Enter which question Paper to view: ");
				viewIndex = sc.nextInt();
				viewPaper(viewIndex);
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