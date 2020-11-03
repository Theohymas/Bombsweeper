import javax.xml.stream.events.StartDocument;

public class BombSquare extends GameSquare
{
    private GameBoard board;                            // Object reference to the GameBoard this square is part of.
    private boolean hasBomb;                            // True if this squre contains a bomb. False otherwise.
    
    private int xLocation;
    private int yLocation;

    private boolean rightClickedStatus = false;
    private boolean leftClickedStatus = false;


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
        }
        rightClickedStatus = true;
    }

    public void rightClicked()
    {

        if(rightClickedStatus == false)
        {
            this.setImage("images/flag.png");
            rightClickedStatus = true;
        }
        else if(rightClickedStatus == true)
        {
            this.setImage("images/blank.png");
            rightClickedStatus = false;
        }
    }

    // public int surroundingBombs()
    // {
    //     int bombCounter = 0;

        // int startX = super.getXlocation();
        // int startY = this.getYlocation();

        // for(int x = -1 ; x <= 1 ; x++)
        // {
        //     for(int y = 0; y <= 1; y++)
        //     {
        //         GameSquare current = board.getSquareAt(startX + x, startY + y);
        //         if(current.hasBomb == true)
        //         {
        //             bombCounter++;
        //         }
        //     }
        // }
        // return bombCounter;

        
    // public void useThisForsurroundingbombs()
    // {
    //     int startX = this.getXlocation();
    //     System.out.println("Test:   "+ this.getXlocation());
    // }
}

    // public String bombCounterImage
    // (
    //     if(this.surroundingBombs())
    // )


