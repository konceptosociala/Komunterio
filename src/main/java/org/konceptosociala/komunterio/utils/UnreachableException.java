package org.konceptosociala.komunterio.utils;

public class UnreachableException extends RuntimeException {
    public UnreachableException() {
        super("Unreachable code");
    }
}
