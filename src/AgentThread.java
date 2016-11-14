import java.util.*;

/**
 * @author Shasthra Ranasinghe
 * @StudentNumber: 100867803
 * 
 * The Agent Thread has an infinite number of all three ingredients.
 * It will place two random ingredients on the table when the chefs
 * notify the agent that they are done making a sandwich with what
 * the agent provided before.
 *
 */
public class AgentThread implements Runnable{

	private static int threadSleepLength = SandwichMakingChefs.threadSleepLength; //just used to slow down the program
	private Table table;
	private List<Ingredient> ingredients = new ArrayList<Ingredient>();//holds the three ingredients
	
	/**
	 * @param table
	 * 
	 * When the agent is created, it takes in the table it will be working with and adds all
	 * all the ingredients to its list
	 * 
	 */
	public AgentThread(Table table){
		this.table = table;
		ingredients.addAll(Arrays.asList(Ingredient.values()));
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 * 
	 * Overrides the run method
	 * loops forever placing 2 random ingredients on the table
	 * 
	 */
	@Override
	public void run() { 
		List<Ingredient> randomIngredients;
		for(int i = 0;;){
			randomIngredients = generateRandomIngredients();
			table.placeOnTable(randomIngredients);
			try {
				Thread.sleep(threadSleepLength);
				System.out.println(Thread.currentThread().getName()+" is placing " 
						+ randomIngredients.get(0).getIngredientString() + " and " 
						+ randomIngredients.get(1).getIngredientString() + " on the table for sandwich number "
						+ ++i +"\n");
				Thread.sleep(threadSleepLength);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			table.notifyChefs();
		}
	}
	
	/**
	 * @return List<Ingredients> - a list of 2 random ingredients from the 3 the agent holds
	 * 
	 * This method randomly chooses two of the three ingredients and returns them in a list
	 * 
	 */
	private List<Ingredient> generateRandomIngredients(){
		List<Ingredient> ingredients = new ArrayList<Ingredient>(Arrays.asList(Ingredient.values()));
		Random random = new Random();
		List<Ingredient> randomIngredients = new ArrayList<Ingredient>();
		
		Ingredient firstIngredient = ingredients.get(random.nextInt(ingredients.size()));
		randomIngredients.add(firstIngredient);
		ingredients.remove(firstIngredient);
		randomIngredients.add(ingredients.get(random.nextInt(ingredients.size())));
		return randomIngredients;
	}

}
