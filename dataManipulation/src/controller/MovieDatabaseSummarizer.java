package controller;

import models.Actors;
import models.Movies;
import parser.ActorsParser;
import parser.MoviesParser;
import view.Summary;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MovieDatabaseSummarizer {
    private Summary summary;

    public Summary getSummary() {
        if(summary == null){
            summary = new Summary();
        }
        return summary;
    }

    public Summary moviesSummarize (List<Movies> movies) {
        List<String> topRatedMovies = movies.stream()
                .sorted(Comparator.comparing(Movies::getRating).reversed())
                .limit(5)
                .map(e -> e.getName() + " (" + e.getRating() + ")")
                .collect(Collectors.toList());
        Summary summary = getSummary();
        summary.setTopRatedMovies(topRatedMovies);
        return summary;
    }

    public Summary actorsSummarize (List<Actors> actors) {
        Map<String, Long> appearances = actors.stream()
                .map(name -> name.getName())
                .flatMap(name -> Stream.of(name))
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        List<String> topHiredActors = appearances.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(5)
                .map(e -> e.getKey() + " (" + e.getValue() + ")")
                .collect(Collectors.toList());
        Summary summary = getSummary();
        summary.setTopHiredActors(topHiredActors);
        return summary;
    }

    public Summary genresSummarize (List<Movies> movies) {
        Map<String, Long> genres = movies.stream()
                .map(genre -> genre.getGenres())
                .flatMap(genre -> genre.stream())
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        List<String> topGenres = genres.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(2)
                .map(e -> e.getKey() + " (" + e.getValue() + ")")
                .collect(Collectors.toList());
        Summary summary = getSummary();
        summary.setTopGenres(topGenres);
        return summary;
    }

    public Summary actorsMaleFemaleRatioSummarize (List<Actors> actors) {
        float size = actors.size();
        String ratio = actors.stream()
                .map(ratios -> ratios.getGender())
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()))
                .entrySet().stream()
                .map(stringLongEntry -> stringLongEntry.getKey() + " ("+ (((stringLongEntry.getValue())*100)/size) + "%) ")
                .collect(Collectors.joining(""));
        Summary summary = getSummary();
        summary.setActorsMaleFemaleRatio(Collections.singletonList(ratio));

        return summary;



    }

    public Optional<Summary> summarize() throws IOException {
        MoviesParser moviesParser = new MoviesParser();
        List<Movies> rating = moviesParser.getMovies();
        List<Movies> genre = moviesParser.getMovies();

        // get summarized movies rating from the List of rating
        this.moviesSummarize(rating);
        this.genresSummarize(genre);

        ActorsParser actorsParser = new ActorsParser();
        List<Actors> appearance = actorsParser.getActor();
        List<Actors> genders = actorsParser.getActor();


        // get summarized actors names from the List of appearance
        this.actorsSummarize(appearance);

        // get summarized actors genders ratio from the List of genders
        this.actorsMaleFemaleRatioSummarize(genders);
        return Optional.of(summary);
    }
}
