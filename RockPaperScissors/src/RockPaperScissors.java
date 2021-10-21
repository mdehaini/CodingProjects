
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

/**
 * This class is used to play repeated games of Rock Paper Scissors
 * with a user. It uses a few helper methods along with methods that
 * interact with user input to accomplish this.
 */
public class RockPaperScissors {
    String[] systemMoves;           // Stores the computer's moves
    ArrayList<String> userMoves;    // Stores the user's moves
    int initialCapacity;            // Initial capacity of systemMoves
    int size;                       // Number of moves the system makes
    boolean playing;                // If user is still playing game or not
    Counter totalGames;             // Total number of games played
    Counter playerWin;              // Number of times player wins
    Counter cpuWin;                 // Number of times cpu wins
    Counter tie;                    // Number of ties

    // Used to calculate percentages
    private static final int PERCENT = 100;

    // Number of most recent games we want to print when game ends
    private static final int NUM_RECENT_GAMES = 10;

    // Use these variables for consistency
    private static final String ROCK = "r";
    private static final String PAPER = "p";
    private static final String SCISSORS = "s";
    private static final String QUIT = "q";
    private static final String ROCK_TIE = "I chose rock. It's a tie.";
    private static final String PAPER_SYS_WIN = "I chose paper. I win!";
    private static final String SCISSORS_USR_WIN =
            "I chose scissors. You win.";
    private static final String PAPER_TIE = "I chose paper. It's a tie.";
    private static final String SCISSORS_SYS_WIN = "I chose scissors. I win!";
    private static final String ROCK_USR_WIN = "I chose rock. You win.";
    private static final String SCISSORS_TIE = "I chose scissors. It's a tie.";
    private static final String ROCK_SYS_WIN = "I chose rock. I win!";
    private static final String PAPER_USR_WIN = "I chose paper. You win.";
    private static final String INVALID_INPUT =
            "That is not a valid move. Please try again.";
    private static final String THANKS =
            "Thanks for playing!\nOur most recent games were: ";
    private static final String SYS_USR_MOVES = "Me: %s, You: %s\n";
    private static final String OVERALL_STATS =
            "Our overall stats are:\n" +
                    "I won: %.2f%%\nYou won: %.2f%%\nWe tied: %.2f%%\n";
    private static final String PROMPT_MOVE =
            "Let's play! What's your move?" +
                    "(r=rock, p=paper, s=scissors or q to quit)";

    /**
     * Constructor for the RockPaperScissors class
     * initializes instance variables
     */
    public RockPaperScissors() {
        initialCapacity = 5;
        systemMoves = new String[initialCapacity];              // Stores the computer's moves
        userMoves = new ArrayList<String>(initialCapacity);     // Initial capacity of systemMoves
        size = 0;                                               // Number of moves the system makes
        playing = true;                                         // If user is still playing game or not
        totalGames = new Counter();                             // Total number of games played
        playerWin = new Counter();                              // Number of times player wins
        cpuWin = new Counter();                                 // Number of times cpu wins
        tie = new Counter();
    }

    /**
     * Generates next cpu move
     *
     * @return String - "r", "p", or "s"
     */
    static String genCPUMove() {
        Random rand = new Random();                             //makes rand object or Random class 
        int ran_int = rand.nextInt(3);                          //sets variable range as zero to three    
        switch (ran_int)
        {
            case 0:                                             //if random int is zero return rock    
                return "r";
            case 1:
                return "p";                                         //if random int is one return paper
            default:                                            //if random int is zero return scissors
                return "s";
        }
    }

    /**
     * Expands (doubles) the capacity of systemMoves
     *
     * @return void
     */
    public void expandCapacity() {
        initialCapacity = 2 * initialCapacity;                  //multiple capacity of array by two                       
        String[] temp = systemMoves;                            //make temp string that holds systemMoves array
        systemMoves = new String[initialCapacity];              //makes new array with new size
        for (int i = 0; i < (initialCapacity/2); i++)           //copy values in
            systemMoves[i] = temp[i];
    }

    /**
     * Adds system move to systemMoves array
     *
     * @param  sysMove - move of the system
     * @return void
     */
    public void addSystemMove(String sysMove) {
        size ++;                                                //size of array that holds the system moves
        if (size > initialCapacity)                             //check if the array needs to be bigger
        {
            expandCapacity();                                   // if so expand it 
        }
        systemMoves[size - 1] = sysMove;                        //add recent sysMove

    }

    /**
     * Takes the user move, the system move, and determines who wins.
     * Updates instance variables accordingly. Ends game if playerMove is "q".
     *
     * @param playerMove - move of the player
     * @param sysMove - move of the system
     * @return void
     */
    void play(String playerMove, String sysMove) {
        if (!playerMove.equals("r") && !playerMove.equals("s")
                && !playerMove.equals("p") && !playerMove.equals("q"))  //check to see if input is valid
            System.out.println(INVALID_INPUT);
        else if (playerMove.equals("q"))                        //checks to see if player wants to quit
        {
            playing = false;
            end();
        }

        else                                                    //check the play
        {
            userMoves.add(playerMove);                          //add player move
            addSystemMove(sysMove);                             //add system move
            totalGames.increment();                             //count of total games increaces 
            if (sysMove.equals("r"))                            //if rock is played by computer
            {
                if (playerMove.equals("p"))                     //if player plays paper to the rock the user wins
                {
                    System.out.println(ROCK_USR_WIN);
                    playerWin.increment();
                }
                else if (playerMove.equals("r"))                //if player plays rock to the rock the result is tie
                {
                    System.out.println(ROCK_TIE);
                    tie.increment();
                }
                else if (playerMove.equals("s"))                //if player plays scissors to the rock the system wins
                {
                    System.out.println(ROCK_SYS_WIN);
                    cpuWin.increment();
                }

            }
            else if (sysMove.equals("p"))
            {
                if (playerMove.equals("p"))                     //if player plays paper to the paper its a tie
                {
                    System.out.println(PAPER_TIE);
                    tie.increment();
                }
                else if (playerMove.equals("r"))                //if player plays rock to the paper the system wins
                {
                    System.out.println(PAPER_SYS_WIN);
                    cpuWin.increment();
                }
                else if (playerMove.equals("s"))                //if player plays scissors to the paper the user wins
                {
                    System.out.println(PAPER_USR_WIN);
                    playerWin.increment();
                }

            }
            else if (sysMove.equals("s"))
            {
                if (playerMove.equals("p"))                    //if player plays paper to the scissors the system wins
                {
                    System.out.println(SCISSORS_SYS_WIN);
                    cpuWin.increment();
                }

                else if (playerMove.equals("r"))               //if player plays rock to the scissors the user wins
                {
                    System.out.println(SCISSORS_USR_WIN);
                    playerWin.increment();
                }
                else if (playerMove.equals("s"))
                {
                    System.out.println(SCISSORS_TIE);           //if player plays scissors to the scissors it is a tie
                    tie.increment();
                }

            }
        }
    }


    /**
     * This method is given to you, make sure to call it at the end of 
     * the game! Do not change any of the given code.
     * Print out the end game stats: moves played and win percentages
     *
     * @return void
     */
    void end() {
        // Calculate percentages
        float systemWinPercent = (float) this.cpuWin.getCount() /
                (float) this.totalGames.getCount() * PERCENT;
        float playerWinPercent = (float) this.playerWin.getCount() /
                (float) this.totalGames.getCount() * PERCENT;
        float tiedPercent = (float) this.tie.getCount() /
                (float) this.totalGames.getCount() * PERCENT;

        System.out.println(THANKS);

        // Get which index to print to
        int printTo = (this.totalGames.getCount() < NUM_RECENT_GAMES) ?
                0 : this.totalGames.getCount() - NUM_RECENT_GAMES;

        // Print system and user moves
        for (int i = this.totalGames.getCount() - 1 ; i >= printTo; i--) {
            System.out.printf(SYS_USR_MOVES, this.systemMoves[i],
                    this.userMoves.get(i));
        }

        System.out.printf(OVERALL_STATS, systemWinPercent, playerWinPercent,
                tiedPercent);
    }

    /**
     * This method is given to you, it will call the play method
     * Main method that reads user input, generates cpu move, and plays game
     *
     * @param args - arguments passed in from command line in String form
     * @return void
     */
    public static void main(String[] args)
    {
        // Create new game and scanner
        RockPaperScissors game = new RockPaperScissors();
        Scanner in = new Scanner(System.in);

        // While user does not input "q" (logic in play method), play game
        while(game.playing) {
            System.out.println(PROMPT_MOVE);
            String userMove = in.nextLine();
            // Generate computer's move
            String cpuMove = genCPUMove();
            game.play(userMove, cpuMove);
        }

        in.close();
    }
}