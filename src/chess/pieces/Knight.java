package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece {

	public Knight(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "N";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
		
		for(int i = -2; i <= 4; i += 4) {
			for(int j = -1; j <= 1; j += 2) {
				p.setValues(position.getRow() + i, position.getColumn() + j);
				if(getBoard().positionExists(p) && (!getBoard().thereIsAPiece(p) || isThereOpponentPiece(p)))
					mat[p.getRow()][p.getColumn()] = true;
			}
			
			for(int j = -1; j <= 1; j += 2) {
				p.setValues(position.getRow() + j, position.getColumn() + i);
				if(getBoard().positionExists(p) && (!getBoard().thereIsAPiece(p) || isThereOpponentPiece(p)))
					mat[p.getRow()][p.getColumn()] = true;
			}
		}
		
		return mat;
	}
	
}
