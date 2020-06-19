//package hw6;

public class person {
	private String name;
	private String id;
	private int height;
	private int location;
	private boolean load = false;
	
	
	person(){
		name = "TEMP";
		id = "FAKE ID";
		height = 0;
		load = false;
	}
	/**
	 * contructor
	 * @param na name of pass
	 * @param x id of pass
	 * @param y height of pass
	 */
	person(String na, String x, int y, int p){
		name = na;
		id = x;
		height = y;
		location = p;
		load = true;
	}
	
	public int getLocation(){
		return location;
	}
	
	/**
	 * getName
	 * @return return name of pass
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * getId
	 * @return return id of pass
	 */
	public String getId(){
		return id;
	}
	
	/**
	 * getHeight
	 * @return return height of pass
	 */
	public int getHeight(){
		return height;
	}
	
	/**
	 * setLoad
	 * @param t boolean 
	 */
	public void setLoad(boolean t){
		load = t;
	}
	
	/**
	 * toString
	 */
	@Override
	public String toString(){
		if(load == true){
		//return getId() + ":  Name: " + getName() + "  Height: " + getHeight();
		return "\tID: " + getId() + "  Name: " + getName() + " Age: " + getHeight();
		}
		return ""; //Return empty string
	}
	
}
