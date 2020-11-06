import java.util.ArrayList;

public class BombSquare extends GameSquare
{
    private GameBoard board;                            // Object reference to the GameBoard this square is part of.
    private boolean hasBomb;                            // True if this squre contains a bomb. False otherwise.
    private int surroundingsCounter;

    private boolean rightClickedStatus = false;
    private boolean leftClickedStatus = false;

    private ArrayList<BombSquare> surroundingsArray = new ArrayList<>();

	public static final int MINE_PROBABILITY = 10;

	/**
	 * Create a new BombSquare, which can be placed on a GameBoard.
	 * 
	 * @param x the x co-ordinate of this square on the game board.
	 * @param y the y co-ordinate of this square on the game board.
	 * @param board the Gmaeboard on which the Bombsquare is placed.
	 */
	public BombSquare(int x, int y, GameBoard board)
	{
		super(x, y, "images/blank.png");

        this.board = board;
        this.hasBomb = ((int) (Math.random() * MINE_PROBABILITY)) == 0;
    }

    
	/**
	 * invoked when mouse is left clicked.
	 */	
    public void leftClicked()
    {
        if(leftClickedStatus == false)
        {
            leftClickIcon();
        }
    }

    /**
	 * invoked wehn mouse is right clicked
	 */	
    public void rightClicked()
    {
        if(rightClickedStatus == false)
        {
            this.setImage("images/flag.png");
            rightClickedStatus = true;
        }
        else
        {
            this.setImage("images/blank.png");
            rightClickedStatus = false;
        }
    }

    /**
	 * iterates through the squares' surrounding squares
	 */	
    public void surroundingBombs()
    {
        for(int x = -1 ; x <= 1 ; x++)
        {
            for(int y = -1; y <= 1; y++)
            {
                addToArrray(x, y);
            }
        }
    }

    /**
	 * Change the image displayed by this square to the correct number, displaying number of surrounding bombs
	 * 
	 * @param bombCounter the integer to be displayed on the Square
	 */	
    public void setNumber(int bombCounter)
    {
        this.setImage("images/" + bombCounter + ".png");
    }

    /**
	 * Calculates the number of surrounding bombs and stores int in surroundingsCounter.
	 */	
    public void surroundingsCounter()
    {
        surroundingBombs();

        for(BombSquare square: surroundingsArray)
        {
            surroundingsCounter += (square.hasBomb? 1:0);
        }
    }

    /**
	 * Sets the square icon to correct image for a left click
	 */	
    public void leftClickIcon()
    {
        surroundingsCounter();

        if(this.hasBomb == true)
        {
            this.setImage("images/bomb.png");
            leftClickedStatus = true;
        }
        else if(this.surroundingsCounter == 0)
        {
            System.out.println("bombCount:  " + surroundingsCounter);
            setNumber(surroundingsCounter);
            revealEmptys(surroundingsArray);
        }
        else
        {
            this.setNumber(surroundingsCounter);
            leftClickedStatus = true;
        }
    }


    /**
	 * Gets the next surrounding square and adds to surroundingsArray
	 * 
	 * @param x the int added to square x coordinate to get surrounding square
	 * @param y the int added to square y coordinate to get surrounding square
	 */	
    public void addToArrray(int x, int y)
    {
        int aX = this.getXLocation()+ x;
        int aY = this.getYLocation()+ y;

        BombSquare current = (BombSquare) board.getSquareAt(aX, aY);

        this.surroundingsArray.add(current);
    }

    /**
	 * reveales all empty, adjacent squares to members of ArrayList
	 * 
	 * @param parentList ArrayList of the square's surrounding squares
	 */	
    public void revealEmptys(ArrayList<BombSquare> parentList)
    {
        for(BombSquare square: parentList)
        {
            square.setNumber(square.surroundingsCounter);

            square.surroundingsCounter();

            while(square.surroundingsCounter == 0)
            {
                square.surroundingsCounter();
                for(BombSquare s: square.surroundingsArray)
                {
                    revealEmptys(s.surroundingsArray);
                }
            }
        }

    }
}
