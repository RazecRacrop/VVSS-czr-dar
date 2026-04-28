package drinkshop.domain;

import drinkshop.domain.*;
import drinkshop.repository.Repository;
import drinkshop.service.ProductService;
import drinkshop.service.validator.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductIntegrationStep3 {
    @Mock private Repository<Integer, Product> productRepo;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductService(productRepo);
    }

    @Test
    @DisplayName("TC_S3_01: Integrare cap-coadă produs valid (S+V+E)")
    void tc_s3_01() {
        Product p = new Product(10, "Latte", 15.0, CategorieBautura.MILK_COFFEE, TipBautura.DAIRY, null);
        productService.addProduct(p);
        verify(productRepo).save(p);
    }

    @Test
    @DisplayName("TC_S3_02: Respingere cap-coadă produs invalid (S+V+E)")
    void tc_s3_02() {
        Product p = new Product(-1, "", -5.0, CategorieBautura.JUICE, TipBautura.BASIC, null);
        assertThrows(ValidationException.class, () -> productService.addProduct(p));
        verify(productRepo, never()).save(any());
    }
}