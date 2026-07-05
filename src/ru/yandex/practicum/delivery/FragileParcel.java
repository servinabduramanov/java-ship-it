package ru.yandex.practicum.delivery;

public class FragileParcel extends Parcel implements Trackable{
    protected static final int PRICE_FOR_FRAGILE = 4;

    public FragileParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public void packageItem() {
        System.out.println("Посылка " + toString() + " обёрнута в защитную плёнку");
        System.out.println("Посылка " + toString() + " упакована");
    }

    @Override
    public int getBaseCost() {
        return PRICE_FOR_FRAGILE;
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.println("Хрупкая посылка " + description + " изменила местоположение на " + newLocation);
    }
}
