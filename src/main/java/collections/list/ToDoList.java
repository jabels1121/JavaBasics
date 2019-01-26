package collections.list;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class ToDoList {

    private LinkedList<String> todoList = new LinkedList<>();

    public void addToList(String task) {
        addInAlphebeticalOrder(task);
        //todoList.add(task);
    }

    private boolean addInAlphebeticalOrder(String task) {
        ListIterator<String> listIterator = todoList.listIterator();
        while (listIterator.hasNext()) {
            int compared = listIterator.next().compareTo(task);
            if (compared == 0) {
                System.out.println("Task already exists in the list");
                return true;
            } else if (compared > 0) {
                listIterator.previous();
                listIterator.add(task);
                return true;
            }
        }
        todoList.add(task);
        return true;
    }

    public void addToListAtPosition(int pos, String newTask) {
        if (pos > todoList.size() || pos < 0) {
            addToList(newTask);
        } else {
            todoList.add(pos, newTask);
        }
    }

    public void printToDoList() {
        /*for (int i = 0; i < todoList.size(); i++) {
            System.out.println("Task #" + i + ": " + todoList.get(i));
        }*/
        Iterator<String> iterator = todoList.iterator();
        while (iterator.hasNext()) {
            System.out.println("Element " + iterator.next());
        }
    }

    public void changeTask(int pos, String newTask) {
        todoList.set(pos, newTask);
    }

    public void removeTask(String task) {
        todoList.remove(task);
        todoList.listIterator();
        todoList.iterator();
    }

    public int getTaskPriority(String task) {
        return todoList.indexOf(task);
    }
}
