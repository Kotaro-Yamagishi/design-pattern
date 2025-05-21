package com.headfirst.designpattern.iterator;

import java.util.List;

public class PancakeHouseMenu {
    List<MenuItem> menuItems;

    public PancakeHouseMenu() {
        menuItems = List.of(
                new MenuItem("K&B's Pancake Breakfast", "Pancakes with scrambled eggs, and toast", true, 2.99),
                new MenuItem("Regular Pancake Breakfast", "Pancakes with fried eggs, sausage", false, 2.99),
                new MenuItem("Blueberry Pancakes", "Pancakes made with fresh blueberries", true, 3.49),
                new MenuItem("Waffles", "Waffles, with your choice of blueberries or strawberries", true, 3.59)
        );
    }
    public List<MenuItem> getMenuItems() {
        return menuItems;
    }
    public Iterator createIterator() {
        return new PancakeHouseMenuIterator(menuItems);
    }
}
