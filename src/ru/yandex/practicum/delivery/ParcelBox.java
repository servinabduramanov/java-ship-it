package ru.yandex.practicum.delivery;

import java.util.ArrayList;

public class ParcelBox <T extends Parcel> {
    protected final int maxWeight;
    protected int totalWeight;
    private ArrayList<T> parcels = new ArrayList<>();

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
        this.totalWeight = 0;
    }

    public boolean addParcel(T parcel) {
        if (parcel.weight > maxWeight - totalWeight) {
            System.out.println("Превышен максимальный вес!");
            return false;
        }

        parcels.add(parcel);
        totalWeight += parcel.weight;
        return true;
    }

    public ArrayList<T> getAllParcels() {
        return parcels;
    }
}
