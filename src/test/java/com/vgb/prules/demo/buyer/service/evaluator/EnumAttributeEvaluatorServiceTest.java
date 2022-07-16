package com.vgb.prules.demo.buyer.service.evaluator;

import com.vgb.prules.demo.buyer.domain.RuleConstants;
import com.vgb.prules.demo.buyer.exception.MatcherException;
import com.vgb.prules.demo.common.domain.attribute.EnumeratedAttribute;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnumAttributeEvaluatorServiceTest {

    EnumAttributeEvaluatorService attributeEvaluatorService = new EnumAttributeEvaluatorService();

    enum A {
        A1,A2
    }

    enum B {
        B1,B2
    }

    @Test
    void evaluateEquals() throws MatcherException {
        assertEquals(true,
                attributeEvaluatorService.evaluate(
                        new EnumeratedAttribute("foo", A.A1), RuleConstants.ComparatorOperator.EQUALS, new EnumeratedAttribute("foo", A.A1)
                )
        );
    }

    @Test
    void evaluateValueMismatchSameEmum() throws MatcherException {
        assertEquals(false,
                attributeEvaluatorService.evaluate(
                        new EnumeratedAttribute("foo", A.A1), RuleConstants.ComparatorOperator.EQUALS, new EnumeratedAttribute("foo", A.A2)
                )
        );
    }

    @Test
    void evaluateValueMismatchDifferentEmum() throws MatcherException {
        assertEquals(false,
                attributeEvaluatorService.evaluate(
                        new EnumeratedAttribute("foo", A.A1), RuleConstants.ComparatorOperator.EQUALS, new EnumeratedAttribute("foo", B.B1)
                )
        );
    }

    @Test
    void evaluateNameMismatch() throws MatcherException {

        MatcherException thrown = Assertions.assertThrows(MatcherException.class, () -> {
            attributeEvaluatorService.evaluate(
                    new EnumeratedAttribute("foo", A.A1), RuleConstants.ComparatorOperator.EQUALS, new EnumeratedAttribute("boo", A.A1)
            );
        });
    }

    @Test
    void evaluateNotEquals() throws MatcherException {
        assertEquals(false,
                attributeEvaluatorService.evaluate(
                        new EnumeratedAttribute("foo", A.A1), RuleConstants.ComparatorOperator.NOT_EQUALS, new EnumeratedAttribute("foo", A.A1)
                )
        );
    }

    @Test
    void evaluateNotEqualsValueMismatchSameEmum() throws MatcherException {
        assertEquals(true,
                attributeEvaluatorService.evaluate(
                        new EnumeratedAttribute("foo", A.A1), RuleConstants.ComparatorOperator.NOT_EQUALS, new EnumeratedAttribute("foo", A.A2)
                )
        );
    }

    @Test
    void evaluateNotEqualsValueMismatchDifferentEmum() throws MatcherException {
        assertEquals(true,
                attributeEvaluatorService.evaluate(
                        new EnumeratedAttribute("foo", A.A1), RuleConstants.ComparatorOperator.NOT_EQUALS, new EnumeratedAttribute("foo", B.B1)
                )
        );
    }

}