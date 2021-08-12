package kata.supermarket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {

    private static final String AMERICAN_SWEETS = "American Sweets";
    private static final String PICK_AND_MIX = "Pick and Mix";
    private static final String DIGESTIVES = "Digestives";
    private static final String MILK = "Milk";

    @DisplayName("basket provides its total value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValue(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket();
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    @DisplayName("basket provides its discounted value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesDiscountedValue(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket();
        items.forEach(basket::add);
        addDiscounts(basket);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> basketProvidesTotalValue() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight()
        );
    }

    static Stream<Arguments> basketProvidesDiscountedValue() {
        return Stream.of(
                noItemsTwoForOne(),
                aSingleItemTwoForOne(),
                twoSameItemsPricedPerUnitTwoForOne(),
                threeSameItemsPricedPerUnitTwoForOne(),
                twoDiscountsInBasket()
        );
    }

    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of("a single weighed item", "1.25", Collections.singleton(twoFiftyGramsOfAmericanSweets()));
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "1.85",
                Arrays.asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix())
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "2.04",
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()));
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.49", Collections.singleton(aPintOfMilk()));
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList());
    }

    private static Item aPintOfMilk() {
        return new Product(MILK, new BigDecimal("0.49")).oneOf();
    }

    private static Item aPackOfDigestives() {
        return new Product(DIGESTIVES, new BigDecimal("1.55")).oneOf();
    }

    private static WeighedProduct aKiloOfAmericanSweets() {
        return new WeighedProduct(AMERICAN_SWEETS, new BigDecimal("4.99"));
    }

    private static Item twoFiftyGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"));
    }

    private static WeighedProduct aKiloOfPickAndMix() {
        return new WeighedProduct(PICK_AND_MIX, new BigDecimal("2.99"));
    }

    private static Item twoHundredGramsOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal(".2"));
    }

    private static Arguments noItemsTwoForOne() {
        return Arguments.of("no items with Two For One discount", "0.00", Collections.emptyList());
    }

    private static Arguments aSingleItemTwoForOne() {
        return Arguments.of("a single item priced per unit", "0.49", Collections.singleton(aPintOfMilk()));
    }

    private static Arguments twoSameItemsPricedPerUnitTwoForOne() {
        return Arguments.of("two items priced per unit with Two For One discount", "1.55",
                Arrays.asList(aPackOfDigestives(), aPackOfDigestives()));
    }

    private static Arguments threeSameItemsPricedPerUnitTwoForOne() {
        return Arguments.of("three items priced per unit with Two For One discount", "3.10",
                Arrays.asList(aPackOfDigestives(), aPackOfDigestives(), aPackOfDigestives()));
    }

    private static Arguments twoDiscountsInBasket() {
        return Arguments.of("five items priced per unit with Two For One and Three For Two discounts", "2.53",
                Arrays.asList(aPackOfDigestives(), aPackOfDigestives(), aPintOfMilk(), aPintOfMilk(), aPintOfMilk()));
    }

    private static void addDiscounts(Basket basket) {
        basket.add(new TwoForOne(basket, DIGESTIVES));
        basket.add(new ThreeForTwo(basket, MILK));
    }
}