
/**
 * @author Shasthra Ranasinghe
 * @StudentNumber: 100867803
 * 
 * SYSC 3303A Assignment 2 The Sandwich0making chef problem
 * 
 */
public class SandwichMakingChefs {

	public static int threadSleepLength = 500;
	private static Thread agentThread, chef_1, chef_2, chef_3;
	
	/**
	 * @param args
	 * Creates a table, agent and the 3 chefs.
	 * As it creates the chefs, it assigns one ingredient to each chef.
	 * The chefs are assigned based on the location of the enums therefore
	 * If one wanted to change the ingredients, one would just change the
	 * ingredient names in the enum class
	 */
	public static void main(String[] args) {
		Table table = new Table();
		System.out.println("The Table is placed");
		
		agentThread = new Thread(new AgentThread(table),"The Agent");
		chef_1 = new Thread(new ChefThread(table,Ingredient.values()[0]),
								"The " + Ingredient.values()[0].getIngredientString() + " Chef");
		chef_2 = new Thread(new ChefThread(table,Ingredient.values()[1]),
								"The " + Ingredient.values()[1].getIngredientString() + " Chef");
		chef_3 = new Thread(new ChefThread(table,Ingredient.values()[2]),
								"The " + Ingredient.values()[2].getIngredientString() + " Chef");
		agentThread.start();
		System.out.println("The Agent has entered the Arena");
		chef_1.start();
		System.out.println("The " + Ingredient.values()[0].getIngredientString() + " Chef has entered the Arena");
		chef_2.start();
		System.out.println("The " + Ingredient.values()[1].getIngredientString() + " Chef has entered the Arena");
		chef_3.start();
		System.out.println("The " + Ingredient.values()[2].getIngredientString() + " Chef has entered the Arena\n");
	}
}
