package isen.java2.library.items;

import isen.java2.library.Genre;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Film extends CulturalItem {
    String director;
    Set<String> actors = new HashSet<>();
    Film() {
    }

    public Film(String title,  String director, Set<Genre> genres, Set<String> actors) {
        super(title, genres);
        this.director = director;
        this.actors = actors;
    }

    @Override
    public void print() {
        final StringBuilder builder = new StringBuilder();
        actors.forEach((val) -> {
            builder.append(val + ",");
        });
        String actorsString = builder.toString();
        System.out.println("'" + this.title + "' written by " + actorsString);
    }

    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", genres=" + genres +
                ", director='" + director + '\'' +
                ", actors=" + actors +
                '}';
    }
}
