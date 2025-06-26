package org.powerreviews;

import org.junit.jupiter.api.Test;
import org.powerreviews.dto.Item;
import org.powerreviews.dto.ItemCount;
import org.powerreviews.util.PackUtils;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PackUtilsTest {
    @Test
    void testFillBag_ReturnsCorrectCounts() {
        List<Item> items = List.of(
                new Item("apples", 5),
                new Item("bread", 1),
                new Item("peanut butter", 2),
                new Item("trail mix", 3)
        );

        LinkedHashMap<String, Integer> itemCounts = new LinkedHashMap<>();
        items.forEach(item -> itemCounts.put(item.getName(), 1));

        int remainingCapacity = 16;

        List<ItemCount> result = PackUtils.fillBag(items, itemCounts, remainingCapacity);

        Map<String, Integer> resultMap = new HashMap<>();
        result.forEach(ic -> resultMap.put(ic.item(), ic.count()));

        assertEquals(4, resultMap.get("apples"));
        assertEquals(2, resultMap.get("bread"));
        assertEquals(1, resultMap.get("peanut butter"));
        assertEquals(1, resultMap.get("trail mix"));
    }

    @Test
    void testPrintBag_HasValidOutput() {
        List<ItemCount> bag = List.of(
                new ItemCount("apples", 4),
                new ItemCount("bread", 2),
                new ItemCount("peanut butter", 1),
                new ItemCount("trail mix", 1)
        );

        String json = PackUtils.printBag(bag);
        assertTrue(json.contains("\"item\":\"apples\""));
        assertTrue(json.contains("\"count\":4"));
    }
}
