package com.vgb.prules.demo.buyer.service.evaluator;

import com.vgb.prules.demo.buyer.domain.RuleConstants;
import com.vgb.prules.demo.buyer.exception.MatcherException;
import com.vgb.prules.demo.common.domain.attribute.BooleanAttribute;
import com.vgb.prules.demo.common.domain.attribute.NumberAttribute;
import com.vgb.prules.demo.common.domain.attribute.StringAttribute;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MasterAttributeEvaluatorServiceTest {

    @InjectMocks
    MasterAttributeEvaluatorService attributeEvaluatorService ;

    @Mock
    BooleanAttributeEvaluatorService booleanAttributeEvaluatorService;

    @Mock
    NumberAttributeEvaluatorService numberAttributeEvaluatorService;

    @Mock
    StringAttributeEvaluatorService stringAttributeEvaluatorService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void evaluateStringAttribute() throws MatcherException {
        Mockito.when(stringAttributeEvaluatorService.evaluate(Mockito.any(),Mockito.any(),Mockito.any()))
                .thenReturn(true);
        assertTrue(attributeEvaluatorService.evaluate(new StringAttribute("foo", "aaa"),
                RuleConstants.ComparatorOperator.EQUALS, new StringAttribute("foo", "aaa")));
        Mockito.verify(stringAttributeEvaluatorService).evaluate(Mockito.any(),Mockito.any(),Mockito.any());
    }

    @Test
    void evaluateNumber() throws MatcherException {
        Mockito.when(numberAttributeEvaluatorService.evaluate(Mockito.any(),Mockito.any(),Mockito.any()))
                .thenReturn(true);
        assertTrue(attributeEvaluatorService.evaluate(new NumberAttribute("foo", 123),
                RuleConstants.ComparatorOperator.EQUALS, new NumberAttribute("foo", 123)));
        Mockito.verify(numberAttributeEvaluatorService).evaluate(Mockito.any(),Mockito.any(),Mockito.any());
    }

    @Test
    void evaluateBoolean() throws MatcherException {
        Mockito.when(booleanAttributeEvaluatorService.evaluate(Mockito.any(),Mockito.any(),Mockito.any()))
                .thenReturn(true);
        assertTrue(attributeEvaluatorService.evaluate(new BooleanAttribute("foo", true),
                RuleConstants.ComparatorOperator.EQUALS, new BooleanAttribute("foo", true)));
        Mockito.verify(booleanAttributeEvaluatorService).evaluate(Mockito.any(),Mockito.any(),Mockito.any());
    }

    @Test
    void evaluateMismatchingNames() {
        MatcherException thrown = Assertions.assertThrows(MatcherException.class, () -> {
            attributeEvaluatorService.evaluate(
                    new NumberAttribute("boo", 1.2f), RuleConstants.ComparatorOperator.EQUALS, new NumberAttribute("foo", 1.2f)
            );
        });
    }

    @Test
    void evaluateMismatchingTypes() {
        MatcherException thrown = Assertions.assertThrows(MatcherException.class, () -> {
            attributeEvaluatorService.evaluate(
                    new BooleanAttribute("boo", true), RuleConstants.ComparatorOperator.EQUALS, new NumberAttribute("foo", 1.2f)
            );
        });
    }
}