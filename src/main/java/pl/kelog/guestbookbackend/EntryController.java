package pl.kelog.guestbookbackend;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kelog.guestbookbackend.dto.CreateEntryDto;
import pl.kelog.guestbookbackend.dto.EntryDto;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
@CrossOrigin
public class EntryController {
    
    private final EntryService entryService;
    
    @RequestMapping(value = "/entry", method = RequestMethod.GET)
    public List<EntryDto> list() {
        return entryService.list();
    }
    
    @RequestMapping(value = "/entry", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<EntryDto> create(@RequestBody CreateEntryDto createEntryDto) {
        EntryDto entryDto = entryService.create(createEntryDto.username, createEntryDto.text);
        return new ResponseEntity<>(entryDto, HttpStatus.CREATED);
    }
}
