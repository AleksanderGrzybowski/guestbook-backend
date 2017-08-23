package pl.kelog.guestbookbackend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kelog.guestbookbackend.dto.EntryDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class EntryService {
    
    private final EntryRepository repository;
    
    List<EntryDto> list() {
        return repository.findAll().stream()
                .map(entry -> new EntryDto(entry.getId(), entry.getUsername(), entry.getText()))
                .collect(toList());
    }
    
    EntryDto create(String username, String text) {
        Entry created = repository.save(new Entry(username, text));
        return new EntryDto(created.getId(), created.getUsername(), created.getText());
    }
}
