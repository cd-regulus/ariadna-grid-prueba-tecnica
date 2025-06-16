package ariadna.grid.pruebatecnica;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EventServiceTest {

	    private EventService eventService;
	    
	    @BeforeEach
	    public void setUp() {	        
	        List<Event> events = Arrays.asList(
	            new Event(1, 1, LocalDateTime.parse("2025-06-01T08:00:00"), 20012, new Source(1, "alpha")),
	            new Event(2, 2, LocalDateTime.parse("2025-06-02T18:00:00"), 41234.5, new Source(2, "beta")),
	            new Event(3, 1, LocalDateTime.parse("2025-06-01T16:00:00"), 78527.3, new Source(1, "alpha")),
	            new Event(4, 1, LocalDateTime.parse("2025-06-04T09:00:00"), 12451.2, new Source(1, "alpha")),
	            new Event(5, 2, LocalDateTime.parse("2025-06-07T06:30:00"), 59842.4, new Source(2, "beta")),
	            new Event(6, 2, LocalDateTime.parse("2025-06-11T10:00:10"), 87076., new Source(2, "beta")),
	            new Event(7, 6, LocalDateTime.parse("2025-06-19T17:00:00"), 73692.0, new Source(6, "zeta")),
	            new Event(8, 3, LocalDateTime.parse("2025-06-25T21:00:00"), 92317.01, new Source(3, "gamma")),
	            new Event(9, 1, LocalDateTime.parse("2025-06-30T23:50:00"), 54395.3, new Source(1, "alpha")),
	            new Event(10, 2, LocalDateTime.parse("2025-06-27T18:30:00"), 11111.2, new Source(2, "beta"))
	        );
	        
	        eventService = new EventService(events);
	    }
	    
	    
	    @Test
	    public void testFindBySourceName() {
	        List<Event> results = eventService.findBySourceName("alpha");
	        assertEquals(4, results.size());
	        results.forEach(event -> 
	            assertTrue(event.getSource().getName().equalsIgnoreCase("alpha"))
	        );
	    }
	    
	    @Test
	    public void testFindBySourceName_NonExisting() {
	        List<Event> results = eventService.findBySourceName("omega");
	        assertEquals(0, results.size());
	    }

	    @Test
	    public void testFindBySourceName_DifferentCases() {
	        List<Event> results1 = eventService.findBySourceName("ALPHA");
	        List<Event> results2 = eventService.findBySourceName("AlPhA");
	        List<Event> results3 = eventService.findBySourceName("alpha");
	        
	        assertEquals(4, results1.size());
	        assertEquals(4, results2.size());
	        assertEquals(4, results3.size());
	    }


	    @Test
	    public void testFindBySourceName_EmptyString() {
	        List<Event> results = eventService.findBySourceName("");
	        assertTrue(results.isEmpty());
	    }
	   
	    
	    // TESTS RANGO DE TIEMPO
	    
	    @Test
	    public void testSearchByTimestampRange() {
	    	List<Event> results = eventService.findByDateRange("2025-06-02", "2025-06-18");
	    	assertEquals(4, results.size());

	    }
	    
	    @Test
	    public void testFindByDateRange_Invalid() {
	        List<Event> results = eventService.findByDateRange("2025-06-XX", "2025-06-02");
	        assertTrue(results.isEmpty());
	    }
	    
	    @Test
	    public void testFindByDateRange_InvertedDateRange() {
	        List<Event> results = eventService.findByDateRange("2025-06-10", "2025-06-01");
	        assertTrue(results.isEmpty());
	    }
	    
	    
	    // TESTS DE RANGO DE VALORES
	    
	    @Test
	    public void testSearchByValueRange() {
	        List<Event> results = eventService.findByValueRange("25000", "63439");
	        assertEquals(3, results.size());
	        results.forEach(e -> {
	            assertTrue(e.getValue() >= 25000);
	            assertTrue(e.getValue() <= 63439);
	        });
	    }
	    
	    
	    @Test
	    public void testFindByExactValueRange() {
	        List<Event> results = eventService.findByValueRange("20012", "20012");
	        assertEquals(1, results.size());
	        assertEquals(20012, results.get(0).getValue());
	    }

	    @Test
	    public void testFindByValueRange_InvalidNumbers() {
	        List<Event> results = eventService.findByValueRange("FECHA_NO", "VALIDA");
	        assertTrue(results.isEmpty());
	    }

	    
	}