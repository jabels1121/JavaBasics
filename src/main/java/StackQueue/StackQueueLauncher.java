package StackQueue;

import collections.CollectionClass.Card;
import collections.CollectionClass.CardComparator;

import java.util.*;

public class StackQueueLauncher {

    public static void main(String[] args) {
        //passengerProcessing();
        //queueExample();
        //System.out.println(Passenger.number);
        Queue<Card> cardDeck = new PriorityQueue<>();
        for (Card.Face face : Card.Face.values()) {
            for (Card.Suit suit : Card.Suit.values()) {
                cardDeck.offer(new Card(suit, face));
            }
        }

        Deque<Card> cards = new ArrayDeque<>();
        Card card = new Card(Card.Suit.CLUBS, Card.Face.Deuce);

        for (int i = 0; i < 10; i++) {
            cards.offer(cardDeck.poll());
        }

        //System.out.println(cards.removeFirstOccurrence(card));

        Deque<Integer> ints = new ArrayDeque<>();

        for (int i = 0; i < 15; i++) {
            ints.offerFirst(i);
        }

        for (int i : ints) {
            System.out.println(i);
        }

        System.out.println(ints.removeFirstOccurrence(10));
        for (int i : ints) {
            System.out.println(i);
        }
    }

    private static void passengerProcessing() {
        Stack<Passenger> bus = new Stack<>();
        Passenger passenger = new Passenger("Katerina", "Petrova");
        bus.push(new Passenger("Ilya", "Grishaev"));
        bus.push(new Passenger("Alexey", "Grishaev"));
        bus.push(new Passenger("Viktoria", "Kazakova"));
        bus.push(new Passenger("Nikolodze", "Zarechkin"));
        bus.push(passenger);
        bus.push(new Passenger("Egor", "Grishatov"));

        System.out.println("Last entered passenger is: " + bus.peek());
    }

    private static void queueExample() {
        Queue<Card> cardDeck = new PriorityQueue<>(52, new CardComparator());
        for (Card.Face face : Card.Face.values()) {
            for (Card.Suit suit : Card.Suit.values()) {
                cardDeck.offer(new Card(suit, face));
            }
        }

        Card card = new Card(Card.Suit.CLUBS, Card.Face.King);
        System.out.println("==================\nCardDeck size is: " + cardDeck.size());
        for (int i = 0; i < 10; i++) {
            System.out.println(cardDeck.peek());
        }
        System.out.println("==================\nCardDeck size is: " + cardDeck.size());
        System.out.println(cardDeck.remove());
        System.out.println(cardDeck.element());
        System.out.println("==================\nCardDeck size is: " + cardDeck.size());

        Iterator<Card> iterator = cardDeck.iterator();
        while (iterator.hasNext()) {
            System.out.println(cardDeck.poll());
        }
    }


    public static class Passenger {

        private static int number;
        private String name;
        private String surName;

        public Passenger(String name, String surName) {
            number++;
            this.name = name;
            this.surName = surName;
        }

        public int getNumber() {
            return number;
        }

        public String getName() {
            return name;
        }

        public String getSurName() {
            return surName;
        }

        @Override
        public String toString() {
            return "Passenger " + number + " is " + name + " " + surName;
        }
    }

}
