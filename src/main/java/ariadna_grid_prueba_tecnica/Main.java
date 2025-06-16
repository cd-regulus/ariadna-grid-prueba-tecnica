package ariadna_grid_prueba_tecnica;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
	private static final String RESOURCES_DIRECTORY = "src/main/resources";

	static EventService evService;

	public static void main(String[] args) throws IOException {
		List<Event> data = loadData();

		evService = new EventService(data);

		startupMenu();
	}

	private static List<Event> loadData() throws IOException {
		List<Event> events = Collections.synchronizedList(new ArrayList<Event>());
		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();

		try {
			Stream<Path> paths = Files.list(Paths.get(RESOURCES_DIRECTORY));
			paths.filter(p -> p.toString().endsWith(".json")).parallel().forEach(filePath -> {
				try {
					InputStream is = Files.newInputStream(filePath);
					if (is == null) {
						throw new RuntimeException("No se ha encontrado el archivo especificado.");
					}
					Event[] eventsArray = gson.fromJson(new InputStreamReader(is), Event[].class);

					events.addAll(Arrays.asList(eventsArray));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			});
			paths.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return events;
	}

	public static void startupMenu() {
		Scanner scanner = new Scanner(System.in);
		int choice;

		do {
			System.out.println("=== Menu ===");
			System.out.println("1. Buscar eventos por nombre de fuente.");
			System.out.println("2. Buscar eventos entre rango de fechas.");
			System.out.println("3. Buscar eventos entre rango de valores.");
			System.out.println("0. Salir.");
			System.out.print("Introduzca selección: ");

			while (!scanner.hasNextInt()) {
				System.out.print("Selección inválida. Please enter a number (0-3): ");
				scanner.next();
			}
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Introduce nombre de la fuente: ");
				String name = scanner.next();

				List<Event> resBySourceName = evService.findBySourceName(name);
				printOutputResult(resBySourceName);
				break;
			case 2:
				System.out.println("Introduce fecha inicial en formato YYYY-MM-DD:");
				String dateInput1 = scanner.next();
				System.out.println("Introduce fecha final en formato YYYY-MM-DD:");
				String dateInput2 = scanner.next();

				List<Event> resByDateRange = evService.findByDateRange(dateInput1, dateInput2);
				printOutputResult(resByDateRange);
				break;
			case 3:
				System.out.println("Introduce valor mínimo: ");
				String min = scanner.next();
				System.out.println("Introduce valor máximo: ");
				String max = scanner.next();

				List<Event> resByValueRange = evService.findByValueRange(min, max);
				printOutputResult(resByValueRange);
				break;
			case 0:
				System.out.println("Saliendo del programa...");
				break;
			default:
				System.out.println("Selección inválida (0, 1, 2, o 3).");
			}

			System.out.println();

		} while (choice != 0);

		scanner.close();
	}

	public static void printOutputResult(List<Event> results) {
		if (results.size() > 0)
			results.forEach(e -> System.out.println(e.toString()));
		else
			System.out.println("No se han encontrado eventos con los parámetros especificados.");
	}

}
