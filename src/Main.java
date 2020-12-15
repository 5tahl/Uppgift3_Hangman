import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Let the player choose the word.
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        Hangman hangman = new Hangman("");
        boolean condition = true;

        System.out.println("Welcome to hangman.");
        System.out.println("Your job is to guess all the letters in the word(1).");
        System.out.println("You win by guessing what the word is(2).");
        System.out.println("You lose if you guess the wrong letter 11 times.");
        System.out.println("You can check how many guesses you have left(3).");
        System.out.println("Do you want to pick the word? yes/no");

        condition = true;
        while (condition) {
            String pickTheWord = scanner.nextLine();

            switch(pickTheWord) {
                case "yes":
                    while (condition) {
                        System.out.println("\nNow please choose a word to guess.");
                        String choosenWord = scanner.nextLine();
                        if (!choosenWord.matches("[a-zA-Z]+")) {
                            System.out.println("Letters only!");
                            continue;
                        } else {
                            hangman.setWordToGuess(choosenWord);
                            condition = false;
                        }
                    }
                    break;
                case "no":
                    System.out.println("Your word will be randomized.");

                    hangman.setWordToGuess(getRandomWord(random.nextInt(3)));
                    condition = false;
                    break;
                default:
                    System.out.println("Not a valid input, please choose between 'yes' or 'no'.");
            }
        }

        // Build the menu.
        condition = true;
        while (condition) {
            System.out.println("[1] Guess a letter.");
            System.out.println("[2] Guess the word");
            System.out.println("[3] Check how many guesses are left.");
            System.out.println();
            hangman.printHiddenWord();
            System.out.println();
            hangman.drawHangman();

            String option = scanner.nextLine();
            switch(option) {

                // Menu option to guess a letter.
                case "1":
                    boolean pickCondition = true;
                    while (pickCondition) {
                        System.out.println("Choose a letter.");
                        String letter = scanner.nextLine();

                        if (!letter.matches("[a-zA-Z]+")) {
                            System.out.println("Only letters please.");
                            continue;
                        } else if (letter.length() > 1) {
                            System.out.println("Only 1 letter please.");
                            continue;
                        } else {
                            char charLetter = letter.charAt(0);
                            hangman.guessAChar(charLetter);
                            pickCondition = false;
                        }
                    }

                    condition = hangman.allGuessesUsed();
                    break;

                // Menu option to guess the word.
                case "2":
                    System.out.println("Make a guess what the word could be.");
                    String word = scanner.nextLine();

                    if (!hangman.guessWord(word)) {
                        condition = false;
                    } else if (!hangman.allGuessesUsed()) {
                        condition = false;
                    }
                    break;

                // Menu option to see how many guesses are left.
                case "3":
                    hangman.checkGuesses();
                    break;

                default:
                    System.out.println("Not a valid input, please choose between 1, 2 or 3.");

            }
        }
    }

    public static String getRandomWord(int num) {
        String randomWord = "";
        switch (num) {
            case 0:
                randomWord = "java";
                break;
            case 1:
                randomWord = "grit";
                break;
            case 2:
                randomWord = "academy";
                break;
        }
        return randomWord;
    }
}
