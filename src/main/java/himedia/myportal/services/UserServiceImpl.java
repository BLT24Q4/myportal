package himedia.myportal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import himedia.myportal.repositories.UserDao;
import himedia.myportal.repositories.vo.UserVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDaoImpl;
	
	@Override
	public boolean join(UserVo vo) {
		return 1 == userDaoImpl.insert(vo);
	}

	@Override
	public UserVo getUser(String email, String password) {
		return userDaoImpl.selectUser(email, password);
	}

	@Override
	public UserVo getUser(String email) {
		return userDaoImpl.selectUser(email);
	}

	@Override
	public boolean isAuthenticated(HttpServletRequest request) {
		// 세션을 통해서 사용자의 인증 상태를 체크 하는 메서드
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			UserVo authUser = 
				(UserVo)session.getAttribute("authUser");
			return authUser != null;
		}
		return false;
	}

}
