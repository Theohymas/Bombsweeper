import java.util.ArrayList;

public class BombSquare extends GameSquare
{
    private GameBoard board;                            // Object reference to the GameBoard this square is part of.
    private boolean hasBomb;                            // True if this squre contains a bomb. False otherwise.

    private boolean rightClickedStatus = false;
    private boolean leftClickedStatus = false;

    private ArrayList<BombSquare> surroundingsArray = new ArrayList<>();

    private boolean emptyStatus = false;

	public static final int MINE_PROBABILITY = 10;

	public BombSquare(int x, int y, GameBoard board)
	{
		super(x, y, "images/blank.png");

        this.board = board;
        this.hasBomb = ((int) (Math.random() * MINE_PROBABILITY)) == 0;
    }

    public void leftClicked()
    {
        if(leftClickedStatus == false)
        {
            leftClickIcon();
        }
    }

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

    public void setNumber(int bombCounter)
    {
        this.setImage("images/" + bombCounter + ".png");
    }

    public int surroundingsCounter()
    {
        this.surroundingBombs();

        int bombCounter = 0;
        for(BombSquare square: surroundingsArray)
        {
            bombCounter += (square.hasBomb? 1:0);
        }

        if(bombCounter == 0)
        {
            emptyStatus = true;
        }
        return bombCounter;
    }

    public void leftClickIcon()
    {
        int bombCount = surroundingsCounter();

        if(this.hasBomb == true)
        {
            this.setImage("images/bomb.png");
            leftClickedStatus = true;
        }
        else if(bombCount == 0)
        {
            revealEmptys();
        }
        else
        {
            this.setNumber(bombCount);
            leftClickedStatus = true;
        }
    }

    public void addToArrray(int x, int y)
    {
        int aX = this.getXLocation()+ x;
        int aY = this.getYLocation()+ y;

        System.out.println("aX ==>  " + (aX + x));
        System.out.println("aY ==>  " + (aY + y));

        BombSquare current = (BombSquare) board.getSquareAt(aX, aY);

        this.surroundingsArray.add(current);
    }

    public void revealEmptys()
    {
        //adds surrounding squares to the surroundingsArray
        
        surroundingBombs();

        for(BombSquare square: this.surroundingsArray)
        {
            //for every bomb square, if false then return out of method, else print blank icon on all 
            if(square.emptyStatus == false)
            {
                return;
            }
        }
        for(BombSquare square: this.surroundingsArray)
        {
            square.setNumber(surroundingsCounter());
        }
        revealEmptys();
    }
}