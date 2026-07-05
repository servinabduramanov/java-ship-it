package ru.yandex.practicum;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class DeliveryCostTest {
    private static ParcelBox<StandardParcel> standardParcelBox = new ParcelBox<>(30);
    private static ParcelBox<FragileParcel> fragileParcelBox = new ParcelBox<>(10);
    private static ParcelBox<PerishableParcel> perishableParcelBox = new ParcelBox<>(20);

    @BeforeAll
    static void beforeAll() {
        StandardParcel standardParcel_1 = new StandardParcel("Одежда", 6, "Москва", 3);
        StandardParcel standardParcel_2 = new StandardParcel("Обувь", 8, "Москва", 3);
        standardParcelBox.addParcel(standardParcel_1);
        standardParcelBox.addParcel(standardParcel_2);

        FragileParcel fragileParcel_1 = new FragileParcel("Ваза", 3, "Москва", 3);
        FragileParcel fragileParcel_2 = new FragileParcel("Набор чашек", 2, "Москва", 3);
        fragileParcelBox.addParcel(fragileParcel_1);
        fragileParcelBox.addParcel(fragileParcel_2);

        PerishableParcel perishableParcel_1 = new PerishableParcel("Пирожки", 3, "Москва", 3, 2);
        PerishableParcel perishableParcel_2 = new PerishableParcel("Йогурт", 2, "Москва", 3, 20);
        perishableParcelBox.addParcel(perishableParcel_1);
        perishableParcelBox.addParcel(perishableParcel_2);
    }

    @Test
    void CalculatingTheParcelCostForEachType() {
        assertEquals(28, calculateSum(standardParcelBox.getAllParcels()));
        assertEquals(20, calculateSum(fragileParcelBox.getAllParcels()));
        assertEquals(15, calculateSum(perishableParcelBox.getAllParcels()));
    }

    private static <T extends Parcel> int calculateSum(ArrayList<T> parcels) {
        int sum = 0;
        for (T parcel : parcels) {
            sum += parcel.calculateDeliveryCost();
        }
        return sum;
    }

    @Test
    void сheckTheExpirationDate() {
        PerishableParcel perishableParcel = perishableParcelBox.getAllParcels().get(1); // йогурт

        boolean isExpired_1 = perishableParcel.isExpired(22);
        boolean isExpired_2 = perishableParcel.isExpired(23);
        boolean isExpired_3 = perishableParcel.isExpired(24);

        // истекает после 23
        assertFalse(isExpired_1);
        assertFalse(isExpired_2);
        assertTrue(isExpired_3);
    }

    @Test
    void verifyingTheAdditionOfParcelToBox() {
        assertTrue(fragileParcelBox.addParcel(new FragileParcel("Ваза 2", 4, "Москва", 3)));
        assertTrue(fragileParcelBox.addParcel(new FragileParcel("Ваза 3", 1, "Москва", 3)));
        assertFalse(fragileParcelBox.addParcel(new FragileParcel("Ваза 4", 1, "Москва", 3)));
    }
}
