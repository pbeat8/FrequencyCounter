package main;

public class Group {
	
	private String chars;
	private int length;
	
	public Group(String chars, int length) {
		super();
		this.chars = chars;
		this.length = length;
	}

	public String getChars() {
		return chars;
	}

	public int getLength() {
		return length;
	}

	public void setChars(String chars) {
		this.chars = chars;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	@Override 
	public boolean equals(Object o) {  
        if (o == this) { 
            return true; 
        } 
        if (!(o instanceof Group)) { 
            return false; 
        } 
        Group g = (Group) o;  
        return chars.equals(g.getChars())
                && Integer.compare(length, g.length) == 0; 
    }

}
