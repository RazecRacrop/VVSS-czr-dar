package drinkshop.repository.file;

import drinkshop.domain.IngredientReteta;
import drinkshop.domain.Stoc;

public class FileStocRepository
        extends FileAbstractRepository<Integer, Stoc> {

    public FileStocRepository(String fileName) {
        super(fileName);
        loadFromFile();
    }

    @Override
    protected Integer getId(Stoc entity) {
        return entity.getId();
    }

    @Override
    protected Stoc extractEntity(String line) {
        String[] elems = line.split(";");

        int id = Integer.parseInt(elems[0]);
        String numeIngredient = elems[1];
        double cantitate = Double.parseDouble(elems[2]);

        IngredientReteta ingredientReteta = new IngredientReteta(numeIngredient,0.0);

        return new Stoc(id, ingredientReteta, cantitate);
    }

    @Override
    protected String createEntityAsString(Stoc entity) {
        return entity.getId() + ";" +
                entity.getIngredient() + ";" +
                entity.getCantitate() + ";";
    }
}