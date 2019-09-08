package com.mvnikitin.lesson1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Задание 3. Большая задача.
 */
public class FruitHandling {

    public static void main(String[] args) {

        Box<Apple> appleBox1 = new Box<>(
                new Apple(),
                new Apple(),
                new Apple()
        );

        Box<Apple> appleBox2 = new Box<>(
                new Apple(),
                new Apple(),
                new Apple(),
                new Apple(),
                new Apple()
        );

        appleBox2.addItem(new Apple());
        // Так не работает
        // appleBox2.addItem(new Orange());

        Box<Orange> orangeBox = new Box<>();
        orangeBox.addItem(new Orange());
        orangeBox.addItem(new Orange());

        System.out.println("БЫЛО:");
        System.out.println("Ящик яблок 1 весит " + appleBox1.getWeight());
        System.out.println("Ящик яблок 2 весит " + appleBox2.getWeight());
        System.out.println("Ящик апельсин весит " + orangeBox.getWeight());
        System.out.println("Ящик яблок 1 вест как ящик апельсин: " + appleBox1.compare(orangeBox));
        System.out.println();

        // Gересыпаем яблоки из яшика 1 в ящик 2:
        appleBox1.moveAll(appleBox2);

        // Так не работает:
        //appleBox1.moveAll(orangeBox);

        System.out.println("СТАЛО:");
        System.out.println("Ящик яблок 1 весит " + appleBox1.getWeight());
        System.out.println("Ящик яблок 2 весит " + appleBox2.getWeight());
        System.out.println("Ящик апельсин весит " + orangeBox.getWeight());
        System.out.println("Ящик яблок 1 вест как ящик апельсин: " + appleBox1.compare(orangeBox));
    }
}

class Box<T extends Fruit> {

    ArrayList<T> boxItems;

    public Box() {
        boxItems = new ArrayList<>();
    }

    public Box(T... items) {
        this();
        Collections.addAll(boxItems,items);
    }

    public float getWeight() {
        return (boxItems.size() > 0 ?
                boxItems.get(0).getWeight() * boxItems.size() : 0);
    }

    public boolean addItem(T item) {
        return boxItems.add(item);
    }

    public boolean compare(Box<? extends Fruit> other) {
        if (Math.abs(this.getWeight() - other.getWeight()) < 0.0001)
            return true;
        return false;
    }

    public void moveAll(Box<T> recipient) {

        Iterator<T> iter = boxItems.iterator();

        while(iter.hasNext()) {
            recipient.addItem(iter.next());
            iter.remove();
        }
    }
}

abstract class Fruit {
    public abstract float getWeight();
}

class Apple extends Fruit {

    private final static float APPLE_WEIGHT = 1.0f;

    @Override
    public float getWeight() {
        return APPLE_WEIGHT;
    }
}

class Orange extends Fruit {

    private final static float ORANGE_WEIGHT = 1.5f;

    @Override
    public float getWeight() {
        return ORANGE_WEIGHT;
    }
}

//Результат:
//        БЫЛО:
//        Ящик яблок 1 весит 3.0
//        Ящик яблок 2 весит 6.0
//        Ящик апельсин весит 3.0
//        Ящик яблок 1 вест как ящик апельсин: true
//
//        СТАЛО:
//        Ящик яблок 1 весит 0.0
//        Ящик яблок 2 весит 9.0
//        Ящик апельсин весит 3.0
//        Ящик яблок 1 вест как ящик апельсин: false
