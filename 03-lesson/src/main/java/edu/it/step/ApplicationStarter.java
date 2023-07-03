package edu.it.step;

public class ApplicationStarter {
    public static void main(String[] args) {
        MyList<Integer> nums = new MyListImpl<>();
        MyList<String> strings = new MyListImpl<>();

        for (int i = 0; i < 5; i++) {
            nums.add(i);
            strings.add(i + " ");
        }
        nums.addToStart(5);
        nums.delete(5);

        strings.deleteFromStart();
        strings.delete();

        nums.forEach(el -> print(el + " "));
        print("\n");
        strings.forEach(ApplicationStarter::print);
    }

    static void print(String text) {
        System.out.print(text);
    }
}
