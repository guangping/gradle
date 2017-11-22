package io.lance.gradle.common.dao.test;

import com.alibaba.druid.pool.DruidPooledConnection;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Lance.
 * @time: 2017-11-22 11:44
 * @desc:
 */
public class DruidTest {

    private DruidPooledConnection pooledConnection = null;

    @BeforeTest
    public void init() throws SQLException {
        System.out.println("init......");
        String url = "";
        String username = "ebs";
        String password = "ebs";
        String driverClass = "com.mysql.jdbc.Driver";

        com.alibaba.druid.pool.DruidDataSource datasource = new com.alibaba.druid.pool.DruidDataSource();
        datasource.setUrl(url);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClass);

        datasource.setInitialSize(1);
        datasource.setMinIdle(1);
        datasource.setMaxActive(5);
        datasource.setMaxWait(60000);
        datasource.setValidationQuery("select 'x'");
        datasource.setTestWhileIdle(true);
        datasource.setTestOnBorrow(false);
        datasource.setTestOnReturn(false);
        datasource.setPoolPreparedStatements(false);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(20);
        pooledConnection = datasource.getConnection();
    }

    @Test
    public void query() {
        try {//qycg
            pooledConnection.setAutoCommit(false);

            for (int i = 0; i < 30; i++) {
                this.query1();
            }
            pooledConnection.commit();

        } catch (Exception e) {
            try {
                pooledConnection.rollback();
            } catch (Exception se) {
            }
            e.printStackTrace();
        }
    }

    private void query1() throws SQLException {
        StringBuilder selectUserId = new StringBuilder();
        selectUserId.append("select sub_channel_id,site_id "
                + "from notice_channel_org WHERE is_deleted='0' "
                + "and org_code = ?"
        );
        PreparedStatement preparedStatement = pooledConnection.prepareStatement(selectUserId.toString());
        preparedStatement.setString(1, "qycg");

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getLong("site_id"));
        }

    }

    @AfterClass
    public void close() {
        try {
            pooledConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
