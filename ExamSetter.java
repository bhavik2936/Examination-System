import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExamSetter {

	private ListOfPapers papers;

	// Default Constructor
	public ExamSetter() {
		papers = new ListOfPapers();
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
			ListOfPapers examPapers;

			try {
				fileInputStream = new FileInputStream(fileName);
				objectInputStream = new ObjectInputStream(fileInputStream);

				examPapers = (ListOfPapers) objectInputStream.readObject();
				papers.resetExamSetterPapers(examPapers);
				return true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					objectInputStream.close();
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return false;
		} else {
			return false;
		}
	}

	// append changes with current changes
	boolean appendChanges() {
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		FileOutputStream fileOutputStream = null;
		ObjectOutputStream objectOutputStream = null;

		ListOfPapers examPapers = null;
		String adminFileName = "./AdminPapers.dat";
		String clientFileName = "./QuestionPapers.dat";

		try {
			fileInputStream = new FileInputStream(adminFileName);
			objectInputStream = new ObjectInputStream(fileInputStream);

			if (new File(adminFileName).exists() && new File(clientFileName).exists()) {
				examPapers = (ListOfPapers) objectInputStream.readObject();

				objectInputStream.close();
				fileInputStream.close();
			}
			examPapers.addPapers(papers);
			fileOutputStream = new FileOutputStream(adminFileName);
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(examPapers);

			objectOutputStream.close();
			fileOutputStream.close();

			fileOutputStream = new FileOutputStream(clientFileName);
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(examPapers.getClientPaper());
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				objectOutputStream.close();
				fileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	// save changes by overriding current changes
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
				objectOutputStream.writeObject(papers);

				objectOutputStream.close();
				fileOutputStream.close();

				fileOutputStream = new FileOutputStream(clientFileName);
				objectOutputStream = new ObjectOutputStream(fileOutputStream);
				objectOutputStream.writeObject(papers.getClientPaper());
				return true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					fileOutputStream.close();
					objectOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
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
			System.out.println("Press 7 to Append Changes");
			System.out.println("Press 8 to Commit Changes (Overriding Current Changes)");

			System.out.println("Press 0 to exit !");
			System.out.println("Enter your choice: ");
			choice = sc.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Enter Exam Paper Name: ");
				sc.skip("[\\r\\n]+");
				papers.addPaper(new QuestionPaper(sc.nextLine()));
				break;
			case 2:
				System.out.println(papers);
				System.out.println("Enter which question Paper to remove: ");
				removeIndex = sc.nextInt();
				papers.removePaper(removeIndex - 1);
				System.out.println("Paper Removed Sucessfully !");
				break;
			case 3:
				if (papers.getCountOfPapers() > 0) {
					System.out.println(papers);
				} else {
					System.out.println("No Question Paper Exists till now !");
				}
				break;
			case 4:
				if (papers.getCountOfPapers() > 0) {
					System.out.println(papers);
					System.out.println("Press 0 NOT to choose any paper");
					System.out.println("Enter which question Paper to view: ");
					viewIndex = sc.nextInt();
					if (viewIndex > 0)
						System.out.println(papers.getPaper(viewIndex - 1));
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
				if (appendChanges())
					System.out.println("Appended changes Sucessfully !");
				else
					System.out.println("Changes Appendation Failed !");
				break;
			case 8:
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

		ExamSetter examSetter = new ExamSetter();
		try {
			examSetter.serve();
		} catch (InputMismatchException e) {
			System.out.println("Input mismatching !");
			System.out.println("Please try again !");
			examSetter.serve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}