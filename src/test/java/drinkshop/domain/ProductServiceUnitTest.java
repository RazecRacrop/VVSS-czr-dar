package drinkshop.domain;

import drinkshop.domain.*;
import drinkshop.repository.Repository;
import drinkshop.service.ProductService;
import drinkshop.service.validator.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceUnitTest {
    @Mock private Repository<Integer, Product> productRepo;
    @Mock private Validator<Product> productValidator;
    @Mock private Product mockProduct;

    private ProductService productService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        productService = new ProductService(productRepo);
        java.lang.reflect.Field field = ProductService.class.getDeclaredField("productValidator");
        field.setAccessible(true);
        field.set(productService, productValidator);
    }

    @Test
    @DisplayName("TC_S1_01: Adăugare produs valid (Unit)")
    void tc_s1_01() {
        doNothing().when(productValidator).validate(mockProduct);
        productService.addProduct(mockProduct);
        verify(productValidator, times(1)).validate(mockProduct);
        verify(productRepo, times(1)).save(mockProduct);
    }

    @Test
    @DisplayName("TC_S1_02: Adăugare produs invalid (Unit)")
    void tc_s1_02() {
        doThrow(new ValidationException("Pret invalid")).when(productValidator).validate(mockProduct);
        assertThrows(ValidationException.class, () -> productService.addProduct(mockProduct));
        verify(productRepo, never()).save(mockProduct);
    }
}