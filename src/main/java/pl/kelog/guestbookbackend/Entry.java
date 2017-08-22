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
    Long id;
    
    String username;
    
    String text;
    
    
    public Entry() {} // JPA
    
    public Entry(Long id, String username, String text) {
        this(username, text);
        this.id = id;
    }
    
    public Entry(String username, String text) {
        this.username = username;
        this.text = text;
    }
}
