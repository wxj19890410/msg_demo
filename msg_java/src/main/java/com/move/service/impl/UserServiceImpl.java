package com.move.service.impl;

import com.move.dao.UseDataDao;
import com.move.model.UserData;
import com.move.service.UserService;
import com.move.utils.Datagrid;
import com.move.utils.QueryBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UseDataDao useDataDao;

	@Override
	@Transactional
	public List<UserData> findUsers(QueryBuilder qb) {
		return useDataDao.find(qb);
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Datagrid userDataGrid(QueryBuilder qb) {
		return useDataDao.datagrid(qb);
	}
}
