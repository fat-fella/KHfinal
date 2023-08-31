package kh.lclass.jjap.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.lclass.jjap.board.model.vo.BoardVo;

@Repository
public class BoardDao {
	@Autowired
	private SqlSession sqlSession; //root-context.xml에 bean SqlSession객체에 적혀있는 아이디
	public List<BoardVo> selectList() throws Exception{ 
		return sqlSession.selectList("board.selectList");
	}
	public BoardVo selectOne(Integer bno) throws Exception{
		return sqlSession.selectOne("board.selectOne", bno);
	}
	// insert 시 selectKey를 이용해 PK 값이 추가되어있는 자료형
	public BoardVo insert(BoardVo vo) throws Exception{
		int result = sqlSession.insert("board.insert", vo);
		return vo;
	}
	public int update(BoardVo vo) throws Exception{
		return sqlSession.update("board.update", vo);
	}
	public int delete(int bno) throws Exception{
		return sqlSession.delete("board.delete", bno);
	}
}
