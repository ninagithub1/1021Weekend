import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.awt.event.ActionEvent;
import java.util.*;
import java.io.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TestFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldLastName;
	private JTextField textFieldFirstName;
	private JTextField textFieldID;
	private TestFrame testFr;
	private ArrayList<Student> studentList = new ArrayList<Student>();
	private DefaultTableModel model = new DefaultTableModel(new String[] { "Last Name", "First Name", "Grade" }, 0);
	private DefaultTableModel globalStudentModel;
	private DefaultTableModel globalCompetitionsModel;

	private DatabaseFolder data = new DatabaseFolder();
	private CompetitionsDatabase compData = new CompetitionsDatabase();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try 
				{
					TestFrame frame = new TestFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * TASK LIST: Complete Roster of Students - 
	 * Use array list to add individual
	 * students - when a student is added, they should immediately show up on the
	 * complete roster of students table - use a text file to save the student data
	 * (might need to clear the array every time) COMPLETE!!!! YES!!!! 9/6/22
	 * 
	 * 
	 * Add Competition - create competition class, make an array of competition type
	 * - add to text file and write to text file every time COMPLETE (((: 9/26-
	 * (check to make sure this function works both for adding and altering previous
	 * competitions) - ensure that once a competition is added, it pops up
	 * effectively on the homepage table-
	 * 
	 * Reset Popup - ensure that button clears all competitions in BOTH the array
	 * list and text file
	 * 
	 * Competition Window - ensure that competitions show up effectively in the view
	 * competition window page - add new event (code and ensure this is added to
	 * seperate array list which shows up on view compeition window page)
	 * 
	 * Student Homepage - code remove window for students - code add event window
	 * for individual students dONE 10/14!- code collision errors windows
	 * 
	 * Final Fix-ups change all the method names to reflect either student or
	 * competition names
	 * 
	 * 
	 * 10/22: add in the sets!
	 * when an event is added to the file, 
	 * under each event there will be a list of students in the event (written as the integer values of their names)
	 * the students folder will only be used to make a table of students to select from
	 * when we add in the event it should be written to the file with the last digit being the time block (Anatomy3)
	 * this way, the list of events will be added to it's specific event set within the competition class. WHEN WE'RE ADDING THE 
	 * EVENTS TO THE SET, WE ALSO ADD THE STUDENTS TO THE SET
	 * if the designated integer value of the selected students is
	 * 
	 * 1) MAKE THE EVENTS SHOW UP THE COMP INFO PAGE AS A TABLE	(DONE! 10/19)
	 * 2) WHEN YOU DOUBLE CLICK THE EVENT, A NEW 'EVENT INFO' WINDOW SHOULD POP UP WITH THE LIST OF STUDENTS IN THE EVENT <--- make sure the table pops up effectively 10/20
	 * 3) ON THAT SAME EVENT INFO WINDOW, THERE SHOULD BE A BUTTON FOR ADDING A STUDENT TO THE EVENT
	 * 4) THE BUTTON SHOULD OPEN A NEW WINDOW WHICH HAS A TABLE THAT ALLOWS YOU TO DOUBLE CLICK ON THE STUDENT YOU WANT TO ADD TO THE EVENT
	 * 5) IF THE STUDENT IS ALREADY IN AN EVENT IN THE SAME TIME BLOCK, A WARNING POPUP SHOULD APPEAR, REMINDING THE USER THAT THERE IS A CONFLICT
	 * 		5A) THE SET INTERFACE HAS A BOOLEAN ADD METHOD (TO MAKE SURE DUPLICATE OBJECTS ARE NOT ADDED). 
	 * 		5B) IF THE ADD BUTTON RETURNS FALSE, PUT UP THE POPUP. OTHERWISE ADD THEM TO THE TIME BLOCK SET FOR THAT COMPETITION. 
	 * 6) METHODOLOGY FOR READING IN THE STUDENTS
	 * 		6A) EVERY TIME THE EVENT LIST IS READ IN FROM THE FILE, LOOK AT THE LAST LETTER TO GET THE EVENT BLOCK (ANATOMY3). THEN ADD IT ITS STUDENTS TO EVENT TO ONE OF 7 EVENT TIME BLOCK SETS
	 * 		6B) EVERY TIME A NEW EVENT IS ADDED, IT SHOULD BE ADDED WITH THE TIME BLOCK AT THE END (ANATOMY3 . 
	 */

	public TestFrame() 
	{

		testFr = this;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 748, 695);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		DefaultTableModel myStudentModel = new DefaultTableModel(new String[] { "Last Name", "First Name", "Grade" },
				0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;

			}
		};

		JTable table = new JTable(myStudentModel); // this is what tests is.
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(398, 45, 292, 253);
		contentPane.add(scrollPane);

		table.setModel(myStudentModel);
		globalStudentModel = myStudentModel;

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if (event.getClickCount() == 2) {
				}
			}
		});

		AddStudentWindow StudentAddFrame = new AddStudentWindow(studentList, model, testFr, data);
		StudentAddFrame.setVisible(false);

		JButton AddStudentWindow = new JButton("Add Student Window");
		AddStudentWindow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							StudentAddFrame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
						testFr.setVisible(false);
					}
				});

			}

		});

		DefaultTableModel myCompetitionsModel = new DefaultTableModel(new String[] { "Name", "Location", "Date" }, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;

			}
		};

		JTable competitionsTable = new JTable(myCompetitionsModel);
		JScrollPane CompScrollPane = new JScrollPane(competitionsTable);
		CompScrollPane.setBounds(128, 362, 441, 253);
		contentPane.add(CompScrollPane);

		competitionsTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent event) {
				if (event.getClickCount() == 2) {
					int row = competitionsTable.getSelectedRow();
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								CompInfoDisplay frame = new CompInfoDisplay(compData, row);
								frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
		});

		competitionsTable.setModel(myCompetitionsModel);
		globalCompetitionsModel = myCompetitionsModel;

		CompetitionWindow CompetitionAddFrame = new CompetitionWindow(testFr, compData);
		CompetitionAddFrame.setVisible(false);

		loadList();

		AddStudentWindow.setBounds(64, 156, 188, 40);
		contentPane.add(AddStudentWindow);

		JButton AddCompButton = new JButton("Add Competition");
		AddCompButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							CompetitionAddFrame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				testFr.setVisible(false);
			}
		});

		AddCompButton.setBounds(64, 72, 188, 40);
		contentPane.add(AddCompButton);
	}

	public void loadList() {
		while (globalStudentModel.getRowCount() > 0) { //A
			globalStudentModel.removeRow(0);
		}
		Scanner infile;
		ArrayList<Student> temp = new ArrayList<Student>();
		try {
			infile = new Scanner(new File("students.txt")); //B
			while (infile.hasNextLine()) {
				String last = infile.nextLine();
				String first = infile.nextLine();
				String grade = infile.nextLine();
				Student stu = new Student(last, first, grade); //C
				int index = stu.findLoc(stu, temp);
				temp.add(index, stu); //D
			}
		} catch (Exception error) {
			System.out.println(error.getMessage());
		}

		for (int i = 0; i < temp.size(); i++) 
		{
			Student current = temp.get(i);
			globalStudentModel.insertRow(i, current.getStudentInfo());
		}
		while (globalCompetitionsModel.getRowCount() > 0) { //1) clear the competitions row (A)
			globalCompetitionsModel.removeRow(0);
		}
		ArrayList<Competition> compList = new ArrayList<Competition>(); //2) create a new competition array list to read in the file
		Scanner infile2;
		try {
			infile2 = new Scanner(new File("competitions.txt")); //B
		
			while (infile2.hasNextLine()) {

				String CompetitionSingleNumberDate = infile2.nextLine();
				String myCompetititonName = infile2.nextLine();
				String myCompetitionLocation = infile2.nextLine();
				String year = CompetitionSingleNumberDate.substring(0, 4);
				String month = CompetitionSingleNumberDate.substring(4, 6);
				String day = CompetitionSingleNumberDate.substring(6, 8);
				String monthSeg = month.substring(0, 1);
				if (monthSeg.equals("0")) {
					month = month.substring(1);
				}
				String daySeg = day.substring(0, 1);
				if (daySeg.equals("0")) {
					day = day.substring(1);
				}
				Competition comp = new Competition(myCompetititonName, Integer.valueOf(month), Integer.valueOf(day),
						Integer.valueOf(year), myCompetitionLocation); //C
				int index = comp.findLoc(comp, compList); //find the location in the arraylist
				System.out.print(index);
				compList.add(index, comp); //add it to our temp array list //D

				if (infile2.hasNextLine()) {  //3) check to make sure there's an event list?
					String check = infile2.nextLine();
					if (check.equals("LOCATION INFO COMPLETE")) {
						while (infile2.hasNextLine()) 
						{
							String eventName = infile2.nextLine();
							if (eventName.equals("EVENT LIST COMPLETE"))
							{
								break; 
							}
							Competition tempCompetition = compList.get(index);
							tempCompetition.addEvent(new Event(eventName, 0));
							
						}
					}
				}
			}
			compData.setCompetitionData(compList);
		} catch (Exception error) {
			System.out.println(error.getMessage());
		}
		for (int i = 0; i < compList.size(); i++) {
			Competition current = compList.get(i);
			globalCompetitionsModel.insertRow(i, current.getCompetitionFormattedInfo());
		}
	}

	public ArrayList<Student> getStudentList() {
		return studentList;
	}

	public void setUneditable() {

	}

	public boolean isCellEditable(int row, int column) {
		return false;
	}
}