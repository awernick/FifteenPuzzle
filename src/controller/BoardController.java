package controller;

import model.Board;
import model.Tile;
import model.TileMovement;

import java.util.ArrayList;
import java.util.Random;

import static model.TileMovement.*;

/**
 * Controller class that manages the Board model and
 * notifies the view on the changes that have taken place.
 *
 * This class controls all the logic of the FifteenPuzzle game
 * and coordinates the model and the view through the BoardEventListener
 * interface. By notifying different state changes in the model,
 * the view is able update accordingly and efficiently without wasting
 * time performing exhaustive checks.
 *
 * @author Alan Wernick
 */
public class BoardController
{
    private Board gameBoard;
    private int userMoves;
    private ArrayList<BoardEventListener> eventListeners;


    public BoardController()
    {
        gameBoard = new Board();
        eventListeners = new ArrayList<BoardEventListener>();
        init();
    }

    /**
     * (Re)Initializes the board and
     * clears the previous game's values.
     */
    public void init()
    {
        userMoves = 0;
        scrambleBoard();
    }

    /**
     * Slide a tile to the left
     *
     * @param x clicked tile's x coordinate
     * @param y clicked tile's y coordinate
     */
    private void moveTileLeft( int x, int y )
    {
        swapTile( x, y, x - 1, y );
    }

    /**
     * Slide a tile to the right
     *
     * @param x clicked tile's x coordinate
     * @param y clicked tile's y coordinate
     */
    private void moveTileRight( int x, int y )
    {
        swapTile( x, y,  x + 1, y );
    }

    /**
     * Slide a tile up
     *
     * @param x clicked tile's x coordinate
     * @param y clicked tile's y coordinate
     */
    private void moveTileUp( int x, int y )
    {
        swapTile( x, y, x, y - 1 );
    }

    /**
     * Slide a tile down
     *
     * @param x clicked tile's x coordinate
     * @param y clicked tile's y coordinate
     */
    private void moveTileDown( int x, int y )
    {
        swapTile( x, y, x, y + 1 );
    }

    /**
     * Swap two tile's positions (values)
     *
     * @param x1 current tile's x coordinate
     * @param y1 current tile's y coordinate
     * @param x2 target tile's x coordinate
     * @param y2 target tile's y coordinate
     */
    private void swapTile( int x1, int y1, int x2, int y2 )
    {
        Tile current = gameBoard.getTile(x1, y1);
        Tile swap = gameBoard.getTile(x2, y2);

        int temp = current.getValue();
        current.setValue(swap.getValue());
        swap.setValue(temp);
    }

    /**
     * Update the board based on the user's action.
     * First check if the tile at the specified x and y
     * has a valid movement. If it does, update the
     * temporary board and check if the movement is
     * valid. If the movement is valid, update the
     * current board, and set the flag to draw the
     * board.
     *
     * @param x tile's x coordinate
     * @param y tile's y coordinate
     */
    public void tileClicked( int x, int y )
    {

        TileMovement movement = canMove(x, y);

        if( movement == NULL )
            return;

        switch (movement)
        {
            case DOWN:
                moveTileDown(x, y);
                break;
            case UP:
                moveTileUp(x, y);
                break;
            case LEFT:
                moveTileLeft(x, y);
                break;
            case RIGHT:
                moveTileRight(x, y);
                break;
        }

        userMoves++;
        notifyBoardChange();

        if(isBoardSolved())
            notifyBoardSolved();
    }

    /**
     * Check if a tile has an adjacent empty space to move to.
     *
     * @param x the tile x coordinate
     * @param y the tile y coordinate
     *
     * @return the direction that the tile can be moved to,
     * null otherwise
     */
    private TileMovement canMove(int x, int y)
    {

        if(x + 1 < 4 && gameBoard.getTile(x + 1, y).getValue() == -1) {
            System.out.println("Move right");
            return RIGHT;
        }

        else if( x - 1 >= 0 && gameBoard.getTile(x - 1, y).getValue() == -1) {
            System.out.println("Move left");
            return LEFT;
        }

        else if(y + 1 < 4 && gameBoard.getTile(x, y + 1).getValue() == -1) {
            System.out.println("Move down");
            return DOWN;
        }

        else if( y - 1 >= 0 &&  gameBoard.getTile(x, y - 1).getValue() == -1) {
            System.out.println("Move up");
            return UP;
        }

        else
        {
            System.out.println("Cannot move!");
            return NULL;
        }
    }


    /**
     * Check if the current board configuration is solved.
     * The board is considered solved if the values of the
     * tiles are in increasing values.
     *
     * @return true if the board's tiles are in increasing
     *         order
     */
    public boolean isBoardSolved()
    {
        Tile[][] tiles = gameBoard.getTiles();

        Tile previousTile = null;

        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {

                if(previousTile == null)
                    previousTile = tiles[j][i];

                else if(tiles[j][i].getValue() > previousTile.getValue())
                    previousTile = tiles[j][i];

                else if(previousTile.getValue() == ((tiles.length * tiles[0].length) - 1) && tiles[j][i].getValue() == -1)
                    previousTile = tiles[j][i];

                else
                    return false;
            }
        }

        return true;
    }

    /**
     * Solve the current board configuration.
     */
    public void solveBoard()
    {
        Tile[][] tiles = gameBoard.getTiles();

        int counter = 1;

        for(int i = 0; i < tiles.length; i++)
        {
            for(int j = 0; j < tiles[0].length; j++)
            {
                if(counter == 16)
                    counter = -1;

                tiles[j][i].setValue(counter++);
            }
        }

        notifyBoardChange();
    }


    /**
     * Generate a new game board and scramble it.
     * -1 value set to represent the blank tile.
     */
    public void scrambleBoard()
    {
        Tile[][] tiles = gameBoard.getTiles();
        Random generator = new Random();

        ArrayList<Integer> numbersTaken = new ArrayList<Integer>(); // List of the numbers that have already been set in the model.Tile's

        int num; // Temporary variable to store random numbers

        for(int i = 0; i < tiles.length; i++)
        {
            for(int j = 0; j < tiles[0].length; j++)
            {
                do { num = generator.nextInt(16) + 1; } while(numbersTaken.contains(num)); // Generate random numbers (0 inclusive, 15 exclusive) while they are already used.

                if(num == 16)
                    tiles[i][j].setValue(-1); // Generate the blank puzzle piece if the current rand number is 16
                else
                    tiles[i][j].setValue(num); // Set the current tile's value to the random number

                numbersTaken.add(num); // Add to the list of numbers taken
            }
        }
    }

    /**
     * Register a listener for board changes such as moved tiles
     * or board being solved
     *
     * @param listener event handler
     */

    public void addBoardEventListener(BoardEventListener listener)
    {
        eventListeners.add(listener);
    }

    /**
     * Notify registered listeners of a board change.
     * Triggered when a tile is moved or when it has
     * been reinitialized.
     */
    private void notifyBoardChange()
    {
        for(BoardEventListener listener : eventListeners)
        {
            listener.boardChanged();

            if(isBoardSolved())
                listener.boardSolved();
        }
    }

    /**
     * Notify registered listeners that the current board
     * configuration has been solved.
     */
    private void notifyBoardSolved()
    {
        for(BoardEventListener listener : eventListeners)
        {
            listener.boardSolved();
        }
    }


    /**
     * Returns the number of valid user moves in the current board
     *
     * @return number of user moves
     */
    public int getUserMoves()
    { return userMoves; }


    /**
     * Return the current board's Tile objects.
     *
     * @return gameboard's Tiles.
     */
    public Tile[][] getBoard()
    {
        Tile[][] tiles = gameBoard.getTiles();

        Tile[][] copy = new Tile[tiles.length][tiles[0].length];

        for(int i = 0; i < tiles.length; i++)
        {
            for(int j = 0; j < tiles[0].length; j++)
            {
                copy[i][j] = new Tile(tiles[i][j].getValue());
            }
        }

        return copy;
    }
}
