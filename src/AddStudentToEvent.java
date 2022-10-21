import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddStudentToEvent extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStudentToEvent frame = new AddStudentToEvent();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddStudentToEvent() {
		contentPane = new JPanel();
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
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					int row = table.getSelectedRow();	
					//need to make sure the student is added to the Event Student array list AND Written into the file
					
					//make a new line after the event list, write the students as their numerical values, add a dollar sign in between each one.
					
				}
			}
		});
		scrollPane.setBounds(310, 69, 292, 253);
		contentPane.add(scrollPane);
		table.setModel(myStudentModel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 684, 432);
		getContentPane().setLayout(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		
	
		while (myStudentModel.getRowCount() > 0) { //A
			myStudentModel.removeRow(0);
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
			myStudentModel.insertRow(i, current.getStudentInfo());
		}
	}

}
