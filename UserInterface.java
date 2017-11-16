import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UserInterface extends JPanel implements MouseListener, MouseMotionListener{
	static int x=0,y=0;
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.setBackground(Color.white);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		g.setColor(Color.RED);
		g.fillRect(x-20, y-20, 40,40);
		g.setColor(Color.BLUE);
		g.fillRect(40,20,80,50);
		g.drawString("Zackery", x, y);
		
		Image chessPiecesImage;
		chessPiecesImage = new ImageIcon("ChessPiece.png").getImage();
		g.drawImage(chessPiecesImage, x, y, 100, 100, this);

	}
	public void mouseMoved(MouseEvent e){

	}
	public void mousePressed(MouseEvent e){

	}
	public void mouseReleased(MouseEvent e){

	}
	public void mouseClicked(MouseEvent e){
		x=e.getX();
		y=e.getY();
		repaint();
		
	}
	public void mouseDragged(MouseEvent e){

	}
	public void mouseEntered(MouseEvent e){

	}
	public void mouseExited(MouseEvent e){

	}
}
