package assign09;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class contains tests for the SlidePuzzleFrame class.
 * 
 * @author Prof. Heisler and Diya Mandot, u1454751
 * @version November 16, 2023grade
 */
public class SlidePuzzleFrameTest {

	private SlidePuzzleFrame puzzleFrame;

	@BeforeEach
	public void setUp() {
		puzzleFrame = new SlidePuzzleFrame();
	}

	@Test
	public void testStartInUnsolvedState() {
		SlidePuzzleFrame frame = new SlidePuzzleFrame();
		assertFalse(frame.isSolved());
	}
	

	}


