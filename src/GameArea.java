
import java.awt.*;
import java.awt.event.*;


import javax.swing.*;

/**
 * Created by awernick on 1/27/15.
 */
public class GameArea extends JApplet implements BoardEventListener
{
	// Game Components Instantiation
	private BoardController boardController;

	// Instantiate GUI Components and Layout Managers
	private JPanel controlPanel;
	private JButton newGameButton;
	private JButton solveButton;
	private GridLayout gridLayout;
	private JButton[][] gridButtons;
	private JPanel gameBoard;
	private JLabel userMovesLabel;

	/**
	 * Initializes main Pane and adds the control pane together with the board
	 * */
	public void init()
	{
		boardController = new BoardController();
		boardController.addBoardEventListener(this);

		//Initialize Components
		gridLayout = new GridLayout();
		controlPanel = new JPanel();

		// "New Game" Button
		newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boardController = new BoardController();
			}
		});


		// "Solve" Button
		solveButton = new JButton("Solve");
		solveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boardController.solveBoard();
			}
		});
		solveButton.setEnabled(false); // Incapacitate for now


		//Add Buttons to sub-Panel
		controlPanel.add(newGameButton);
		controlPanel.add(solveButton);

		//Add Panel to main component
		this.add(controlPanel, BorderLayout.NORTH);


		//Add User Moves Label
		userMovesLabel = new JLabel("Moves: " + boardController.getUserMoves());
		userMovesLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
		JPanel center = new JPanel(new BorderLayout()); // Create JPanel to center label
		center.add(separator, BorderLayout.NORTH);
		center.add(userMovesLabel, BorderLayout.CENTER);
		this.add(center, BorderLayout.SOUTH);

		gameBoard = new JPanel(new GridLayout(4,4,0,0));

		// Generate Game Buttons
		gridButtons = new JButton[4][4];
		for(int i  = 0; i < gridButtons.length; i++)
		{
			for(int j = 0; j < gridButtons[0].length; j++)
			{
				gridButtons[i][j] = new JButton();
				gridButtons[i][j].addMouseListener(new MouseAdapter()
				{
					public void mousePressed(MouseEvent e)
					{
						int xClicked = ((int) (e.getComponent().getX() / e.getComponent().getWidth()));
						int yClicked = ((int) (e.getComponent().getY() / e.getComponent().getHeight()));
						boardController.tileClicked(xClicked, yClicked);
					}
				});

				gameBoard.add(gridButtons[i][j]);
			}
		}

		refreshTiles();


		this.add(gameBoard);
		this.setSize(240, 320);
	}


	/**
	 * Updates game buttons based on user movements.
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
				if (tiles[i][j].getValue() < 0)
				{
					gridButtons[j][i].setText("");
					gridButtons[j][i].setBackground(Color.white);
					gridButtons[j][i].setEnabled(false);
				}

				else
				{
					gridButtons[j][i].setText(tiles[i][j].getValue() + "");
					gridButtons[j][i].setBackground(Color.gray);
					gridButtons[j][i].setEnabled(true);
				}
			}
		}
	}

	@Override
	public void boardChanged()
	{
		refreshTiles();
		userMovesLabel.setText("Moves: " + boardController.getUserMoves());
	}

	@Override
	public void boardSolved()
	{
		JOptionPane.showMessageDialog(null, "The board was solved");
	}
}
