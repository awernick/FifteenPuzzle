/**
 * Created by awernick on 1/27/15.
 */
public class BoardController
{
    public Board gameBoard;
    private int userMoves;
    private boolean boardChanged;

    public BoardController()
    {
        gameBoard = new Board();
    }

    public void moveTileLeft(Tile current)
    {

    }

    public void moveTileRight(Tile current)
    {

    }

    public void moveTileUp(Tile current)
    {

    }

    public void moveTileDown(Tile current)
    {

    }

    public void tileClicked(int x, int y)
    {
        Tile current = gameBoard.getTile(x, y);

        TileMovement movement = canMove(current);

        if( movement != TileMovement.NULL)
        {
            switch(movement)
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
        }

    }

    public TileMovement canMove(Tile current)
    {
        return TileMovement.NULL;
    }

    public boolean validMovement()
    {
        return false;
    }

    public boolean isBoardSolved()
    {
        return false;
    }

    public void solveBoard()
    {

    }

    public boolean hasChanged()
    { return boardChanged; }
}
