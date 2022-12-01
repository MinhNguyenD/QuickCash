package users;

import com.google.android.gms.tasks.Task;
import com.voidstudio.quickcashreg.firebase.Firebase;
import com.voidstudio.quickcashreg.jobpost.Job;

import java.util.ArrayList;

public class Employer extends User {
  private static Firebase firebase;
  private static Employer employer;
  private ArrayList<Job> myJobs;
  public ArrayList<Employee> observerList;
  private double balance;

  protected static Employee employee;
  public Employer(String username, String email, String password){
    this.username = username;
    this.email = email;
    this.password = password;
    firebase = Firebase.getInstance();
    observerList = new ArrayList<>();
    myJobs = firebase.getJobsFromUser(username);
    balance = 2000;
  }

  protected Task<Void> search(){
    return null;
  }

  protected boolean validate(){
    return false;
  }


  public void setJob(String jobName, String jobWage, String jobTag){
    Job job = new Job(jobName, jobWage, jobTag, username);
    if(locate.getMyLocation()!=null){
      double latitude = locate.getLatLong()[0];
      double longitude = locate.getLatLong()[1];
      firebase.setJobCoordinates(jobName,latitude,longitude);
    }
    firebase.addJob(job);
  }
  public User getInstance(){
    if(employer == null){
      employer = new Employer(username, email, password);
    }
    return employer;
  }

  public ArrayList<Job> getMyJobs(){
    return myJobs;
  }

  public double getBalance() {
    return this.balance;
  }
  public double makePayment(double amount) {
    balance = balance - amount;
    if (balance < 0) {
      balance = 0;
    }
    return balance;
  }

}


