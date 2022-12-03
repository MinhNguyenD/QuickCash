package com.voidstudio.quickcashreg.management;

import com.voidstudio.quickcashreg.jobpost.Job;

import java.util.ArrayList;

import users.Employee;

public class EmployeeContractManager implements IContractManager {



  private final Employee employee;

  public EmployeeContractManager(Employee e){
    this.employee = e;

  }

  @Override
  public void acceptContract(Job j) {
    JobContract jc  = new JobContract(j, employee);
    inProgressContracts.add(jc);
  }

  @Override
  public void setContractStatus(JobContract jc, String status) {
    int index = inProgressContracts.indexOf(jc);
    IJobActions action;
    if(status.equals(ManagementConstants.COMPLETED)){
      action = new Complete();//Action modifies jc object and returns it
      completedContracts.add(action.execute(jc));
      inProgressContracts.remove(jc);
    }
    else if(status.equals(ManagementConstants.AVAILABLE)){
      inProgressContracts.remove(index);
    }
    else if(status.equals(ManagementConstants.IN_PROGRESS)){
      inProgressContracts.remove(index);
      action = new Work();
      inProgressContracts.add(action.execute(jc));
    }
  }

  public ArrayList<JobContract> getIncompleteContracts(){
    return (ArrayList<JobContract>) inProgressContracts;
  }
  public ArrayList<JobContract> getCompletedContracts(){
    return (ArrayList<JobContract>) completedContracts;
  }

  @Override
  public ArrayList<JobContract> getIncompletedContracts() {
    return null;
  }

  @Override
  public ArrayList<String> getPaymentList() {
    return null;
  }


}
