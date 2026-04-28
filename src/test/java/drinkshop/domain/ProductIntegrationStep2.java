package drinkshop.domain;

import drinkshop.domain.*;
import drinkshop.repository.Repository;
import drinkshop.service.ProductService;
import drinkshop.service.validator.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductIntegrationStep2 {
    @Mock private Repository<Integer, Product> productRepo;
    @Mock private Product mockProduct;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductService(productRepo);
        // Aici serviciul folosește ProductValidator-ul său real creat la inițializare
    }

    @Test
    @DisplayName("TC_S2_01: Validare reală succes (S+V)")
    void tc_s2_01() {
        when(mockProduct.getId()).thenReturn(1);
        when(mockProduct.getNume()).thenReturn("Cafea");
        when(mockProduct.getPret()).thenReturn(10.0);

        productService.addProduct(mockProduct);
        verify(productRepo).save(mockProduct);
    }

    @Test
    @DisplayName("TC_S2_02: Validare reală eșec (S+V)")
    void tc_s2_02() {
        when(mockProduct.getPret()).thenReturn(-5.0); // Preț invalid
        assertThrows(ValidationException.class, () -> productService.addProduct(mockProduct));
        verify(productRepo, never()).save(mockProduct);
    }
}