package kata.supermarket;

import java.math.BigDecimal;

public class Product {

    private final String name;
    private final BigDecimal pricePerUnit;

    public Product(String name, final BigDecimal pricePerUnit) {
        this.name = name;
        this.pricePerUnit = pricePerUnit;
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }

    public String getName() {
        return name;
    }
}
