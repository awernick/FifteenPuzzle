
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

/**
 * Created by awernick on 1/27/15.
 */
public class GameArea extends JApplet implements MouseListener
{
	//Game Components Instantiation
	private static BoardController boardController;
	
	// Define Tile Measurements
    public static final int TILE_WIDTH = 50;
    public static final int TILE_HEIGHT = 50;
    
    //Instantiate GUI Components and Layout Managers
    private JPanel controlPanel;
    private JButton newGameButton;
    private JButton solveButton;
    private GridBagLayout gLayout;
    private static JPanel gameBoard;    
    
    public GameArea()
    {
    	boardController = new BoardController();
    	
    	initGui();
    }
    
    public void paint()
    {
    	initGui();
    }
    /**
     * Initializes main Pane and adds the control pane together with the board
     * */
    private void initGui()
    {
    	//Initialize Components
		gLayout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
    	controlPanel = new JPanel();
    	newGameButton = new JButton("New Game");
    	solveButton = new JButton("Solve");
    	solveButton.setEnabled(false); // Incapacitate for now

    	
    	//Add Buttons to sub-Panel
    	controlPanel.add(newGameButton);
    	controlPanel.add(solveButton);
    	
    	//Add Panel to main component
    	this.setLayout(gLayout);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 0;
    	c.gridy = 0;
    	c.insets = new Insets(10,0,0,0);
    	this.add(controlPanel,c);
    	
    	//Set up Layout Manager for separator
    	c.insets = new Insets(0,0,0,0);
    	c.gridy = 1;
    	this.add(new JSeparator(), c);
    	
    	c.fill = GridBagConstraints.VERTICAL;
    	c.gridx = 0;
    	c.gridy = 2;
    	this.add(new JLabel("Moves = " + boardController.getUserMoves()), c);
    	
    	c.fill = GridBagConstraints.VERTICAL;

    	c.ipady = 400;
    	c.ipadx = 400;
    	c.weightx = 1;
    	c.weighty = 1;
    	c.gridwidth = 1;
    	c.gridx = 0;
    	c.gridy = 3;

    	c.insets = new Insets(5, 5, 5, 5);
    	gameBoard = refreshTiles(boardController.getBoard());
    	this.add(gameBoard,c);
    	
    	this.setPreferredSize(getPreferredSize());

    }
    
    
    /** 
	 * 
	 * Method makes buttons depending on the parameter passed and builds a JPanel 
	 * component out of the parameter then returns it.
	 * 
	 * @param tiles 2-D array with Tile objects
	 * @return JPanel containing the board representation according to the current board
	 * */
    public static JPanel refreshTiles(Tile[][] tiles)
	{
		gameBoard = new JPanel(new GridLayout(4,4,1,1));
		JButton currentButton;
		
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
			{
				//Empty Button
				if(tiles[i][j].getValue()<0)
				{
					currentButton = new JButton();
					currentButton.setPreferredSize(new Dimension(TILE_WIDTH,TILE_HEIGHT));;
					currentButton.setBackground(Color.white);
					gameBoard.add(currentButton);
				}else{
				//Numbered Button
				currentButton = new JButton(Integer.toString(tiles[i][j].getValue()));
				currentButton.setPreferredSize(new Dimension(TILE_WIDTH,TILE_HEIGHT));;
				currentButton.setBackground(Color.gray);
				gameBoard.add(currentButton);
				}
			}
		return gameBoard;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
