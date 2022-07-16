package com.vgb.prules.demo.buyer.service.evaluator;

import com.vgb.prules.demo.buyer.domain.RuleConstants;
import com.vgb.prules.demo.buyer.exception.MatcherException;
import com.vgb.prules.demo.common.domain.attribute.NumberAttribute;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberAttributeEvaluatorServiceTest {

    NumberAttributeEvaluatorService attributeEvaluatorService = new NumberAttributeEvaluatorService();

    @Test
    void evaluateEquals() throws MatcherException {
        assertEquals(true,
                attributeEvaluatorService.evaluate(
                        new NumberAttribute("foo", 1.2f), RuleConstants.ComparatorOperator.EQUALS, new NumberAttribute("foo", 1.2f)
                )
        );
    }
    @Test
    void evaluateEqualsFalse() throws MatcherException {
        assertEquals(false,
                attributeEvaluatorService.evaluate(
                        new NumberAttribute("foo", 1.25f), RuleConstants.ComparatorOperator.EQUALS, new NumberAttribute("foo", 1.2f)
                )
        );
    }

    @Test
    void evaluateNotEquals() throws MatcherException {
        assertEquals(false,
                attributeEvaluatorService.evaluate(
                        new NumberAttribute("foo", 1.2f), RuleConstants.ComparatorOperator.NOT_EQUALS, new NumberAttribute("foo", 1.2f)
                )
        );
    }

    @Test
    void evaluateNotEqualsFalse() throws MatcherException {
        assertEquals(true,
                attributeEvaluatorService.evaluate(
                        new NumberAttribute("foo", 1.25f), RuleConstants.ComparatorOperator.NOT_EQUALS, new NumberAttribute("foo", 1.2f)
                )
        );
    }


    @Test
    void evaluateNameMismatch() throws MatcherException {
        MatcherException thrown = Assertions.assertThrows(MatcherException.class, () -> {
            attributeEvaluatorService.evaluate(
                    new NumberAttribute("boo", 1.2f), RuleConstants.ComparatorOperator.EQUALS, new NumberAttribute("foo", 1.2f)
            );
        });
    }



    @Test
    void evaluateGT() throws MatcherException {
        assertEquals(true,
                attributeEvaluatorService.evaluate(
                        new NumberAttribute("foo", 10.2f), RuleConstants.ComparatorOperator.GREATER_THAN, new NumberAttribute("foo", 1.2f)
                )
        );

    }

    @Test
    void evaluateGTFalse() throws MatcherException {
        assertEquals(false,
                attributeEvaluatorService.evaluate(
                        new NumberAttribute("foo", 1.2f), RuleConstants.ComparatorOperator.GREATER_THAN, new NumberAttribute("foo", 10.2f)
                )
        );
    }

    @Test
    void evaluateLT() throws MatcherException {
        assertEquals(true,
                attributeEvaluatorService.evaluate(
                        new NumberAttribute("foo", 10.2f), RuleConstants.ComparatorOperator.LESS_THAN, new NumberAttribute("foo", 100.2f)
                )
        );

    }

    @Test
    void evaluateLTFalse() throws MatcherException {
        assertEquals(false,
                attributeEvaluatorService.evaluate(
                        new NumberAttribute("foo", 100.2f), RuleConstants.ComparatorOperator.LESS_THAN, new NumberAttribute("foo", 10.2f)
                )
        );
    }

}