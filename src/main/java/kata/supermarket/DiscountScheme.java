package kata.supermarket;

public interface DiscountScheme extends Item {

    Basket getBasket();

    @Override
    default int compareTo(Item o) {
        return 1;
    }
}
