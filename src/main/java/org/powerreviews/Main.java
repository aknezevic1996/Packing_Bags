package org.powerreviews;

import org.powerreviews.dto.Item;
import org.powerreviews.dto.ItemCount;
import org.powerreviews.util.PackUtils;

import java.util.LinkedHashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Item> items = PackUtils.createAvailableItems();

        int capacity = PackUtils.getInput();

        BagPacker packer = new BagPacker(new LinkedHashMap<>(), items, capacity);
        List<ItemCount> packedBag = packer.pack();

        if (packedBag != null) {
            System.out.println(PackUtils.printBag(packedBag));
        }
    }
}
