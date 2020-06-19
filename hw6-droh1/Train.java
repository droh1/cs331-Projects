import java.util.ArrayList;

//package hw6;


public class Train {
	private int speed = 0;
	//private int x = 0;
	private int min = 0;
	private int max = 0;
	private int maxCars = 0;
	private int cars = 0;
	private String city;
	//private car[] carArr;
	private ArrayList<car> trainCars = new ArrayList<car>();
	private boolean depart = false;
	private String destination;
	
	/**
	 * Default constructor
	 */
	Train(){
		min = 10;
		max = 50;
		city = "Boston";
		depart = false;
		cars = 0;
		maxCars = 3;
	}
	
	
	/**
	 * Overloaded
	 * @param Starting city
	 * @param slowest speed of train
	 * @param fastest speed of train
	 * @return 
	 */
	Train(String origin, int mins, int maxs){
		min = mins;
		max = maxs;
		city = origin;
		depart = false;
		cars = 0;
	}
	
	/**
	 * Add a car
	 * @param Type of car to add
	 * @param Capacity of car
	 */
	public void addCar(String type, int x){
		//carArr[x] = new car(type, x);
		trainCars.add(new car(type,x));
		cars++;
	}
	
	/**
	 * remove car
	 * @param x the car to remove
	 */
	public void removeCar(int x){
		if(getDepart() == true){
			System.out.println("\tYou cant throw people/cargo out while the train is moving");
			return;
		}
		if(trainCars.size() < x){
			System.out.println("Car does not exist and cant be removed");
			return;
		}
		if(trainCars.size() != 0){
			boolean check = trainCars.get(x).removeBox();
			
			if(check == false){ //Check if the the car was removed properlly 
				for(int j = 0; x < trainCars.size(); x++){
					System.out.println("\tCar " + (j + 1) + " still has people/cargo inside");
				}
				return;
			}
		}
		trainCars.remove(x);
		cars--;
	}
	
	
	/**
	 * Add pass
	 * @param Location in the car to add
	 * @param Name of pass
	 * @param Id of pass
	 * @param Height of pass
	 */
	public void addPerson(int q, String name, String id, int height){
		if(q > trainCars.size()){ //If the cars in the array is smaller then the one requested
			System.out.println("Cant not add person to car\n");
			return;
		}
		
		if(trainCars.get(q).getType().equals("PERSON")){
			trainCars.get(q).addPe(name,  id, height);
		}
		
		System.out.println("This car cant hold passngers\n");
		return;
		}
	
	/**
	 * Add cargo
	 * @param Which car to add 
	 * @param Where to add in car
	 * @param Wiegh of the car
	 * @param Height of the cargo
	 */
	public void addCargo(int q, int w, int e, int r){
		if(q > trainCars.size()){ //If the cars are wrong
			System.out.println("Cant not add this cargo\n");
			return;
		}
		if(trainCars.get(q).getType().equals("CARGO")){
			trainCars.get(q).addCargo(e, r);
		}
		
		System.out.println("This car cant hold cargo\n");
	}
	
	/**
	 * Remove
	 * @param Car to remove at
	 * @param Location to remove
	 */
	public void remove(int k, String m){
		if(getDepart() == true){
			System.out.println("\tYou cant throw people/cargo out while the train is moving");
		}
		if(k > trainCars.size()){
			System.out.println("Car does not exist");
			return;
		}
		if(trainCars.get(k).getType().equals("PERSON")){
			trainCars.get(k).remove(1, m);//Remove person
		}else{
			trainCars.get(k).remove(0, m);//Remove cargo
		}
	}
	
	
	public boolean getDepart(){
		return depart;
	}
	/**
	 * Depart
	 * @param Location the train will go to
	 */
	public void depart(String dest){
		depart = true;
		destination = dest;
		speed = min;
	}
	
	/**
	 * Arrive
	 */
	public void arrive(){
		city = destination;
		depart = false;
		speed = 0;
	}
	
	/**
	 * Speed up
	 * @param Amount to increase the speed by
	 */
	public void speedUp(int spd){
		setSpeed(spd);
	}
	
	/**
	 * Slow down
	 * @param Amount to decrease the speed by
	 */
	public void slowDown(int spd){
		setSpeed(-spd);
	}
	
	/**
	 * setSpeed
	 * @param Speed to increase or decrease by
	 */
	public void setSpeed(int x){
		if(depart == false){
			System.out.println("\tTrain has not departed yet");
			return;
		}
		if(speed + x > max){
			System.out.println("\tUnable to go that fast");
			return;
		}
		if(speed + x < min){
			System.out.println("\tUnable to go that slow, might as well walk then");
			return;
		}
		speed = speed + x;
		System.out.println("Speed changed to: " + getSpeed());
	}
	
	/**
	 * getSpeed
	 * @return The current speed of the train
	 */
	public int getSpeed(){
		return speed;
	}
	
	/**
	 * getStopped
	 * @return String that returns city
	 */
	public String getStopped(){
		if(depart == false){
			return "Stopped in ";
		}
		return "Travaling to ";
	}
	
	/**
	 * getCity
	 * @return String that reutrns current sity
	 */
	public String getCity(){
		return city;
	}
	
	/**
	 * getCars
	 * @return returns the amount of cars on the train
	 */
	public int getCars(){
		return cars;
	}
	
	/**
	 * getMaxCars
	 * @return returns the amount of cars possible
	 */
	public int getMaxCars(){
		return maxCars;
	}
	
	/**
	 * toString
	 */
	@Override
	public String toString(){
		String result = "PRINT\n" + "Train Status\n";
		
		result += "---------------------------------------------------\n";
		result = result + "Current Speed: " + speed + "\nMinimum Speed: " + min + "\nMaximum Speed: " + max
				+ "\nCurrent Position: " + getStopped() + getCity() + "\n" + "Current Number of Boxcars: " +
				getCars() + "\nMaximum Number of Boxcars: " + getMaxCars() + "\n"; 
		
		for(int i = 0; i < trainCars.size(); i++){
			result = result + "BoxCar: " + i + "\n" + "\tcomments:\n" + trainCars.get(i).toString() + "\n";
		}
		
		result += "------------------End of Status--------------------\n";
		
		return result;
	}	
	
}

	