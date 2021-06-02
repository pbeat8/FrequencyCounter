package main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Map;

public class FrequencyPrinter {
	
	private FrequencyCounter counter;

	public FrequencyPrinter(FrequencyCounter counter) {
		setCounter(counter);
	}
	
	public FrequencyCounter getCounter() {
		return counter;
	}

	public void setCounter(FrequencyCounter counter) {
		this.counter = counter;
	}
	
	public void printToConsole(String input) {
		ArrayList<SimpleEntry<Group, Integer>> result=counter.count(input);
		int last_checked_count=counter.getLastCheckedCount();
		int last_checked_len=counter.getLastCheckedLen();
		for(Map.Entry<Group, Integer> entry:result) {
			System.out.println(String.format("{(%s), %s} = %.2f (%d/%d)", 
					entry.getKey().getChars(), entry.getKey().getLength(), 
					(float) entry.getValue()/(float)last_checked_count, 
					entry.getValue(), last_checked_count ));
		}
		System.out.println(String.format("Total frequency: %.2f (%d/%d)", 
				(float)last_checked_count/(float)last_checked_len, 
				last_checked_count, last_checked_len));
	}
	
	public void printToFile(String input, String filename) throws IOException {
	    FileWriter fileWriter = new FileWriter(filename);
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	    ArrayList<SimpleEntry<Group, Integer>> result=counter.count(input);
		int last_checked_count=counter.getLastCheckedCount();
		int last_checked_len=counter.getLastCheckedLen();
		for(Map.Entry<Group, Integer> entry:result) {
			printWriter.printf("{(%s), %s} = %.2f (%d/%d)\n", 
					entry.getKey().getChars(), entry.getKey().getLength(), 
					(float) entry.getValue()/(float)last_checked_count, 
					entry.getValue(), last_checked_count );
		}
		printWriter.printf("Total frequency: %.2f (%d/%d)", 
				(float)last_checked_count/(float)last_checked_len, 
				last_checked_count, last_checked_len);	    
	    printWriter.close();
	}

}
