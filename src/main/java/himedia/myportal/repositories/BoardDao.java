package himedia.myportal.repositories;

import java.util.List;

import himedia.myportal.repositories.vo.BoardVo;

public interface BoardDao {
	public List<BoardVo> selectAll();
	public int insert(BoardVo boardVo);
	public BoardVo getContent(Integer no);
	public int update(BoardVo boardVo);
	public int delete(Integer no, Integer userNo);
}
