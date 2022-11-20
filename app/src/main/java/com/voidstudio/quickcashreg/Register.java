package com.voidstudio.quickcashreg;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import user.IUser;
import user.UserFactory;


public class Register extends AppCompatActivity {

    private static Firebase firebase;
    private IUser user;
    private boolean userNameExisted;

    public static final String NULL_USERTYPE = "-";
    public static final String EMAIL_REGEX_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    public static final String USER_TYPE_ERROR_MESSAGE = "Please choose your role";
    public static final String USER_NAME_ERROR_MESSAGE = "User Name is already registered";
    public static final String EMAIL_ERROR_MESSAGE = "Invalid email address";
    public static final String PASSWORD_ERROR_MESSAGE = "Password has to be at least 6 characters";
    public static final String CONFIRM_PASSWORD_ERROR_MESSAGE = "Password and Confirm Password are not match";
    public static final String SUCCESS_MESSAGE = "User created successfully";


    public Register(){
        firebase = Firebase.getInstance();
    }

    /**
     Check if email address is valid
     **/
    protected static boolean isValidEmailAddress(String emailAddress) {
        /*
            Reference: OWASP Email Regex
            https://owasp.org/www-community/OWASP_Validation_Regex_Repository
         */
        Pattern p = Pattern.compile(EMAIL_REGEX_PATTERN);
        Matcher m = p.matcher(emailAddress);
        if(m.find()){
            return true;
        }
        return false;
    }

    /**
     Check if password is valid
     **/
    protected static boolean isValidPassword(String password) {
        if(password.length() >= 6){
            return true;
        }
        return false;
    }

    /**
     * Check if password matches confirm password
     * @param password entered password
     * @param confirmPassword entered confirmPassword
     * @return true if password.equals(confirmPassword)
     */
    protected static boolean isValidConfirmPassword(String password, String confirmPassword) {
        if(password.equals(confirmPassword)){
            return true;
        }
        return false;
    }

    /**
     * Checks if username already exists in database
     * @param userName userName to check
     * @return boolean of userNameExisted, true if username exists
     */
    protected boolean userNameExisted(String userName){
        userNameExisted = firebase.existingUser(userName);
        return userNameExisted;
    }

    protected boolean isValidRole(String role) {
        if(role.equals(NULL_USERTYPE)){
            return false;
        }

        return true;
    }



    protected String registerUser(String userName, String email, String password, String confirmPassword, String selectedRole){
        String message = "";

        if (!isValidRole(selectedRole) || userNameExisted(userName) || !isValidPassword(password) || !isValidConfirmPassword(password, confirmPassword) || !isValidEmailAddress(email)) {
            if (!isValidPassword(password)) {
                message = PASSWORD_ERROR_MESSAGE;
            } else if (!isValidEmailAddress(email)) {
                message = EMAIL_ERROR_MESSAGE;
            } else if (!isValidConfirmPassword(password, confirmPassword)) {
                message = CONFIRM_PASSWORD_ERROR_MESSAGE;
            } else if (userNameExisted(userName)) {
                message = USER_NAME_ERROR_MESSAGE;
            } else if (!isValidRole(selectedRole)) {
                message = USER_TYPE_ERROR_MESSAGE;
            }
        } else {
            message = SUCCESS_MESSAGE;
            user = new UserFactory().getUser(selectedRole);
            user.save();
        }
        return message;
    }
}
