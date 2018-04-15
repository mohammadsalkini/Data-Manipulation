package view;

public class SummaryDisplayer {
    public void moviesDisplay(Summary summary) {
        System.out.println("Movies Database Summary\n-------");
        System.out.println("Most rated films:");
        summary.getTopRatedMovies()
                .forEach(movie -> System.out.println(movie));
        System.out.println("-------");
    }

    public void actorsDisplay(Summary summary) {
        System.out.println("-------");
        System.out.println("Most hired actors:");
        summary.getTopHiredActors()
                .forEach(actor -> System.out.println(actor));
        System.out.println("-------");
    }

    public void mostAppearingMovieGenres(Summary summary){
        System.out.println("-------");
        System.out.println("Most appearing movie genres:");
        summary.getTopGenres()
                .forEach(genre -> System.out.println(genre));
        System.out.println("-------");
    }

    public void actorsMaleFemaleRatio(Summary actorsGenderSummery){
        System.out.println("-------");
        System.out.println("Actors male-female ratio:");
        actorsGenderSummery.getActorsMaleFemaleRatio()
                .forEach(ratio -> System.out.println(ratio));
        System.out.println("-------");
    }

    public void display(Summary summary) {
        moviesDisplay(summary);
        actorsDisplay(summary);
        mostAppearingMovieGenres(summary);
        actorsMaleFemaleRatio(summary);
    }

    public void display() {
        System.out.println("Foo");
    }
}
