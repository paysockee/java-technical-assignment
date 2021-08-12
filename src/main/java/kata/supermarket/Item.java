package kata.supermarket;

import java.math.BigDecimal;

public interface Item extends Comparable<Item> {
    BigDecimal price();

    @Override
    default int compareTo(Item o) {
        return -1;
    }
}
