package com.move.action;

import com.move.service.LoginService;
import com.move.service.UserService;
import com.move.utils.DictUtils;
import com.move.utils.QueryBuilder;
import com.move.utils.QueryUtils;
import com.move.utils.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping(value = "user")
public class UserAction {
	@Autowired
	private UserService userService;

	@Autowired
	private LoginService loginService;

	private static final Logger logger = LoggerFactory.getLogger(UserAction.class);

	@GetMapping(value = "changPassword")
	public Object changPassword(UserInfo userInfo, String oldPassword, String newPassword) throws Exception {
		return loginService.changPassword(userInfo, oldPassword, newPassword);
	}

	@GetMapping(value = "findUserList")
	public Object findUserData() {
		QueryBuilder qb = new QueryBuilder();
		QueryUtils.addColumn(qb, "t.id", "id");
		QueryUtils.addWhere(qb, "and t.delFlag = {0}", DictUtils.NO);
		return userService.findUsers(qb);
	}

}
