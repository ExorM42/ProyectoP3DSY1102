package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 *
 * @author baygo
 */
public class Conexion {
    

    public Connection obtenerConexion() {


        String JDBC_URL = "jdbc:oracle:thin:@(description= (retry_count=20)(retry_delay=3)(address=(protocol=tcps)(port=1522)(host=adb.sa-valparaiso-1.oraclecloud.com))(connect_data=(service_name=g6fcc75154b8b3d_mcvtx2e1m25lm93b_high.adb.oraclecloud.com))(security=(ssl_server_dn_match=yes)))"; // (a very long string)
        
        String DB_USER = "PCARTASDSY1102";
        String DB_PASSWORD = "SyriaDSY1102";


        Connection conexion = null;
        try {
           
            System.out.println("Conectando..");
            conexion = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
            System.out.println("Conexion Exitosa");

            
          

        } catch (SQLException e) {
            System.err.println("Falló conexión");
        }
        return conexion;
    }



}
