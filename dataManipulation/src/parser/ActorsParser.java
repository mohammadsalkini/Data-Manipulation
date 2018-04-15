package parser;

import models.Actors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ActorsParser {

    public List<Actors> getActor () throws IOException {
        String filePath = "C:\\Users\\engsa\\new project\\stream test\\src\\data\\actors.csv";
        List<Actors> actors = Files.lines(Paths.get(filePath))
                .skip(1)
                .map(line -> line.split(";")[0])
                .map(object -> object.split("}, "))
                .flatMap(columns -> Stream.of(columns))
                .map(s -> s.split(","))
                .filter(line -> line.length >= 5)
                .map(toActor())
                .collect(Collectors.toList());
        return actors;
    }

    private static Function<String [], Actors> toActor() {
        return fields -> {
            String name = Stream.of(fields)
                    .filter(a -> a.contains("'name': "))
                    .map(a -> a.replaceAll("'name': ", "").replaceAll("'", ""))
                    .findFirst().get();

            String gender = Stream.of(fields)
                    .filter(a -> a.contains("'gender': "))
                    .map(a -> a.replaceAll("'gender': ", "").replaceAll("'", ""))
                    .map(a -> a.contains("2") ? "Male" : "Female")
                    .findFirst().get();
            Actors actor = new Actors();
            actor.setName(name);
            actor.setGender(gender);
            return actor;
        };
    }

}