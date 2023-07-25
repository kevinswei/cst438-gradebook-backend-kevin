package com.cst438;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cst438.domain.Assignment;
import com.cst438.domain.AssignmentGrade;
import com.cst438.domain.AssignmentGradeRepository;
import com.cst438.domain.AssignmentRepository;
import com.cst438.domain.Course;
import com.cst438.domain.CourseRepository;
import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentRepository;

@SpringBootTest
public class EndToEndNewAssignment {

    public static final String CHROME_DRIVER_FILE_LOCATION = "C:/chromedriver_win32/chromedriver.exe";

    public static final String URL = "http://localhost:3000";
    public static final String TEST_USER_EMAIL = "test@csumb.edu";
    public static final String TEST_INSTRUCTOR_EMAIL = "dwisneski@csumb.edu";
    public static final int SLEEP_DURATION = 1000; // 1 second.
    public static final String TEST_ASSIGNMENT_NAME = "Test Assignment"; 
    public static final String TEST_COURSE_TITLE = "Test Course";
    public static final String TEST_STUDENT_NAME = "Test";
    public static final String TEST_COURSE_ID = "123456"; 
    public static final String TEST_ASSIGNMENT_DATE = "2021-09-02"; 

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    AssignmentGradeRepository assignnmentGradeRepository;

    @Autowired
    AssignmentRepository assignmentRepository;

    @Test
    public void addAssignment() throws Exception {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_FILE_LOCATION);

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL);
        Thread.sleep(SLEEP_DURATION);
        
        Assignment newAssignment = null;

        try {
            WebElement we = driver.findElement(By.xpath("//input[@name='AssignmentName']"));
            we.sendKeys(TEST_ASSIGNMENT_NAME);
            we = driver.findElement(By.xpath("//input[@name='id']"));
            we.sendKeys(TEST_COURSE_ID);
            we = driver.findElement(By.xpath("//input[@name='date']"));
            we.sendKeys(TEST_ASSIGNMENT_DATE);
            driver.findElement(By.xpath("//button[text()='Submit']")).click();
            Thread.sleep(SLEEP_DURATION);
        } catch (Exception e) {
            throw e;
        } finally {
        	if (newAssignment != null) {
                assignmentRepository.delete(newAssignment);
            }
            driver.quit();
        }
    }
}

