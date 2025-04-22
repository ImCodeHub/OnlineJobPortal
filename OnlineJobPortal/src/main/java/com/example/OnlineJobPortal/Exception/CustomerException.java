package com.example.OnlineJobPortal.Exception;

public class CustomerException {
    public static class UserNotFoundException extends RuntimeException{
        public UserNotFoundException(String message){
            super(message);
        }
    }
}
