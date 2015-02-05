package view;

import controller.BoardController;
import controller.BoardEventListener;
import model.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * View component for the FifteenPuzzle MVC design.
 *
 * The class extends JApplet in order to draw a GUI
 * for the game.
 *
 * Also, this class implements the BoardEventListener
 * to listen to events fired by the
 * BoardController when the Board model's state changes.
 *
 * @author Alan Wernick
 */
public class BoardView extends JApplet implements BoardEventListener
{
	// Will only be changing these three variables in game.
	// No need to make the rest global.

	private BoardController boardController; // Controls all the game logic
	private JButton[][] gridButtons; // Buttons that trigger BoardEvents
	private JLabel userMovesLabel; // Displays number of moves performed by user

	/**
	 * Initialize the applet and add necessary components
	 * */
	public void init()
	{

		//Initialize our board controller and register the instance as a BoardEvent listener
		boardController = new BoardController();
		boardController.addBoardEventListener(this);
		boardController.init();

		//Initialize Components
		GridLayout gridLayout = new GridLayout();
		JPanel controlPanel = new JPanel();

		// "New Game" Button
		JButton newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boardController.init();
				refreshTiles();
			}
		});

		// "Solve" Button
		JButton solveButton = new JButton("Solve");
		solveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boardController.solveBoard();
			}
		});
		//solveButton.setEnabled(false); // Incapacitate for now


		//Add Buttons to sub-Panel
		controlPanel.add(newGameButton);
		controlPanel.add(solveButton);

		//Add Panel to main component
		this.add(controlPanel, BorderLayout.NORTH);


		// Add User Moves Label
		userMovesLabel = new JLabel("Moves: " + boardController.getUserMoves());
		userMovesLabel.setHorizontalAlignment(SwingConstants.CENTER);

		//Separate UserMovesLabel from Button Grid
		JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
		JPanel center = new JPanel(new BorderLayout()); // Create JPanel to center label
		center.add(separator, BorderLayout.NORTH);
		center.add(userMovesLabel, BorderLayout.CENTER);
		this.add(center, BorderLayout.SOUTH);


		// Initialize button grid
		JPanel gameBoard = new JPanel(new GridLayout(4,4,0,0));

		// Generate Game Buttons
		gridButtons = new JButton[4][4];
		for(int i  = 0; i < gridButtons.length; i++)
		{
			for(int j = 0; j < gridButtons[0].length; j++)
			{
				gridButtons[j][i] = new JButton();
				gridButtons[j][i].addMouseListener(new MouseAdapter()
				{
					public void mousePressed(MouseEvent e)
					{
						// Get X, Y from click event relative to button size
						int xClicked = ((int) (e.getComponent().getX() / e.getComponent().getWidth()));
						int yClicked = ((int) (e.getComponent().getY() / e.getComponent().getHeight()));
						boardController.tileClicked(xClicked, yClicked);
					}
				});

				gameBoard.add(gridButtons[j][i]);
			}
		}

		// Update board
		refreshTiles();

		// Start Applet
		this.add(gameBoard);
		this.setSize(240, 320);
	}


	/**
	 * Refreshes the JButton grid relative to the board's current state.
	 */
	private void refreshTiles()
	{

		getAppletContext().showStatus(""); // Removes "Applet Started" status message (Aesthetic purposes)

		Tile[][] tiles = boardController.getBoard();

		for(int i = 0; i < tiles.length; i++)
		{
			for(int j = 0; j < tiles[0].length; j++)
			{
				//Empty Button
				if (tiles[i][j].getValue() < 0) {
					gridButtons[i][j].setText("");
					gridButtons[i][j].setBackground(Color.white);
					gridButtons[i][j].setEnabled(false);
				}

				else
				{
					gridButtons[i][j].setText(tiles[i][j].getValue() + "");
					gridButtons[i][j].setBackground(Color.gray);
					gridButtons[i][j].setEnabled(true);
				}
			}
		}
	}

	/**
	 * Handle BoardEvent Changed
	 */
	@Override
	public void boardChanged()
	{
		refreshTiles();
		userMovesLabel.setText("Moves: " + boardController.getUserMoves());
	}

	/**
	 * Handle BoardEvent solved
	 */
	@Override
	public void boardSolved()
	{
		JOptionPane.showMessageDialog(null, "The board was solved");
	}
}
