package main;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

public class FrequencyCounter {
	
	private char[] characters;
	private String specialCharacters=" |!|\"|#|\\$|%|&|'|\\(|\\)|\\*|\\+|,|-|\\.|/|:|;|<|=|>|\\?|@|\\[|\\|\\]|^|_|`|\\{|\\||}|~";
	private int lastCheckedLen;
	private int lastCheckedCount;
	private ArrayList<SimpleEntry<Group, Integer>> lastCheckedItems;
	
	public FrequencyCounter(char[] characters) {
		setCharacters(characters);
		lastCheckedLen=0;
		lastCheckedCount=0;
		lastCheckedItems=new ArrayList<>();
	}
	
	public FrequencyCounter(String characters) {
		setCharacters(characters);
		lastCheckedLen=0;
		lastCheckedCount=0;
		lastCheckedItems=new ArrayList<>();
	}

	public char[] getCharacters() {
		return characters;
	}
	
	public void setCharacters(char[] characters) {
		this.characters = normalizeInput(characters);
	}
	
	public void setCharacters(String characters) {
		this.characters = normalizeInput(characters);
	}
	
	public int getLastCheckedLen() {
		return lastCheckedLen;
	}

	public int getLastCheckedCount() {
		return lastCheckedCount;
	}

	public ArrayList<SimpleEntry<Group, Integer>> getLastCheckedItems() {
		return lastCheckedItems;
	}

	public ArrayList<SimpleEntry<Group, Integer>> count(String input) {
		String input_lower=input.toLowerCase();
		lastCheckedLen=0;
		lastCheckedCount=0;
		lastCheckedItems.clear();
		
		String[] words=input_lower.split(specialCharacters);
		for(String word:words) {
			checkWord(word);
		}
		lastCheckedItems.sort(getEntryComparator());
		
		return lastCheckedItems;
	}
	
	private void checkWord(String word) {
		lastCheckedLen+=word.length();
		String chars_included="";
		int chars_count=0;
		for(char character:characters) {
			String modified=word.replace(Character.toString(character), "");
			if(modified.length() != word.length()) {
				chars_included+=Character.toString(character);
				chars_count+=word.length()-modified.length();
			}
		}
		if (chars_count>0) {
			lastCheckedCount+=chars_count;
			Group group=new Group(chars_included, word.length());
			if(lastCheckedItems.stream().filter(entry -> entry.getKey().equals(group)).count()!=0) {
				SimpleEntry<Group, Integer> entry=lastCheckedItems.stream().filter(e -> e.getKey().equals(group)).findFirst().get();
				int index=lastCheckedItems.indexOf(entry);
				lastCheckedItems.get(index).setValue(entry.getValue()+chars_count);
			}
			else
				lastCheckedItems.add(new SimpleEntry<Group, Integer>(group, chars_count));
		}
	}
	
	private char[] normalizeInput(String input) {
		return input.trim().replace(specialCharacters, "").toLowerCase().toCharArray();
	}
	
	private char[] normalizeInput(char[] input) {
		return normalizeInput(new String(input));
	}

	private Comparator<Map.Entry<Group, Integer>> getEntryComparator(){
		return new Comparator<Map.Entry<Group, Integer>>() {
			@Override
			public int compare(Map.Entry<Group, Integer> entry1, Map.Entry<Group, Integer> entry2)
			{
			  int CharNumberCompare = Integer.compare(entry1.getValue(),entry2.getValue());
			  int WordLengthCompare = Integer.compare(entry1.getKey().getLength(),entry2.getKey().getLength());
			  int CharValueCompare = entry1.getKey().getChars().compareTo(entry2.getKey().getChars());		
			  return (CharNumberCompare == 0) 
					  ? ((WordLengthCompare == 0) ? CharValueCompare : WordLengthCompare) 
					  : CharNumberCompare;
			}
		};
	}

}
