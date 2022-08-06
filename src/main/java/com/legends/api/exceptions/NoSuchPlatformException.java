package com.legends.api.exceptions;

public class NoSuchPlatformException extends RuntimeException {

    public NoSuchPlatformException(String platform) {
        super(platform);
    }

}
