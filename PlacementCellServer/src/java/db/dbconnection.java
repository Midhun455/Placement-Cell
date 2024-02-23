
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class dbconnection {

    private ResultSetMetaData rsm = null;
    private Connection con = null;
    private Vector rowData = null;
    private Statement st = null;
    private ResultSet rs = null;

    public dbconnection() throws SQLException {
        try {
            if (con == null) {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/placementcell", "root", "");
//                con = DriverManager.getConnection("localhost","lcc1001","lcc1001","location_tracer");
                st = con.createStatement();
            }
        } catch (ClassNotFoundException | SQLException er) {
            System.out.println("db Access failed.." + er);
        }
    }

    public int putData(String qry) {
        try {
            return st.executeUpdate(qry);
        } catch (SQLException e) {
            System.out.println("Access failed.." + e);
        }
        return 0;
    }

    public Vector getData(String qry) {
        try {
            rowData = new Vector();
            rs = st.executeQuery(qry);
            rsm = rs.getMetaData();
            int colCount = rsm.getColumnCount();
            while (rs.next()) {
                Vector colVector = new Vector();
                for (int i = 0; i < colCount; i++) {
                    colVector.add(rs.getString((i + 1)));
                }
                rowData.add(colVector);
            }
            return rowData;
        } catch (SQLException e) {
            System.out.println("Access failed.." + e);
        }
        return rowData;
    }
}