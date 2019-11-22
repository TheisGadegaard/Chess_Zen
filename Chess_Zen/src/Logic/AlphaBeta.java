package Logic;

import java.util.ArrayList;

public class AlphaBeta {

	private boolean isWhite;
	private int searchDepth;
	Evaluation eva;

	public AlphaBeta(boolean isWhite, int searchDepth) {
		this.isWhite = isWhite;
		this.searchDepth = searchDepth;
		this.eva = new Evaluation();
	}

	public double runAlphaBeta(double alpha, double beta, Gamestate gamestate, int currentDepth) {

		//node is leaf
		if(currentDepth == searchDepth) {	
			return eva.evaluateState(gamestate, isWhite)-eva.evaluateState(gamestate, !isWhite);

		} 

		Movegenerator movegen = new Movegenerator(gamestate);
		ArrayList<Move> moves = movegen.generateMoves();
		ArrayList<Gamestate> childnodes = new ArrayList<Gamestate>();
		for(Move m: moves) {
			Gamestate tempGamestate = gamestate;
			tempGamestate.movePiece(m);
			childnodes.add(tempGamestate);
		}

		int nextNode = 0;

		//node is MAX
		if(currentDepth % 2 == 1) {				
			while(alpha < beta && nextNode < childnodes.size()) {
				if(childnodes.get(nextNode) != null) {
					double V = runAlphaBeta(alpha, beta, childnodes.get(nextNode++), currentDepth+1);
					if(V > alpha) {
						alpha = V;
					}
				} else {
					nextNode++;
				}

			}

			return alpha;

			//node is MIN
		} else {		
			while(alpha < beta && nextNode < childnodes.size()) {
				if(childnodes.get(nextNode) != null) {
					double V = runAlphaBeta(alpha, beta, childnodes.get(nextNode++), currentDepth+1);
					if(V < beta) {
						beta = V;
					}
				} else {
					nextNode++;
				}
			}
			
			return beta;
		}
	}
}
