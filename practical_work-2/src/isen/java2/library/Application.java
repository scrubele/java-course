package isen.java2.library;

import isen.java2.library.items.Film;

import java.util.HashSet;
import java.util.Set;

public class Application {

    public static void main(String[] args) {
        Library library = new Library();
        Film harryPotter = new Film("Inception", "Director", Set.of(Genre.COMEDY), Set.of("Actor1"));
        System.out.println(harryPotter);
        library.add(harryPotter);
        library.add(new Film("Harry Potter", "Director", Set.of(Genre.COMEDY), Set.of("Actor1")));
        library.printCatalogue();

    }
}
