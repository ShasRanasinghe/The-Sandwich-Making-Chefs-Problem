import java.util.*;

/**
 * @author Shasthra Ranasinghe
 * @StudentNumber: 100867803
 * 
 * The table class is the one that does all the notifys and the waits
 * as it is the one that in shared between the chefs and agent threads
 * 
 * The agent places ingredients on the table
 * and the chefs take them if that chef can make a sandwich with what he/she has.
 *
 */
public class Table {
	
	private List<Ingredient> ingredients = new ArrayList<Ingredient>(); //hold the ingredients on the table
	private boolean empty = true;
	private int count = 0; //counts the number of sandwiches made


	/**
	 * @param ingredients
	 * 
	 * Synchronized method that places ingredients on the table if the table is empty
	 * if the able is not empty, then it would just wait for the table to be empty
	 * 
	 */
	public synchronized void placeOnTable(List<Ingredient> ingredients){
		while(!empty){
			try{
				wait();
			}catch(InterruptedException e){
				return;
			}
		}
		this.ingredients = ingredients;
	}
	
	/**
	 * This synchronized method is used by the agentThread to notify the chefs that
	 * ingredients have been placed on the table
	 * 
	 * The reason for creating a separate method for this was so that
	 * the print statements and sleep functions would all be in the agent class
	 * and once all that is done, the agent would notify the chefs
	 */
	public synchronized void notifyChefs(){
		empty = false;
		notifyAll();
	}
	
	/**
	 * @param ingredient
	 * @return a list of ingredients that was on the table
	 * 
	 * This synchronized method is invoked by the chefs to take the ingredients from the table 
	 * this is only done if the table is not empty and the chef can make a sandwich with the ingredients
	 * this method does'nt remove the items from the table, the chefs "borrow it"
	 * 
	 */
	public synchronized List<Ingredient> takeFromTable(Ingredient ingredient){
		List<Ingredient> items = new ArrayList<Ingredient>();
		while(empty || ingredients.contains(ingredient)){
			try{
				wait();
			} catch (InterruptedException e){
				return null;
			}
		}
		items.addAll(ingredients);
		return items;
	}
	
	/**
	 * This synchronized method is the one that actually removed the ingredients from the table
	 * just like the notifyChefs method, this method is used by the chefs to clear the table
	 * after it has made and ate the sandwich and notifys everyone that the table is empty.
	 * 
	 * once 20 sandwiches have been made, the program is ended
	 */
	public synchronized void clearTable(){
		ingredients.removeAll(ingredients);
		empty = true;
		count++;
		notifyAll();
		if(count>=20){//if its the last sandwich, end the threads
			System.out.println("The Agent is tired, i think we are done making sandwiches for today.");
			System.out.println("Winner: The programmer for awesome code :)");
			System.out.println("Thank you and come again");
			System.exit(1);
		}
	}
}
