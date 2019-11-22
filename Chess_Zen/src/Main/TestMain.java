package Main;

import java.util.ArrayList;

import Logic.AlphaBeta;
import Logic.Evaluation;
import Logic.Gamestate;
import Logic.Move;
import Logic.Movegenerator;
import staticValues.Colour;

public class TestMain {

	public static void main(String[] args) {

		Gamestate gm = new Gamestate();
		Evaluation eva = new Evaluation();
		Movegenerator moveGenerator = new Movegenerator(gm);
		ArrayList<Move> moves = moveGenerator.generateMoves();
		
		AlphaBeta AB = new AlphaBeta(false, 1);
		
		ArrayList<Double> moveEvaluations = new ArrayList<Double>();
		
//		gm.printBoard();
//		
//		System.out.println("move.size() = " + moves.size());
//		
//		Move testMove = new Move(0, 6, 0, 4);
//		Move testMove2 = new Move(6, 0, 4, 0);
//		gm.movePiece(testMove);
		
//		gm.printBoard();
		
		
//		Gamestate tempGM = new Gamestate();
		int i = 0;
		
		for(Move m: moves) {
			Gamestate tempGM = new Gamestate(gm);
			tempGM.movePiece(m);
			moveEvaluations.add(AB.runAlphaBeta(-100000, 100000, tempGM, 1));
//			System.out.println("tempGM:");
			System.out.println(moveEvaluations.get(i++));
			tempGM.printBoard();
//			System.out.println("gm:");
//			gm.printBoard();
//			System.out.println(i++);
		}
		
//		gm.printBoard();
//		tempGM.printBoard();
		
		
//		for(int i = 0; i < moves.size(); i++) {
//			moves.get(i).printMove();
//			System.out.println(moveEvaluations.get(i));
//		}
		
	}

}
