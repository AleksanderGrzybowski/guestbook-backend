package pl.kelog.guestbookbackend;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.kelog.guestbookbackend.dto.EntryDto;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class EntryControllerTest {
    
    private EntryService entryService;
    private MockMvc mockMvc;
    
    @Before
    public void setup() {
        entryService = mock(EntryService.class);
        
        this.mockMvc = standaloneSetup(new EntryController(entryService))
                .build();
    }
    
    @Test
    public void should_list_entries() throws Exception {
        when(entryService.list()).thenReturn(asList(
                new EntryDto(1L, "firstUsername", "firstText"),
                new EntryDto(2L, "secondUsername", "secondText")
        ));
        
        mockMvc.perform(MockMvcRequestBuilders.get("/entry"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].username", is("firstUsername")))
                .andExpect(jsonPath("$[0].text", is("firstText")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].username", is("secondUsername")))
                .andExpect(jsonPath("$[1].text", is("secondText")));
    }
    
    @Test
    public void should_create_entry() throws Exception {
        when(entryService.create("newUsername", "newText"))
                .thenReturn(new EntryDto(1L, "newUsername", "newText"));
        
        mockMvc.perform(MockMvcRequestBuilders.post("/entry")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"username\": \"newUsername\",\n" +
                        "  \"text\": \"newText\"\n" +
                        "}")
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.username", is("newUsername")))
                .andExpect(jsonPath("$.text", is("newText")));
    }
}