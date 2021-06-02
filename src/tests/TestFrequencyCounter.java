package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;

import main.FrequencyCounter;
import main.Group;

class TestFrequencyCounter {

	@org.junit.jupiter.api.Test
	void testConstuctorCharArray() {
		char[] constructor_input= {'l','o','g','i','c'};
		FrequencyCounter fcounter=new FrequencyCounter(constructor_input);
		assertArrayEquals(constructor_input, fcounter.getCharacters());
		assertEquals(0, fcounter.getLastCheckedLen());
		assertEquals(0, fcounter.getLastCheckedCount());
		assertTrue(fcounter.getLastCheckedItems().isEmpty());
	}

	@org.junit.jupiter.api.Test
	void testConstuctorString() {
		String constructor_input="logic";
		FrequencyCounter fcounter=new FrequencyCounter(constructor_input);
		assertArrayEquals(constructor_input.toCharArray(), fcounter.getCharacters());
		assertEquals(0, fcounter.getLastCheckedLen());
		assertEquals(0, fcounter.getLastCheckedCount());
		assertTrue(fcounter.getLastCheckedItems().isEmpty());
	}

	@org.junit.jupiter.api.Test
	void testSetCharactersCharArray() {
		char[] constructor_input= {'l','o','g','i','c'};
		FrequencyCounter fcounter=new FrequencyCounter(constructor_input);
		assertArrayEquals(constructor_input, fcounter.getCharacters());
		char[] new_input= {'g','l','o','b','a','l'};
		fcounter.setCharacters(new_input);
		assertArrayEquals(new_input, fcounter.getCharacters());
	}
	
	@org.junit.jupiter.api.Test
	void testSetCharactersString() {
		String constructor_input="logic";
		FrequencyCounter fcounter=new FrequencyCounter(constructor_input);
		assertArrayEquals(constructor_input.toCharArray(), fcounter.getCharacters());
		String new_input="global";
		fcounter.setCharacters(new_input);
		assertArrayEquals(new_input.toCharArray(), fcounter.getCharacters());
	}

	@org.junit.jupiter.api.Test
	void testCount() {
		String constructor_input="LOGIC";
		FrequencyCounter fcounter=new FrequencyCounter(constructor_input);
		String count_input="I am logical!)";
		ArrayList<SimpleEntry<Group, Integer>> result=fcounter.count(count_input);
		assertEquals(2, result.size());
		assertTrue(result.get(0).getValue()<result.get(1).getValue());
		assertEquals("i", result.get(0).getKey().getChars());
		assertEquals(1, result.get(0).getKey().getLength());
		assertEquals(Integer.valueOf(1), result.get(0).getValue());
		assertEquals("logic", result.get(1).getKey().getChars());
		assertEquals(7, result.get(1).getKey().getLength());
		assertEquals(Integer.valueOf(6), result.get(1).getValue());
		assertEquals(10, fcounter.getLastCheckedLen());
		assertEquals(7, fcounter.getLastCheckedCount());
	}

	@org.junit.jupiter.api.Test
	void testCountUpperCase() {
		String constructor_input="LOGIC";
		FrequencyCounter fcounter=new FrequencyCounter(constructor_input);
		String count_input="I AM LOGICAL";
		ArrayList<SimpleEntry<Group, Integer>> result=fcounter.count(count_input);
		assertEquals(2, result.size());
		assertTrue(result.get(0).getValue()<result.get(1).getValue());
		assertEquals("i", result.get(0).getKey().getChars());
		assertEquals(1, result.get(0).getKey().getLength());
		assertEquals(Integer.valueOf(1), result.get(0).getValue());
		assertEquals("logic", result.get(1).getKey().getChars());
		assertEquals(7, result.get(1).getKey().getLength());
		assertEquals(Integer.valueOf(6), result.get(1).getValue());
		assertEquals(10, fcounter.getLastCheckedLen());
		assertEquals(7, fcounter.getLastCheckedCount());
	}
	
	@org.junit.jupiter.api.Test
	void testCountBadString() {
		String constructor_input="LOGIC";
		FrequencyCounter fcounter=new FrequencyCounter(constructor_input);
		String count_input="$,%!.[";
		ArrayList<SimpleEntry<Group, Integer>> result=fcounter.count(count_input);
		assertEquals(0, result.size());
		assertEquals(0, fcounter.getLastCheckedLen());
		assertEquals(0, fcounter.getLastCheckedCount());
	}

	@org.junit.jupiter.api.Test
	void testCountEmpty() {
		String constructor_input="LOGIC";
		FrequencyCounter fcounter=new FrequencyCounter(constructor_input);
		String count_input="";
		ArrayList<SimpleEntry<Group, Integer>> result=fcounter.count(count_input);
		assertEquals(0, result.size());
		assertEquals(0, fcounter.getLastCheckedLen());
		assertEquals(0, fcounter.getLastCheckedCount());
	}
}
