package pl.kelog.guestbookbackend;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntryDto {
    Long id;
    String username, text;
}
