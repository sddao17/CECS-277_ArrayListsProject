
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Alexander Loo, Phoenix Ngan, Steven Dao
 * @version 1.1
 * Due Date: February 3, 2021, 2:00pm
 * Purpose: Model an instance of the game Bulgarian Solitaire, where you start with a total of 45 cards and randomly
 *          divide them into some number of piles of random size. A card is taken from each pre-existing pile to create
 *          a new pile during each round. The solitaire is over when the piles have size 1, 2, 3, 4, 5, 6, 7, 8, and 9,
 *          in some order. Only ArrayLists are allowed.
 * Target Output: The final ArrayList output should consist of the numbers [1, 2, 3, 4, 5, 6, 7, 8, 9] in any order.
 */

public class ArrayListsProject {
    /**
     * @param args command-line arguments for the application of type String array
     */
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        ArrayList<Integer> stacks = new ArrayList<>();      // the stacks of cards of type ArrayList
        int numOfCardsLeft = 45;
        int randomNumOfCards;

        System.out.print("Choose the number of piles you would like to start the game with (1-10): ");

        // form the initial randomized stacks
        for (int numOfPiles = userInput.nextInt(); numOfPiles > 0; numOfPiles--) {
            if (numOfPiles == 1) {
                stacks.add(numOfCardsLeft);
            } else {
                /* generate a random number of cards to be included in a stack, but account for the upper bound
                    so that all the declared piles can have at least 1 card each */
                randomNumOfCards = (int) (Math.random() * (numOfCardsLeft - (numOfPiles - 1)) + 1);
                stacks.add(randomNumOfCards);
                numOfCardsLeft -= randomNumOfCards;
            }
        }

        int stacksLength = stacks.size();

        // output the initial piles
        System.out.println("The initial piles are:");
        for (int i = 0; i < stacksLength; i++) {
            if (i != stacksLength - 1) {
                System.out.print("\t" + stacks.get(i) + " ");
            } else {
                System.out.print("\t" + stacks.get(i));
            }
        }

        // the ArrayList target output, in any order: [1, 2, 3, 4, 5, 6, 7, 8, 9]
        ArrayList<Integer> targetArr = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            targetArr.add(i);
        }

        int cardsRemoved = 0;

        // if the stack ArrayList's contents match those of the target ArrayList, end the loop
        while (!stacks.containsAll(targetArr)) {
            for (int i = 0; i < stacks.size(); i++) {
                stacks.set(i, stacks.get(i) - 1);       // remove a card from each pre-existing stack
                cardsRemoved++;

                // remove the stack if it no longer has cards, and adjust the index counter to account for the shift
                if (stacks.get(i) == 0) {
                    stacks.remove(i);
                    i--;
                }
            }
            stacks.add(cardsRemoved);       // add the removed cards as a new stack
            cardsRemoved = 0;

            // output the new piles
            if (!stacks.containsAll(targetArr)) {
                System.out.println("\nThe new piles are:");
            } else {
                System.out.println("\nThe last piles are:");
            }
            for (int element : stacks) {
                System.out.print("\t" + element);
            }
        }
    }
}
