package com.github.netty.http.bussiness;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import org.nutz.dao.entity.Record;

import com.github.netty.http.util.CardType;

public class DataCenter {
	private static long last_id = 0;
	public static final ArrayBlockingQueue<Record> pushHistory = new ArrayBlockingQueue<Record>(100);
	private static List<Record> card_list ;
	public static Record getMessage() throws Exception{
		Record msg = pushHistory.poll();
		if(msg == null){
			Thread.sleep(100);
			return getMessage();
		}
		return msg;
	}
	static {
		DBDelever db = new DBDelever();
		Thread thread = new Thread(db);
		thread.start();
	}
	public static String getMessageString() throws Exception{
		Record history = getMessage();
		if(history != null){
			StringBuffer sb = new StringBuffer();
			String push_time = history.getString("push_time");
			String card_id = history.getString("card_id");
			int push_type = history.getInt("push_type");
			int msg_type = history.getInt("card_type");
			String card_name = asCardName(msg_type);
			
			String admin_name = "系统";
			if(push_type == 0){
				admin_name = history.getString("admin_name");
			}
			
			sb.append(push_time).append(" ").append(admin_name).append("  ").append("推送").append(card_name).append(" :").append(card_id);
			return sb.toString();
		}
		return null;
	}
	private static String asCardName(int msg_type) {
		int card_type = CardType.toCardType(msg_type);
		for(Record r : card_list){
			if(card_type == r.getInt("card_type")){
				return r.getString("card_name");
			}
		}
		return null;
	}
	static class DBDelever implements Runnable{
		public void run() {
			while(true){
				if(last_id == 0){
					String sql = "select max(id) as id from kuyun_push_historys where deleted = 0 ";
					String card_msg = "select * from kuyun_cards_card_type where deleted = 0 ";
					Record record = Dao.queryOneBysql(sql);
					card_list = Dao.queryBysql(card_msg);
					last_id = record.getInt("id");
				}else{
					String sql = "select * from kuyun_push_historys where deleted = 0 and id > " + last_id + " order by id desc";
					List<Record> record = Dao.queryBysql(sql);
					if(record != null  && record.size() > 0){
						pushHistory.addAll(record);
						last_id = record.get(0).getInt("id");
					}
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
