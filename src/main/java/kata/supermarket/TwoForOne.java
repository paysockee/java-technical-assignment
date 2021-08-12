package kata.supermarket;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class TwoForOne extends AbstractDiscountScheme {

    private final String discountedProduct;

    public TwoForOne(Basket basket, String discountedProduct) {
        super(basket);
        this.discountedProduct = discountedProduct;
    }

    @Override
    public BigDecimal price() {

        BigDecimal result = new BigDecimal(0);

        boolean hasOne = false;

        for (Item item : getBasket().items()) {
            if (!discountedProduct.equals(item.toString())) {
                continue;
            }

            if (hasOne) {
                result = result.add(item.price());
                hasOne = false;
            } else {
                hasOne = true;
            }
        }
        return result.negate();
    }
}
