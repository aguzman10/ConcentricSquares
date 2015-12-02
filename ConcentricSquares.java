import java.awt.*; //import needed to use the canvas extention
import java.math.*; // import needed to use the power function
import java.awt.event.*; // import needed to use the window listener

public class ConcentricSquares extends Frame
{
	public static void main(String[] args) // main function used to call the creation of a new ConcentricSquares() object
	{
		new ConcentricSquares(); // creates new ConcentricSquares object
	}
	
	ConcentricSquares()
	{
		super("ConcentricSquares"); // creates a window with the title "ConcentricSquares"
		
		addWindowListener(new WindowAdapter() // an event method that is used to close the window when user clicks the X
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		
		setSize(1370,730); // sets the size of the frame
		add("Center", new CvConcentricSquares()); //adds a new CvConcentricSquare to the center of the frame
		show(); 
	}
	
	class CvConcentricSquares extends Canvas
	{
		public void paint(Graphics g)
		{
			Dimension d = getSize(); // gets the size(width and height) of the window
			int xBefore=350,yBefore=40,maxX=d.width-750,maxY=d.height-100; // variables needed to form the first square
			double counter = 2; //counter variable that is used as a power variable
			
			g.drawRect(xBefore,yBefore,maxX,maxY); // draws the first rectangle
			
			//these formulas set the square to be turned at a 45 degree angle, making them appear as a rhombus rather than a square
			int xAfter=(xBefore)+(maxX/2); //sets the midpoint x coordinate for the next square 
			int yAfter=(yBefore)+(maxY/2); //sets the midpoint y coordinate for the next square
				
			for(int i = 0; i < 50; i++) //spec wasn't clear on how many squares were needed, and an infinite while loop kept crashing on my laptop, so i set a for loop to end after 50 iterations
			{
				// these lines for a square at 45 degree angles, making them appear as a rhombus
				g.drawLine(xAfter, yBefore, xBefore, yAfter); // draws the first line using the midpoints set
				g.drawLine(xBefore,yAfter,xAfter,yAfter*2-yBefore); // draws the second line using midpoints and the formula (yAfter*2-yBefore) to find the second y coordinate
				g.drawLine(xAfter, yBefore, xAfter*2-xBefore, yAfter); //draws the third line using midpoints and the formula (xAfter*2-xBefore) to find the second x coordinate
				g.drawLine(xAfter*2-xBefore, yAfter, xAfter, yAfter*2-yBefore); //draws the final line to complete the square using midpoints and the 2 above formulas to find the remaining coordinates of the midpoints
					
				//these formulas set the next shape to be back to a straight square upright instead of a rhombus
				xAfter = (xAfter+xBefore)/2; //sets the new x coordinates midpoints for the next square by adding the current midpoint, and the distance between the current x point of the square and the last, then dividing by 2
				yAfter = (yAfter+yBefore)/2; //sets the new y coordinates midpoints for the next square by adding the current midpoint, and the distance between the current y point of the square and the last, then dividing by 2
					
					
				//draws the new square using the midpoints found above, as well as new formulas to find missing midpoints by using the known midpoints
				g.drawLine(xAfter, yAfter,(((xAfter*2)-xBefore)+(((xAfter*2)-xBefore)*2-xBefore))/2,yAfter);
				g.drawLine(xAfter, (((yAfter*2)-yBefore)+(((yAfter*2)-yBefore)*2-yBefore))/2, (((xAfter*2)-xBefore)+(((xAfter*2)-xBefore)*2-xBefore))/2, (((yAfter*2)-yBefore)+(((yAfter*2)-yBefore)*2-yBefore))/2);
				g.drawLine(xAfter, yAfter, xAfter, (((yAfter*2)-yBefore)+(((yAfter*2)-yBefore)*2-yBefore))/2);
				g.drawLine((((xAfter*2)-xBefore)+(((xAfter*2)-xBefore)*2-xBefore))/2, yAfter, (((xAfter*2)-xBefore)+(((xAfter*2)-xBefore)*2-xBefore))/2, (((yAfter*2)-yBefore)+(((yAfter*2)-yBefore)*2-yBefore))/2);
					
				//sets up the midpoints to be used by the next iteration of the loop
				xBefore = xAfter;
				xAfter = (xAfter)+(maxX/(int)Math.pow(2.0,counter)); //i had the most trouble here.  I kept getting wrong results because i multiplied rather than powering 2.
				yBefore = yAfter;
				yAfter = (yAfter)+(maxY/(int)Math.pow(2.0, counter));
				counter++;
			}
		}
	}
}
