
public class Rating {
    static int pawnOnBoard[][]={//attribute to http://chessprogramming.wikispaces.com/Simplified+evaluation+function
        { 0,  0,  0,  0,  0,  0,  0,  0},
        {50, 50, 50, 50, 50, 50, 50, 50},
        {10, 10, 20, 30, 30, 20, 10, 10},
        { 5,  5, 10, 25, 25, 10,  5,  5},
        { 0,  0,  0, 20, 20,  0,  0,  0},
        { 5, -5,-10,  0,  0,-10, -5,  5},
        { 5, 10, 10,-20,-20, 10, 10,  5},
        { 0,  0,  0,  0,  0,  0,  0,  0}};
    static int rookOnBoard[][]={
        { 0,  0,  0,  0,  0,  0,  0,  0},
        { 5, 10, 10, 10, 10, 10, 10,  5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        { 0,  0,  0,  5,  5,  0,  0,  0}};
    static int knightOnBoard[][]={
        {-50,-40,-30,-30,-30,-30,-40,-50},
        {-40,-20,  0,  0,  0,  0,-20,-40},
        {-30,  0, 10, 15, 15, 10,  0,-30},
        {-30,  5, 15, 20, 20, 15,  5,-30},
        {-30,  0, 15, 20, 20, 15,  0,-30},
        {-30,  5, 10, 15, 15, 10,  5,-30},
        {-40,-20,  0,  5,  5,  0,-20,-40},
        {-50,-40,-30,-30,-30,-30,-40,-50}};
    static int bishopOnBoard[][]={
        {-20,-10,-10,-10,-10,-10,-10,-20},
        {-10,  0,  0,  0,  0,  0,  0,-10},
        {-10,  0,  5, 10, 10,  5,  0,-10},
        {-10,  5,  5, 10, 10,  5,  5,-10},
        {-10,  0, 10, 10, 10, 10,  0,-10},
        {-10, 10, 10, 10, 10, 10, 10,-10},
        {-10,  5,  0,  0,  0,  0,  5,-10},
        {-20,-10,-10,-10,-10,-10,-10,-20}};
    static int queenOnBoard[][]={
        {-20,-10,-10, -5, -5,-10,-10,-20},
        {-10,  0,  0,  0,  0,  0,  0,-10},
        {-10,  0,  5,  5,  5,  5,  0,-10},
        { -5,  0,  5,  5,  5,  5,  0, -5},
        {  0,  0,  5,  5,  5,  5,  0, -5},
        {-10,  5,  5,  5,  5,  5,  0,-10},
        {-10,  0,  5,  0,  0,  0,  0,-10},
        {-20,-10,-10, -5, -5,-10,-10,-20}};
    static int kingMidBoard[][]={
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-20,-30,-30,-40,-40,-30,-30,-20},
        {-10,-20,-20,-20,-20,-20,-20,-10},
        { 20, 20,  0,  0,  0,  0, 20, 20},
        { 20, 30, 10,  0,  0, 10, 30, 20}};
    static int kingEndBoard[][]={
        {-50,-40,-30,-20,-20,-30,-40,-50},
        {-30,-20,-10,  0,  0,-10,-20,-30},
        {-30,-10, 20, 30, 30, 20,-10,-30},
        {-30,-10, 30, 40, 40, 30,-10,-30},
        {-30,-10, 30, 40, 40, 30,-10,-30},
        {-30,-10, 20, 30, 30, 20,-10,-30},
        {-30,-30,  0,  0,  0,  0,-30,-30},
        {-50,-30,-30,-30,-30,-30,-30,-50}};
    public static int rate(int list, int depth) {// designed to keep track of who is winning
    	int counter= 0, material= rateMaterial();
    	counter+= rateAttack();
        counter+=material;
    	counter+= rateMaterial();
    	counter+= rateMoveable(list, depth, material);
    	counter+= ratePosition(material);
    	ABChess.flipBoard();
    	counter-= rateAttack();
        counter-= material;
    	counter-= rateMaterial();
    	counter-= rateMoveable(list, depth, material);
    	counter-= ratePosition(material);    	
    	ABChess.flipBoard();
   	
        return -(counter+depth*50);
    }
    public static int rateAttack() { //logic revolving around attack
        int counter= 0;
        int tempPositionC= ABChess.kingPositionC;
        for (int i=0;i<64;i++) {
            switch (ABChess.chessBoard[i/8][i%8]) {
                case "P": {
                	ABChess.kingPositionC=i; //is the king safe?
                	if(!ABChess.kingSafe()){
                		counter-=32/2;
                	}
                }
                    break;
                case "R": {
                	ABChess.kingPositionC=i; //is the king safe?
                	if(!ABChess.kingSafe()){
                		counter-=500/2;
                	}
                }
                    break;
                case "K": {
                	ABChess.kingPositionC=i; //is the king safe?
                	if(!ABChess.kingSafe()){
                		counter-=300/2;
                	}
                }
                    break;
                case "B": {
                	ABChess.kingPositionC=i; //is the king safe?
                	if(!ABChess.kingSafe()){
                		counter-=300/2;
                	}
                }
                    break;
                case "Q": {
                	ABChess.kingPositionC=i; //is the king safe?
                	if(!ABChess.kingSafe()){
                		counter-=900/2;
                	}
                }
                    break;
            }
        }
        ABChess.kingPositionC= tempPositionC;
        if(!ABChess.kingSafe()) counter-=200;
    	return counter;
    }
    public static int rateMaterial() { //logic around pieces
        int counter= 0, bishopCnt= 0;
        for(int i=0;i<64;i++){
        	switch (ABChess.chessBoard[i/8][i%8]){
        	case "P": counter+=100; //worth 100 centi pawns
        		break;
        	case "R": counter+=500;
        		break;
        	case "K": counter+=300;
    			break;
        	case "B": bishopCnt+=1;
    			break;
        	case "Q": counter+=900;
    			break;

        	}
        }
        if(bishopCnt>=2) counter+=300*bishopCnt;
        else counter+=250*bishopCnt;
    	return counter;
    }
    public static int rateMoveable(int listLength, int depth, int material) { //rate how much its able to move
        int counter= 0;
        counter+=listLength; // 5 points per valid move
        if(listLength==0){
        	if(!ABChess.kingSafe()){
        		counter+=-(20*1000000)*depth; //Checkmate: very bad situation
        	}else{
        		counter+=-(10*1000000)*depth; // Stalemate: not so bad situation
        	}
        }
    	return counter;
    }
    public static int ratePosition(int material) { //should this piece be here
    	 int counter=0;
         for (int i=0;i<64;i++) {
             switch (ABChess.chessBoard[i/8][i%8]) {
                 case "P": counter+=pawnOnBoard[i/8][i%8];
                     break;
                 case "R": counter+=rookOnBoard[i/8][i%8];
                     break;
                 case "K": counter+=knightOnBoard[i/8][i%8];
                     break;
                 case "B": counter+=bishopOnBoard[i/8][i%8];
                     break;
                 case "Q": counter+=queenOnBoard[i/8][i%8];
                     break;
                 case "A": if (material>=1750) {counter+=kingMidBoard[i/8][i%8]; counter+=ABChess.possibleA(ABChess.kingPositionC).length()*10;} else
                 {counter+=kingEndBoard[i/8][i%8]; counter+=ABChess.possibleA(ABChess.kingPositionC).length()*30;}
                     break;
             }
         }
         return counter;
    }
   
}
