package isen.java2.library;

import isen.java2.library.items.Book;
import isen.java2.library.items.CulturalItem;
import isen.java2.library.items.Film;

import java.util.*;

public class Library {
    List<Book> booksList;
    List<Film> filmList;

    Library() {
        booksList = new ArrayList<>();
        filmList = new ArrayList(){
        };
    }

    public void add(Film film) {
        this.filmList.add(film);
    }

    public void add(Book book) {
        this.booksList.add(book);
    }

    public void sortCatalogue(){
        booksList.sort(Comparator.<Book, String>comparing(Book::getTitle));
        filmList.sort(Comparator.<Film, String>comparing(Film::getTitle));
    }
    public void printCatalogue() {
        sortCatalogue();
        System.out.println("Film list:" + Arrays.toString(filmList.toArray()));
        System.out.println("Book list:" + Arrays.toString(booksList.toArray()));
    }
    public void searchByGenre(Genre genre){
        Collections.binarySearch(filmList, Comparator.<CulturalItem,Genre>comparing();

    }
}
