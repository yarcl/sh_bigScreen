package com.xqkj.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertyUtil {
    private static String url;
    private static String user;
    private static String password;
    private static String driverName;
    private static String hbase_namespace;

    private static String codeing;

    private static String oracle_url;
    private static String oracle_user;
    private static String oracle_passwd;
    private static String oracle_driver;

    static {
        Properties prop = new Properties();
        InputStream in = null;
        Properties code = new Properties();
        InputStream icode = null;
        try {
            in = ReadPropertyUtil.class
                    .getResourceAsStream("/db.properties");
            prop.load(in);
            icode = ReadPropertyUtil.class
                    .getResourceAsStream("/coded.properties");
            code.load(icode);

            url = prop.getProperty("url").trim();
            user = prop.getProperty("user").trim();
            password = prop.getProperty("password").trim();
            driverName = prop.getProperty("driverName").trim();
            hbase_namespace = prop.getProperty("hbase_namespace").trim();
            codeing = code.getProperty("byte").trim();
            //ReadPropertyUtil.getXmlDataSource();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (icode != null) {
                    icode.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private ReadPropertyUtil() {

    }

    // 通过xml获取database的配置信息
    private static void getXmlDataSource(){
        try {
            File file = new File(ReadPropertyUtil.class.getResource("/").getPath()+"/datasource.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();//步骤1
            DocumentBuilder builder = factory.newDocumentBuilder();//步骤2
            Document doc = builder.parse(file);
            NodeList nl = doc.getElementsByTagName("property");

            for(int i =0;i<nl.getLength();i++){
                Node property = nl.item(i);
                Element elem = (Element)property;
                if(elem.hasAttribute("name")&& elem.getAttribute("name").equals("driverClassName")){
                    oracle_driver = elem.getAttribute("value");
                }
                if(elem.hasAttribute("name")&& elem.getAttribute("name").equals("url")){
                    oracle_url = elem.getAttribute("value");
                }
                if(elem.hasAttribute("name")&& elem.getAttribute("name").equals("username")){
                    oracle_user = elem.getAttribute("value");
                }
                if(elem.hasAttribute("name")&& elem.getAttribute("name").equals("password")){
                    oracle_passwd = elem.getAttribute("value");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static String getUrl() {
        return url;
    }
    public static String getUser() {
        return user;
    }
    public static String getPassword() {
        return password;
    }
    public static String getDriverName() {
        return driverName;
    }
    public static String getHbase_namespace() {
        return hbase_namespace;
    }

    public static String getOracle_url(){return oracle_url;}
    public static String getOracle_user(){return oracle_user;}
    public static String getOracle_passwd(){return oracle_passwd;}
    public static String getOracle_driver(){return oracle_driver;}

    public static String getCodeing() {
        return codeing;
    }
}
