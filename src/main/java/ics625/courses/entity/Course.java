
package ics625.courses.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 *
 * @author Tom Coffee
 */
@Entity
public class Course {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
    private String number;
    private String title;
    private String description;
    private boolean offer;

    public Course() {
    }
    
    public Course(String number, String title, String description, boolean offer) {
        this.number = number;
        this.title = title;
        this.description = description;
        this.offer = offer;
    }    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getOffer() {
        if (offer)
            return "Open";
        else
            return "Closed";
    } 
    
    public void setOffer(boolean offer) {
        this.offer = offer;
    }
    
    public String toString(){
        return String.format("%-5s %-10s %-20s %-20s %-5s", 
                id, 
                number, 
                title, 
                description, 
                getOffer());
    }

    public void setCourse(Course c) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
