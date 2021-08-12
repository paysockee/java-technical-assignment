package kata.supermarket;

public abstract class AbstractDiscountScheme implements DiscountScheme {

    private final Basket basket;

    public AbstractDiscountScheme(Basket basket) {
        this.basket = basket;
    }

    @Override
    public Basket getBasket() {
        return basket;
    }
}
