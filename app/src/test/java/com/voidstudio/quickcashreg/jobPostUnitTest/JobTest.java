package com.voidstudio.quickcashreg.jobPostUnitTest;

import static org.junit.Assert.assertEquals;

import com.voidstudio.quickcashreg.jobpost.Job;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class JobTest {


  private static final String JOB_NAME = "Job";

  private static final String JOB_WAGE = "Wage";

  private static final String JOB_TAG = "Tag";

  private static final String EMPLOYER = "Scumbag";

  private static final String JOB_LOCATION = "Far";


  @BeforeClass
  public static void setup(){

  }

  @AfterClass
  public static void tearDown(){
    System.gc();
  }

  @Test
  public void addition_isCorrect() {
    assertEquals(4, 2 + 2);
  }

  @Test
  public void jobToString(){
    Job job = new Job(JOB_NAME,JOB_WAGE,JOB_TAG,EMPLOYER,JOB_LOCATION);
    String expected = "Title: " + JOB_NAME + "\nWage: " + JOB_WAGE + " Tag(s): " + JOB_TAG + "\nPosted by:" + EMPLOYER;
    assertEquals("To string error", job.toString(), expected);
  }





}