package drinkshop.domain;

public class Stoc {

    private int id;
    private IngredientReteta ingredientReteta;
    private double cantitate;

    public Stoc(int id, IngredientReteta ingredientReteta, double cantitate) {
        this.id = id;
        this.ingredientReteta = ingredientReteta;
        this.cantitate = cantitate;
    }

    // --- getters ---
    public int getId() {
        return id;
    }

    public IngredientReteta getIngredient() {
        return ingredientReteta;
    }

    public double getCantitate() {
        return cantitate;
    }



    // --- setters ---
    public void setIngredient(IngredientReteta ingredientReteta) {
        this.ingredientReteta = ingredientReteta;
    }

    public void setCantitate(double cantitate) {
        this.cantitate = cantitate;
    }




    @Override
    public String toString() {
        return ingredientReteta + " (" + cantitate + " / minim: " + ")";
    }
}