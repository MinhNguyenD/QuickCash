package com.voidstudio.quickcashreg;

import android.widget.Toast;

import com.google.android.gms.tasks.Task;

public class LogIn {
  private final LogInActivity logInActivity;
  private static Firebase firebase;

  private static final String EMPTY_CREDENTIALS = "Username or password is empty";
  private static final String USER_DOES_NOT_EXIST = "This username does not exist";
  private static final String INCORRECT_PASSWORD = "Incorrect Password!";
  private static final String SUCCESS = "Success";
  private static String alertMessage = "BROKEN";

  protected boolean isLogged;
  protected boolean logInAsEmployee;

  public LogIn(LogInActivity logInActivity) {
    this.logInActivity = logInActivity;
    firebase = Firebase.getInstance();
  }


  private String getUserName(){
   return logInActivity.getUserName();
  }

  private String getPassword(){
    return logInActivity.getPassword();
  }

  protected Task<Void> getAlertMessage(){
    Toast.makeText(logInActivity, alertMessage, Toast.LENGTH_SHORT).show();
    return null;
  }


  /**
   * Checks if user that is logging in is an employee
   *
   * @return True if the user is an employee
   */
  protected boolean isEmployee() {
    if (firebase.getUserType(getUserName()).equals("Employee")) {
      logInAsEmployee = true;
    } else {
      logInAsEmployee = false;
    }
    return logInAsEmployee;
  }

  private boolean isEmptyUsername(){
    return getUserName().isEmpty();
  }

  private boolean isEmptyPassword(){
    return getPassword().isEmpty();
  }


  /**
   * Checks if there is empty fields
   * @return true if there is an empty field
   */
  protected boolean emptyCredentials(){
    if(isEmptyPassword()||isEmptyUsername()){
      return true;
    }
    else return false;
  }

  /**
   *
   * @param username entered username
   * @return true if username exists in the database
   */
  protected boolean existingUser(String username){
    //This method is identical to method in register, Consider refactor
    return firebase.existingUser(username);
  }

  /**
   * Checks if the entered password matches the password associated to the user
   * @param password password entered by the user about to log in
   * @return boolean that is true if the password matches the password associated to the user
   */
  protected boolean passwordMatch(String password){

    if(firebase.checkIfPasswordMatches(getUserName(),password)) return true;
    else return false;
  }

  /**
   * Log In method
   * @return true if log in is successful.
   */
  protected boolean logIn(String username, String password){
    if(isEmployee()) logInAsEmployee = true;

    if(emptyCredentials()){
      alertMessage = EMPTY_CREDENTIALS;
      isLogged = false;
    }

    else if(!passwordMatch(password)){
      alertMessage = INCORRECT_PASSWORD;
      isLogged = false;
    }

    else if(!existingUser(username)){
      alertMessage = USER_DOES_NOT_EXIST;
      isLogged = false;
    }

    else{
      alertMessage = SUCCESS;
      isLogged = true;
    }
    return isLogged;
  }


}