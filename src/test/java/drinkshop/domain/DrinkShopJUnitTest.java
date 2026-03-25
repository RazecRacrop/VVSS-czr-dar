package drinkshop.domain;

import drinkshop.service.validator.ProductValidator;
import drinkshop.service.validator.ValidationException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

@DisplayName("Lab02 - Testare Black-Box Magazin Bauturi")
class DrinkShopJUnitTest {
    private ProductValidator validator;
    private Reteta dummyReteta;

    @BeforeEach
    void setUp() {
        validator = new ProductValidator();
        dummyReteta = new Reteta(1, new ArrayList<>());
    }

    // --- ADNOTARE NOUA 1: @Tag (pentru organizarea testelor) ---
    @Tag("Pozitiv")
    @Test
    @DisplayName("TC_01 (EPV): Produs valid - Cafea clasica")
    void tc01() {
        Product p = new Product(10, "Americano", 12.0,
                CategorieBautura.CLASSIC_COFFEE, TipBautura.WATER_BASED,
                "Cafea neagra tare", dummyReteta);
        assertDoesNotThrow(() -> validator.validate(p));
    }

    @Tag("Pozitiv")
    @Test
    @DisplayName("TC_02 (BVA): Pret la limita minima valida (0.01)")
    void tc02() {
        Product p = new Product(2, "Apa", 0.01,
                CategorieBautura.JUICE, TipBautura.WATER_BASED,
                "Apa plata", dummyReteta);
        assertDoesNotThrow(() -> validator.validate(p));

    }

    @Tag("Pozitiv")
    @Test
    @DisplayName("TC_03 (EPV): Produs valid - Milk Coffee")
    void tc03() {

        Product p = new Product(12, "Latte", 15.5,
                CategorieBautura.MILK_COFFEE, TipBautura.DAIRY,
                "Cafea cu lapte fin", dummyReteta);


        assertDoesNotThrow(() -> validator.validate(p));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 100})
    @DisplayName("TC_04 (BVA): Validare ID-uri pozitive multiple")
    void tc04(int id) {
        Product p = new Product(id, "Smoothie", 20.0,
                CategorieBautura.SMOOTHIE, TipBautura.PLANT_BASED,
                "Smoothie fructe", dummyReteta);
        assertDoesNotThrow(() -> validator.validate(p));
    }

    @Tag("Negativ")
    @Test
    @DisplayName("TC_05 (EPV): Respingere pret negativ")
    void tc05() {
        Product p = new Product(13, "Invalid", -1.0,
                CategorieBautura.SPECIAL_COFFEE, TipBautura.POWDER,
                "Descriere", dummyReteta);
        assertThrows(ValidationException.class, () -> validator.validate(p));
    }

    @Tag("Negativ")
    @Test
    @DisplayName("TC_06 (BVA): Respingere pret zero")
    void tc06() {
        Product p = new Product(5, "Freebie", 0.0,
                CategorieBautura.ICED_COFFEE, TipBautura.WATER_BASED,
                "Descriere", dummyReteta);
        assertThrows(ValidationException.class, () -> validator.validate(p));
    }

    @Tag("Negativ")
    @Test
    @DisplayName("TC_07 (EPV): Respingere nume vid")
    void tc07() {
        Product p = new Product(14, "", 10.0,
                CategorieBautura.BUBBLE_TEA, TipBautura.WATER_BASED,
                "Descriere", dummyReteta);
        assertThrows(ValidationException.class, () -> validator.validate(p));
    }

    @Tag("Negativ")
    @Test
    @DisplayName("TC_08 (BVA): Respingere ID invalid (0)")
    void tc08() {
        Product p = new Product(0, "Test ID", 15.0,
                CategorieBautura.TEA, TipBautura.BASIC,
                "Descriere", dummyReteta);
        assertThrows(ValidationException.class, () -> validator.validate(p));
    }
}