import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class UserInterface extends JPanel implements MouseListener, MouseMotionListener{
	static int mouseX, mouseY, newMouseX, newMouseY; //set current mouse position to the new mouse positions when released
	static int x=0,y=0;
	static int squareSize=64;
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.setBackground(Color.blue);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		for(int i=0;i<64;i+= 2){ //creating the tiles of the chessboard
			g.setColor(new Color(255,200,100)); 
			g.fillRect((i%8+(i/8)%2)*squareSize, (i/8)*squareSize, squareSize, squareSize);
			g.setColor(new Color(150,50,30));
			g.fillRect(((i+1)%8-((i+1)/8)%2)*squareSize, ((i+1)/8)*squareSize, squareSize, squareSize);
			
		}
		Image chessPiecesImage;
		chessPiecesImage=new ImageIcon("ChessPieces.png").getImage();
		//startlocation, endlocation, currentlocation
		for(int i=0;i<64;i++){
			int j =-1, k=-1;
            switch (AlphaBetaChess.chessBoard[i/8][i%8]) {
            case "P": j=5; k=0;
                break;
            case "p": j=5; k=1;
            break;
            case "R": j=2; k=0;
                break;
            case "r": j=2; k=1;
            break;
            case "K": j=4; k=0;
                break;
            case "k": j=4; k=1;
            break;            
            case "B": j=3; k=0;
                break;
            case "b": j=3; k=1;
            break;
            case "Q": j=1; k=0;
                break;
            case "q": j=1; k=1;
            break;
            case "A": j=0; k=0;
                break;
            case "a": j=0; k=1;
            break;
		}
		if(j!=-1 && k!=-1){
			g.drawImage(chessPiecesImage, (i%8)*squareSize, (i/8)*squareSize, (i%8+1)*squareSize, (i/8+1)*squareSize, j*64, k*64, (j+1)*64, (k+1)*64,this);

			}
		}
		/*
		g.setColor(Color.RED);
		g.fillRect(x-10, y-10, 90, 90);
		g.setColor(Color.GREEN);
		g.fillRect(50, 50, 80, 80);
		g.drawString("Varinder", x, y);

		*/
	}
	public void mouseMoved(MouseEvent e){

	}
	public void mousePressed(MouseEvent e){
		//pressing the mouse inside the board
		if(e.getX()<8*squareSize && e.getY()<8*squareSize){
			mouseX=e.getX();// takes current value of the mouse 
			mouseY=e.getY();
			repaint();
		}
	}
	public void mouseReleased(MouseEvent e){
		//pressing the mouse inside the board
		if(e.getX()<8*squareSize && e.getY()<8*squareSize){
			newMouseX=e.getX(); //changes the current value of the mouse
			newMouseY=e.getY();
			if (e.getButton()==MouseEvent.BUTTON1){
				String dragMove;
				if(newMouseY/squareSize==0 && mouseY/squareSize==1 && "P".equals(AlphaBetaChess.chessBoard[mouseY/squareSize][mouseX/squareSize])){
					//pawn promotion 
					 dragMove= ""+mouseX/squareSize + newMouseX/squareSize +AlphaBetaChess.chessBoard[newMouseY/squareSize][newMouseX/squareSize]+"QP"; // this is used to track the drag move

				}else{
					//like normal
					dragMove= ""+mouseY/squareSize + mouseX/squareSize + newMouseY/squareSize + newMouseX/squareSize+AlphaBetaChess.chessBoard[newMouseY/squareSize][newMouseX/squareSize]; // this is used to track the drag move

				}
				String userPosibilities = AlphaBetaChess.posibleMoves();
				
				if(userPosibilities.replaceAll(dragMove, "").length()<userPosibilities.length()){ //back and forth between comp and human
					AlphaBetaChess.makeMove(dragMove);
					AlphaBetaChess.flipBoard();
					AlphaBetaChess.makeMove(AlphaBetaChess.alphaBeta(AlphaBetaChess.globalDepth, 1000000, -1000000, "", 0));
					AlphaBetaChess.flipBoard();
					repaint();
				} 
			}
			repaint();
		}
	}
		
	public void mouseClicked(MouseEvent e){
	}
	public void mouseDragged(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}


}
