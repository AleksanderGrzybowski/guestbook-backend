package pl.kelog.guestbookbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntryDto {
    public final Long id;
    public final String username, text;
}
