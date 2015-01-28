import java.util.ArrayList;
import java.util.Random;

/**
 * Created by awernick on 1/27/15.
 */
public class BoardController
{
    private Board gameBoard;
    private int userMoves;
    private boolean boardChanged;

    public BoardController()
    {
        gameBoard = new Board();
        scrambleBoard();

        userMoves = 0;
        boardChanged = false;
    }

    /**
     * Slide a tile to the left
     *
     * @param x clicked tile's x coordinate
     * @param y clicked tile's y coordinate
     */
    public void moveTileLeft( int x, int y )
    {
        swapTile( x, y, x - 1, y );
    }

    /**
     * Slide a tile to the right
     *
     * @param x clicked tile's x coordinate
     * @param y clicked tile's y coordinate
     */
    public void moveTileRight( int x, int y )
    {
        swapTile( x, y,  x + 1, y );
    }

    /**
     * Slide a tile to the up
     *
     * @param x clicked tile's x coordinate
     * @param y clicked tile's y coordinate
     */
    public void moveTileUp( int x, int y )
    {
        swapTile( x, y, x, y + 1 );
    }

    /**
     * Slide a tile to the down
     *
     * @param x clicked tile's x coordinate
     * @param y clicked tile's y coordinate
     */
    public void moveTileDown( int x, int y )
    {
        swapTile( x, y, x, y - 1 );
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

        TileMovement movement = canMove( x, y);

        if( movement == TileMovement.NULL )
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
        boardChanged = true;
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
    public TileMovement canMove(int x, int y)
    {

        if(gameBoard.getTile(x + 1, y).getValue() == -1)
            return TileMovement.RIGHT;

        else if(gameBoard.getTile(x - 1, y).getValue() == -1)
            return TileMovement.LEFT;

        else if(gameBoard.getTile(x, y - 1).getValue() == -1)
            return TileMovement.UP;

        else if(gameBoard.getTile(x, y + 1).getValue() == -1)
            return TileMovement.DOWN;

        else
            return TileMovement.NULL;
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
                    previousTile = tiles[i][j];

                else if(tiles[i][j].getValue() > previousTile.getValue())
                    previousTile = tiles[i][j];

                else
                    return false;
            }
        }

        return true;
    }

    /**
     * Solve the current board.
     */
    public void solveBoard()
    {

    }


    /**
     * Generate a new game board and scramble it.
     */
    public void scrambleBoard()
    {
        Tile[][] tiles = gameBoard.getTiles();
        Random generator = new Random();

        ArrayList<Integer> numbersTaken = new ArrayList<Integer>(); // List of the numbers that have already been set in the Tile's

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

        setBoardChanged(true); // Flag the view for an update
    }

    /**
     * Signal the view to update if the board has changed.
     *
     * @return true if the board has changed.
     */
    public boolean hasChanged()
    { return boardChanged; }

    /**
     * Set if the board has been changed. Usually only used to indicate
     * that the board view has been updated.
     */
    public void setBoardChanged(boolean boardChanged)
    { this.boardChanged = boardChanged; }

    /**
     * Get the number of valid user moves in the current board
     *
     * @return number of user moves
     */
    public int getUserMoves()
    { return userMoves; }

    /**
     * Return the current board's tiles.
     *
     * @return gameboard's tiles.
     */
    public Tile[][] getBoard()
    { return gameBoard.getTiles().clone(); }
}
