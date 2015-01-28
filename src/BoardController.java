/**
 * Created by awernick on 1/27/15.
 */
public class BoardController
{
    public Board previousBoard;
    public Board gameBoard;
    private int userMoves;
    private boolean boardChanged;

    public BoardController()
    {
        gameBoard = new Board();
        userMoves = 0;
        boardChanged = false;
    }

    public void moveTileLeft( Tile current )
    {

    }

    public void moveTileRight( Tile current )
    {

    }

    public void moveTileUp( Tile current )
    {

    }

    public void moveTileDown( Tile current )
    {

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
        Tile current = gameBoard.getTile(x, y);

        TileMovement movement = canMove(current);

        if( movement == TileMovement.NULL )
            return;

        switch (movement)
        {
            case DOWN:
                moveTileDown(current);
                break;
            case UP:
                moveTileUp(current);
                break;
            case LEFT:
                moveTileLeft(current);
                break;
            case RIGHT:
                moveTileRight(current);
                break;
        }

        userMoves++;
        boardChanged = true;
    }

    /**
     * Check if a tile can move to the empty space.
     *
     * @param current the tile clicked
     * @return the direction that can be moved, null otherwise
     */
    public TileMovement canMove(Tile current)
    {
        return TileMovement.NULL;
    }

    /**
     * Validate the movement before updating the board
     *
     * @return true if valid movement, false otherwise
     */
    public boolean validMovement()
    {
        return false;
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
                {
                    previousTile = tiles[i][j];
                }

                else if(tiles[i][j].getValue() > previousTile.getValue())
                {
                    previousTile = tiles[i][j];
                }

                else
                {
                    return false;
                }
            }
        }

        return false;
    }

    /**
     * Solve the current board.
     */
    public void solveBoard()
    {

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

    public boolean setBoardChanged(boolean boardChanged)
    {
        this.boardChanged = boardChanged;
    }
}
