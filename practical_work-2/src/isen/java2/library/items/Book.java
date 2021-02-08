package isen.java2.library.items;

public class Book extends CulturalItem {
    String author;

    public Book() {
    }

    @Override
    public void print() {
        System.out.println("'" + this.title + "' written by " + author);
    }
}
