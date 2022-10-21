import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class CompetitionsDatabase 
{
	private ArrayList<Competition> CompetitionData; 
	String file; 

	public CompetitionsDatabase()
	{
		file = "competitions.txt";
		CompetitionData = new ArrayList<Competition>(); 
		CompetitionData = loadFile(); //possibly eliminate this?
	}

	private ArrayList<Competition> loadFile() //7) creates a new array list to replicate what is currently in the file
	{ //ISSUE IS HERE
		Scanner infile;
		ArrayList<Competition> complete = new ArrayList<Competition>();
		try  //20220729
		{
			infile = new Scanner(new File(file));
			while(infile.hasNextLine())
			{

				/*if(infile.nextLine().equals("EVENT LIST COMPLETE"))
				{
					break;
				}*/

				String CompetitionSingleNumberDate = infile.nextLine();
				String myCompetititonName = infile.nextLine();
				String myCompetitionLocation = infile.nextLine();
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
						Integer.valueOf(year), myCompetitionLocation);
				int index = comp.findLoc(comp, complete); // these need to be switched
				complete.add(index, comp);

				if (infile.hasNextLine()) {
					String check = infile.nextLine();
					if (check.equals("LOCATION INFO COMPLETE")) {
						while (!infile.nextLine().equals("EVENT LIST COMPLETE")) {
							String eventName = infile.nextLine();
							Competition tempCompetition = complete.get(index);
							tempCompetition.addEvent(new Event(eventName, 0));//BIG ISSUE HERE
						}
					//	infile.nextLine();
					}
				}

			
			}
			CompetitionData=complete;
		}
		catch(Exception error)
		{
			System.out.println(error.getMessage()); 
		}
		return complete; 
	}

	public ArrayList<Competition> getCompetitionData()
	{
		return CompetitionData; 
	}
	
	public void setCompetitionData(ArrayList<Competition> comps)
	{
		CompetitionData = comps;
	}

	public void addCompetition(Competition comp) //4 (F)
	{
		  int index = comp.findLoc(comp, CompetitionData); //5) find it's alphabetic location
		  CompetitionData.add(index, comp); // 6) see loadfile method above, 
		  //8) ^^add the new student into a replicated array list at the right determined location
		 // System.out.print(CompetitionData.get(0).getReferenceName());
		  refreshStudentFile(); 
	}
	
	public void addEvent(int location, Event temp)
	{
		Competition comp = CompetitionData.get(location);
		comp.addEvent(temp); 
		refreshStudentFile(); 
	}

	public void refreshStudentFile() //9) (G)
	{
		{ 
		    try //10) CLEAR THE WHOLE FILE
		    {
		    FileWriter fw = new FileWriter("competitions.txt", false);
		    PrintWriter pw = new PrintWriter(fw, false);
		    pw.flush();
		    pw.close();
		    fw.close();
		    System.out.println("FILE SUCCESSFULLY CLEARED");
		    }
		    catch(Exception exception)
		    {
		        System.out.println("Exception have been caught");
		    } //REMEMBER TO CITE THIS 

		try //11) REWRITE TO THE FILE WITH THE NEW UPDATED ARRAY LIST!
		{
			FileWriter writer = new FileWriter("competitions.txt", true);
			for(Competition comp : CompetitionData)
			{
				String[] info = comp.getCompetitioniInfo();
				for(String piece : info)
				{
					writer.write(piece + "\n");
				} //
				writer.write("LOCATION INFO COMPLETE" + "\n");
				ArrayList<Event> events = comp.getEventList();//need to code this method
				for(int i =0; i<events.size(); i++)
				{
					String temp = events.get(i).getName();
					writer.write(temp + "\n");
				}
				writer.write("EVENT LIST COMPLETE" + "\n"); 
			}
			writer.close(); 
			System.out.print("we wrote to the file");
		}
		catch(Exception err)
		{
			System.out.println(err.getMessage()); 
		}
	} // 9) add into the text file the whole new replicated list (ERROR: IT APPEARS THE FILE WAS NEVER CLEARED.)
}



}