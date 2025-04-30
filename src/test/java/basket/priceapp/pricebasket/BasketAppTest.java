package basket.priceapp.pricebasket;

import basket.priceapp.pricebasket.model.Basket;
import basket.priceapp.pricebasket.model.Promotion;
import basket.priceapp.pricebasket.service.OfferService;
import basket.priceapp.pricebasket.service.impl.OfferServiceImpl;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BasketAppTest {

    @Test
    public void testApplyPromotionWithAppleMilkBread() {
        // Arrange: Prépare basket
        Basket basket = new Basket();
        basket.addProduct("apples");
        basket.addProduct("milk");
        basket.addProduct("bread");

        Promotion promotion = new Promotion("apples", "apples", 1, 10.0);
        List<Promotion> promotions = List.of(promotion);

        StringBuilder sb = new StringBuilder();
        //Act: Applied a promotion
        OfferService offerService = new OfferServiceImpl();

        double discount = offerService.apply(basket, promotions, sb);

        // Assert: check the result
        assertEquals(0.1, discount, 0.001);
        assertTrue(sb.toString().contains("apples 10% off"));
    }

    @Test
    public void testApplyPromotionWhenNotEligible() {
        // Arrange :Prépare basket
        Basket basket = new Basket();
        basket.addProduct("soup");
        basket.addProduct("bread");

        Promotion promotion = new Promotion("soup", "bread", 2, 50.0);
        List<Promotion> promotions = List.of(promotion);

        StringBuilder sb = new StringBuilder();
        //Act: Applied a promotion
        OfferService offerService = new OfferServiceImpl();

        double discount = offerService.apply(basket, promotions, sb);
        // Assert: check the result
        assertEquals(0.0, discount, 0.001);
        assertFalse(sb.toString().contains("bread 50% off"));
    }

    @Test
    public void testApplyPromotionWithEligibleDiscount() {
        //Arrange: Prépare basket
        Basket basket = new Basket();
        basket.addProduct("soup");
        basket.addProduct("soup");
        basket.addProduct("bread");

        Promotion promotion = new Promotion("soup", "bread", 2, 50.0);
        List<Promotion> promotions = List.of(promotion);

        StringBuilder sb = new StringBuilder();

        OfferService offerService = new OfferServiceImpl();
        //Act: Applied a promotion
        double discount = offerService.apply(basket, promotions, sb);
        // Assert: check the result
        assertEquals(0.4, discount, 0.001);
        assertTrue(sb.toString().contains("bread 50% off"));
    }


    @Test
    public void testApplesMilkBread() throws IOException {
        //Arrange
        String[] args = {"Apples", "Milk", "Bread"};
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        //act
        BasketApp.main(args);

        String out = output.toString();
        // Assert: check the result
        assertTrue(out.contains("Subtotal: £3.10"));
        assertTrue(out.contains("Apples 10% off: -10p"));
        assertTrue(out.contains("Total: £3.00"));
    }

    @Test
    public void testOnlyMilk() throws IOException {
        String[] args = {"milk"};
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        BasketApp.main(args);
        String out = output.toString();

        assertTrue(out.contains("Subtotal: £1.30"));
        assertTrue(out.contains("(No offers available)"));
        assertTrue(out.contains("Total: £1.30"));
    }

    @Test
    public void testSoupWithBread() throws IOException {
        String[] args = {"Soup", "Soup", "Bread", "Milk"};
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        BasketApp.main(args);
        String out = output.toString();

        assertTrue(out.contains("Subtotal: £3.40"));
        assertTrue(out.contains("Bread 50% off: -40p"));
        assertTrue(out.contains("Total: £3.00"));
    }

    @Test
    public void testOneSoupWithBread() throws IOException {
        String[] args = {"Soup", "Bread", "Milk"};
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        BasketApp.main(args);
        String out = output.toString();

        assertTrue(out.contains("Subtotal: £2.75"));
        assertTrue(out.contains("(No offers available)"));
        assertTrue(out.contains("Total: £2.75"));
    }

}
