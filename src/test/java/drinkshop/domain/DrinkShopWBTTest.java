package drinkshop.domain;

import drinkshop.repository.file.FileStocRepository;
import drinkshop.service.StocService;
import org.junit.jupiter.api.*;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DrinkShopWBTTest {
    private StocService stocService;
    private FileStocRepository stocRepo;
    private final String TEST_FILE = "stoc_test.txt";

    @BeforeEach
    void setUp() throws Exception {
        // Resetăm fișierul fizic pentru a izola testele.
        new PrintWriter(TEST_FILE).close();
        stocRepo = new FileStocRepository(TEST_FILE);
        stocService = new StocService(stocRepo);
    }

    @Test
    @DisplayName("P231-155: TC1_Valid - Consum partial")
    void tc1_valid() {
        // Preconditions: Cafea cu cantitatea 10.0[cite: 5].
        Stoc s = new Stoc(1, new IngredientReteta("Cafea", 0.0), 10.0);
        stocRepo.save(s);

        // Input: Necesar 2.0[cite: 5].
        List<IngredientReteta> ingrediente = new ArrayList<>();
        ingrediente.add(new IngredientReteta("Cafea", 2.0));
        Reteta reteta = new Reteta(1, ingrediente);

        stocService.consuma(reteta);

        // Expected: Rămân 8.0[cite: 5].
        assertEquals(8.0, stocRepo.findOne(1).getCantitate());
    }

    @Test
    @DisplayName("P231-156: TC2_Valid - Consum total")
    void tc2_valid() {
        // Preconditions: Sirop cu cantitatea 1.5[cite: 5].
        Stoc s = new Stoc(2, new IngredientReteta("Sirop", 0.0), 1.5);
        stocRepo.save(s);

        // Input: Necesar 1.5 (cantitate exactă)[cite: 5].
        List<IngredientReteta> ingrediente = new ArrayList<>();
        ingrediente.add(new IngredientReteta("Sirop", 1.5));
        Reteta reteta = new Reteta(2, ingrediente);

        stocService.consuma(reteta);

        // Expected: Rămân 0.0[cite: 5].
        assertEquals(0.0, stocRepo.findOne(2).getCantitate());
    }

    @Test
    @DisplayName("P231-157: TC3_NonValid - Stoc insuficient")
    void tc3_nonValid() {
        // Preconditions: Lapte cu cantitatea 1.0[cite: 5].
        Stoc s = new Stoc(3, new IngredientReteta("Lapte", 0.0), 1.0);
        stocRepo.save(s);

        // Input: Necesar 3.0 (mai mult decât există)[cite: 5].
        List<IngredientReteta> ingrediente = new ArrayList<>();
        ingrediente.add(new IngredientReteta("Lapte", 3.0));
        Reteta reteta = new Reteta(3, ingrediente);

        // Expected: IllegalStateException[cite: 5].
        assertThrows(IllegalStateException.class, () -> stocService.consuma(reteta));
    }

    @Test
    @DisplayName("P231-158: TC4_NonValid - Ingredient lipsa")
    void tc4_nonValid() {
        // Input: Necesar 1.0 Miere (nu există în stoc)[cite: 5].
        List<IngredientReteta> ingrediente = new ArrayList<>();
        ingrediente.add(new IngredientReteta("Miere", 1.0));
        Reteta reteta = new Reteta(4, ingrediente);

        // Expected: IllegalStateException[cite: 5].
        assertThrows(IllegalStateException.class, () -> stocService.consuma(reteta));
    }

    @AfterAll
    static void tearDownAll() throws Exception {
        Files.deleteIfExists(Paths.get("stoc_test.txt"));
    }
}