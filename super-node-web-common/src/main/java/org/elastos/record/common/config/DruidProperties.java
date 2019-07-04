package org.elastos.record.common.config;

import com.alibaba.druid.pool.DruidAbstractDataSource;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

import static com.alibaba.druid.pool.DruidAbstractDataSource.DEFAULT_WHILE_IDLE;

/**
 * @author hb.nie
 * duid数据监控
 */
@Getter
@Setter
public class DruidProperties implements Serializable {

    private static final long serialVersionUID = 1L;


    protected String name;
    protected String username;
    protected String password;
    protected String url;
    protected String driverClass;
    protected int initialSize = DruidAbstractDataSource.DEFAULT_INITIAL_SIZE;
    protected int maxActive = DruidAbstractDataSource.DEFAULT_MAX_ACTIVE_SIZE;
    protected long maxWait = DruidAbstractDataSource.DEFAULT_MAX_WAIT;
    protected String validationQuery = DruidAbstractDataSource.DEFAULT_VALIDATION_QUERY;
    private boolean testOnBorrow = DruidAbstractDataSource.DEFAULT_TEST_ON_BORROW;
    private boolean testOnReturn = DruidAbstractDataSource.DEFAULT_TEST_ON_RETURN;
    protected int notFullTimeoutRetryCount = 0;
    protected int validationQueryTimeout = -1;
    private boolean testWhileIdle = DEFAULT_WHILE_IDLE;
    protected boolean poolPreparedStatements = false;
    protected boolean sharePreparedStatements = false;
    protected int maxPoolPreparedStatementPerConnectionSize = 10;
    private boolean clearFiltersEnable = true;
    protected int queryTimeout;
    protected int transactionQueryTimeout;
    protected int maxWaitThreadCount = -1;
    protected boolean removeAbandoned;
    protected long removeAbandonedTimeoutMillis = 300 * 1000;
    protected boolean logAbandoned;
    protected int maxOpenPreparedStatements = -1;
    protected List<String> connectionInitSqls;
    protected String dbType;


}
