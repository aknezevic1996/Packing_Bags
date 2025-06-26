package org.powerreviews.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.powerreviews.dto.ItemCount;
import org.powerreviews.dto.Item;
import org.powerreviews.dto.ItemsAvailable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class PackUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<Item> createAvailableItems() {
        try (InputStream inputStream = PackUtils.class.getClassLoader().getResourceAsStream("items.json");
             BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {
            return mapper.readValue(reader, ItemsAvailable.class).getItemsAvailable();
        } catch (IOException e) {
            throw new RuntimeException("Unable to parse items.json!");
        }
    }

    public static int getInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter bag capacity:");
        int capacity;

        try {
            capacity = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Input capacity must be an integer value!");
        }

        return capacity;
    }

    public static List<ItemCount> fillBag(List<Item> items, LinkedHashMap<String, Integer> itemCounts,
                                       int remainingCapacity) {
        List<Item> sortedItems = items.stream()
                .sorted(Comparator.comparing(Item::getWeight).reversed())
                .toList();

        for (Item item : sortedItems) {
            int count = remainingCapacity / item.getWeight();
            if (count > 0) {
                itemCounts.put(item.getName(), itemCounts.get(item.getName()) + count);
                remainingCapacity -= count * item.getWeight();
            }
        }

        return itemCounts.entrySet().stream()
                .map(e -> new ItemCount(e.getKey(), e.getValue()))
                .toList();
    }

    public static String printBag(List<ItemCount> bag) {
        try {
            return mapper.writeValueAsString(bag);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to write output as JSON string!");
        }
    }
}
