package himedia.myportal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import himedia.myportal.repositories.BoardDao;
import himedia.myportal.repositories.vo.BoardVo;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardDao boardDaoImpl;
	
	@Override
	public List<BoardVo> getList() {
		return boardDaoImpl.selectAll();
	}

	@Override
	public BoardVo getContent(Integer no) {
		BoardVo vo = boardDaoImpl.getContent(no);
		return vo;
	}

	@Override
	public boolean write(BoardVo boardVo) {
		int insertedCount = boardDaoImpl.insert(boardVo);
		return 1 == insertedCount;
	}

	@Override
	public boolean update(BoardVo boardVo) {
		return 1 == boardDaoImpl.update(boardVo);
	}

	@Override
	public boolean deleteByNoAndUserNo(Integer no, Integer userNo) {
		return 1 == boardDaoImpl.delete(no, userNo);
	}

}
