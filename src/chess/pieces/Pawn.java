package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	private ChessMatch chessMatch;
	
	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	public String toString() {
		return "P";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
		
		//Front
		p.setValues(position.getRow() - 1 * direction(color), position.getColumn());
		if(getBoard().positionExists(p) && (ChessPiece)getBoard().piece(p) == null) {
			mat[p.getRow()][p.getColumn()] = true;
			if(((ChessPiece)getBoard().piece(position)).getMoveCount() == 0) {
				p.setValues(position.getRow() - 2 * direction(color), position.getColumn());
				if(getBoard().positionExists(p) && (ChessPiece)getBoard().piece(p) == null)
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
		
		if(position.getRow() == (3.5 - direction(color) * 0.5)) {
			p.setValues(position.getRow(), position.getColumn() - 1);
			if(getBoard().positionExists(p) && isThereOpponentPiece(p) && (ChessPiece)getBoard().piece(p) == chessMatch.getEnPassantVunerable())
				mat[position.getRow() - direction(color)][position.getColumn() - 1] = true;

			p.setValues(position.getRow(), position.getColumn() + 1);
			if(getBoard().positionExists(p) && isThereOpponentPiece(p) && (ChessPiece)getBoard().piece(p) == chessMatch.getEnPassantVunerable())
				mat[position.getRow() - direction(color)][position.getColumn() + 1] = true;
		}
		
		return mat;
	}
}
