
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.*;

/**
 * Created by awernick on 1/27/15.
 */
public class GameArea extends JApplet
{
	//Game Components Instantiation
	private static BoardController boardController;
	
	// Define Tile Measurements
    public static final int TILE_WIDTH = 50;
    public static final int TILE_HEIGHT = 50;
    private static int WINDOW_WIDTH = 300;
    private static int WINDOW_HEIGHT = 500;
    
    //Instantiate GUI Components and Layout Managers
    private JPanel controlPanel;
    private JButton newGameButton;
    private JButton solveButton;
    private BorderLayout gLayout;
    
    //Test Value
    // ! CHANGE TO FIT THE GAME CONTROLLER !
    public static int[][] tiles = {{1,2,3,4},
    	{5,6,7,8},
		{9,10,11,12},
		{13,14,15,16}};
    

    public GameArea()
    {
    	this.initGui();    	
    }
    
    public void init(){}
    
    private void initGui()
    {
    	//Initialize Components
		gLayout = new BorderLayout();
    	controlPanel = new JPanel();
    	newGameButton = new JButton("New Game");
    	solveButton = new JButton("Solve");
    	solveButton.setEnabled(false); // Incapacitate for now

    	
    	//Add Buttons to sub-Panel
    	controlPanel.add(newGameButton);
    	controlPanel.add(solveButton);
    	
    	//Add Panel to main component
    	this.setLayout(gLayout);
    	this.add(controlPanel,BorderLayout.NORTH);
    	this.add(new JSeparator(), BorderLayout.CENTER);
    	this.add(refreshTiles(tiles), BorderLayout.PAGE_END);
    }
    
    public JPanel refreshTiles(int[][] tiles)
	{
		gameBoard = new JPanel(new GridLayout(4,4,1,1));
		JButton currentButton;
		
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
			{
				currentButton = new JButton(Integer.toString(tiles[i][j]));
				currentButton.setPreferredSize(new Dimension(50,50));;
				gameBoard.add(currentButton);
			}
		return gameBoard;
	}
}
