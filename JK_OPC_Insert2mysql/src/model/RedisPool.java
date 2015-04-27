package model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import util.LogWriter;
import vo.Redis;
import vo.RedisData;

public class RedisPool {
	private Map<String, RedisData> map = new HashMap<String, RedisData>();
	private static RedisPool redisPool = null;

	private RedisPool() {
	}

	public static RedisPool getRedisPool() {
		if (redisPool == null) {
			synchronized (RedisPool.class) {
				if (redisPool == null) {
					redisPool = new RedisPool();
				}
			}
		}
		return redisPool;
	}

	public void putIn(Redis redis) {
		if (redis == null)
			return;
		long time = redis.getTime(); // * 1000;
		// SimpleDateFormat format = new
		// SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		// String day = format.format(new Date(time));
		for (int i = 0; i < redis.getData().size(); i++) {
			RedisData data1 = new RedisData();
			data1.setTime(time);
			data1.setType(redis.getData().get(i).getType());
			data1.setValue(redis.getData().get(i).getValue());
			map.put(redis.getData().get(i).getId(), data1);
		}
	}

	public RedisData getDate(String id) {
		RedisData re = map.get(id);
		if (re == null) {
			LogWriter.writeConnectionOutTime(id);
			re = new RedisData();
			re.setValue(0);
			re.setTime(0);
		} else if (re.getTime() < (new Date().getTime() / 1000 - 600)) {
			LogWriter.writeConnectionOutTime(id);
			re = new RedisData();
			re.setValue(0);
			re.setTime(0);
		}
		if (id.equals("cdyz_ys_gfjf_gfjzt1")
				|| id.equals("cdyz_ys_gfjf_gfjzt2")
				|| id.equals("cdyz_ys_gfjf_gfjzt3")) {
			if ((Double) re.getValue() == 2) {
				re.setValue(0);
			}
		}
		return re;
	}
}
