package kata.supermarket;

import java.math.BigDecimal;

public class ThreeForTwo extends AbstractDiscountScheme {

    private final String discountedProductName;

    public ThreeForTwo(Basket basket, String discountedProductName) {
        super(basket);
        this.discountedProductName = discountedProductName;
    }

    @Override
    public BigDecimal price() {
        BigDecimal result = new BigDecimal(0);

        int count = 0;

        for (Item item : getBasket().items()) {
            if (!discountedProductName.equals(item.toString())) {
                continue;
            }

            if (count == 2) {
                result = result.add(item.price());
                count = 0;
            } else {
                count++;
            }
        }
        return result.negate();
    }
}
