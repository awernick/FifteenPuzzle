import java.util.Scanner;

/**
 * Created by awernick on 1/27/15.
 */
public class TerminalInterface
{
    BoardController boardController;
    Scanner reader;

    public TerminalInterface()
    {
        boardController= new BoardController();
        reader = new Scanner(System.in);

        startGame();
    }

    public void startGame()
    {
        while(!boardController.isBoardSolved())
        {
            Tile[][] tiles = boardController.getBoard();

            for(int i = 0; i < tiles.length; i++ )
            {
                for(int j = 0; j < tiles[0].length; j++)
                {
                    System.out.print(" | ");

                    if(tiles[j][i].getValue() > 9 || tiles[j][i].getValue() < 0)
                        System.out.print(tiles[j][i].getValue());
                    else
                        System.out.print(" " + tiles[j][i].getValue());

                    System.out.print(" | ");
                }

                System.out.println();
            }

            System.out.print("Choose tile: ");
            boardController.tileClicked(reader.nextInt(), reader.nextInt());
        }
    }

    public static void main(String [] args)
    {
        TerminalInterface gameArea = new TerminalInterface();
    }
}
