package himedia.myportal.repositories;

import himedia.myportal.repositories.vo.UserVo;

public interface UserDao {
	public int insert(UserVo vo);
	public UserVo selectUser(String email);
	public UserVo selectUser(String email, String password);
}
