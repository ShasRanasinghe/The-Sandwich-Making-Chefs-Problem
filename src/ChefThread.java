import java.util.*;

/**
 * @author Shasthra Ranasinghe
 * @StudentNumber: 100867803
 *
 * The chef thread simply takes in the ingredients on the table if there are any
 * and if the current chef can make a full sandwich with what it has and what is
 * on the table
 * 
 * Once it takes them, it makes a sandwich , eats it and clears the table
 * which will notify the agent to place 2 more ingredients for the chefs
 * to use
 *
 */
public class ChefThread implements Runnable{

	private static int threadSleepLength = SandwichMakingChefs.threadSleepLength; // used to slow down the program
	private Table table;
	Ingredient ingredient; // hold the one ingredient the chef has
	
	/**
	 * @param table - the table we are working on
	 * @param ingredient - the ingredient this chef has an infinite amount of
	 */
	public ChefThread(Table table, Ingredient ingredient){
		this.table = table;
		this.ingredient = ingredient;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 * 
	 * Overrides the run method
	 * loops forever and takes ingredients from the table
	 * if and only if the table is not empty and
	 * if the ingredients on the table allow the current chef
	 * to create a sandwich with what it already has
	 * 
	 */
	@Override
	public void run() {
		List<Ingredient> ingredients = new ArrayList<Ingredient>(); //hold the list of ingredients taken from the table
		for(;;){
			ingredients.addAll(table.takeFromTable(ingredient));
			System.out.println(Thread.currentThread().getName() + " is now making a sandwich...");
			try {
				Thread.sleep(threadSleepLength);
				System.out.println(Thread.currentThread().getName() + " is now eating the sandwich...");
				Thread.sleep(threadSleepLength);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ingredients.clear();
			System.out.println("The Agent can now place two new ingredients on the table\n");
			table.clearTable();
		}
		
	}

}
