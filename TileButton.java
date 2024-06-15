package assign09;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * This class contains tests for the TileButton class.
 * 
 * @author Prof. Heisler and Diya Mandot
 * @version November 16, 2023
 */

public class TileButton extends JButton {

    // Serial version UID for serialization
    private static final long serialVersionUID = 1L;

    // The row and column position of the tile in the puzzle grid
    private final int row;
    private final int column;

    // The unique identifier for the image associated with the tile
    private int imageID;

    /**
     * Constructs a TileButton with the specified image file, row, column, and image ID.
     *
     * @param filename The path to the image file for the tile.
     * @param row      The row position of the tile in the puzzle grid.
     * @param column   The column position of the tile in the puzzle grid.
     * @param imageID  The unique identifier for the image associated with the tile.
     */
    public TileButton(String filename, int row, int column, int imageID) {
        super(new ImageIcon(filename));
        this.row = row;
        this.column = column;
        this.imageID = imageID;
    }

    /**
     * Gets the row position of the tile in the puzzle grid.
     *
     * @return The row position of the tile.
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the column position of the tile in the puzzle grid.
     *
     * @return The column position of the tile.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Gets the image ID associated with the tile.
     *
     * @return The image ID of the tile.
     */
    public int getImageID() {
        return imageID;
    }

    /**
     * Sets the image ID associated with the tile.
     *
     * @param imageID The new image ID to set.
     */
    private void setImageID(int imageID) {
        this.imageID = imageID;
    }

    /**
     * Swaps the properties (image and image ID) of the current tile with another tile.
     *
     * @param other The TileButton with which to swap properties.
     */
    public void swap(TileButton other) {
        Icon icon = this.getIcon();

        // Swap icons
        this.setIcon(other.getIcon());
        other.setIcon(icon);

        int tempImageID = this.getImageID();

        // Swap image IDs
        this.setImageID(other.getImageID());
        other.setImageID(tempImageID);
    }
}
