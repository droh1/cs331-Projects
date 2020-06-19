//package hw6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class hw6 {
	public static void main(String[] args) throws FileNotFoundException
	{
		//int x = 0;
		Train master = new Train(); //Make a new train
		
		Scanner scan = null; 
		File file = new File("train_commands.txt"); //Open file
		scan = new Scanner(file); //Send file to scanner

		while (scan.hasNextLine()) // Scan until EOF
		{
			String line = scan.nextLine(); // Read line

			switch (line) {
			case "PRINT":
				System.out.println("\n\n" + master.toString());
				break;

			case "ADDCAR":
				System.out.println("ADD CAR");
				String type = scan.nextLine(); // Get car type
				int amount = scan.nextInt(); // Get max amount
				scan.nextLine(); // Heat the newline char
				master.addCar(type, amount);
				break;

			case "REMOVECAR":
				System.out.println("REMOVE CAR");
				int l = scan.nextInt();
				scan.nextLine(); // Eat new line car
				master.removeCar(l);
				break;

			case "LOAD":
				String check = scan.nextLine();
				if(check.equals("PERSON")){
					System.out.println("LOAD PERSON");
					int q = scan.nextInt();
					scan.nextLine(); // eat the newline char
					String id = scan.nextLine();
					String name = scan.nextLine();
					int height = scan.nextInt();
					scan.nextLine(); // eat the newline char
					master.addPerson(q, name, id, height);

				} else if (check.equals("CARGO")){
					System.out.println("LOAD CARGO");
					int q = scan.nextInt();
					scan.nextLine(); // eat the newline char
					int w = scan.nextInt();
					scan.nextLine(); // eat the newline char
					int e = scan.nextInt();
					scan.nextLine(); // eat the newline char
					int r = scan.nextInt();
					scan.nextLine(); // eat the newline char
					master.addCargo(q, w, e, r);
				}
				break;

			case "UNLOAD":
				System.out.println("UNLOAD");
				int k = scan.nextInt();
				scan.nextLine(); // eat the newline char
				String m = scan.nextLine();
				
				System.out.println("UNLOADTEST: " + k + " " + m);
				master.remove(k, m);
				break;

			case "DEPART":
				String dest = scan.nextLine();
				System.out.println("DEPART to " + dest);
				master.depart(dest);
				break;

			case "ARRIVE":
				System.out.println("ARIVE");
				master.arrive();
				break;

			case "SPEEDUP":
				System.out.println("SPEED UP");
				int g = scan.nextInt();
				scan.nextLine(); // Heat the newline char
				master.speedUp(g);
				break;

			case "SLOWDOWN":
				System.out.println("SLOW DOWN");
				int n = scan.nextInt();
				scan.nextLine(); // Heat the newline char
				master.slowDown(n);
				break;

			case "QUIT":
				System.out.println("Ending program");
				break;
			default:
				break;

			}

		}
			
		scan.close();
	}
}
