package ru.yandex.practicum.delivery;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static HashMap<Parcel, String> trackedParcels = new HashMap<>();

    private static ParcelBox<StandardParcel> standardParcelBox = new ParcelBox<>(30);
    private static ParcelBox<FragileParcel> fragileParcelBox = new ParcelBox<>(10);
    private static ParcelBox<PerishableParcel> perishableParcelBox = new ParcelBox<>(20);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    changeLocationOfTrackedParcel();
                    break;
                case 5:
                    showBox();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Указать новое местоположение отслеживаемой посылки");
        System.out.println("5 - Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    // реализуйте методы ниже

    private static void addParcel() {
        System.out.print("Укажите описание посылки: ");
        String description = scanner.nextLine();
        System.out.print("Укажите тип посылки (1 - стандартная, 2 - хрупкая, 3 - скоропортящаяся): ");
        int type = Integer.parseInt(scanner.nextLine());
        System.out.print("Укажите вес посылки: ");
        int weight = Integer.parseInt(scanner.nextLine());
        System.out.print("Укажите адрес доставки: ");
        String deliveryAddress = scanner.nextLine();
        System.out.print("Укажите день месяца отправки (от 1 до 31): ");
        int sendDay = Integer.parseInt(scanner.nextLine());

        boolean isAdded;
        switch (type) {
            case 1:
                StandardParcel standardParcel = new StandardParcel(description, weight, deliveryAddress, sendDay);
                isAdded = standardParcelBox.addParcel(standardParcel);
                if (!isAdded) {
                    return;
                }
                allParcels.add(standardParcel);
                break;
            case 2:
                FragileParcel fragileParcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
                isAdded = fragileParcelBox.addParcel(fragileParcel);
                if (!isAdded) {
                    return;
                }
                allParcels.add(fragileParcel);
                trackedParcels.put(fragileParcel, "Собрана отправителем");
                break;
            case 3:
                System.out.print("Укажите срок хранения скоропортящийся посылки в днях: ");
                int timeToLive = Integer.parseInt(scanner.nextLine());
                PerishableParcel perishableParcel = new PerishableParcel(description, weight, deliveryAddress, sendDay, timeToLive);
                isAdded = perishableParcelBox.addParcel(perishableParcel);
                if (!isAdded) {
                    return;
                }
                allParcels.add(perishableParcel);
                break;
            default:
                System.out.println("Типа посылки под таким номером несуществует!");
                return;
        }
        System.out.println("Посылка успешно добавлена!");
    }

    private static void sendParcels() {
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        // Посчитать общую стоимость всех доставок и вывести на экран
        int sum = 0;
        for (Parcel parcel : allParcels) {
            sum += parcel.calculateDeliveryCost();
        }
        System.out.println("Общая стоимость всех доставок = " + sum);
    }

    private static void changeLocationOfTrackedParcel() {
        System.out.println("Список отслеживаемых посылок: ");
        int counter = 1;
        HashMap<Integer, Parcel> numberedParcels = new HashMap<>();
        for (Parcel parcel : trackedParcels.keySet()) {
            System.out.println(counter + " - " + parcel + " Текущий статус - " + trackedParcels.get(parcel));
            numberedParcels.put(counter, parcel);
            counter++;
        }
        System.out.print("Укажите номер посылки, у которой хотите указать новое местоположение: ");
        int number = Integer.parseInt(scanner.nextLine());
        if (number > trackedParcels.size() || number <= 0) {
            System.out.println("Такого номера посылки в списке несуществует!");
            return;
        }
        Parcel parcel = numberedParcels.get(number);

        System.out.print("Укажите новое местоположение: ");
        String newLocation = scanner.nextLine();
        trackedParcels.put(parcel, newLocation);

        System.out.println("Статус посылки успешно обновлен!");
    }

    private static void showBox() {
        System.out.print("Выберите тип коробки (1 - стандартная, 2 - хрупкая, 3 - скоропорт): ");
        int typeOfBox = Integer.parseInt(scanner.nextLine());
        switch (typeOfBox) {
            case 1:
                showListOfParcels(standardParcelBox.getAllParcels());
                break;
            case 2:
                showListOfParcels(fragileParcelBox.getAllParcels());
                break;
            case 3:
                showListOfParcels(perishableParcelBox.getAllParcels());
                break;
            default:
                System.out.println("Типа коробки под таким номером несуществует!");
                return;
        }
    }

    private static <T extends Parcel> void showListOfParcels(ArrayList<T> parcels) {
        System.out.println("Список посылок: ");
        for (T parcel : parcels) {
            System.out.println(parcel);
        }
    }
}

