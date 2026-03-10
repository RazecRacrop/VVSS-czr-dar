package drinkshop.repository.file;

import drinkshop.domain.Product;
import drinkshop.domain.CategorieBautura;
import drinkshop.domain.Reteta;
import drinkshop.domain.TipBautura;
import drinkshop.repository.Repository;

public class FileProductRepository
        extends FileAbstractRepository<Integer, Product> {

    private Repository<Integer, Reteta> retetaRepository;

    public FileProductRepository(String fileName,Repository<Integer, Reteta> retetaRepository) {
        super(fileName);
        this.retetaRepository = retetaRepository;
        loadFromFile();
    }

    @Override
    protected Integer getId(Product entity) {
        return entity.getId();
    }

    @Override
    protected Product extractEntity(String line) {

        String[] elems = line.split(",");

        int id = Integer.parseInt(elems[0]);
        String name = elems[1];
        double price = Double.parseDouble(elems[2]);
        CategorieBautura categorie = CategorieBautura.valueOf(elems[3]);
        TipBautura tip = TipBautura.valueOf(elems[4]);

        Reteta reteta = retetaRepository.findOne(id);

        return new Product(id, name, price, categorie, tip,reteta);
    }

    @Override
    protected String createEntityAsString(Product entity) {
        return entity.getId() + "," +
                entity.getNume() + "," +
                entity.getPret() + "," +
                entity.getCategorie() + "," +
                entity.getTip();
    }
}