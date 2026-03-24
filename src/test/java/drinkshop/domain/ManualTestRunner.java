package drinkshop.domain;

import drinkshop.service.validator.ProductValidator;
import drinkshop.service.validator.ValidationException;

import java.util.ArrayList;

public class ManualTestRunner {
    public static void main(String[] args) {
        ProductValidator validator = new ProductValidator();
        Reteta dummyReteta = new Reteta(1, new ArrayList<>());
        int passed = 0;

        System.out.println("=== START MANUAL TEST RUNNER (LAB02 - 8 CAZURI) ===\n");

        // --- TESTE POZITIVE (4 CAZURI) ---

        // TC_01: Pozitiv EPV - Pret valid (clasa medie)
        try {
            Product p1 = new Product(1, "Americano", 12.0, CategorieBautura.CLASSIC_COFFEE, TipBautura.WATER_BASED, "Cafea", dummyReteta);
            validator.validate(p1);
            System.out.println("[OK] TC_01: Produs valid (EPV) acceptat."); passed++;
        } catch (Exception e) { System.out.println("[FAIL] TC_01"); }

        // TC_02: Pozitiv BVA - Pret limita minima valida (0.01)
        try {
            Product p2 = new Product(2, "Apa", 0.01, CategorieBautura.JUICE, TipBautura.WATER_BASED, "Apa", dummyReteta);
            validator.validate(p2);
            System.out.println("[OK] TC_02: Limita minima pret (BVA) acceptata."); passed++;
        } catch (Exception e) { System.out.println("[FAIL] TC_02"); }

        // TC_03: Pozitiv EPV - Categorie diferita (MILK_COFFEE)
        try {
            Product p3 = new Product(3, "Latte", 15.5, CategorieBautura.MILK_COFFEE, TipBautura.DAIRY, "Lapte", dummyReteta);
            validator.validate(p3);
            System.out.println("[OK] TC_03: Categorie Milk Coffee (EPV) acceptata."); passed++;
        } catch (Exception e) { System.out.println("[FAIL] TC_03"); }

        // TC_04: Pozitiv BVA - ID minim valid (1)
        try {
            Product p4 = new Product(1, "Smoothie", 20.0, CategorieBautura.SMOOTHIE, TipBautura.PLANT_BASED, "Fructe", dummyReteta);
            validator.validate(p4);
            System.out.println("[OK] TC_04: ID minim 1 (BVA) acceptat."); passed++;
        } catch (Exception e) { System.out.println("[FAIL] TC_04"); }


        // --- TESTE NEGATIVE (4 CAZURI) ---

        // TC_05: Negativ EPV - Pret negativ
        try {
            Product p5 = new Product(4, "Invalid", -1.0, CategorieBautura.SPECIAL_COFFEE, TipBautura.POWDER, "Eroare", dummyReteta);
            validator.validate(p5);
            System.out.println("[FAIL] TC_05: Trebuia sa arunce ValidationException la pret negativ.");
        } catch (ValidationException e) {
            System.out.println("[OK] TC_05: Pret negativ (EPV) respins corect."); passed++;
        }

        // TC_06: Negativ BVA - Pret zero (frontiera invalida)
        try {
            Product p6 = new Product(5, "Freebie", 0.0, CategorieBautura.ICED_COFFEE, TipBautura.WATER_BASED, "Gratis", dummyReteta);
            validator.validate(p6);
            System.out.println("[FAIL] TC_06: Trebuia sa arunce ValidationException la pret 0.");
        } catch (ValidationException e) {
            System.out.println("[OK] TC_06: Pret zero (BVA) respins corect."); passed++;
        }

        // TC_07: Negativ EPV - Nume vid
        try {
            Product p7 = new Product(6, "", 10.0, CategorieBautura.BUBBLE_TEA, TipBautura.WATER_BASED, "Vid", dummyReteta);
            validator.validate(p7);
            System.out.println("[FAIL] TC_07: Trebuia sa arunce ValidationException la nume vid.");
        } catch (ValidationException e) {
            System.out.println("[OK] TC_07: Nume vid (EPV) respins corect."); passed++;
        }

        // TC_08: Negativ BVA - ID invalid (0)
        try {
            Product p8 = new Product(0, "ID Zero", 15.0, CategorieBautura.TEA, TipBautura.BASIC, "Zero", dummyReteta);
            validator.validate(p8);
            System.out.println("[FAIL] TC_08: Trebuia sa arunce ValidationException la ID 0.");
        } catch (ValidationException e) {
            System.out.println("[OK] TC_08: ID zero (BVA) respins corect."); passed++;
        }

        System.out.println("\n=== REZULTAT FINAL: " + passed + "/8 TESTE TRECUTE ===");
    }
}