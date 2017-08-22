package pl.kelog.guestbookbackend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class EntryController {
    private final EntryService service;
    
    
    @RequestMapping(value = "/entry", method = RequestMethod.GET)
    public List<EntryDto> list() {
        return service.list();
    }
    
    @RequestMapping(value = "/entry", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<EntryDto> create(@RequestBody CreateEntryDto dto) {
        EntryDto entryDto = service.create(dto.username, dto.text);
        return new ResponseEntity<>(entryDto, HttpStatus.CREATED);
    }
    
    @Data
    @AllArgsConstructor
    public static class CreateEntryDto {
        String username, text;
    }
}
