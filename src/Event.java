import java.util.ArrayList;

public class Event {
	private String eventName;
	private int eventTime;
	private ArrayList<Student> myStudentList; 

	public Event(String name, Integer timeBlock) {
		eventName = name;
		eventTime = timeBlock; 
	}

	public String getName()
	{
		return eventName;
	}

	public Integer getTimeBlock()
	{
		return eventTime; 
	}
	
	public ArrayList<Student> getStudentList()
	{
		return myStudentList; 
	}

}