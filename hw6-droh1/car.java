import java.util.ArrayList;

//package hw6;

public class car {

	private int pass = 0;
	private int cag = 0;
	private int capacity;
	private String car;
	//private person[] people;
	public ArrayList<person> people = new ArrayList<person>();
	//private cargo[] cargoc; 
	public ArrayList<cargo> cargoc = new ArrayList<cargo>();
	
	/**
	 * Constructor
	 * @param type Type of car
	 * @param cap Max amount of items in car
	 */
	car(String type, int cap){ //Make a new car
		capacity = cap;
		car = type;
	}
		
	/**
	 * removeBox
	 * @return returns bool if the item was removed
	 */
	public boolean removeBox(){
		if(cargoc.size() == 0 && people.size() == 0){
			return true;
		}
		System.out.println("Car still has passangers or cargo\n");
		return false;
	}
	
	/**
	 * addpe
	 * @param name mame of pass
	 * @param id id of pass
	 * @param height height of pass
	 */
	public void addPe(String name, String id, int height){
		if(people.size() == capacity){ //Check if the car is full
			System.out.println("Car is full: unable to add");
			return;
		}
		
		for(int x = 0; x < people.size(); x++){ //check if person exists
			if(people.get(x).getId().equals(id)){
				System.out.println( id + " has already been added");
				return;
			}
		}
		people.add(new person(name, id, height, people.size()));
		//people[pass] = new person(name, id, height);
		//pass++;
	}
	
	/**
	 * addCargo
	 * @param x weight of cargo
	 * @param y height of cargo
	 */
	public void addCargo(int x, int y){
		if(cargoc.size() == capacity){
			System.out.println("Car is full: unable to add");
			return;
		}
		cargoc.add(new cargo(x,y, cargoc.size() + 1));
		
		//cargoc[cag] = new cargo(x, y);
		//cag++;		
	}
	
	/**
	 * remove
	 * @param test which car to remove from
	 * @param m location to remove
	 */
	public void remove(int test, String m){
		if(test == 1){
		
			for(int e = 0; e < capacity; e++){
				if(people.get(e).getId().equals(m)){//If the item is found
					people.get(e).setLoad(false);
					people.remove(e);
				}
			}
			//pass--;
		}else{
			int f = Integer.parseInt(m);
				
			for(int k = 0; k < cargoc.size(); k++){
				if(cargoc.get(k).getLocation() == f){
					cargoc.get(k).setLoad(false);
					cargoc.remove(k);
					return;
				}
			}
			System.out.println("\tCargo does not exist");
			return;
			
			/*
			if((cargoc.size() - 1) < f) { //If the size of cargo is larger then array
				System.out.println("\tCargo does not exist and cant be removed");
				return;
			}
			if(cargoc.get(f).getLoad() != false){ //If cargo at f is loaded
				cargoc.get(f).setLoad(false);
				cargoc.remove(f);
			}
			if(cargoc.get(f-1).getLocation() == f){
				cargoc.get(f).setLoad(false);
				cargoc.remove(f);
			}*/
			//cag--;
		}
	}
	
	/**
	 * getType
	 * @return return the type of car
	 */
	public String getType(){
		return car;
	}
	
	/**
	 * toString
	 */
	@Override
	public String toString(){
		String a = "";
		for(int x = 0; x < people.size(); x++){
			a += people.get(x).toString() + "\n";
		}
		
		for(int x = 0; x < cargoc.size(); x++){
			
			a = a + "\t" + (x + 1) + ": " + cargoc.get(x).toString() + "\n";
		}
		return a;
	}
}
