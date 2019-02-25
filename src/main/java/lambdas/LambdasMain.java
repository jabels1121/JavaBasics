package lambdas;

import java.util.Arrays;
import java.util.Comparator;

public class LambdasMain {

    public static void main(String[] args) {
        String[] colours = {"green", "blue", "white", "black", "yellow"};

        Player player1 = new Player("Alex", 30);
        Player player2 = new Player("Sam", 64);
        Player player3 = new Player("Elijah", 92);
        Player player4 = new Player("Jazy", 122);
        Player player5 = new Player("Lessy", 82);

        Player[] players = {player1, player2, player3, player4, player5};

        Arrays.sort(players, (p1, p2) -> {
            if ((p2.getScore() - p1.getScore()) != 0) {
                return p2.getScore() - p1.getScore();
            } else {
                return p2.getName().compareTo(p1.getName());
            }
        });

        System.out.println(Arrays.toString(players));

        Arrays.sort(colours, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s2.charAt(s2.length() - 1) - s1.charAt(s1.length() - 1);
            }
        });

        System.out.println(Arrays.toString(colours));

        // Лямбда выражение - анонимная функция, которая имплементирует абстрактный метод функционального интерфейса
        Arrays.sort(colours, (s1, s2) ->
                s2.charAt(s2.length() - 1) - s1.charAt(s1.length() - 1));

        System.out.println(Arrays.toString(colours));
    }

    private static final class Player {
        private String name;
        private int score;

        public Player(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        @Override
        public String toString() {
            return "Player{" +
                    "name='" + name + '\'' +
                    ", score=" + score +
                    '}';
        }
    }
}
