package com.vgb.prules.demo.buyer.exception;

/**
 * Exception to capture issues with matching
 */
public class MatcherException extends Throwable {
    /**
     * Constructor
     * @param msg
     */
    public MatcherException(String msg) {
        super(msg);
    }

    /**
     * Constructor
     * @param msg
     * @param t
     */
    public MatcherException(String msg, Throwable t) {
        super(msg, t);
    }
}
