package pl.kelog.guestbookbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateEntryDto {
    public final String username, text;
}
