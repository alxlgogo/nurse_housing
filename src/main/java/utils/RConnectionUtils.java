package utils;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public class RConnectionUtils {

    private volatile static RConnectionUtils rConnectionUtils;

    private RConnectionUtils() {
    }

    public static RConnectionUtils getRConnectionUtils() {
        if (rConnectionUtils == null) {
            synchronized (RConnectionUtils.class) {
                if (rConnectionUtils == null) {
                    rConnectionUtils = new RConnectionUtils();
                }
            }
        }
        return rConnectionUtils;
    }

    public RConnection getConnection() throws RserveException {
        RConnection connection = new RConnection();
//        ExcuteFunction(connection);
        return connection;
    }

    private void ExcuteFunction(RConnection connection) throws RserveException {
        String rPath = "/Users/lwj/testR.R";
        connection.assign("fileName", rPath);
        connection.eval("source(fileName)");
        int num = 9;
        REXP rexp = connection.eval("myFunc(" + num + ")");
        System.out.println(rexp);
        connection.close();
    }


    public static int expectancyAge(int expectancyAge, String gender) throws RserveException {
        String sql1 = "";
        if (gender.equalsIgnoreCase("male")) {
            sql1 = "SELECT (SELECT TIMESTAMPDIFF(YEAR,birthday,checkout_time)) as age,(SELECT YEAR(checkout_time)) as year1 FROM nurse_housing.user where gender='male';";
        } else {
            sql1 = "SELECT (SELECT TIMESTAMPDIFF(YEAR,birthday,checkout_time)) as age,(SELECT YEAR(checkout_time)) as year1 FROM nurse_housing.user where gender='female';";
        }

        RConnection connection = null;
        String host = "localhost";
        int port = 6311;
        String db_name = "nurse_housing";
        String db_user = "root";
        String db_pwd = "root1234";
        int year = 0;

        try {
            connection = RConnectionUtils.getRConnectionUtils().getConnection();
            connection.setStringEncoding("utf8");
            connection.eval("library('DBI')");
            connection.eval("library('RMySQL')");
            connection.eval("con <- DBI::dbConnect(RMySQL::MySQL(), dbname = '" + db_name + "', host = 'localhost', port = 3306, user = '" + db_user + "', password = '" + db_pwd + "')");
            connection.eval("base <- dbGetQuery(con,\"" + sql1 + "\")");

            connection.eval("table_age1 <- base$age");
            connection.eval("year1 <- base$year1");
            connection.eval("relation <- lm(year1~table_age1)");
            connection.eval("pre_time <- data.frame(table_age1 = " + expectancyAge + ")");
            connection.eval("result <-  predict(relation,pre_time)");
            REXP rexp = connection.eval("print(result)");
            year = rexp.asInteger();

            connection.eval("dbDisconnect(conn = con)");
            connection.close();
        } catch (RserveException | REXPMismatchException e) {
            try {
                connection.eval("dbDisconnect(conn = con)");
                connection.close();
            } catch (RserveException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return year;
    }

    public static String lifeExpectancy(String gender, String year) throws RserveException {
        String startYear = year.substring(0, year.indexOf(","));
        String endYear = year.substring(year.lastIndexOf(",") + 1, year.length());
        String sql = "";
        if ("male".equalsIgnoreCase(gender)) {
            sql = "SELECT (SELECT TIMESTAMPDIFF(YEAR,birthday,checkout_time)) as age,(SELECT YEAR(checkout_time)) as year1 FROM nurse_housing.user where gender='male';";
        } else {
            sql = "SELECT (SELECT TIMESTAMPDIFF(YEAR,birthday,checkout_time)) as age,(SELECT YEAR(checkout_time)) as year1 FROM nurse_housing.user where gender='female';";
        }
        RConnection connection = null;
        String host = "localhost";
        int port = 6311;
        String db_name = "nurse_housing";
        String db_user = "root";
        String db_pwd = "root1234";
        String fileName = "LifeExpectancy(" + gender + "" + startYear + " - " + endYear + ")";

        try {
            connection = RConnectionUtils.getRConnectionUtils().getConnection();
            connection.setStringEncoding("utf8");
            connection.eval("library('DBI')");
            connection.eval("library('RMySQL')");
            connection.eval("con <- DBI::dbConnect(RMySQL::MySQL(), dbname = '" + db_name + "', host = 'localhost', port = 3306, user = '" + db_user + "', password = '" + db_pwd + "')");
            connection.eval("base <- dbGetQuery(con,\"" + sql + "\")");
            connection.eval("print(base)");
            connection.eval("print(base$age)");
            connection.eval("print(base$year1)");
            connection.eval("table_age1 <- base$age");
            connection.eval("year1 <- base$year1");
            connection.eval("relation <- lm(table_age1~year1)");
            connection.eval("pre_time <- data.frame(year1 = c(" + year + "))");
            connection.eval("result <-  predict(relation,pre_time)");
            connection.eval("print(result)");
            connection.eval("age_Target=c(result)");
            connection.eval("year2 = c(" + year + ")");
            connection.eval("png(file=\"/Users/lwj/RScript/image/" + fileName + ".png\")");
            connection.eval("plot(year2,age_Target,\n" +
                    "     col = \"blue\",\n" +
                    "     xlab = \"Year\",\n" +
                    "     ylab = \"Age\",\n" +
                    "     main = \"Life Expectancy (" + startYear + "-" + endYear + " " + gender + ")\",\n" +
                    "     abline(lm(table_age1~year1)),\n" +
                    "     cex = 1.3,pch = 16\n" +
                    ")");
            connection.eval("dev.off()");
            connection.eval("dbDisconnect(conn = con)");
            connection.close();
        } catch (RserveException e) {
            try {
                connection.eval("dbDisconnect(conn = con)");
                connection.close();
            } catch (RserveException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return fileName + ".png";
    }


    public static String avgLife_year(String gender) throws RserveException {
        String sql = "";
        if (gender.equalsIgnoreCase("male")) {
            sql = "SELECT (SELECT TIMESTAMPDIFF(YEAR,birthday,checkout_time)) as age,(SELECT YEAR(checkout_time)) as year1 FROM nurse_housing.user where gender='male';";
        } else {
            sql = "SELECT (SELECT TIMESTAMPDIFF(YEAR,birthday,checkout_time)) as age,(SELECT YEAR(checkout_time)) as year1 FROM nurse_housing.user where gender='female';";
        }

        RConnection connection = null;
        String host = "localhost";
        int port = 6311;
        String db_name = "nurse_housing";
        String db_user = "root";
        String db_pwd = "root1234";
        String fileName = "AvgLife_Year(" + gender + ")";

        try {
            connection = RConnectionUtils.getRConnectionUtils().getConnection();
            connection.setStringEncoding("utf8");
            connection.eval("library('DBI')");
            connection.eval("library('RMySQL')");
            connection.eval("con <- DBI::dbConnect(RMySQL::MySQL(), dbname = '" + db_name + "', host = 'localhost', port = 3306, user = '" + db_user + "', password = '" + db_pwd + "')");
            connection.eval("base <- dbGetQuery(con,\"" + sql + "\")");
            connection.eval("print(base)");
            connection.eval("print(base$age)");
            connection.eval("print(base$year1)");

            connection.eval("png(file=\"/Users/lwj/RScript/image/" + fileName + ".png\")");
            connection.eval("plot(base$year1,base$age,\n" +
                    "     col = \"blue\",\n" +
                    "     xlab = \"year\",\n" +
                    "     ylab = \"age\",\n" +
                    "     main = \"avgLife vs Year (2000-2015 " + gender + ")\",\n" +
                    "     abline(lm(base$age~base$year1)),\n" +
                    "     cex = 1.3,pch = 16\n" +
                    ")");
            connection.eval("dev.off()");
            connection.eval("dbDisconnect(conn = con)");
            connection.close();
        } catch (RserveException e) {
            try {
                connection.eval("dbDisconnect(conn = con)");
                connection.close();
            } catch (RserveException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return fileName + ".png";
    }


    /**
     * Age and stay_time relation chart.
     *
     * @param fileName2
     * @throws RserveException
     */
    public static void ageStay_time(String fileName2) throws RserveException {
        RConnection connection = null;
        String host = "localhost";
        int port = 6311;
        String db_name = "nurse_housing";
        String db_user = "root";
        String db_pwd = "root1234";
        String fileName = "Age_stayTime11111";

        try {
            connection = RConnectionUtils.getRConnectionUtils().getConnection();
            connection.setStringEncoding("utf8");
            connection.eval("library('DBI')");
            connection.eval("library('RMySQL')");
            connection.eval("con <- DBI::dbConnect(RMySQL::MySQL(), dbname = '" + db_name + "', host = 'localhost', port = 3306, user = '" + db_user + "', password = '" + db_pwd + "')");
            connection.eval("base <- dbGetQuery(con,\"SELECT (SELECT TIMESTAMPDIFF(YEAR,birthday,checkout_time)) as age,(SELECT TIMESTAMPDIFF(YEAR,checkin_time,checkout_time)) as stay_time FROM nurse_housing.user where gender='male';\")");
            connection.eval("print(base)");
//            connection.eval("print(base$age)");
//            connection.eval("print(base$stay_time)");
//            connection.eval("setwd(\"/Users/lwj/RScript/image/\")");
            connection.eval("png(file=\"/Users/lwj/RScript/image/" + fileName + ".png\")");
            connection.eval("plot(base$age,base$stay_time,\n" +
                    "     col = \"blue\",\n" +
                    "     xlab = \"age\",\n" +
                    "     ylab = \"stay_time\",\n" +
                    "     main = \"age vs stay_time\",\n" +
                    "     abline(lm(base$stay_time~base$age)),\n" +
                    "     cex = 1.3,pch = 16\n" +
                    ")");
            connection.eval("dev.off()");
            connection.eval("dbDisconnect(conn = con)");
            connection.close();
        } catch (RserveException e) {
            try {
                connection.eval("dbDisconnect(conn = con)");
                connection.close();
            } catch (RserveException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }


//https://blog.csdn.net/ianly123/article/details/83025492
    /*public static void showImage() {
        String host = "localhost";
        int port = 6311;
        String db_name = "nurse_housing";
        String db_user = "root";
        String db_pwd = "root1234";
        try {
            //连接 R
            RConnection connection = new RConnection(host, port);
            connection.setStringEncoding("utf8");
            //加载包,创建连接
            connection.eval("library(RODBC)");
            connection.eval("conn <- odbcConnect(\"" + db_name + "\",uid=\"" + db_user + "\",pwd=\"" + db_pwd + "\")");
            //从数据库查询数据
            connection.eval("base <- sqlQuery(conn,\"SELECT u.*, cc.field11 as djzs, " +
                    "pc.field11 as 'jqzs' " +
                    "FROM unit u LEFT JOIN population_v2 p ON p.field2 = u.code LEFT JOIN police_forces_v2 pf ON pf.field2 = u.code " +
                    "LEFT JOIN police_case_v2 pc ON pc.field2 = u.code " +
                    "LEFT JOIN combat_crime_v2 cc ON cc.field2 = u.code where p.year = '2017' and pf.year = '2017' and pc.year = '2017' and cc.year = '2017' ORDER BY u.code\")");
            //生成线性图片的存储路径
            connection.eval("png('/Users/ianly/Desktop/test.png',width=600*3,height=3*600,res=72*3))");
            // 绘图，散列点
            connection.eval("plot(base$jqzs,base$djzs,xlab='警情',ylab='打击',main='打击-警情关系图',family='STXihei')");
            // 绘图 ，线性直线
            connection.eval("abline(lm(base$djzs~base$jqzs),col='red')");
            //本单位散点特殊标色
            connection.eval("index <- which(base$name == '虹桥所')");
            connection.eval("current_dj_point_x <- base$jqzs[index]");
            connection.eval("current_dj_point_y <- base$djzs[index]");
            connection.eval("points(current_dj_point_x, current_dj_point_y, pch=19, col='red', cex=2)");
            //关闭设备 生成图片
            connection.eval("dev.off()");
            //记得关闭连接，否则内存的资源不被释放，会引起进程冲突
            connection.close();
        } catch (RserveException e) {
            e.printStackTrace();
        }
    }*/


}
