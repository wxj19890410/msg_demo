package com.move.service;

import com.move.model.UserData;
import com.move.utils.Datagrid;
import com.move.utils.QueryBuilder;

import java.util.List;

public interface UserService {

    public List<UserData> findUsers(QueryBuilder qb);
	public Datagrid userDataGrid(QueryBuilder qb);
}
