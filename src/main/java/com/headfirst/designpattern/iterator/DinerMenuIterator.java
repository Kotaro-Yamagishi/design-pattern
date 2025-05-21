package com.headfirst.designpattern.iterator;

import java.util.Arrays;

public class DinerMenuIterator implements Iterator {
    private MenuItem[] items;
    private int position = 0;

    public DinerMenuIterator(MenuItem[] items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        return position < items.length && items[position] != null;
    }

    @Override
    public MenuItem next() {
        MenuItem menuItem = items[position];
        position++;
        return menuItem;
    }

    @Override
    public String toString() {
        return "DinerMenuIterator{" +
                "items=" + Arrays.toString(items) +
                ", position=" + position +
                '}';
    }
    
}
