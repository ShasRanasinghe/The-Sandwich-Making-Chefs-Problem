
/**
 * @author Shasthra Ranasinghe
 * @StudentNumber: 100867803
 * 
 * This enum class holds the definitions of the ingredients that is used in all the chefs and agent
 * 
 */
public enum Ingredient {
	BREAD("Bread"), PEANUT_BUTTER("Peanut Butter"), JAM("Jam");

    private String ingredientString;

    //Create ingredient from string
    Ingredient(final String ingredient) {
    	ingredientString = ingredient;
    }

    //return as string
    public String getIngredientString() {
        return ingredientString;
    }
}
