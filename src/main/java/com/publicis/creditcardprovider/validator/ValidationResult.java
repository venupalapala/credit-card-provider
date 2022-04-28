package com.publicis.creditcardprovider.validator;

public class ValidationResult {
    private static String validateMsg = null;
    private static boolean isValidate = true;
    public ValidationResult(boolean isValidate, String validateMsg){
        this.isValidate = isValidate;
        this.validateMsg = validateMsg;
    }
    public static ValidationResult valid(){
        return new ValidationResult(true,null);
    }
    public static ValidationResult inValid(String message){

        return new ValidationResult(false, message);
    }
    public boolean isValid(){
        return isValidate;
    }
    public String getValidateMsg(){
        return validateMsg;
    }
 }
