package org.powerreviews;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.powerreviews.dto.Item;
import org.powerreviews.dto.ItemCount;
import org.powerreviews.util.PackUtils;

import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
@AllArgsConstructor
public class BagPacker {
    private final LinkedHashMap<String, Integer> itemCounts;
    private final List<Item> items;
    private final int capacity;

    public List<ItemCount> pack() {
        int minCapacity = 0;

        if (items == null || items.isEmpty()) {
            log.error("itemsAvailable JSON array is null or empty!");
            return null;
        }

        for (Item item : items) {
            if (item.getName() == null || item.getName().isEmpty()) {
                log.error("Item name is null or empty!");
                return null;
            }
            else if (item.getWeight() == null || item.getWeight() <= 0) {
                log.error("{} weight is null or non-positive!", item.getName());
                return null;
            }

            itemCounts.put(item.getName(), 1);
            minCapacity += item.getWeight();
        }

        if (capacity < minCapacity) {
            log.error("Input capacity cannot be less than {}.", minCapacity);
            return null;
        }

        int remainCapacity = capacity - minCapacity;

        return PackUtils.fillBag(items, itemCounts, remainCapacity);
    }
}
