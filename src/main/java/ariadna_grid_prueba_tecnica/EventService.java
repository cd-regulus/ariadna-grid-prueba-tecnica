package ariadna_grid_prueba_tecnica;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EventService {

	List<Event> events;

	public EventService(List<Event> events) {
		this.events = events;
	}

	public List<Event> findBySourceName(String name) {
		List<Event> byName = new ArrayList<>();

		if (name != null && name.length() > 0) {
			byName = events.stream().filter(e -> e.getSource().getName().equalsIgnoreCase(name))
					.collect(Collectors.toList());
		}

		return byName;
	}

	public List<Event> findByDateRange(String dateInput1, String dateInput2) {
		List<Event> byDates = new ArrayList<>();

		try {
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			LocalDate localDate1 = LocalDate.parse(dateInput1, dateFormatter);
			LocalDate localDate2 = LocalDate.parse(dateInput2, dateFormatter);

			LocalDateTime localDateTime1 = localDate1.atStartOfDay();
			LocalDateTime localDateTime2 = localDate2.atStartOfDay();

			byDates = events.stream()
					.filter(e -> e.getTs().isAfter(localDateTime1) && e.getTs().isBefore(localDateTime2))
					.collect(Collectors.toList());
		} catch (DateTimeParseException e) {
			System.out.println("Formato de fecha no válida (YYYY-MM-DD)");
		}
		return byDates;
	}

	public List<Event> findByValueRange(String min, String max) {
		List<Event> byValueRange = new ArrayList<>();

		try {
			byValueRange = events.stream()
					.filter(e -> e.getValue() <= Double.valueOf(max) && e.getValue() >= Double.valueOf(min))
					.collect(Collectors.toList());
		} catch (NumberFormatException nfe) {
			System.out.println("Valor introducido no válido.");
		}

		return byValueRange;
	}
}
