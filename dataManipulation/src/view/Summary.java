package view;

import java.util.List;

public class Summary {
    private List<String> topRatedMovies;
    private List<String> topHiredActors;
    private List<String> topGenres;
    private List<String> actorsMaleFemaleRatio;

    public List<String> getActorsMaleFemaleRatio() {
        return actorsMaleFemaleRatio;
    }

    public void setActorsMaleFemaleRatio(List<String> actorsMaleFemaleRatio) {
        this.actorsMaleFemaleRatio = actorsMaleFemaleRatio;
    }

    public List<String> getTopGenres() {
        return topGenres;
    }

    public void setTopGenres(List<String> topGenres) {
        this.topGenres = topGenres;
    }

    public List<String> getTopRatedMovies() {
        return topRatedMovies;
    }

    public void setTopRatedMovies(List<String> topRatedMovies) {
        this.topRatedMovies = topRatedMovies;
    }

    public List<String> getTopHiredActors() {
        return topHiredActors;
    }

    public void setTopHiredActors(List<String> topHiredActors) {
        this.topHiredActors = topHiredActors;
    }


}
