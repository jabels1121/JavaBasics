package collections.list;

import java.util.Scanner;

public class ListRunner {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ToDoList list = new ToDoList();
        printOut();
        int param = scanner.nextInt();

        while (param != 0) {
            switch (param) {
                case 1:
                    System.out.println("Please enter a task to add");
                    scanner.nextLine();
                    String task = scanner.nextLine();
                    list.addToList(task);
                    System.out.println("Please choose an action by typing [0-6]");
                    param = scanner.nextInt();
                    break;
                case 2:
                    System.out.println("Printing out TO DO LIST");
                    list.printToDoList();
                    System.out.println("Please choose an action by typing [0-6]");
                    param = scanner.nextInt();
                    break;
                case 3:
                    System.out.println("Please enter an item number to update");
                    scanner.nextLine();
                    int i = scanner.nextInt();
                    System.out.println("Please enter a new task");
                    scanner.nextLine();
                    String task3 = scanner.nextLine();
                    list.changeTask(i, task3);
                    System.out.println("Please choose an action by typing [0-6]");
                    param = scanner.nextInt();
                    break;
                case 4:
                    System.out.println("Please enter a task to remove");
                    scanner.nextLine();
                    String task4 = scanner.nextLine();
                    list.removeTask(task4);
                    System.out.println("Please choose an action by typing [0-6]");
                    param = scanner.nextInt();
                    break;
                case 5:
                    System.out.println("Please enter the task to get priority");
                    scanner.nextLine();
                    String task5 = scanner.nextLine();
                    int taskPriority = list.getTaskPriority(task5);
                    if (taskPriority != -1) {
                        System.out.println("Priority of the task is: " + taskPriority);
                        System.out.println("Please choose an action by typing [0-6]");
                        param = scanner.nextInt();
                    } else {
                        System.out.println("Wrote task is not exist.");
                        System.out.println("Please choose an action by typing [0-6]");
                        param = scanner.nextInt();
                    }
                    break;
                case 6:
                    System.out.println("Please enter a number of position for the task");
                    scanner.nextLine();
                    int idx = scanner.nextInt();
                    System.out.println("Please enter a new task");
                    scanner.nextLine();
                    String task6 = scanner.nextLine();
                    list.addToListAtPosition(idx, task6);
                    System.out.println("Please choose an action by typing [0-6]");
                    param = scanner.nextInt();
                    break;
                default:
                    param = 0;
            }
        }

    }

    private static void printOut() {
        System.out.println("Please choose an action. Press :\n" +
                "1 to add a new item into ToDoList\n" +
                "2 to print out the list\n" +
                "3 to update and existing item\n" +
                "4 to remove an item from the list\n" +
                "5 to get task priority or number in the list\n" +
                "6 to add a new item at specific position\n" +
                "press 0 for exit\n\n" +
                "AFTER CHOOSING AN OPTION PLEASE PRESS ENTER");
    }

}
