package javaTester.javaOop.javaAccModiFirst;

import org.openqa.selenium.WebDriver;

public class Dog {
    // Public Access:
        // Su dung tu khoa public khi tao class
        // Cho phep tat ca class tu tat ca package khac truy cap va tao moi doi tuong
    WebDriver driver;
    Annimal annimal = new Annimal(); // instance/ object
    public void showProperty(){
        System.out.printf(annimal.color);
    }
}
