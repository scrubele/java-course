package isen.java2.library.items;

import isen.java2.library.Genre;

import java.util.HashSet;
import java.util.Set;

public abstract class CulturalItem {
    String title;

    Set<Genre> genres = new HashSet<>();

    CulturalItem() {
    }

    CulturalItem(String title, Set <Genre> genres) {
        this.title = title;
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public abstract void print();

}
