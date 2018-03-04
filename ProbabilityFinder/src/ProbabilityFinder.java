
import java.util.Scanner;

/**
 *
 * @author Jason Whiting
 */
public class ProbabilityFinder {

    // Create a scanner in order to read in user input. In the future I might
    // instead read input from TextFields in a JavaFX window or something.
    // But for now I need to work out the logic first.
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        checkRollType();
    }

    /**
     * The checkRollType() method is the beginning of this program and is where
     * the user will enter input in order to get back a calculation.
     */
    public static void checkRollType() {

        // These are the four possible die people can use. I might add a
        // standard die in the future as well.
        int green, black, blue, orange;

        // Ask what kind of action the user will be performing.
        System.out.println("Are you dodging or blocking?");
        String type = scan.nextLine();
        System.out.println("");

        // Depending on their action, figure out how many of each type of die
        // they plan to roll.
        switch (type.toLowerCase()) {
            case "dodging":
                System.out.println("How many green dodge dice do you have to roll?");
                System.out.println("Please enter a number that is greater than 0 but less than or equal to 4.");
                green = scan.nextInt();
                System.out.println("");
                selectDice(green);
                break;
            case "blocking":
                System.out.println("Note: The total number of dice cannot exceed 6.");
                System.out.println("How many black block dice do you have to roll?");
                System.out.println("Please enter a number that is greater than 0 but less than or equal to 5.");
                black = scan.nextInt();
                System.out.println("");
                System.out.println("How many blue block dice do you have to roll?");
                System.out.println("Please enter a number that is greater than 0 but less than or equal to 4.");
                blue = scan.nextInt();
                System.out.println("");
                System.out.println("How many orange block dice do you have to roll?");
                System.out.println("Please enter a number that is greater than 0 but less than or equal to 2.");
                orange = scan.nextInt();
                System.out.println("");
                selectDice(black, blue, orange);
                break;
            default:
                System.out.println("You entered invalid input. Please try again.");
                checkRollType();
                break;
        }

        tryAgain();

    }

    /**
     * The tryAgain() method simply prompts the user if they would like to
     * calculate another set of dice or not.
     */
    public static void tryAgain() {

        System.out.println("Would you like to enter another set of dice?");
        String answer = scan.nextLine();
        System.out.println("");

        switch (answer.toLowerCase()) {
            case "yes":
                checkRollType();
                break;
            case "no":
                System.exit(0);
                break;
            case "maybe":
                System.out.println("Perhaps you should make up your mind.");
                tryAgain();
                break;
            default:
                System.out.println("You entered invalid input. Please try again.");
                tryAgain();
                break;
        }

    }

    /**
     * Both of the following selectDice() methods will create a 2D array with
     * the number of rows equal to the total number of dice the user inputs. The
     * length of all rows will be 6. In the future I might try to figure out how
     * to change this program to work with 10-sided and 20-sided dice, but for
     * now I am keeping things simple.
     *
     * @param green
     */
    public static void selectDice(int green) {

        int[] greenDie = {1, 1, 1, 0, 0, 0};
        int[][] allDice = new int[green][6];

        for (int i = 0; i < green; i++) {
            for (int j = 0; j < 6; j++) {
                allDice[i][j] = greenDie[j];
            }
        }

        for (int i = 0; i < green; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(allDice[i][j] + "  ");
            }
            System.out.println();
        }

        calculateProbabilities(allDice);

    }

    /**
     * Both of the following selectDice() methods will create a 2D array with
     * the number of rows equal to the total number of dice the user inputs. The
     * length of all rows will be 6. In the future I might try to figure out how
     * to change this program to work with 10-sided and 20-sided dice, but for
     * now I am keeping things simple.
     *
     * @param black
     * @param blue
     * @param orange
     */
    public static void selectDice(int black, int blue, int orange) {

        int[] blackDie = {2, 2, 1, 1, 1, 0};
        int[] blueDie = {3, 2, 2, 2, 1, 1};
        int[] orangeDie = {4, 3, 3, 2, 2, 1};

        int totalDice = black + blue + orange;

        int[][] allDice = new int[totalDice][6];

        for (int i = 0; i < black; i++) {
            for (int j = 0; j < 6; j++) {
                if (black > 0) {
                    allDice[i][j] = blackDie[j];
                }
            }
        }

        for (int i = black; i < black + blue; i++) {
            for (int j = 0; j < 6; j++) {
                if (blue > 0) {
                    allDice[i][j] = blueDie[j];
                }
            }
        }

        for (int i = black + blue; i < black + blue + orange; i++) {
            for (int j = 0; j < 6; j++) {
                if (orange > 0) {
                    allDice[i][j] = orangeDie[j];
                }
            }
        }

        for (int i = 0; i < totalDice; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(allDice[i][j] + "  ");
            }
            System.out.println();
        }

        calculateProbabilities(allDice);

    }

    /**
     * The calculateProbabilities() method will actually read the 2D array that
     * contains all the dice being rolled and then calculate all the possible
     * rolls using those dice. It will then sum up the rolls and get a number.
     * That number will be stored by incrementing its index in the resultsArray.
     * So, a roll of 6 will make resultsArray[6] increment.
     *
     * @param allDice
     */
    public static void calculateProbabilities(int[][] allDice) {

        int totalDice = allDice.length;
        int totalPossibilities = (int) Math.pow(6, totalDice);
        int result;
        int[] resultsArray = new int[32];

        switch (totalDice) {
            case 6:
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 6; j++) {
                        for (int k = 0; k < 6; k++) {
                            for (int m = 0; m < 6; m++) {
                                for (int n = 0; n < 6; n++) {
                                    for (int p = 0; p < 6; p++) {
                                        result = allDice[0][i] + allDice[1][j] + allDice[2][k] + allDice[3][m] + allDice[4][n] + allDice[5][p];
                                        resultsArray[result]++;
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            case 5:
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 6; j++) {
                        for (int k = 0; k < 6; k++) {
                            for (int m = 0; m < 6; m++) {
                                for (int n = 0; n < 6; n++) {
                                    result = allDice[0][i] + allDice[1][j] + allDice[2][k] + allDice[3][m] + allDice[4][n];
                                    resultsArray[result]++;
                                }
                            }
                        }
                    }
                }
                break;
            case 4:
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 6; j++) {
                        for (int k = 0; k < 6; k++) {
                            for (int m = 0; m < 6; m++) {
                                result = allDice[0][i] + allDice[1][j] + allDice[2][k] + allDice[3][m];
                                resultsArray[result]++;
                            }
                        }
                    }
                }
                break;
            case 3:
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 6; j++) {
                        for (int k = 0; k < 6; k++) {
                            result = allDice[0][i] + allDice[1][j] + allDice[2][k];
                            resultsArray[result]++;
                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 6; j++) {
                        result = allDice[0][i] + allDice[1][j];
                        resultsArray[result]++;
                    }
                }
                break;
            case 1:
                for (int i = 0; i < 6; i++) {
                    result = allDice[0][i];
                    resultsArray[result]++;
                }
                break;
            default:
                System.out.println("You have either entered 0 dice or more than 6 dice. Please try again.");
                checkRollType();
                break;
        }

        System.out.println("");
        for (int i = 0; i < resultsArray.length; i++) {
            System.out.print(resultsArray[i] + "  ");
        }
        System.out.println("\n");

        printProbabilities(resultsArray, totalPossibilities);

    }

    /**
     * The printProbabilities() method simply takes the resultsArray that has
     * been filled with the roll counts and then prints out the probabilities
     * for each index.
     *
     * @param results
     * @param total
     */
    public static void printProbabilities(int[] results, int total) {

        System.out.println("The total number of possible rolls is: " + total + ".");
        System.out.println("");

        for (int i = 0; i < results.length; i++) {
            System.out.format("The probability of rolling a %d is: %.2f percent.\n", i, (double)results[i] / total * 100);
        }
        System.out.println("");

        for (int i = 0; i < results.length; i++) {
            System.out.format("The probability of rolling a %d or higher is: %.2f percent.\n", i, (double)addResults(results, i) / total * 100);
        }
        System.out.println("");

    }

    /**
     * The addResults() method is simply an easy calculator to find the total
     * probability of rolling a given number of higher. It adds the probability
     * of the given number and all numbers greater than itself.
     *
     * @param results
     * @param index
     * @return sum
     */
    public static int addResults(int[] results, int index) {

        int sum = 0;

        for (int i = results.length - 1; i >= index; i--) {
            sum = sum + results[i];
        }

        return sum;

    }

}
