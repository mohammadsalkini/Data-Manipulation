import controller.MovieDatabaseSummarizer;
import view.Summary;
import view.SummaryDisplayer;

import java.io.IOException;
import java.util.Optional;

public class AppMain {
    public static void main(String[] args) throws IOException {
        MovieDatabaseSummarizer summarizer = new MovieDatabaseSummarizer();
        SummaryDisplayer displayer = new SummaryDisplayer();

        Optional<Summary> summary = summarizer.summarize();
        if(summary.isPresent()){
            displayer.display(summary.get());
        } else {
            displayer.display();
        }
    }
}
