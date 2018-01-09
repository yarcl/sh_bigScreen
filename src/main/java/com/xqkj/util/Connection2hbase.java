package com.xqkj.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.*;

/**
 * 连接hbase 获得Htable
 * 
 * @author duzhou.xu
 * 
 */
public class Connection2hbase {
    static Configuration HBASE_CONFIG = null;
    static  String byteCode = ReadPropertyUtil.getCodeing();
    static {
        HBASE_CONFIG = HBaseConfiguration.create();
        HBASE_CONFIG.addResource("hbase-site.xml");

        KerberosUtil.kinit(HBASE_CONFIG);
    }

    public static HTable getHTable(String tablename) {
        HTable table = null;
        try {
            table = new HTable(HBASE_CONFIG, tablename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return table;
    }

    public static boolean createTable(String tablename) {
        HBaseAdmin admin = null;
        try {
            admin = new HBaseAdmin(HBASE_CONFIG);
            if (admin.tableExists(tablename)) {
                return true;
            } else {
                HTableDescriptor tableDesc = new HTableDescriptor(tablename);
                tableDesc.addFamily(new HColumnDescriptor("f".getBytes(byteCode)));
                admin.createTable(tableDesc);
                admin.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                admin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //for test
    public static void main(String args[]){

        String productid = "10032520";
        String startdate = "20171225";
        String namespace = ReadPropertyUtil.getHbase_namespace();
        String tablename = namespace+":"+"Sum_Statistic";
        String byteCode = ReadPropertyUtil.getCodeing();
        HTable table =  Connection2hbase.getHTable(tablename);

        JSONArray array = new JSONArray();
        Scan scan = new Scan();
        scan.setCaching(Constants.SCANCACHING);
        scan.setCacheBlocks(Constants.CACHEBLOCKS);

        //标识
        String tableid = "ic";
        String keyRow=productid+"-"+startdate+"-"+tableid;
        String keyRowUser=productid+"-"+startdate+"-id";

        Result rs;
        Result rsUser;
        try {
            Get get = new Get(keyRow.getBytes(byteCode));
            Get getUser = new Get(keyRowUser.getBytes(byteCode));
            rs = table.get(get);
            rsUser =table.get(getUser);
            Map<String,Long> map = new HashMap<String,Long>();
            for(Cell kv : rs.rawCells()){
                String qualifier=new String(CellUtil.cloneQualifier(kv),byteCode);
                long value = Bytes.toLong(CellUtil.cloneValue(kv));
                map.put(qualifier, value);
            }

            Map<String,Long> mapUser = new HashMap<String,Long>();
            for(Cell kv : rsUser.rawCells()){
                String qualifier=new String(CellUtil.cloneQualifier(kv),byteCode);
                long value = Bytes.toLong(CellUtil.cloneValue(kv));
                mapUser.put(qualifier, value);
            }

            List<String> sortList = new ArrayList<String>();
            Set<String> set = map.keySet();
            Iterator<String> it = set.iterator();
            while(it.hasNext()){
                String qu = it.next();
                Long value = map.get(qu);
                if(sortList.size()==0){
                    sortList.add(qu);
                }else{
                    int index=0;
                    for (int i = 0; i < sortList.size(); i++) {
                        String currqu = sortList.get(i);
                        long currvalue = map.get(currqu);
                        if(value<currvalue){
                            index+=1;
                        }
                    }
                    sortList.add(index,qu);
                }
            }


            List<String> sortListUser = new ArrayList<String>();
            Set<String> setUser = mapUser.keySet();
            Iterator<String> itUser = setUser.iterator();
            while(itUser.hasNext()){
                String qu = itUser.next();
                Long value = mapUser.get(qu);
                if(sortListUser.size()==0){
                    sortListUser.add(qu);
                }else{
                    int index=0;
                    for (int i = 0; i < sortListUser.size(); i++) {
                        String currqu = sortListUser.get(i);
                        long currvalue = mapUser.get(currqu);
                        if(value<currvalue){
                            index+=1;
                        }
                    }
                    sortListUser.add(index,qu);
                }
            }

            JSONArray countArray = new JSONArray();
            for (int i = 0; i < sortList.size(); i++) {
                JSONObject object = new JSONObject();
                String key = sortList.get(i);
                long value = map.get(key);
                object.put(key, value);
                countArray.add(object);
            }

            JSONArray userArray = new JSONArray();
            for (int i = 0; i < sortListUser.size(); i++) {
                JSONObject object = new JSONObject();
                String key = sortListUser.get(i);
                long value = mapUser.get(key);
                object.put(key, value);
                userArray.add(object);
            }
            JSONObject jobj1 = new JSONObject();
            jobj1.put("countlist", countArray);
            JSONObject jobj2 = new JSONObject();
            jobj2.put("userlist", userArray);
            array.add(jobj1);
            array.add(jobj2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
