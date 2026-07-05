package ru.yandex.practicum.delivery;

public class StandardParcel extends Parcel {
    protected static final int PRICE_FOR_STANDARD = 2;

    public StandardParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public int getBaseCost() {
        return PRICE_FOR_STANDARD; // реализация для стандартной посылки
    }
}
