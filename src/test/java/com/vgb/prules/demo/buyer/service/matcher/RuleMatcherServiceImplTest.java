package com.vgb.prules.demo.buyer.service.matcher;

import com.vgb.prules.demo.buyer.domain.RuleMatchResults;
import com.vgb.prules.demo.buyer.exception.MatcherException;
import com.vgb.prules.demo.buyer.service.ProductRulesService;
import com.vgb.prules.demo.buyer.service.evaluator.*;
import com.vgb.prules.demo.common.domain.Product;
import com.vgb.prules.demo.util.DemoDataUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(MockitoExtension.class)
class RuleMatcherServiceImplTest {

    @InjectMocks
    RuleMatcherServiceImpl ruleMatcherService;

    @Mock
    ProductRulesService productRulesService;

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
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void matchWithNoRules() {
        final Product product = DemoDataUtils.PRODUCT1;
        Mockito.when(productRulesService.getRuleByProductName(product.name()))
                .thenReturn(new ArrayList<>());
        RuleMatchResults ruleMatchResults = ruleMatcherService.matchRules(product);
        assertEquals(0, ruleMatchResults.getConditionCount());
        assertEquals(0, ruleMatchResults.getMatchedConditionCount());
        assertEquals(0, ruleMatchResults.getRuleMatchResultList().size());
        Mockito.verify(productRulesService).getRuleByProductName(Mockito.any());
    }

    @Test
    void matchWithRules() throws MatcherException {
        Product product = DemoDataUtils.PRODUCT1;
        Mockito.when(productRulesService.getRuleByProductName(product.name()))
                .thenReturn(new ArrayList<>() {{
                    add(DemoDataUtils.RULE1A);
                    add(DemoDataUtils.RULE1B);
                }});
        RuleMatchResults results = ruleMatcherService.matchRules(product);

        assertEquals(2, results.getRuleMatchResultList().size());
        assertEquals(4, results.getMatchedConditionCount());
        assertEquals(5, results.getConditionCount());
        Mockito.verify(attributeEvaluatorService, Mockito.times(5)).evaluate(Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.verify(numberAttributeEvaluatorService, Mockito.times(2)).evaluate(Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.verify(stringAttributeEvaluatorService, Mockito.times(1)).evaluate(Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.verify(booleanAttributeEvaluatorService, Mockito.never()).evaluate(Mockito.any(), Mockito.any(), Mockito.any()); //attribute was not present in target product, so never evaluated (still tagged in master evaluated service once though)
        Mockito.verify(enumAttributeEvaluatorService, Mockito.times(1)).evaluate(Mockito.any(), Mockito.any(), Mockito.any());
    }

}