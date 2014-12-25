package com.simbirsoft.utils.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;




public class DBConnectionPool {
	private JDBCParams params;
	private BlockingQueue<Connection> pool;
	//private Logger logger = Logger.getLogger(DBConnectionPool.class);
	private final static String dbconfigFile = "WEB-INF\\classes\\com\\simbirsoft\\db.properties";
	private final int poolSize = 100;
	private final int  timeout= 500;
	private AtomicInteger poolCount;
	private static DBConnectionPool instance = null;
	

	private DBConnectionPool() {
		init();
	}

	public static DBConnectionPool getInstance() {
		if (instance == null) {
			instance = new DBConnectionPool();
		}
		return instance;
	}

	private void init() {
		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	    String deploymentDirectoryPath = ctx.getRealPath("/");
		JDBCConfig config = JDBCConfig.load(deploymentDirectoryPath+dbconfigFile);
		if (config != null) {
			params = config.getJDBCParams();
		}
		poolCount = new AtomicInteger();
		pool = new LinkedBlockingQueue<Connection>();
	}

	
	public Connection getConnection() {
		return getConnection(timeout);
	}
	
	public Connection getConnection(int timeout) {
		Connection conn;
		try {
			conn = pool.poll(timeout, TimeUnit.MILLISECONDS);
			if (conn == null || conn.isClosed()) {
				synchronized (poolCount) {
					if (poolCount.get() < poolSize) {
						Class.forName(params.getDbDriver());
						conn = DriverManager.getConnection(params.getDbUrl(), params.getUsername(), params.getPassword());
						pool.offer(conn);
						poolCount.incrementAndGet();
					}
				}
			}
			return conn;
		} catch (InterruptedException e) {
			//logger.error("get connection failed: ", e);
		} catch (ClassNotFoundException e) {
			//logger.error("get connection failed: ", e);
		} catch (SQLException e) {
			//logger.error("get connection failed: ", e);
		}
		return null;
	}
	
	

	public void release(Connection conn) {
		try {
			pool.add(conn);
		} catch (Exception e) {
			//logger.error("release connection failed: ", e);
			try {
				conn.close();
			} catch (SQLException e2) {
				//logger.error("close connection failed: ", e2);
			}
		}
	}

	public void closePool() {
		for (int i = 0; i < pool.size(); i++) {
			try {
				pool.poll().close();
			} catch (SQLException e) {
				//logger.error("close connection failed: ", e);
			}
		}
	}

	@Override
	protected void finalize() throws Throwable {
		closePool();
		super.finalize();
	}
}


