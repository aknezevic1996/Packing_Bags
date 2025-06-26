package org.powerreviews;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.powerreviews.dto.Item;
import org.powerreviews.dto.ItemCount;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class BagPackerTest {
    private static final List<Item> items = List.of(
            new Item("apples", 5),
            new Item("bread", 1),
            new Item("peanut butter", 2),
            new Item("trail mix", 3)
    );

    private LinkedHashMap<String, Integer> itemCounts;

    @BeforeEach
    void setUp() {
        itemCounts = new LinkedHashMap<>();
    }

    @Test
    void testPack_ReturnsCorrectCounts() {
        int capacity = 27;
        BagPacker packer = new BagPacker(itemCounts, items, capacity);

        List<ItemCount> result = packer.pack();
        assertNotNull(result);

        Map<String, Integer> counts = new HashMap<>();
        result.forEach(ic -> counts.put(ic.item(), ic.count()));

        assertEquals(4, counts.get("apples"));
        assertEquals(2, counts.get("bread"));
        assertEquals(1, counts.get("peanut butter"));
        assertEquals(1, counts.get("trail mix"));
    }

    @Test
    void testPack_CapacityTooSmall_ReturnsNull() {
        BagPacker packer = new BagPacker(itemCounts, items, 5);
        List<ItemCount> result = packer.pack();
        assertNull(result);
    }

    @Test
    void testPack_NullName_ReturnsNull() {
        List<Item> badItems = List.of(
                new Item(null, 5),
                new Item("bread", 1)
        );

        BagPacker packer = new BagPacker(itemCounts, badItems, 20);
        assertNull(packer.pack());
    }

    @Test
    void testPack_EmptyName_ReturnsNull() {
        List<Item> badItems = List.of(
                new Item("", 5),
                new Item("bread", 1)
        );

        BagPacker packer = new BagPacker(itemCounts, badItems, 20);
        assertNull(packer.pack());
    }

    @Test
    void testPack_NullWeight_ReturnsNull() {
        List<Item> badItems = List.of(
                new Item("apples", null),
                new Item("bread", 1)
        );

        BagPacker packer = new BagPacker(itemCounts, badItems, 20);
        assertNull(packer.pack());
    }

    @Test
    void testPack_NegativeWeight_ReturnsNull() {
        List<Item> badItems = List.of(
                new Item("apples", -5),
                new Item("bread", 1)
        );

        BagPacker packer = new BagPacker(itemCounts, badItems, 20);
        assertNull(packer.pack());
    }

    @Test
    void testPack_NullList_ReturnsNull() {
        BagPacker packer = new BagPacker(itemCounts, null, 20);
        assertNull(packer.pack());
    }

    @Test
    void testPack_EmptyList_ReturnsNull() {
        BagPacker packer = new BagPacker(itemCounts, Collections.emptyList(), 20);
        assertNull(packer.pack());
    }
}
