package com.vgb.prules.demo.buyer.service.evaluator;

import com.vgb.prules.demo.buyer.domain.RuleConstants;
import com.vgb.prules.demo.buyer.exception.MatcherException;
import com.vgb.prules.demo.common.domain.attribute.StringAttribute;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringAttributeEvaluatorServiceTest {

    StringAttributeEvaluatorService attributeEvaluatorService = new StringAttributeEvaluatorService();

    @Test
    void evaluateEquals() throws MatcherException {
        assertEquals(true,
                attributeEvaluatorService.evaluate(
                        new StringAttribute("foo", "aaaa"), RuleConstants.ComparatorOperator.EQUALS, new StringAttribute("foo", "aaaa")
                )
        );
    }

    @Test
    void evaluateEqualsFalse() throws MatcherException {
        assertEquals(false,
                attributeEvaluatorService.evaluate(
                        new StringAttribute("foo", "aaaa"), RuleConstants.ComparatorOperator.EQUALS, new StringAttribute("foo", "bbbb")
                )
        );
    }

    @Test
    void evaluateNotEqualsFalse() throws MatcherException {
        assertEquals(false,
                attributeEvaluatorService.evaluate(
                        new StringAttribute("foo", "aaaa"), RuleConstants.ComparatorOperator.NOT_EQUALS, new StringAttribute("foo", "aaaa")
                )
        );
    }

    @Test
    void evaluateNotEqualsTrue() throws MatcherException {
        assertEquals(true,
                attributeEvaluatorService.evaluate(
                        new StringAttribute("foo", "aaaa"), RuleConstants.ComparatorOperator.NOT_EQUALS, new StringAttribute("foo", "bbbb")
                )
        );
    }

    @Test
    void evaluateNameMismatch() throws MatcherException {
        MatcherException thrown = Assertions.assertThrows(MatcherException.class, () -> {
            attributeEvaluatorService.evaluate(
                    new StringAttribute("boo", "aaaa"), RuleConstants.ComparatorOperator.EQUALS, new StringAttribute("foo", "aaaa")
            );
        });
    }
}