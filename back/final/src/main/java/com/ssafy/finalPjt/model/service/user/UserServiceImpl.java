package com.ssafy.finalPjt.model.service.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.finalPjt.model.dao.UserDao;
import com.ssafy.finalPjt.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean registUser(User user) {
		System.out.println(user.toString());
		return userDao.createUser(user) == 1;
	}

	@Override
	public String loginUser(String id, String pw) {

		String raw = pw;

		// sha512 μνΈν

		// -------
//		System.out.println(id);
		logger.trace("TRACE LEVEL TEST" + id);
		User found_user = userDao.findUser(id);
//		System.out.println(found_user);
		logger.debug("DEBUG LEVEL TEST" + found_user.toString());

		if (found_user == null)
			return "fail";
		else {
			String DB_pw = found_user.getPw();
			System.out.println(DB_pw.equals(pw));
			if (DB_pw.equals(pw))
				return "success";
			else
				return "wrong";
		}
	}

	@Override
	public User datailUser(String id) {
		return userDao.findUser(id);
	}

	@Override
	public void dropUser(String id) {
		System.out.println(id);
		userDao.deleteUser(id);
	}

	@Override
	public boolean modifyUser(User user) {
		User orginUser = userDao.findUser(user.getU_id());
		orginUser.setPw(user.getPw());
		orginUser.setEmail(user.getEmail());
		orginUser.setName(user.getName());
		orginUser.setPhone_no(user.getPhone_no());
		orginUser.setNickname(user.getNickname());
		orginUser.setGender(user.getGender());
		orginUser.setState_message(user.getState_message());

		return userDao.updateUser(orginUser) == 1;
	}

	@Override
	public boolean checkId(String id) {
		return (0 == userDao.chkUserID(id));
	}

	@Override
	public String checkJoin(User user) {
		if (0 != userDao.chkEmail(user.getEmail())) {
			System.out.println("μ€λ³΅ λ©μΌ");
			return "dup_email";
		}

		if (0 != userDao.chkNickname(user.getNickname())) {
			System.out.println("μ€λ³΅ λλ€μ");
			return "dup_nickname";
		}

		if (0 != userDao.chkPhoneNo(user.getPhone_no())) {
			System.out.println("μ€λ³΅ λ²νΈ");
			return "dup_phone";
		}

		return "μ±κ³΅";
	}

	@Override
	public HashMap<User, Double> findAllUser(String id, int dist) {
		HashMap<User, Double> map = new HashMap<>();
		User myUser = userDao.findUser(id);
		// μλ : y, κ²½λ : x
		double myX = Double.parseDouble(myUser.getX());
		double myY = Double.parseDouble(myUser.getY());

		List<User> userList = (ArrayList<User>) userDao.findAllUser();

		for (int i = 0; i < userList.size(); i++) {
			// κ±°λ¦¬κ³μ° ν λ§΅μ μ§μ΄λ£μ΄λ²λ¦¬κΈ°
			User user = userList.get(i);

			double userX = Double.parseDouble(user.getX());
			double userY = Double.parseDouble(user.getY());

			double theta = myX - userX;

			// k : λμ λμ κ±°λ¦¬
			double k = Math.sin(myY * Math.PI / 180.0) * Math.sin(userY * Math.PI / 180.0)
					+ Math.cos(myY * Math.PI / 180.0) * Math.cos(userY * Math.PI / 180.0)
							* Math.cos(theta * Math.PI / 180.0);
//			System.out.println(k);

			k = Math.acos(k);
//			System.out.println(k);
			k = k * 180 / Math.PI;
//			System.out.println(k);
			k = k * 60 * 1.1515 * 1.609344;
//			System.out.println(k);

			if (k <= dist) {
				map.put(user, k);
			}

		}
		return map;
	}

	@Override
	public HashMap<String, String> getNicknames() {
		HashMap<String, String> res = new HashMap<String, String>();
		List<User> sqlResult = (List<User>) userDao.getNicknames();
		for (User user : sqlResult) {
			res.put(user.getNickname(), user.getU_id());
		}
		System.out.println(res);
		return res;
	}

}
