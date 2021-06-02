package main;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FrequencyCounter fcounter=new FrequencyCounter("LOGIC");
		FrequencyPrinter fprinter=new FrequencyPrinter(fcounter);
		
		Scanner in=new Scanner(System.in);
		boolean continue_ui=true;
		System.out.println("Welcome to frequency checking application");
		while(continue_ui) {
			System.out.println("Do you want to:");
			System.out.println("1) Write the output to console");
			System.out.println("2) Write the output to file");
			System.out.println("3) Change searched characters");
			String choice=in.nextLine();
			
			String input;
			switch(Integer.parseInt(choice)) {
			case 1:
				System.out.println("Write your phrase for frequency checking:");
				input=in.nextLine();
				fprinter.printToConsole(input);
				break;
			case 2:
				try {
					System.out.println("Write your phrase for frequency checking:");
					input=in.nextLine();
					fprinter.printToFile(input, "output.txt");
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 3:
				System.out.println("Write your new characters for searching:");
				input=in.nextLine();
				fcounter.setCharacters(input);
				break;
			}
			System.out.println("Do you want to continue? (Y/N)");
			input=in.nextLine();
			if (input.toUpperCase().equals("N"))
				continue_ui=false;
		}
		in.close();
	}

}
