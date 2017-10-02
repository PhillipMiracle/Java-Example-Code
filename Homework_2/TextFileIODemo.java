import java.util.*;
import java.io.*;

public class TextFileIODemo
{
  public static void main(String[] args) throws FileNotFoundException
  {			
	// ...
	// Here is code for opening a text file and reading from it,
	// and storing the information in an ArrayList of Project.
	// You would need to write the Project class yourself,
	// and also declare and initialize inputFileName (String).
	Scanner dataFile = new Scanner(new File(inputFileName));
	ArrayList<Project> projects = new ArrayList<>();
	while (dataFile.hasNextLine())
	{
		String line = dataFile.nextLine();
		String[] split = line.split(" ");
		projects.add(new Project(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3])));
	}
	dataFile.close();

	// Here is code for writing to a text file
	// You will need to declare and initialize outputFileName (String).
	PrintWriter outputFile = new PrintWriter(outputFileName);
	outputFile.println("Hello text file!");
	outputFile.close();
	// ...
  }
  
  private static class Project {
  	// ...
  }
}