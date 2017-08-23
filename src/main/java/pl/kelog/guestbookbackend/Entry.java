package pl.kelog.guestbookbackend;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Entry {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String username;
    
    private String text;
    
    
    public Entry() { // JPA
    }
    
    Entry(Long id, String username, String text) {
        this(username, text);
        this.id = id;
    }
    
    Entry(String username, String text) {
        this.username = username;
        this.text = text;
    }
}
