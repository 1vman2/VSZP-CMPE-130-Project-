import javax.swing.*;
public class ABChess {
	static String chessBoard[][]={
		{"r","k","b","q","a","b","k","r"},
		{"p","p","p","p","p","p","p","p"},
		{" "," "," "," "," "," ","P"," "},
		{" "," "," "," "," "," "," "," "},
		{" "," "," ","Q"," "," "," "," "},
		{" "," "," "," "," "," "," "," "},
		{"P","P","P","P","P","P","P","P"},
		{"R","K","B","Q","A","B","K","R"}};		//Uppercase is white side, lower is black
												//A is for the king, since k is taken by the Knight

	static int kingPositionC,kingPositionL;		//Capital and Lowercase king
	
	public static void main(String[] args) {
		/*
		JFrame frame = new JFrame("CHESS");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		UserInterface ui=new UserInterface();
		frame.add(ui);
		frame.setSize(500, 500);
		frame.setVisible(true);
		 */
		System.out.println(posibleMoves());

	}

	public static String posibleMoves(){
		String list="";
		for(int i=0;i<64;i++){
			switch (chessBoard[i/8][i%8]){
			
			case("P"):list+=possibleP(i);
				break;
			case("K"):list+=possibleK(i);
				break;
			case("B"):list+=possibleB(i);
				break;
			case("Q"):list+=possibleQ(i);
				break;
			case("A"):list+=possibleA(i);
				break;


			}
		}
		return list ;		//returns (x1,y1,x2,y2,captured_peice) , x1 y1 being starting location; x2y2 being
	}

	public static String possibleP(int i){
		String list ="";
		return list;
		}
	public static String possibleK(int i){
		String list ="";
		return list;
	}
	public static String possibleB(int i){
		String list ="";
		return list;
	}
	public static String possibleQ(int i){
		String list ="", oldPiece;
		int r=i/8,c=i%8;
		int temp=1;
		
		for(int j=-1;j<=1;j++){
			for(int k=-1;k<=1;k++){
				try{
					while(" ".equals(chessBoard[r+temp*j][c+temp*k])){
						oldPiece=chessBoard[r+temp*j][c+temp*k];
						chessBoard[r][c]=" ";
						chessBoard[r+temp*j][c+temp*k]="Q";
						if(kingSafe()){
							list=list+r+c+(r+temp*j)+(c+temp*k)+oldPiece;
						}
						chessBoard[r][c]="Q";
						chessBoard[r+temp*j][c+temp*k]=oldPiece;
						temp++;
					}
					if(Character.isLowerCase(chessBoard[r+temp*j][c+temp*k].charAt(0))){
						oldPiece=chessBoard[r+temp*j][c+temp*k];
						chessBoard[r][c]=" ";
						chessBoard[r+temp*j][c+temp*k]="Q";
						if(kingSafe()){
							list=list+r+c+(r+temp*j)+(c+temp*k)+oldPiece;
						}
						chessBoard[r][c]="Q";
						chessBoard[r+temp*j][c+temp*k]=oldPiece;
					}
				}catch(Exception e){}
				temp=1;
			}
			
			
		}
		
		
		
		return list;
	}
	public static String possibleA(int i){
		String list ="", oldPiece;
		int r=i/8,c=i%8;
		for(int j=0;j<9;j++){
			if(j!=4){
				
				try{
					if(Character.isLowerCase(chessBoard[r-1+j/3][c-1+j%3].charAt(0)) || " ".equals(chessBoard[r-1+j/3][c-1+j%3])){
						oldPiece=chessBoard[r-1+j/3][c-1+j%3];
						chessBoard[r][c]= " ";
						chessBoard[r-1+j/3][c-1+j%3]="A";
						int kingTemp=kingPositionC;
						kingPositionC =i+(j/3) * 8+j%3-9; 
						if(kingSafe()){
							list=list+r+c+(r-1+j/3)+(c-1+j%3)+oldPiece;
						}
						chessBoard[r][c]="A";
						chessBoard[r-1+j/3][c-1+j%3]=oldPiece;
						kingPositionC=kingTemp;
					}
				} catch(Exception e){}
			}
		}
		return list;
		//Need to add Castling move
	}
	public static boolean kingSafe(){
		return true;
	}
}
