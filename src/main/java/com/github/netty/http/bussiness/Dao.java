package com.github.netty.http.bussiness;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.netty.http.util.ResourceHelper;

@SuppressWarnings("unchecked")
public class Dao  {
	static NutDao dao = null;
	private static final String driver = ResourceHelper.get("db.cards.driver");
	private static final String url = ResourceHelper.get("db.cards.url");
	private static final String user = ResourceHelper.get("db.cards.user");
	private static final String password = ResourceHelper.get("db.cards.password");
	public static void init(){
		DruidDataSource ds = new DruidDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(password);
		ds.setMaxActive(1);//只维护一个链接
		dao = new NutDao();
		dao.setDataSource(ds);
	}
	public static List<Record> queryBysql(String sqlStr) {
		if(dao == null){
			init();
		}
		Sql sql = Sqls.create(sqlStr);
		dao.execute(sql.setCallback(new SqlCallback() {
			public List<Record> invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				List<Record> result = new ArrayList<Record>();
				while (rs.next()) {
					result.add(Record.create(rs));
				}
				return result;
			}
		}));
		return (List<Record>) sql.getResult();
	}
	public static Record queryOneBysql(String sqlStr) {
		List<Record> select = queryBysql(sqlStr);
		if (select != null && select.size() > 0) {
			return select.get(0);
		} else {
			return null;
		}
	}
}
