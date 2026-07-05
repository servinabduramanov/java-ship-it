package ru.yandex.practicum.delivery;

public abstract class Parcel {
    protected String description;
    protected int weight;
    protected String deliveryAddress;
    protected int sendDay;

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

    @Override
    public String toString() {
        return description + " (вес - " + weight + ", адрес доставки - " + deliveryAddress + ").";
    }

    public abstract int getBaseCost();

    public int calculateDeliveryCost() {
        return weight * getBaseCost();
    }
}
