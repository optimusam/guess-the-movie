import java.io.*;
import java.util.*;
public class GuessTheMovie {
    public static ArrayList<String> pickMovie() {
        ArrayList<String> movies = new ArrayList<String>();
        try {
            File file = new File("movie-titles.txt");
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String movie_name = fileScanner.nextLine().split("\t")[1];
                movies.add(movie_name);
            }
            return movies;
        } catch (FileNotFoundException e) {
            System.out.println("File not found :/");
            return null;
        }
    }
    public static void main(String[] args) {
        final int MAX_CHANCES = 10; // change this value of maximum no of guesses one can do
        Scanner sc= new Scanner(System.in);
        ArrayList<String> movies = new ArrayList<String>();
        movies = pickMovie();
        int len = movies.size();
        // pick random  movie
        Random rand = new Random();
        String movie = movies.get(rand.nextInt(len)).toUpperCase();
        //creating puzzle
        String puzzle = "";
        for (char c: movie.toCharArray()) {
            if(c == ' ') {
                puzzle+=" ";
            }
            else {
                puzzle+="_";
            }
        }
        //make the user guess
        String answer=puzzle;
        String wrong_letters = "";
        int chances = 0;
        while(chances<MAX_CHANCES || !(answer.contains("_"))) {
            try {
                Runtime.getRuntime().exec("cls");
            } catch (IOException e) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
            System.out.println("You are guessing: "+puzzle);
            System.out.println("You have guessed "+chances+" wrong letters : "+wrong_letters);
            System.out.println("You can guess maximum of "+MAX_CHANCES+ " chances");
            System.out.print("Guess a letter: ");
            char ch = sc.next().toUpperCase().charAt(0);
            boolean wrongGuess = true;
            answer = puzzle;
            puzzle ="";
            for(int i=0; i<movie.length(); i++) {
                if(movie.charAt(i) == ch) {
                    puzzle+=ch;
                    wrongGuess = false;

                }
                else if(answer.charAt(i)=='_') {
                    puzzle+="_";
                }
                else {
                    puzzle+=answer.charAt(i);
                }
            }
            puzzle = puzzle.toUpperCase();
            answer = puzzle;
            if(answer.equalsIgnoreCase(movie)) {
                System.out.println("Congrats! You Won! The movie was "+movie+".");
                return;
            }
            if(wrongGuess && wrong_letters.indexOf(ch)==-1) {
                wrong_letters+=ch+" ".toUpperCase();
                chances++;
            }
        }
        System.out.println("You lost! Try again maybe? ;)\nThe movie was "+movie+".");

    }
}
