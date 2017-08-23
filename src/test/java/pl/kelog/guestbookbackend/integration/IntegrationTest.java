package pl.kelog.guestbookbackend.integration;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.kelog.guestbookbackend.dto.CreateEntryDto;
import pl.kelog.guestbookbackend.dto.EntryDto;

import java.util.Arrays;
import java.util.List;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class IntegrationTest {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    public void create_then_list() {
        ResponseEntity<EntryDto> response = restTemplate.postForEntity(
                "/entry",
                new CreateEntryDto("newUsername1", "newText1"),
                EntryDto.class
        );
        
        Assertions.assertThat(response.getBody()).isEqualTo(new EntryDto(1L, "newUsername1", "newText1"));
        
        restTemplate.postForEntity(
                "/entry",
                new CreateEntryDto("newUsername2", "newText2"),
                EntryDto.class
        );
        
        // https://stackoverflow.com/questions/23674046/get-list-of-json-objects-with-spring-resttemplate 
        ResponseEntity<List<EntryDto>> exchange = restTemplate.exchange(
                "/entry",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<EntryDto>>() {
                }
        );
        
        List<EntryDto> entries = exchange.getBody();
        
        Assertions.assertThat(entries).isEqualTo(Arrays.asList(
                new EntryDto(1L, "newUsername1", "newText1"),
                new EntryDto(2L, "newUsername2", "newText2")
        ));
    }
    
}
