
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.*;


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
	private JButton[][] gridButtons;
	private JPanel gameBoard;
	private JLabel userMovesLabel;

	private Thread runner;

	/**
	 * Initializes main Pane and adds the control pane together with the board
	 * */
	public void init()
	{
		boardController = new BoardController();

		//Initialize Components
		gLayout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		controlPanel = new JPanel();
		newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boardController = new BoardController();
				refreshTiles();
			}
		});
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
		userMovesLabel = new JLabel("Moves = " + boardController.getUserMoves());
		this.add(userMovesLabel, c);

		c.fill = GridBagConstraints.VERTICAL;
		c.anchor = GridBagConstraints.PAGE_END;
		c.ipady = 10;
		c.ipadx = 10;
		c.gridwidth = 1;
		c.gridheight = 4;
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(0, 0, 0, 0);

		gameBoard = new JPanel(new GridLayout(4,4,0,0));

		gridButtons = new JButton[4][4];

		for(int i  = 0; i < gridButtons.length; i++)
		{
			for(int j = 0; j < gridButtons[0].length; j++)
			{
				gridButtons[i][j] = new JButton();

				gridButtons[i][j].setPreferredSize(new Dimension(TILE_WIDTH, TILE_HEIGHT));
				gridButtons[i][j].addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						int xClicked = ((int) (e.getComponent().getX() / TILE_WIDTH));
						int yClicked = ((int) (e.getComponent().getY() / TILE_HEIGHT));
						boardController.tileClicked(xClicked, yClicked);
					}
				});
				gridButtons[i][j].setBackground(Color.white);

				gameBoard.add(gridButtons[i][j]);
			}
		}


		refreshTiles();
		this.add(gameBoard, c);
		this.setSize(300, 500);

		runner = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true)
				{
					if(boardController.hasChanged())
					{
						refreshTiles();
						userMovesLabel.setText("Moves = " + boardController.getUserMoves());
						boardController.setBoardChanged(false);
					}
				}
			}
		});

		runner.start();
	}


	/** 
	 * 
	 * Method makes buttons depending on the parameter passed and builds a JPanel 
	 * component out of the parameter then returns it.
	 * 
	 *
	 *
	 * */
	public void refreshTiles()
	{
		System.out.println("Refreshing"); // Don't remove, program breaks if this is commented out. Reason: Unknown

		Tile[][] tiles = boardController.getBoard();

		for(int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				//Empty Button
				if (tiles[i][j].getValue() < 0)
				{
					gridButtons[j][i].setText("");
					gridButtons[j][i].setBackground(Color.white);
					gridButtons[j][i].setPreferredSize(new Dimension(TILE_WIDTH, TILE_HEIGHT));
				}

				else
				{
					gridButtons[j][i].setText(tiles[i][j].getValue() + "");
					gridButtons[j][i].setBackground(Color.gray);
					gridButtons[j][i].setPreferredSize(new Dimension(TILE_WIDTH, TILE_HEIGHT));
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
