package himedia.myportal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import himedia.myportal.repositories.UserDao;
import himedia.myportal.repositories.vo.UserVo;
import jakarta.servlet.http.HttpServletRequest;
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
		// TODO Auto-generated method stub
		return false;
	}

}
