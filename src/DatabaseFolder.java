import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;



public class DatabaseFolder {

	private ArrayList<Student> studentData; 
	String file; 

	public DatabaseFolder()
	{
		file = "students.txt";
		//private ArrayList<Compeition> (needs to be created)
		studentData = new ArrayList<Student>(); 
		studentData = loadFile(); 
		//System.out.println("here" + studentData);
	}

	private ArrayList<Student> loadFile() //7) creates a new array list to replicate what is currently in the file
	{
		Scanner infile;
		ArrayList<Student> complete = new ArrayList<Student>();
		try 
		{
			
			infile = new Scanner(new File(file));
			while(infile.hasNextLine())
			{
				String last = infile.nextLine();
				String first =infile.nextLine(); 
				String grade = infile.nextLine();
				Student stu = new Student(last, first, grade); 
				
				int index = stu.findLoc(stu, complete); 
				complete.add(index, stu); 
			}
		}
		catch(Exception error)
		{
			System.out.println(error.getMessage()); 
		}
		return complete; 
	}

	public ArrayList<Student> getStudentData()
	{
		return studentData; 
	}
	
	public void addStudent(Student stu) //4
	{
		  int index = stu.findLoc(stu, studentData); //5) find it's alphabetic location
		  studentData.add(index, stu); // 6) see loadfile method above, 
		  //8) ^^add the new student into a replicated array list at the right determined location
		  refreshStudentFile(); 
	}
	
	public void refreshStudentFile() //9)
	{
		{ 
		    try //10) CLEAR THE WHOLE FILE
		    {
		    FileWriter fw = new FileWriter("students.txt", false);
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
			FileWriter writer = new FileWriter("students.txt", true);
			for(Student stu : studentData)
			{
				String[] info = stu.getStudentInfo();
				for(String piece : info)
				{
					writer.write(piece + "\n");
				}
			}
			writer.close(); 
		}
		catch(Exception err)
		{
			System.out.println(err.getMessage()); 
		}
	} // 9) add into the text file the whole new replicated list (ERROR: IT APPEARS THE FILE WAS NEVER CLEARED.)
}
}


