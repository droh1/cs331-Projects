//package hw6;

public class cargo {

	private int weight = 0;
	private int height = 0;
	private int location = 0;
	private boolean load = false;
	
	cargo(){
		weight = -1;
		height = -1;
		location = -1;
		load = false;
	}
	
	/**
	 * Constructor
	 * @param x weight of item
	 * @param y height of item
	 */
	cargo(int x, int y, int p){
		weight = x;
		height = y;
		location = p;
		load = true;
	}	
	
	public int getLocation(){
		return location;
	}
	
	/**
	 * getHeight
	 * @return return the height of item
	 */
	public int getHeight(){
		return height;
	}
	
	/**
	 * getCargo
	 * @return return the weight of cargo
	 */
	public int getCargo(){
		return weight;
	}
	
	/**
	 * getLoad
	 * @return return bool if this space has been used
	 */
	public boolean getLoad(){
		return load;
	}
	
	/**
	 * setLoad
	 * @param t boolean 
	 */
	public void setLoad(boolean t){
		load = t;
	}
	
	@Override
	public String toString(){
		if(load == true){
			return "Weight: " + getCargo() + "  Height: " + getHeight();
		}
		return ""; //Return empty string
	}
	
}
