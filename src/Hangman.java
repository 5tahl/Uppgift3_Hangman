import java.util.ArrayList;

public class Hangman {
    private String wordToGuess;
    private Integer guesses;
    private String[][] hangman = new String[7][5];
    ArrayList hiddenWord = new ArrayList();

    // Constructor
    public Hangman(String wordToGuess) {
        setWordToGuess(wordToGuess);
        guesses = 0;
    }

    // Getter and setter.
    public String getWordToGuess() {
        return wordToGuess;
    }
    public void setWordToGuess(String wordToGuess) {
            this.wordToGuess = wordToGuess;
            makeHiddenWord();
    }


    // Method that shows how many wrong guesses are used and how many that are left until failure.
    public void checkGuesses() {
        System.out.println("You have used " + guesses + " guesses, you have " + (11 - guesses) + " guesses left.");
    }
    // Method that checks if all the guesses have been used.
    public boolean allGuessesUsed() {
        if (guesses >= 11) {
            System.out.println("You have used all of your guesses, you lose.");
            drawHangman();
            return false;
        } else {
            return true;
        }
    }

    // Method that lets the player guess a char.
    public void guessAChar(char guess) {
        if (wordToGuess.indexOf(guess) == -1) {
            System.out.println("Wrong guess.");
            guesses++;
            makeHangman();
        } else {
            System.out.println("Your guess was right!");
            changeHiddenWord(guess);
        }
    }

    // Method that lets the player guess the word.
    public boolean guessWord(String guess) {
        if (wordToGuess.equals(guess)) {
            System.out.println("Correct, you win!");
            return false;
        } else {
            System.out.println("Wrong, try again.");
            guesses++;
            makeHangman();
            return true;
        }
    }

    // Methods that makes the hangman, one that adds a part of it to a 2d array
    // and one that prints it to the console.
    public void makeHangman() {
        if (guesses == 1) {
            hangman[6][0] = "{";
            hangman[6][1] = "|";
            hangman[6][2] = "}";
        } else if (guesses == 2) {
            hangman[5][1] = " |";
        } else if (guesses == 3) {
            hangman[4][1] = " |";
        } else if (guesses == 4) {
            hangman[3][1] = " |";
        } else if (guesses == 5) {
            hangman[2][1] = " |";
        } else if (guesses == 6) {
            hangman[1][1] = " |";
        } else if (guesses == 7) {
            hangman[0][1] = " _";
            hangman[0][2] = "_";
            hangman[0][3] = "_";
        } else if (guesses == 8) {
            hangman[1][3] = " |";
        } else if (guesses == 9) {
            hangman[2][3] = " O";
        } else if (guesses == 10) {
            hangman[3][2] = "(";
            hangman[3][3] = "|";
            hangman[3][4] = ")";
        } else if (guesses == 11) {
            hangman[4][3] = "( )";
        }
    }
    public void drawHangman() {
        for (String[] x : hangman) {
            for (String y : x) {
                if (y == null) {
                    y = "";
                }
                System.out.print(y + " ");
            }
            System.out.println();
        }
    }

    // Methods that draws the _ _ _ _ _.
    public void makeHiddenWord() {
        for (int i = 0; i < wordToGuess.length(); i++) {
            hiddenWord.add("_");
        }
    }
    public void printHiddenWord() {
        for (Object x : hiddenWord) {
            System.out.print(x + " ");
        }
    }
    public void changeHiddenWord(char guess) {
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == guess) {
                hiddenWord.set(i, guess);
            }
        }
    }
}
