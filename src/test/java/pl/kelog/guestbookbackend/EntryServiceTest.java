package pl.kelog.guestbookbackend;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class EntryServiceTest {
    
    EntryRepository repository;
    EntryService entryService;
    
    
    @Before
    public void setup() {
        repository = mock(EntryRepository.class);
        entryService = new EntryService(repository);
    }
    
    @Test
    public void should_list_all_entries() {
        when(repository.findAll()).thenReturn(asList(
                new Entry(1L, "firstUser", "firstText"),
                new Entry(2L, "secondUser", "secondTest")
        ));
        
        List<EntryDto> result = entryService.list();
        
        assertThat(result).isEqualTo(asList(
                new EntryDto(1L, "firstUser", "firstText"),
                new EntryDto(2L, "secondUser", "secondTest")
        ));
    }
    
    @Test
    public void should_create_new_entry() {
        when(repository.save(new Entry("newUsername", "newText")))
                .thenReturn(new Entry("newUsername", "newText"));
        
        entryService.create("newUsername", "newText");
        
        ArgumentCaptor<Entry> captor = ArgumentCaptor.forClass(Entry.class);
        Mockito.verify(repository, times(1)).save(captor.capture());
        
        assertThat(captor.getValue().username).isEqualTo("newUsername");
        assertThat(captor.getValue().text).isEqualTo("newText");
    }
}