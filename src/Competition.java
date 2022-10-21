import java.util.*;

public class Competition implements Comparable {
	private String myCompetititonName;
	private Integer myCompetitionMonth;
	private Integer myCompetitionDay;
	private Integer myCompetitionYear;
	private String myCompetitionLocation;
	private Integer totalDate;
	private ArrayList<Event> eventsList;

	public Competition(String name, Integer month, Integer day, Integer year, String location) {
		myCompetititonName = name;
		myCompetitionMonth = month;
		myCompetitionDay = day;
		myCompetitionYear = year;
		myCompetitionLocation = location;
		eventsList = new ArrayList<Event>();
		totalDate = 0; 
	}

	public int compareTo(Object other) // must return number from dates so that dates can be placed in order, combine/ all the numbers to get a full number, than sort via that (2022 + 6 + 17 = 20220612) MUST ACCOUNT FOR SINGLE DIGIT MONTHS AND DAYS ('6' should be 06)
	{
		Competition temp = (Competition) other;
		int compare = getCompetitionSingleNumberDate() - temp.getCompetitionSingleNumberDate();
		return compare; 
	}

	public void setCompetitionName(String comp) {
		myCompetititonName = comp;
	}

	public void setCompetitionMonth(Integer month) {
		myCompetitionMonth = month;
	}

	public void setCompetitionDay(Integer day) {
		myCompetitionDay = day;
	}

	public void setCompetitionYear(Integer year) {
		myCompetitionYear = year;
	}

	public void setCompetitionLocation(String location) {
		myCompetitionLocation = location;
	}

	public String getReferenceName()
	{
		return myCompetititonName;
	}

	public int getCompetitionSingleNumberDate() {
		String year = myCompetitionYear.toString();
		String month = "";
		String day = "";
		if (myCompetitionMonth > 0 && myCompetitionMonth < 10) 
		{
			month = "0" + myCompetitionMonth.toString();
		} 
		else 
		{
			month = myCompetitionMonth.toString();
		}
		if (myCompetitionDay > 0 && myCompetitionDay < 10) 
		{
			day = "0" + myCompetitionDay.toString();
		} 
		else 
		{
			day = myCompetitionDay.toString();
		}
		String total = year + month + day; 

		return Integer. valueOf(total); 
	}

	public int findLoc(Competition comp, ArrayList arr) 
	{

		for(int i =0; i <arr.size(); i++)
		{
			if(comp.compareTo(arr.get(i)) <0)
			{
				return i; 
			}
		}
		return arr.size(); 
	}

	public String[] getCompetitioniInfo() {
		String[] info = new String[3];
		info[0] = ((Integer)getCompetitionSingleNumberDate()).toString();
		info[1] = myCompetititonName;
		info[2] = myCompetitionLocation;
		return info;
	}

	public String[] getCompetitionFormattedInfo()
	{
		String[] info = new String[3];
		info[0]= myCompetititonName;
		info[1] = myCompetitionLocation;
		info[2]= myCompetitionMonth + "/" + myCompetitionDay + "/" + myCompetitionYear;
		return info; 
	}

	public void addEvent(Event temp)
	{
		//Event temp = new Event(name, time);
		eventsList.add(temp);
		
	}
	
	/*public void addEvent(int loc, String name)
	{
		Event temp = new Event(name, loc);
		eventsList.add(temp);
	}*/

	public ArrayList<Event> getEventList()
	{
		return eventsList;
	}
	///VERY IMPORTANT: THE ORDER OF THE STUFF ON THE TEXT FILE IS GOING IN ---> BIG DATE, COMPETITION NAME, COMPETITION LOCATION

	/*
	 * public int compareTo(Object other) { Student otherStu = (Student) other; int
	 * compare = myLastName.compareTo(otherStu.myLastName); if(compare ==0) { return
	 * myFirstName.compareTo(otherStu.myFirstName); } else { return compare; } }
	 */

}