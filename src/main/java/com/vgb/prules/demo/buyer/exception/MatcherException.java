package com.vgb.prules.demo.buyer.exception;

public class MatcherException extends Throwable{
    public MatcherException(String msg) {
        super(msg);
    }

    public MatcherException(String msg, Throwable t) {
        super(msg, t);
    }
}
