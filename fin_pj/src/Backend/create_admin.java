package Backend;

import java.sql.Connection;

public class create_admin {
    

    public static void main(String[] args){
        Connection conn = DBConnection.getConnection();
        Queries.setConnection(conn);
        Queries.addAdmin("adminuser01", "A", "adminpwd01");
    }

}
