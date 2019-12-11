package access;

import dao.UserSearchKeywordDao;
import mapping.Converter;
import models.UserSearchKeyword;
import org.json.JSONArray;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.Date;

public class UserSearchKeywordAccess {

	private UserSearchKeywordDao dao;
	private Converter converter;

	@Inject
	public UserSearchKeywordAccess(UserSearchKeywordDao dao, Converter converter) {
		this.dao = dao;
		this.converter = converter;
	}

	public void insertUserSearchKeyword(String user_id, String keyword) throws SQLException {
		UserSearchKeyword userSearchKeyword = new UserSearchKeyword(user_id, keyword, new Date().getTime());
		if(!checkExist(userSearchKeyword)){
			userSearchKeyword.insert();
		}else {
			dao.updateTimeCreate(user_id, keyword, new Date().getTime());
		}
	}

	public Boolean checkExist(UserSearchKeyword userSearchKeyword) throws SQLException {
		JSONArray jsonArrayUserId = dao.getByUserKeyword(userSearchKeyword.getUser_id(), userSearchKeyword.getKeyword());
		return jsonArrayUserId.length() > 0;
	}
}
