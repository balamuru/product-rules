package com.vgb.prules.demo.buyer.service.evaluator;

import com.vgb.prules.demo.buyer.domain.RuleConstants;
import com.vgb.prules.demo.buyer.exception.MatcherException;
import com.vgb.prules.demo.common.domain.attribute.BooleanAttribute;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BooleanAttributeEvaluatorServiceTest {

    BooleanAttributeEvaluatorService attributeEvaluatorService = new BooleanAttributeEvaluatorService();

    @Test
    void evaluateEquals() throws MatcherException {
        assertEquals(true,
                attributeEvaluatorService.evaluate(
                        new BooleanAttribute("foo", true), RuleConstants.ComparatorOperator.EQUALS, new BooleanAttribute("foo", true)
                )
        );
    }

    @Test
    void evaluateNameMismatch() throws MatcherException {

        MatcherException thrown = Assertions.assertThrows(MatcherException.class, () -> {
            attributeEvaluatorService.evaluate(
                    new BooleanAttribute("foo", true), RuleConstants.ComparatorOperator.EQUALS, new BooleanAttribute("boo", true)
            );
        });

    }

    @Test
    void evaluateValueMismatch() throws MatcherException {
        assertEquals(false,
                attributeEvaluatorService.evaluate(
                        new BooleanAttribute("foo", true), RuleConstants.ComparatorOperator.EQUALS, new BooleanAttribute("foo", false)
                )
        );
    }

////

    @Test
    void evaluateNotEquals() throws MatcherException {
        assertEquals(true,
                attributeEvaluatorService.evaluate(
                        new BooleanAttribute("foo", false), RuleConstants.ComparatorOperator.NOT_EQUALS, new BooleanAttribute("foo", true)
                )
        );
    }

    @Test
    void evaluateNameMismatch2() throws MatcherException {

        MatcherException thrown = Assertions.assertThrows(MatcherException.class, () -> {
            attributeEvaluatorService.evaluate(
                    new BooleanAttribute("foo", true), RuleConstants.ComparatorOperator.NOT_EQUALS, new BooleanAttribute("boo", true)
            );
        });
    }

    @Test
    void evaluateValueMismatchNotEquals() throws MatcherException {
        assertEquals(true,
                attributeEvaluatorService.evaluate(
                        new BooleanAttribute("foo", true), RuleConstants.ComparatorOperator.NOT_EQUALS, new BooleanAttribute("foo", false)
                )
        );
    }
}