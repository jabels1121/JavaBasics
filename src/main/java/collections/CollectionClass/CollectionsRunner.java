package collections.CollectionClass;

import java.util.*;

public class CollectionsRunner {

    public static void main(String[] args) {

        List<Card> deckOfCards = new ArrayList<>();

        for (Card.Face face : Card.Face.values()) {
            for (Card.Suit suit : Card.Suit.values()) {
                deckOfCards.add(new Card(suit, face));
            }
        }

//        System.out.println("Original deck of cards");
        /*for (Card card : deckOfCards) {
            if (String.valueOf(card.suit).equals("SPADES")) {
                System.out.printf(card.face.toString() + " of SPADES, ");
            }
        }*/
        /*Collections.shuffle(deckOfCards);
        System.out.println("Card after shuffle");
        printOutput(deckOfCards);


        Collections.sort(deckOfCards);
        System.out.println("\n\nCards after sorting");
        printOutput(deckOfCards);

        Collections.sort(deckOfCards, new CardComparator());
        System.out.println("\n\nCards after sorting with custom comparator");
        printOutput(deckOfCards);*/
        Card card = new Card(Card.Suit.HEARTS, Card.Face.Ace);

        List<Card> cardList = new ArrayList<>(deckOfCards);
        Collections.fill(cardList, card);
        //printOutput(cardList);
        Collections.addAll(cardList, card, card, card);
        Collections.copy(cardList, deckOfCards);
        int frequency = Collections.frequency(cardList, card);
        System.out.println("Frequency of " + card + " is " + frequency);


    }

    private static void printOutput(List<Card> deckOfCards) {
        for (int i = 0; i < deckOfCards.size(); i++) {
            System.out.printf("%-20s %s", deckOfCards.get(i), (i + 1) % 4 == 0 ? "\n" : "  ");
        }
    }

}
