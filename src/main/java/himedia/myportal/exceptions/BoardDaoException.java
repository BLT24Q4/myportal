package himedia.myportal.exceptions;

import himedia.myportal.repositories.vo.BoardVo;

public class BoardDaoException extends RuntimeException {
	private BoardVo boardVo = null;

	public BoardDaoException() {
		super();
	}

	public BoardDaoException(String message) {
		super(message);
	}
	
	public BoardDaoException(String message,
			BoardVo vo) {
		super(message);
		this.boardVo = vo;
	}

	public BoardVo getBoardVo() {
		return boardVo;
	}

	public void setBoardVo(BoardVo boardVo) {
		this.boardVo = boardVo;
	}
}
