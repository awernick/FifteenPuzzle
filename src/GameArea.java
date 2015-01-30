
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

/**
 * Created by awernick on 1/27/15.
 */
public class GameArea extends JApplet implements MouseListener, Runnable
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
	
	//Board Components
	private JLabel moves;
	private static JPanel gameBoard;
	private static JButton[][] boardTiles;
	
	
	//initiate and populate sub-components
	public void init()
	{
		moves = new JLabel("Moves : " + "0");
		boardController = new BoardController();
		gameBoard = new JPanel(new GridLayout(4, 4, 0, 0));
		boardTiles = new JButton[4][4];
		populateBoard();
		
		
		initGui();
	}

	public void start()
	{
		
	}
	
	public void run()
	{
		
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
		this.add(moves, c);

		c.fill = GridBagConstraints.VERTICAL;
		c.anchor = GridBagConstraints.PAGE_END;
		c.ipady = 10;
		c.ipadx = 10;
		c.gridwidth = 1;
		c.gridheight = 4;
		c.gridx = 0;
		c.gridy = 3;

		c.insets = new Insets(0, 0, 0, 0);
		this.add(gameBoard,c);
		this.setSize(300, 500);
		
	}
	
	public void populateBoard()
	{
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
			{
				boardTiles[i][j] = new JButton();
				boardTiles[i][j].addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e)
					{
						int xTile = e.getComponent().getX() / TILE_WIDTH;
						int yTile = e.getComponent().getY() / TILE_HEIGHT;
						System.out.println("x : " + xTile + " y : " + yTile + " - > On Button : " + boardTiles[xTile][yTile].getText());
						boardController.tileClicked(xTile,yTile);								
						refreshTiles();
					}
				});
			}
				
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
			{
				boardTiles[i][j].setPreferredSize(new Dimension(TILE_WIDTH,TILE_HEIGHT));
				if(boardController.getBoard()[i][j].getValue() > 0)
				{
					boardTiles[i][j].setText(Integer.toString(boardController.getBoard()[i][j].getValue()));
					boardTiles[i][j].setBackground(Color.GRAY);
				}
				else
				{
					boardTiles[i][j].setBackground(Color.white);
				}	
				gameBoard.add(boardTiles[i][j], i,j);
			}
			
	}
	

	/** 
	 * 
	 * Method makes buttons depending on the parameter passed and builds a JPanel 
	 * component out of the parameter then returns it.
	 * 
	 * */
	public void refreshTiles()
	{
		moves.setText("Moves : " + boardController.getUserMoves());
		Tile[][] tiles = boardController.getBoard();
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
			{
				
				if(boardTiles[i][j].equals(tiles[i][j]))
				{
					
				}
				else
				{
					if(tiles[i][j].getValue()>0)
					{
						boardTiles[i][j].setText(Integer.toString(tiles[i][j].getValue()));
						boardTiles[i][j].setBackground(Color.GRAY);
					}
					else
					{
						boardTiles[i][j].setText(null);
						boardTiles[i][j].setBackground(Color.white);
					}
				}
				
			}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Mouse entered Button.");
		e.consume();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
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
