package com.vgb.prules.demo.buyer.service.matcher;

import com.vgb.prules.demo.buyer.domain.ProductMatchResult;
import com.vgb.prules.demo.buyer.exception.MatcherException;
import com.vgb.prules.demo.buyer.service.ProductRulesService;
import com.vgb.prules.demo.buyer.service.evaluator.*;
import com.vgb.prules.demo.common.domain.Product;
import com.vgb.prules.demo.seller.service.ProductService;
import com.vgb.prules.demo.util.DemoDataUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductMatchingServiceImplTest {

    @InjectMocks
    ProductMatchingServiceImpl productMatchingService;

    @Mock
    private ProductService productService;
    @Mock
    private ProductRulesService productRulesService;
    @Spy
    @InjectMocks
    private MasterAttributeEvaluatorService attributeEvaluatorService;
    @Spy
    private NumberAttributeEvaluatorService numberAttributeEvaluatorService;
    @Spy
    private BooleanAttributeEvaluatorService booleanAttributeEvaluatorService;
    @Spy
    private StringAttributeEvaluatorService stringAttributeEvaluatorService;
    @Spy
    private EnumAttributeEvaluatorService enumAttributeEvaluatorService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        productMatchingService.setSuccessfulConditionPercentageThreshold(50);
    }

    @Test
    void matchWithNoRules() {
        Product product = DemoDataUtils.PRODUCT1;
        //assume no rules available for product
        Mockito.when(productRulesService.getRuleByProductName(product.name()))
                .thenReturn(new ArrayList<>());
        ProductMatchResult result = productMatchingService.match(product);
        assertEquals(product.name(), result.getProductName());
        assertEquals(0, result.getPercentConditionsSatisfied());
        assertFalse(result.isMatch());

        Mockito.verify(productRulesService).getRuleByProductName(Mockito.any());
    }


    @Test
    void matchWithRules() throws MatcherException {
        Product product = DemoDataUtils.PRODUCT1;
        //assume no rules available for product

        Mockito.when(productRulesService.getRuleByProductName(product.name()))
                .thenReturn(new ArrayList<>() {{
                    add(DemoDataUtils.RULE1A);
                    add(DemoDataUtils.RULE1B);
                }});
        ProductMatchResult result = productMatchingService.match(product);
        assertEquals(product.name(), result.getProductName());
        assertEquals(80f, result.getPercentConditionsSatisfied());
        assertTrue(result.isMatch());
        assertEquals(5, DemoDataUtils.RULE1A.getConditions().size() + DemoDataUtils.RULE1B.getConditions().size()); //4 conditions in total

        Mockito.verify(productRulesService).getRuleByProductName(Mockito.any());
        Mockito.verify(attributeEvaluatorService, Mockito.times(5)).evaluate(Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.verify(numberAttributeEvaluatorService, Mockito.times(2)).evaluate(Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.verify(stringAttributeEvaluatorService, Mockito.times(1)).evaluate(Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.verify(booleanAttributeEvaluatorService, Mockito.never()).evaluate(Mockito.any(), Mockito.any(), Mockito.any()); //attribute was not present in target product, so never evaluated (still tagged in master evaluated service once though)
        Mockito.verify(enumAttributeEvaluatorService, Mockito.times(1)).evaluate(Mockito.any(), Mockito.any(), Mockito.any());
    }
}