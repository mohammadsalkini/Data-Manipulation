package parser;

import models.Movies;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MoviesParser {

    public List<Movies> getMovies() throws IOException {
        String filePath = "C:\\Users\\engsa\\new project\\stream test\\src\\data\\movies.csv";
        Stream<String> lines = Files.lines(Paths.get(filePath));
        return lines.skip(1)
                .map(line -> line.split(";"))
                .filter(line -> line.length >= 22)
                .map(toMovies())
                .collect(Collectors.toList());
    }

    public static Function<String[], Movies> toMovies() {
        return columns -> {
            List<String> genres = Stream.of(columns[3])
                    .filter(line -> line.length() > 2)
                    .map(s -> s.split("}, "))
                    .flatMap(column -> Stream.of(column))
                    .map(s -> s.replaceAll("\\[", "").replaceAll("]", "").replaceAll("\\{", "").replaceAll("}", ""))
                    .map(s -> s.split(", "))
                    .flatMap(column -> Stream.of(column))
                    .filter(column -> column.contains("'name': "))
                    .map(genre -> genre.replaceAll("'name': ", "").replaceAll("'", ""))
                    .map(genre -> genre.trim())
                    .collect(Collectors.toList());

            Movies movie = new Movies();
            movie.setName(columns[8]);
            movie.setRating(Double.parseDouble(columns[22]));
            movie.setGenres(genres);
            return movie;
        };
    }
}