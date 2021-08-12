package kata.supermarket;

import java.math.BigDecimal;

public class WeighedProduct {

    private String name;
    private final BigDecimal pricePerKilo;

    public WeighedProduct(String name, final BigDecimal pricePerKilo) {
        this.name = name;
        this.pricePerKilo = pricePerKilo;
    }

    BigDecimal pricePerKilo() {
        return pricePerKilo;
    }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
    }

    public String getName() {
        return name;
    }
}
