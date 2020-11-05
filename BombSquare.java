import java.util.ArrayList;

public class BombSquare extends GameSquare
{
    private GameBoard board;                            // Object reference to the GameBoard this square is part of.
    private boolean hasBomb;                            // True if this squre contains a bomb. False otherwise.

    private boolean rightClickedStatus = false;
    private boolean leftClickedStatus = false;

    private ArrayList<BombSquare> surroundingsArray = new ArrayList<>();

	public static final int MINE_PROBABILITY = 10;

	public BombSquare(int x, int y, GameBoard board)
	{
		super(x, y, "images/blank.png");

        this.board = board;
        this.hasBomb = ((int) (Math.random() * MINE_PROBABILITY)) == 0;
    }

    public void leftClicked()
    {
        if(this.hasBomb == true)
        {
            System.out.println("bomb clicked");
            this.setImage("images/bomb.png");
            leftClickedStatus = true;
        }
        else
        {
            this.setNumber(surroundingsCounter());
            leftClickedStatus = true;
        }
    }

    public void rightClicked()
    {
        if(rightClickedStatus == false)
        {
            this.setImage("images/flag.png");
            rightClickedStatus = true;
        }
        else if(rightClickedStatus)
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
                int aX = this.getXLocation()+ x;
                int aY = this.getYLocation()+ y;

                System.out.println("aX ==>  " + (aX + x));
                System.out.println("aY ==>  " + (aY + y));

                BombSquare current = (BombSquare) board.getSquareAt(aX, aY);

                surroundingsArray.add(current);
            }
        }
        for(int i = 0; i < surroundingsArray.size(); i++)
        {
            System.out.println("===>" + surroundingsArray.get(i).hasBomb);
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
        return bombCounter;
    }
}