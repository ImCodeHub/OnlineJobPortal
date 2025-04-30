package com.example.OnlineJobPortal.Exception;

public class CustomerException {
    public static class UserNotFoundException extends RuntimeException{
        public UserNotFoundException(String message){
            super(message);
        }
    }
    public static class JobPostNotFoundException extends  RuntimeException{
        public JobPostNotFoundException(String message){
            super(message);
        }
    }

    public static class ProfileNotFoundException extends  RuntimeException{
        public ProfileNotFoundException(String message){
            super(message);
        }
    }

    public static class UserAlreadyExistException extends  RuntimeException{
        public UserAlreadyExistException(String message){
            super(message);
        }
    }
}
