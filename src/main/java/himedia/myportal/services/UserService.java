package himedia.myportal.services;

import himedia.myportal.repositories.vo.UserVo;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {
	public boolean join(UserVo vo);
	public UserVo getUser(String email, String password);
	public UserVo getUser(String email);
	
	public boolean isAuthenticated(HttpServletRequest request);
}
