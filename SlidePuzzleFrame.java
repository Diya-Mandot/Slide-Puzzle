package assign09;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This SlidePuzzleFrame class extends Jframe and implements Action Listener.
 * 
 * @author Prof. Heisler and Diya Mandot
 * @version November 16, 2023
 */

public class SlidePuzzleFrame extends JFrame implements ActionListener {

	// Serial version UID for serialization
	private static final long serialVersionUID = 1L;

	// 2D array to hold the TileButtons representing the puzzle tiles
	private TileButton[][] tiles;

	// Reference to the empty tile for tracking its position
	private TileButton emptyTile;

	// Buttons for shuffling and solving the puzzle
	private JButton shuffleButton;
	private JButton solutionButton;

	/**
	 * Constructs a SlidePuzzleFrame with a 4x4 grid of TileButtons, shuffle and
	 * solution buttons, and sets up the graphical user interface.
	 */
	public SlidePuzzleFrame() {
		// Exit on close
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Create 16 buttons and set up the ActionListener
		tiles = new TileButton[4][4];
		int id = 0;
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				String filename = "src/assign09/tile_" + row + "_" + col + ".png";
				tiles[row][col] = new TileButton(filename, row, col, id);
				id++;

				tiles[row][col].addActionListener(this);
			}
		}

		// Set the initial empty tile
		emptyTile = tiles[0][0];

		// Create the panel for the puzzle grid
		JPanel panel = new JPanel(new GridLayout(4, 4, 1, 1));
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				panel.add(tiles[row][col]);
			}
		}
		panel.setPreferredSize(new Dimension(720, 720));

		// Create the panel for shuffle and solution buttons
		JPanel controlPanel = new JPanel();
		shuffleButton = new JButton("Shuffle");
		shuffleButton.addActionListener(this);
		solutionButton = new JButton("Solution");
		solutionButton.addActionListener(this);
		controlPanel.add(shuffleButton);
		controlPanel.add(solutionButton);

		// Create the main panel and add the puzzle grid and control panel
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(panel, BorderLayout.CENTER);
		mainPanel.add(controlPanel, BorderLayout.SOUTH);

		setContentPane(mainPanel);

		// Shuffle the puzzle on initialization
		shuffle();
		this.pack();
	}

	/**
	 * Checks if a given tile is adjacent to the empty tile.
	 *
	 * @param tile The TileButton to check for adjacency with the empty tile.
	 * @return True if the tile is adjacent to the empty tile, false otherwise.
	 */
	private boolean adjacentToEmpty(TileButton tile) {
		return Math.abs(tile.getRow() - emptyTile.getRow()) + Math.abs(tile.getColumn() - emptyTile.getColumn()) == 1;
	}

	/**
	 * Shuffles the puzzle by performing a series of random tile swaps with the empty tile.
	 */
	private void shuffle() {
	    Random rng = new Random();
	    for (int i = 0; i < 1000; i++) {
	        int row = emptyTile.getRow();
	        int col = emptyTile.getColumn();
	        int direction = rng.nextInt(4);
	        int newRow = row, newCol = col;

	        // Update the new position based on the random direction
	        if (direction == 0 && row > 0)
	            newRow--;
	        else if (direction == 2 && row < 3)
	            newRow++;
	        else if (direction == 3 && col > 0)
	            newCol--;
	        else if (direction == 1 && col < 3)
	            newCol++;

	        // Perform the swap if the new position is valid
	        if (newRow != row || newCol != col) {
	            emptyTile.swap(tiles[newRow][newCol]);
	            emptyTile = tiles[newRow][newCol];
	        }
	    }
	}

	/**
	 * Solves the puzzle by first shuffling and then rearranging the tiles to their correct positions.
	 */
	public void solve() {
	    shuffle(); // Shuffle the puzzle before solving

	    for (int row = 0; row < 4; row++) {
	        for (int col = 0; col < 4; col++) {
	            while (tiles[row][col].getImageID() != (row * 4 + col)) {
	                int correctRow = tiles[row][col].getImageID() / 4;
	                int correctCol = tiles[row][col].getImageID() % 4;
	                tiles[row][col].swap(tiles[correctRow][correctCol]);
	            }
	        }
	    }
	}


	/**
	 * Checks if the puzzle is in a solved state.
	 *
	 * @return True if the puzzle is solved, false otherwise.
	 */
	public boolean isSolved() {
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				if (tiles[row][col].getImageID() != (row * 4 + col)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Handles ActionListener events for shuffle and solution buttons, as well as
	 * tile clicks.
	 *
	 * @param e The ActionEvent representing the button click or tile interaction.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (isSolved()) {
			return; // If the puzzle is already solved, do nothing
		}

		JLabel label = new JLabel("");
		Object source = e.getSource();

		if (source == shuffleButton) {
			shuffle();
		} else if (source == solutionButton) {
			solve();
		} else if (source instanceof TileButton) {
			TileButton clickedTile = (TileButton) source;

			// If the clicked tile is adjacent to the empty tile, perform the swap
			if (adjacentToEmpty(clickedTile)) {
				clickedTile.swap(emptyTile);
				emptyTile = clickedTile;
			}

			// Display message based on the puzzle state
			if (isSolved()) {
				label.setText("Congratulations! You solved the puzzle!");
			} else {
				label.setText("Click on a tile next to the empty/adjacent tile");
			}
		}
	}
}
