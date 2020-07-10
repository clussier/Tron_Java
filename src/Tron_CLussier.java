

public class Tron_CLussier
{
	public static Board_CLussier myBoard;
	private int posR;
	private int posC;
	private int newColor;
	private int dir;
	public boolean isDead;
	public static boolean isDraw = false;
	
	public Tron_CLussier(int r, int c, int cl, int d, Board_CLussier b)
	{
		isDead = false;
		posR = r;
		posC = c;
		newColor = cl;
		dir = d;						
		myBoard = b;	
		myBoard.setValue(r, c, cl);
	}

	public void setDir(int d)
	{
		if (Math.abs(dir-d) != 2)
		{
			dir = d;
		}
	}
	
	public boolean checkCollisions()
	{
		int newC = posC;
		int newR = posR;
		isDraw = false;
		//finds next spot based on direction
		switch (dir)
		{
		
			//left
			case 3: newR = posR-1;
					break;
			//down
			case 2: newC = posC+1;
					break;
			//right
			case 1: 
					newR = posR+1;
					break;
			//up
			case 0:	newC = posC-1;
			 		break;
		}
		
		
		//checks collision for top and bottom walls
		if((newR < 0) || (newR >= myBoard.NUM_ROWS))
		{
			return false;
		}
		//checks collision for left and right walls
		else if((newC < 0) || (newC >= myBoard.NUM_COLS))
		{
			return false;
		}
		//checks collisions for body collisions
		else if((myBoard.getValue(newR, newC)) != 0)
		{		
			return false;
		}
		
		
		return true;
	}

	
	
	
	public boolean move()
	{
		int color = newColor;
		switch (dir)
		{
			//left
			case 3: myBoard.setValue(posR, posC, --color);
					myBoard.setValue(--posR, posC, newColor);
					myBoard.repaint();
					break;
			//down
			case 2: myBoard.setValue(posR, posC, --color);
					myBoard.setValue(posR, ++posC, newColor);
					break;
			//right
			case 1: myBoard.setValue(posR, posC, --color);
					myBoard.setValue(++posR, posC, newColor);
					break;
			//up
			case 0: myBoard.setValue(posR, posC, --color);
					myBoard.setValue(posR, --posC, newColor);
			 		break;
		
		}
		return true;
	}


}
