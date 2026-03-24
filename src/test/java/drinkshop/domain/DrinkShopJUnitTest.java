package drinkshop.domain;

import drinkshop.service.validator.ProductValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import drinkshop.domain.Product;
import drinkshop.service.validator.ValidationException;

import java.util.ArrayList;


class DrinkShopJUnitTest {
    private ProductValidator validator;
    private Reteta dummyReteta;

    @BeforeEach
    void setUp() {
        validator = new ProductValidator();
        // Presupunem ca Reteta are un constructor simplu sau unul cu lista de ingrediente
        dummyReteta = new Reteta(1, new ArrayList<>());
    }

    // --- TESTE POZITIVE (4 CAZURI) ---

    @Test
    @DisplayName("TC_01 (EPV): Produs valid - Cafea clasica")
    void tc01() {
        // Constructor: id, nume, pret, categorie, tip, descriere, reteta
        Product p = new Product(1, "Americano", 12.0,
                CategorieBautura.CLASSIC_COFFEE, TipBautura.WATER_BASED,
                "Cafea neagra tare", dummyReteta);
        assertDoesNotThrow(() -> validator.validate(p));
    }

    @Test
    @DisplayName("TC_02 (BVA): Pret la limita minima valida (0.01)")
    void tc02() {
        Product p = new Product(2, "Apa", 0.01,
                CategorieBautura.JUICE, TipBautura.WATER_BASED,
                "Apa plata", dummyReteta);
        assertDoesNotThrow(() -> validator.validate(p));
    }

    @Test
    @DisplayName("TC_03 (EPV): Produs valid - Milk Coffee")
    void tc03() {
        Product p = new Product(3, "Latte", 15.5,
                CategorieBautura.MILK_COFFEE, TipBautura.DAIRY,
                "Cafea cu lapte fin", dummyReteta);
        assertDoesNotThrow(() -> validator.validate(p));
    }

    @Test
    @DisplayName("TC_04 (BVA): ID valid minim (1)")
    void tc04() {
        Product p = new Product(1, "Smoothie", 20.0,
                CategorieBautura.SMOOTHIE, TipBautura.PLANT_BASED,
                "Smoothie fructe", dummyReteta);
        assertDoesNotThrow(() -> validator.validate(p));
    }

    // --- TESTE NEGATIVE (4 CAZURI) ---

    @Test
    @DisplayName("TC_05 (EPV): Pret negativ")
    void tc05() {
        Product p = new Product(4, "Invalid", -1.0,
                CategorieBautura.SPECIAL_COFFEE, TipBautura.POWDER,
                "Descriere", dummyReteta);
        assertThrows(ValidationException.class, () -> validator.validate(p));
    }

    @Test
    @DisplayName("TC_06 (BVA): Pret zero")
    void tc06() {
        Product p = new Product(5, "Freebie", 0.0,
                CategorieBautura.ICED_COFFEE, TipBautura.WATER_BASED,
                "Descriere", dummyReteta);
        assertThrows(ValidationException.class, () -> validator.validate(p));
    }

    @Test
    @DisplayName("TC_07 (EPV): Nume vid")
    void tc07() {
        Product p = new Product(6, "", 10.0,
                CategorieBautura.BUBBLE_TEA, TipBautura.WATER_BASED,
                "Descriere", dummyReteta);
        assertThrows(ValidationException.class, () -> validator.validate(p));
    }

    @Test
    @DisplayName("TC_08 (BVA): ID invalid (0)")
    void tc08() {
        Product p = new Product(0, "Test ID", 15.0,
                CategorieBautura.TEA, TipBautura.BASIC,
                "Descriere", dummyReteta);
        assertThrows(ValidationException.class, () -> validator.validate(p));
    }
}