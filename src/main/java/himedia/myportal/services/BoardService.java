package himedia.myportal.services;

import java.util.List;

import himedia.myportal.repositories.vo.BoardVo;

public interface BoardService {
	public List<BoardVo> getList();
	public BoardVo getContent(Integer no);
	public boolean write(BoardVo boardVo);
	public boolean update(BoardVo boardVo);
	public boolean deleteByNoAndUserNo(Integer no, Long userNo);
}
