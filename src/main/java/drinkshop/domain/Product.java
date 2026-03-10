package drinkshop.domain;

public class Product {

    private int id;
    private String nume;
    private double pret;
    private CategorieBautura categorie;
    private TipBautura tip;
    private String descriere;
    private Reteta reteta;

    public Product(int id, String nume, double pret,
                  CategorieBautura categorie,
                  TipBautura tip,  String descriere, Reteta reteta) {
        this.id = id;
        this.nume = nume;
        this.pret = pret;
        this.categorie = categorie;
        this.tip = tip;
        this.descriere = descriere;
        this.reteta = reteta;
    }

    public Product(int id, String nume, double pret,
                   CategorieBautura categorie,
                   TipBautura tip, Reteta reteta) {
        this(id, nume, pret, categorie, tip, "",reteta);
    }

    public int getId() { return id; }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getNume() { return nume; }
    public double getPret() { return pret; }
    public CategorieBautura getCategorie() { return categorie; }

    public void setCategorie(CategorieBautura categorie) {
        this.categorie = categorie;
    }

    public TipBautura getTip() { return tip; }

    public void setTip(TipBautura tip) {
        this.tip = tip;
    }
    public void setNume(String nume) { this.nume = nume; }
    public void setPret(double pret) { this.pret = pret; }

    public Reteta getReteta() {
        return reteta;
    }

    public void setReteta(Reteta reteta) {
        this.reteta = reteta;
    }

    @Override
    public String toString() {
        String base =  nume + " (" + categorie + ", " + tip + ") - " + pret + " lei";
        if(descriere != null && !descriere.isBlank()){
            return  base + " [" + descriere + "]";
        }
        return base;
    }
}