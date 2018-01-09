package com.xqkj.util;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * Created by jiangwenjie on 2017/10/24.
 */
public class RazorBasicDataSource extends BasicDataSource {

    public RazorBasicDataSource(){
        super();
    }
    @Override
    public void setPassword(String password){
        try{
            this.password = EncryptUtils.decrypt(password);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}
