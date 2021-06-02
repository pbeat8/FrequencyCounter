package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.FrequencyCounter;
import main.FrequencyPrinter;

class TestFrequencyPrinter {

	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	@BeforeEach
	public void setUp() {
	    System.setOut(new PrintStream(outputStreamCaptor));
	}
	
	@AfterEach
	public void tearDown() {
	    System.setOut(standardOut);
	}
	
	@Test
	void testConstructor() {
		String constructor_input="logic";
		FrequencyCounter fcounter=new FrequencyCounter(constructor_input);
		FrequencyPrinter fprinter=new FrequencyPrinter(fcounter);
		assertEquals(fcounter, fprinter.getCounter());
	}
	
	@Test
	void testPrintToConsole() {
		String constructor_input="logic";
		FrequencyCounter fcounter=new FrequencyCounter(constructor_input);
		FrequencyPrinter fprinter=new FrequencyPrinter(fcounter);
		fprinter.printToConsole("Logic was here!!");
		String[] lines=outputStreamCaptor.toString().trim().split("\n");
		assertEquals("{(logic), 5} = 1,00 (5/5)", lines[0].trim());
		assertEquals("Total frequency: 0,42 (5/12)", lines[1].trim());
	}
	
	@Test
	void testPrintToFile() {
		String constructor_input="logic";
		FrequencyCounter fcounter=new FrequencyCounter(constructor_input);
		FrequencyPrinter fprinter=new FrequencyPrinter(fcounter);
		try {
			String filename="test.txt";
			fprinter.printToFile("Logic was there!!", filename);
			
		    BufferedReader reader = new BufferedReader(new FileReader(filename));
		    ArrayList<String> lines=new ArrayList<>();
		    String currentLine;
		    while ((currentLine=reader.readLine())!=null)
		    	lines.add(currentLine);
		    reader.close();
			
		    assertEquals(2, lines.size());
			assertEquals("{(logic), 5} = 1,00 (5/5)", lines.get(0).trim());
			assertEquals("Total frequency: 0,38 (5/13)", lines.get(1).trim());
			File file= new File(filename); 
			file.delete();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//printToConsole
	//printToFile

}
