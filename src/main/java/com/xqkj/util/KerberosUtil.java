package com.xqkj.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.UserGroupInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/** 
 * @author zhouli
 * Version 1.0
 */
public class KerberosUtil {
    private KerberosUtil() {
    }

    private static Logger logger = LoggerFactory.getLogger(KerberosUtil.class);
    
    public static boolean kinit(Configuration hbaseConfig,
            String userName,String keyTabFile) {
       
        hbaseConfig.set("hadoop.security.authentication", "kerberos");
        UserGroupInformation.setConfiguration(hbaseConfig);
        try {
            UserGroupInformation.loginUserFromKeytab(userName,keyTabFile);
            logger.error("Connected to hbase with kerberos:" + userName + "," + keyTabFile);
            return true;
        } catch (IOException e) {
            logger.error("kerberos init error", e);
        }
       
        return false;
    }
    
    public static boolean kinit(Configuration conf) {
        String kerberosEnabled = conf.get("kerberos.enable");
        //Needn't kinit
        if (kerberosEnabled == null || "false".equalsIgnoreCase(kerberosEnabled)) {
            return true;
        } else {
            String name =  conf.get("kerberos.username");
            String keytab =  conf.get("kerberos.keytab");
            return kinit(conf,name,keytab);
        }        
    }
}
