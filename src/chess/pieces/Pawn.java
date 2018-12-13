package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	public Pawn(Board board, Color color) {
		super(board, color);
	}

	public String toString() {
		return "P";
	}

	public boolean canMove(Position p) {
		ChessPiece piece = (ChessPiece)getBoard().piece(position);
		return piece != null || isThereOpponentPiece(position);
	}
	
	public int direction(Color color) {
		return color == Color.WHITE ? 1 : -1;
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
		
		//Front
		p.setValues(position.getRow() - 1 * direction(color), position.getColumn());
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			if(((ChessPiece)getBoard().piece(position)).getMoveCount() == 0) {
				p.setValues(position.getRow() - 2 * direction(color), position.getColumn());
				if(getBoard().positionExists(p) && canMove(p))
					mat[p.getRow()][p.getColumn()] = true;
			}
		}
		
		//Left
		p.setValues(position.getRow() - 1 * direction(color), position.getColumn() - 1);
		if(getBoard().positionExists(p) && isThereOpponentPiece(p))
			mat[p.getRow()][p.getColumn()] = true;
		
		//Right
		p.setValues(position.getRow() - 1 * direction(color), position.getColumn() + 1);
		if(getBoard().positionExists(p) && isThereOpponentPiece(p))
			mat[p.getRow()][p.getColumn()] = true;
		
		return mat;
	}
}
