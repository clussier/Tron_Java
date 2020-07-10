import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Font;

public class Board_CLussier extends JPanel
{
	public static int[][] matrix;
	public final int NUM_ROWS;
	public final int NUM_COLS;
	static final int size = 3;
	public static boolean isTronOneAlive = true;
	public static boolean isTronTwoAlive = true;
	public static int tron1Win = 0;
	public static int tron2Win = 0;
	public static int phase = 1;
	public static int colr = 0;
			
	public Board_CLussier(int r, int c)
	{	
		NUM_ROWS = r;
		NUM_COLS = c;
		matrix =new  int[NUM_ROWS][NUM_COLS];
		setPreferredSize(new Dimension((NUM_ROWS)*size, (NUM_COLS)*size));
	}
	
	public void setValue(int r, int c, int color)
	{
		matrix[r][c] = color;	
	}
	
	public int getValue(int r, int c)
	{
		return matrix[r][c];
	}

	public void paintComponent(Graphics g)
		{	
			super.paintComponents(g);
			g.setColor(Color.darkGray);
			setBackground(Color.BLACK);
			int maxWidth = NUM_COLS * size;
			int maxHeight = NUM_ROWS * size;
			Color rainbow;
			
			//draw grids
			g.drawRect(0, 0, maxHeight, maxWidth);
			for(int x = 0; x < maxHeight; x = x + size)
				for(int y = 0; y < maxWidth; y = y + size)
				{
					g.drawRect(x, y, size, size);
				}
					
			//checks and fills non zero entities
			for(int i = 0; i < NUM_ROWS; i++)
				for( int j = 0; j < NUM_COLS; j++)
				{
					if(matrix[i][j] == 2)						//head 1
					{	g.setColor(Color.cyan);
						g.fillRect(i*size, j*size, size, size);
					}
					else if(matrix[i][j] == 1)					//body 1
					{
						g.setColor(Color.BLUE);
						g.fillRect(i*size, j*size, size, size);
					}
					else if(matrix[i][j] == 4)					//head 2
					{
						g.setColor(Color.orange);
						g.fillRect(i*size, j*size, size, size);
					}
					else if(matrix[i][j] == 3)					//body 2
					{
						g.setColor(Color.RED);
						g.fillRect(i*size, j*size, size, size);
						switch(phase)
						{
							case 1:
								rainbow = new Color(255, colr, 0);
								g.setColor(rainbow);
								g.fillRect(i*size, j*size, size, size);
								break;
							case 2:
								rainbow = new Color(255 - colr, 255, 0);
								g.setColor(rainbow);
								g.fillRect(i*size, j*size, size, size);
								break;
							case 3:
								rainbow = new Color(0, 255, colr);
								g.setColor(rainbow);
								g.fillRect(i*size, j*size, size, size);
								break;
							case 4:
								rainbow = new Color(colr, 255 - colr, 255);
								g.setColor(rainbow);
								g.fillRect(i*size, j*size, size, size);
								break;
							case 5:
								rainbow = new Color(255, 0, 255 - colr);
								g.setColor(rainbow);
								g.fillRect(i*size, j*size, size, size);
								break;
							
						}
					}		
				}
			
			//Draw score-Board
			Font font = new Font("Times New Roman", Font.PLAIN, 100);
			g.setColor(Color.YELLOW);
			g.setFont(font);
			
			if((TronGame_CLussier.tron1.isDead) || (TronGame_CLussier.tron2.isDead))
			{
				g.drawString("Game Over", (maxHeight*13/44), maxWidth/6);
				g.drawString(tron2Win + " : " + tron1Win, maxHeight*5/12, maxWidth*3/9);
				TronGame_CLussier.myBoard.repaint();
			}
		}
}