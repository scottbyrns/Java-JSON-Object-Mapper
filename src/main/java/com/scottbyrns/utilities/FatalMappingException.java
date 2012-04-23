package com.scottbyrns.utilities;

/**
 * The entity was not compatible.
 */
public class FatalMappingException extends Exception
{
    public FatalMappingException(Throwable throwable) {
        super(throwable);
    }
}
