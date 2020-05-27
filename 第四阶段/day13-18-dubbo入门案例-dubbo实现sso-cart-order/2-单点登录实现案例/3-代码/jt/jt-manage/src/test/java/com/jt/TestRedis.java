package com.jt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.Transaction;

/**
 * 该测试类,测试Redis全部操作
 * @author Administrator
 * 项目打包时,会将测试类运行成功之后才install
 */
public class TestRedis {
	
	/**
	 * 报错:
	 * 	1.检查防火墙
	 *  2.redis-server redis.conf
	 *  3.配置文件修改问题.  3处
	 */
	@Test
	public void testString() {
		String host = "192.168.38.128";	//ip地址
		int port = 6379;				//端口
		Jedis jedis = new Jedis(host, port);
		jedis.set("1705","redis学习");
		System.out.println("获取redis数据:"+jedis.get("1705"));
	}
	
	/**
	 * 为参数设定超时时间
	 */
	@Test
	public void testString_time() {
		String host = "192.168.38.128";	//ip地址
		int port = 6379;				//端口
		Jedis jedis = new Jedis(host, port);
		//jedis.set("1705","redis学习");
		//int a = 1/0;
		//表示数据存活10秒
		//jedis.expire("1705",10);
		jedis.setex("1705", 10, "redis学习");
		System.out.println("获取redis数据:"+jedis.get("1705"));
	}
	
	/**
	 * 需求:
	 * 	如果redis中已经存在这个key.则不允许修改.
	 * 用法:当做标识   开关  boolean flag= true/false
	 */
	
	@Test
	public void testString_nx() {
		String host = "192.168.38.128";	//ip地址
		int port = 6379;				//端口
		Jedis jedis = new Jedis(host, port);
		jedis.setnx("1905", "快毕业了");
		Long flag = jedis.setnx("1905", "不久任职CTO");
		System.out.println
		("获取数据:"+jedis.get("1905")+":"+flag);
		/*
		 * String value = jedis.get("1705"); 
		 * if(value == null || value.length()==0) {
		 * jedis.set("1705", "快毕业了"); }
		 */
	}
	
	/**
	 * 不允许修改数据,同时设定超时时间
	 * 分布式锁: 同步锁 LOCK锁 数据库锁 redis锁 zookeeper锁
	 */
	@Test
	public void testString_ex_nx() {
		String host = "192.168.38.128";	//ip地址
		int port = 6379;				//端口
		Jedis jedis = new Jedis(host, port);
		jedis.set("1905","6666", "NX", "EX", 30);
		System.out.println("操作成功!!!!");
	}
	
	
	private Jedis jedis;
	
	@Before //当执行@Test方法时,先执行.
	public void init() {
		String host = "192.168.38.128";	//ip地址
		int port = 6379;				//端口
		jedis = new Jedis(host, port);
	}
	
	@Test
	public void testHash() {	
		jedis.hset("user","id","1001");
		jedis.hset("user","name","tomcat猫");
		System.out.println(jedis.hgetAll("user"));
	}
	
	
	/**
	 * 操作list类型
	 * 面试题 
	 *list: 1 2 3
	 *List: 2 3 1 2 3
	 *
	 */
	@Test
	public void testList() {
		jedis.lpush("list", "1","2","3");
		jedis.rpush("list", "4","5","6");
		System.out.println(jedis.rpop("list"));
	}
	
	/**
	 * Redis事务控制  AOP操作
	 */
	@Test
	public void testTx() {
		//1.开启事务
		Transaction transaction = jedis.multi();
		try {
			transaction.set("cc", "cc");
			//2.事务提交
			transaction.exec();
		} catch (Exception e) {
			//3.事务回滚
			transaction.discard();
		}
	}
	
	
	/**
	 * 实现redis分片测试  6379/6380/6381
	 */
	@Test
	public void testShards() {
		String host = "192.168.38.128";
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		shards.add(new JedisShardInfo(host, 6379));
		shards.add(new JedisShardInfo(host, 6380));
		shards.add(new JedisShardInfo(host, 6381));
		ShardedJedis  jedis = new ShardedJedis(shards);
		jedis.set("1905", "分片学习,为集群做准备!!!");
		//6380服务器 set
		System.out.println(jedis.get("1905"));
	}
	
	@Test
	public void testSentinel() {
		Set<String> sentinels = new HashSet<>();
		sentinels.add("192.168.38.128:26379");
		JedisSentinelPool pool = 
				new JedisSentinelPool("mymaster", sentinels);
		//获取主机的连接
		Jedis jedis = pool.getResource();
		jedis.set("1905", "哨兵搭建完成!!!!!");
		System.out.println(jedis.get("1905"));
	}
	
	
	@Test
	public void testCluster() {
		String host = "192.168.38.128";
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort(host,7000));
		nodes.add(new HostAndPort(host,7001));
		nodes.add(new HostAndPort(host,7002));
		nodes.add(new HostAndPort(host,7003));
		nodes.add(new HostAndPort(host,7004));
		nodes.add(new HostAndPort(host,7005));
		//3主3从
		JedisCluster jedisCluster = new JedisCluster(nodes);
		jedisCluster.set("1905", "你好redis集群");
		System.out.println(jedisCluster.get("1905"));
	}
	
	
	
	
}
