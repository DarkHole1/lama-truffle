package com.lama.truffle;

import com.oracle.truffle.api.nodes.Node;

/**
 * Exception thrown during Lama program execution.
 * Includes source position information for better error reporting.
 */
public class LamaException extends RuntimeException {

    private final String message;
    private final Node location;

    public LamaException(String message, Node location) {
        super(message);
        this.message = message;
        this.location = location;
    }

    public LamaException(String message, Node location, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.location = location;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Node getLocation() {
        return location;
    }
}
