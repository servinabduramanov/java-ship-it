package ru.yandex.practicum.delivery;

public abstract class Parcel {
    protected String description;
    protected int weight;
    protected String deliveryAddress;
    protected int sendDay;

    protected static final int PRICE_FOR_STANDARD = 2;
    protected static final int PRICE_FOR_PARISHABLE = 3;
    protected static final int PRICE_FOR_FRAGILE = 4;

    public Parcel(String description, int weight, String deliveryAddress, int sendDay) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
    }

    public void packageItem() {
        System.out.println("Посылка " + toString() + " упакована");
    }

    public void deliver() {
        System.out.println("Посылка " + toString() + " доставлена по адресу " + deliveryAddress);
    }

    public int calculateDeliveryCost() {
        return weight * PRICE_FOR_STANDARD; // реализация для стандартной посылки
    }

    @Override
    public String toString() {
        return description + " (вес - " + weight + ", адрес доставки - " + deliveryAddress + ").";
    }
}
