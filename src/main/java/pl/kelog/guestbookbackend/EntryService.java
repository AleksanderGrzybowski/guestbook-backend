package pl.kelog.guestbookbackend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class EntryService {
    private final EntryRepository repository;
    
    public List<EntryDto> list() {
        return repository.findAll().stream()
                .map(entry -> new EntryDto(entry.id, entry.username, entry.text))
                .collect(toList());
    }
    
    public EntryDto create(String username, String text) {
        Entry created = repository.save(new Entry(username, text));
        return new EntryDto(created.id, created.username, created.text);
    }
}
