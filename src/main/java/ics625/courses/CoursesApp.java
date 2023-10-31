
package ics625.courses;

import ics625.courses.entity.Course;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ics625.courses.entity.CourseRepo;
import java.util.Iterator;

/**
 *
 * @author Tom Coffee
 */
@Component
public class CoursesApp {

    private final Scanner kb = new Scanner(System.in);
    
    @Autowired
    private CourseRepo repo;

    public void start(){
         loop: while (true) {
            printHeading();
            Iterable<Course> it = repo.findAll();
            it.forEach(System.out::println);
            System.out.print("a)dd d)elete u)pdate s)search course e)xit: ");
            switch (kb.nextLine()) {
                case "a" -> add();
                case "d" -> delete();
                case "u" -> update();
                case "s" -> searchCourse();
                case "e" -> {
                    break loop;
                }
            }
        }       
    }
    
    private void add() {
        System.out.print("Enter course number: ");
        String number = kb.nextLine();
        System.out.print("Enter course title: ");
        String title = kb.nextLine();
        System.out.print("Enter course description: ");
        String description = kb.nextLine();
        boolean offer = getBoolean();
        repo.save(new Course(number, title, description, offer));
    }

    private void delete() {
        String s;
        long id = 0;
        
        while(id == 0) {
           System.out.print("Enter course id (must be a number): ");
           s = kb.nextLine();
           id = returnLongFromScanner(s);
           repo.deleteById(id);
        }
    }

    private void update() {
        long id = getCourseIdFromScanner();

        Optional<Course> option = repo.findById(id);
        if (option.isEmpty()) {
            System.out.println("No such course found.");
            return;
        }

        Course c = option.get();

        System.out.print("Enter new course title: ");
        String title = kb.nextLine();
        if (!title.isBlank()) {
            c.setTitle(title);
        }
        
        System.out.print("Enter new course description: ");
        String description = kb.nextLine();
        if (!description.isBlank()) {
            c.setDescription(description);
        }
        
        boolean offer = getBoolean();
        c.setOffer(offer);
        
        if (!title.isBlank() 
            || !description.isBlank()) repo.save(c);

    }
    
    private void searchCourse(){
        System.out.print("Enter 1 to search by ID, or 2 to search by course numbner. Enter 3 to go back. ");
        switch (kb.nextLine()) {
                case "1" -> searchById();
                case "2" -> searchByNumber();
                case "3" -> {
                    break;
                }
            }
    } 
    
    private void searchById() {
        long id = getCourseIdFromScanner();
        Optional<Course> option = repo.findById(id);
        
        if (option.isEmpty()) {
            System.out.println("No such course found.");
            return;
        }
        
        Course c = option.get();
        printHeading();
        System.out.println(c.toString());
        
    }
    
    private void searchByNumber() {
        System.out.print("Enter course number: ");
        String number = kb.nextLine();
        boolean found = false;
        for (Course c : repo.findAll()) {
            System.out.println(c.getNumber());
            if(c.getNumber() == number) {
                System.out.println(c.toString());
                found = true;
            }
        }
        if (!found)
            System.out.println("No such course found.");
    }
    
    private long getCourseIdFromScanner(){
        String s;
        long id = 0;
        
        while(id == 0) {
           System.out.print("Enter course id (must be a number): ");
           s = kb.nextLine();
           id = returnLongFromScanner(s);
        }
        return id;
    }
    
    private long returnLongFromScanner(String s) { 
        long l = 0;
        
        try {
            l = Long.parseLong(s);
        }
        catch (NumberFormatException e) {
            System.out.println("Please enter a number: ");
        }
        
        return l;
    }
            
   private boolean getBoolean() {
       boolean ret = true;
       String s;
       boolean cont = true;
       while(cont) {
        try {
                System.out.println("Enter true if the couse is open, false if the course is closed: ");
                String line = kb.nextLine();
                ret = Boolean.parseBoolean(line);
                cont = false;
            } 
            catch (NumberFormatException e) {
                System.out.println("Enter the exact text \"true\" or \"false\" without the quotation marks. ");
                getBoolean();
            }   
       }
       return ret;
   }
   
   private void printHeading() {
       System.out.printf("%-5s %-10s %-20s %-20s %-5s \n", 
                    "ID", 
                    "NUMBER", 
                    "TITLE", 
                    "DESCRIPTION", 
                    "OFFERED");
            System.out.print("-".repeat(68));
            System.out.println();
   }
}
