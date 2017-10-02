//Phillip Miracle
//CSC 364-001
//Solves multiple constraint knapsack problem
import java.util.*;
import java.io.*;

public class Assign_2
{
	public static void main(String[] args) throws FileNotFoundException
	{
		// Here is code for opening a text file and reading from it,
		// and storing the information in an ArrayList of Project.
		// You would need to write the Project class yourself,
		// and also declare and initialize inputFileName (String).
		int number = 0;
		System.out.print("Enter a file name: ");
		Scanner input = new Scanner(System.in);
		String inputFileName = input.next();
		Scanner dataFile = new Scanner(new File(inputFileName));
		ArrayList<Project> projects = new ArrayList<>();
		projects.add(new Project(" ", 0, 0, 0));
		while (dataFile.hasNextLine())
		{
			String line = dataFile.nextLine();
			String[] split = line.split(" ");
			projects.add(new Project(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3])));
			number++;
		}
		dataFile.close();

		//gathers weights
		System.out.print("Enter Employee work weeks available: ");
		int weight = input.nextInt();
		System.out.print("Enter the Amount of Liquidity available: ");
		int weight_2 = input.nextInt();


		// Here is code for writing to a text file
		// You will need to declare and initialize outputFileName (String).
		System.out.print("Enter and output file name");
		String outputFileName = input.next();
		PrintWriter outputFile = new PrintWriter(outputFileName);
		KnapSack(weight, weight_2, number, projects, outputFile);
		outputFile.close();
	}


	private static class Project
	{
		public String name;
		public int hours;
		public int liquid;
		public int net;
		public Project(String string, int parseInt, int parseInt2, int parseInt3)
		{
			name = string;
			hours = parseInt;
			liquid = parseInt2;
			net = parseInt3;
		}


	}

	public static int KnapSack(int weight,int weight_2, int number, ArrayList<Project> projects, PrintWriter outputFile)
	{
		int[][][] profits = new int[weight + 1][weight_2 + 1][number + 1];
		for (int x = 0; x <= weight; x++)
		{
			profits[x][0][0] = 0;
		}

		for (int y = 0; y <= weight_2; y++)
		{
			profits[0][y][0] = 0;
		}

		for (int z = 0; z <= number; z++)
		{
			profits[0][0][z] = 0;
		}

		for (int k = 1; k <= number; k++)
		{
			for (int j = 1; j <= weight_2; j++)
			{
				for (int w = 1; w <= weight; w++)
				{
					if (projects.get(k).hours > w)
					{
						profits[w][j][k] = profits[w][j][k-1];
					}
					else if (projects.get(k).liquid > j)
					{
						profits[w][j][k] = profits[w][j][k-1];
					}
					else
					{
						profits[w][j][k]= max(projects.get(k).net + profits[w - projects.get(k).hours][j - projects.get(k).liquid][k - 1], profits[w][j][k-1]);

					}
				}
			}
		}

		//Stores the results of the knapsack
		ArrayList<Project> rec = new ArrayList<>();
		int count = 0;
		int row = weight;
		int col = weight_2;
		for (int i = number; i > 0; --i)
		{
			if (profits[row][col][i] != profits[row][col][i-1])
			{
				rec.add(new Project(projects.get(i).name, projects.get(i).hours, projects.get(i).liquid, projects.get(i).net));
				row = row - projects.get(i).hours;
				col = col - projects.get(i).liquid;
				count++;
			}
		}

		//Prints the results
		outputFile.println("Number of Projects available: " + number);
		outputFile.println("Employee work weeks available: " + weight);
		outputFile.println("Liquidity available: " + weight_2);
		outputFile.println("Number of projects chosen: " + count);
		outputFile.println("Total Profit: " + profits[weight][weight_2][number]);
		for (int t = 0; t < rec.size(); t++)
			outputFile.println(rec.get(t).name +" " + rec.get(t).hours + " " + rec.get(t).liquid +" " + rec.get(t).net +" ");
		return profits[weight][weight_2][number];
	}

	public static int max(int i, int j)
	{
        if(i > j)
            return i;
        else if (j > i)
            return j;
        else
        	return i;
	}
}

