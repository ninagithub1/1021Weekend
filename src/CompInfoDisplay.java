import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CompInfoDisplay extends JFrame {

	private JPanel contentPane;
	private CompetitionsDatabase data;
	private Integer rowLoc;
	private Competition myCompetition;
	private CompInfoDisplay ourWindow;

	public CompInfoDisplay(CompetitionsDatabase compData, Integer location) {

		data = compData;
		rowLoc = location;
		ourWindow = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 869, 614);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		ArrayList<Competition> temp = compData.getCompetitionData();
		Competition ourComp = temp.get(location);
		myCompetition = ourComp;
		String referenceName = ourComp.getReferenceName();

		JLabel CompetitionName = new JLabel(referenceName);
		CompetitionName.setBounds(132, 23, 175, 14);
		contentPane.add(CompetitionName);

		JButton AddEvent = new JButton("Add New Event");
		AddEvent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							AddEvent frame = new AddEvent(data, rowLoc, ourWindow);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

			}
		});
		AddEvent.setBounds(52, 172, 133, 49);
		contentPane.add(AddEvent);

		refreshEvents(); 
		
		DefaultTableModel myEventModel = new DefaultTableModel(new String[] { "Time Block", "Event"},
				0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;

			}
		};
		
		JTable table = new JTable(myEventModel); // this is what tests is.
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(444, 245, 292, 253);
		contentPane.add(scrollPane);
		table.setModel(myEventModel);
		
		ArrayList<Event> eventsList = myCompetition.getEventList();
		System.out.print("the size is:" +eventsList.size());
		
		while (myEventModel.getRowCount() > 0) { //A
			myEventModel.removeRow(0);
		}
		
		String total = "";
		//ArrayList<Event> eventsList = myCompetition.getEventList();
		for (int i = 0; i < eventsList.size(); i++) {
			total += eventsList.get(i).getName();
			total += eventsList.get(i).getTimeBlock();
			total += "/n";
		}
		JLabel Holder = new JLabel(total);
		Holder.setBounds(209, 59, 46, 14);
		contentPane.add(Holder);
		
		for (int i=0; i<  eventsList.size(); i++)
		{
			Event current = eventsList.get(i);
			String name = current.getName();
			String time = current.getTimeBlock().toString();
			String[] info = new String[2];
			info[0]=name;
			info[1]= time; 
			myEventModel.insertRow(i, info);
		}
		
		
		
		//make each event have an array list of students, add the student
		
	}
	
	

	public void addEvents() {
		
		
	}
	
	public void refreshEvents() 
	{
		JLabel CompEvents = new JLabel("Competition Events:");
		CompEvents.setBounds(62, 59, 275, 14);
		contentPane.add(CompEvents);
		
		JButton AddAnatomy = new JButton("Add Student to 1st Event");
		AddAnatomy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//open the addStudentToEvent
				
					try {
						AddStudentToEvent frame = new AddStudentToEvent();
						frame.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				
			}
		});
		AddAnatomy.setBounds(213, 172, 211, 49);
		contentPane.add(AddAnatomy);

		if (myCompetition.getEventList().size() > 0) {
			String eventNames = "";
			for (int i = 0; i < myCompetition.getEventList().size(); i++) {
				eventNames +=  " "+ myCompetition.getEventList().get(i).getName();
			}
			JLabel FirstEvent = new JLabel(eventNames);
			FirstEvent.setBounds(232, 99, 101, 14);
			contentPane.add(FirstEvent);
		}
		else
		{
			String eventNames = "No events added yet";
			JLabel FirstEvent = new JLabel(eventNames);
			FirstEvent.setBounds(232, 99, 101, 14);
			contentPane.add(FirstEvent);
		}
	}
}