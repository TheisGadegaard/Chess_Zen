import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Logic.Gamestate;
import Logic.Move;
import Logic.Movegenerator;
import staticValues.Colour;

class Evaluation_test {

	@Test
	void test() {

		String[][] gameSample1 = {
				{"--","--","--","BR","--","BR","BK","--"},
				{"--","BP","--","BT","--","BP","BP","BP"},
				{"BP","WT","BB","BP","BP","--","--","--"},
				{"--","--","--","--","--","--","--","--"},
				{"--","--","WP","--","WP","--","--","--"},
				{"--","--","--","--","--","WP","--","--"},
				{"WP","WP","WB","--","--","--","WP","WP"},
				{"--","--","WR","WR","--","--","WK","--"}
		};

		Gamestate gm1B = new Gamestate(Colour.BLACK, gameSample1);
		Movegenerator moveGenerator1B = new Movegenerator(gm1B);
		ArrayList<Move> moves1B = moveGenerator1B.generateMoves();
		System.out.println("gm1B: " + moves1B.size());
		assertEquals(24, moves1B.size());								//Test 1 black

		Gamestate gm1W = new Gamestate(Colour.WHITE, gameSample1);
		Movegenerator moveGenerator1W = new Movegenerator(gm1W);
		ArrayList<Move> moves1W = moveGenerator1W.generateMoves();
		System.out.println("gm1W: " + moves1W.size());
		assertEquals(32, moves1W.size());								//Test 1 white

		String[][] gameSample2 =	{
				{"BR","--","--","BQ","--","--","BK","--"},
				{"--","BB","BP","BT","--","BP","BP","BP"},
				{"--","BP","--","BB","BR","--","--","--"},
				{"BP","WP","--","BP","--","--","--","--"},
				{"WP","--","--","WP","BT","--","--","--"},
				{"--","--","--","WB","WP","WT","--","--"},
				{"--","WB","--","--","WT","WP","WP","WP"},
				{"WR","--","--","WQ","--","WR","WK","--"}
		};

		Gamestate gm2B = new Gamestate(Colour.BLACK, gameSample2);
		Movegenerator moveGenerator2B = new Movegenerator(gm2B);
		ArrayList<Move> moves2B = moveGenerator2B.generateMoves();
		System.out.println("gm2B: " + moves2B.size());
		assertEquals(52, moves2B.size());								//Test 2 black

		Gamestate gm2W = new Gamestate(Colour.WHITE, gameSample2);
		Movegenerator moveGenerator2W = new Movegenerator(gm2W);
		ArrayList<Move> moves2W = moveGenerator2W.generateMoves();
		System.out.println("gm2W: " + moves2W.size());
		assertEquals(32, moves2W.size());								//Test 2 white

		String[][] gameSample3 =	{
				{"BR","--","BB","BQ","BK","BB","BT","BR"},
				{"BP","BP","--","BP","BP","BP","BP","BP"},
				{"--","--","BT","--","--","--","--","--"},
				{"--","--","BP","--","--","--","--","--"},
				{"--","--","--","--","WP","--","--","--"},
				{"--","--","WP","--","--","WT","--","--"},
				{"WP","WP","--","WP","--","WP","WP","WP"},
				{"WR","WT","WB","WQ","WK","WB","--","WR"}
		};

		Gamestate gm3B = new Gamestate(Colour.BLACK, gameSample3);
		Movegenerator moveGenerator3B = new Movegenerator(gm3B);
		ArrayList<Move> moves3B = moveGenerator3B.generateMoves();
		System.out.println("gm3B: " + moves3B.size());
		assertEquals(26, moves3B.size());							//Test 3 black

		Gamestate gm3W = new Gamestate(Colour.WHITE, gameSample3);
		Movegenerator moveGenerator3W = new Movegenerator(gm3W);
		ArrayList<Move> moves3W = moveGenerator3W.generateMoves();
		System.out.println("gm3W: " + moves3W.size());
		assertEquals(29, moves3W.size());							//Test 3 white

		String[][] gameSample4 =	{
				{"--","--","--","--","--","--","--","--"},
				{"--","--","--","WQ","--","--","BP","BK"},
				{"--","--","--","--","--","BP","--","BP"},
				{"--","--","--","--","--","WP","--","--"},
				{"--","--","--","--","--","WK","--","WP"},
				{"BP","--","--","--","BR","--","--","--"},
				{"BB","--","--","BT","--","--","WP","--"},
				{"--","--","--","--","--","--","--","--"}
		};

		Gamestate gm4B = new Gamestate(Colour.BLACK, gameSample4);
		Movegenerator moveGenerator4B = new Movegenerator(gm4B);
		ArrayList<Move> moves4B = moveGenerator4B.generateMoves();
		System.out.println("gm4B: " + moves4B.size());
		assertEquals(32, moves4B.size());							//Test 4 black

		Gamestate gm4W = new Gamestate(Colour.WHITE, gameSample4);
		Movegenerator moveGenerator4W = new Movegenerator(gm4W);
		ArrayList<Move> moves4W = moveGenerator4W.generateMoves();
		System.out.println("gm4W: " + moves4W.size());
		assertEquals(28, moves4W.size());							//Test 4 white

	}

}
