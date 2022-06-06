/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/*import com.sun.jdi.connect.spi.Connection;
import java.beans.Statement;*/

/**
 *
 * @author thiag
 */
public class SQL {
    
    public static final String BDUSER = "users";
    public static final String BDPASSWORD = "SI@#2022";
    public static final String BDIP = "srv2.infordoc.com";
    public static final String BDDRIVER = "com.mysql.cj.jdbc.Driver";//"org.gjt.mm.mysql.Driver";
    public static final String BDURL = "jdbc:mysql://"+BDIP+"/UAM";
    public String error = "";
    
    public boolean login(String user, String password){
    try
    {
      // create our mysql database connection
      
      
      Class.forName(BDDRIVER);
      Connection conn = DriverManager.getConnection(BDURL, BDUSER, BDPASSWORD);
      
      // our SQL SELECT query. 
      // if you only need a few columns, specify them by name instead of using "*"
      String query = "SELECT EXISTS(SELECT nome FROM Usuarios WHERE nome = '@user' and senha = '@senha') as Status".replace("@user", user.trim()).replace("@senha", password);//"if exists (select username where username = '@username' and password = '@password' from Usuarios) select 1 as Status else select 0 as Status".replace("@username", user.trim()).replace("@password",password); //"SELECT * FROM users";

      // create the java statement
      Statement st = conn.createStatement();
      
      // execute the query, and get a java resultset
      ResultSet rs = st.executeQuery(query);
      
      // iterate through the java resultset
      while (rs.next())
      {
        int id = rs.getInt("Status");
          /*String firstName = rs.getString("first_name");
          String lastName = rs.getString("last_name");
          Date dateCreated = rs.getDate("date_created");
          boolean isAdmin = rs.getBoolean("is_admin");
          int numPoints = rs.getInt("num_points");*/
          //(id == 0 ? return false : return true);
          return id != 0; // print the results
          //System.out.format("%s, %s, %s, %s, %s, %s\n", id, firstName, lastName, dateCreated, isAdmin, numPoints);
      }
      st.close();
    }
    catch (ClassNotFoundException | SQLException e)
    {
      System.err.println("Got an exception! ");
      //System.err.println(e.getMessage());
      JOptionPane.showMessageDialog(null, e.getMessage(),"Erro", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    return false;
  }
    
    public boolean isRegistered(String CPF){
    try
    {
      // create our mysql database connection
      
      Class.forName(BDDRIVER);
      Connection conn = DriverManager.getConnection(BDURL, BDUSER, BDPASSWORD);
      
      
      // our SQL SELECT query. 
      // if you only need a few columns, specify them by name instead of using "*"
      //String query = "if exists (select Nome from Dados Cliente where CPF = '@CPF' ) select 1 as Status, Nome, CPF, Telefone, Email from DadosCliente where CPF = '@CPF'else select 0 as Status".replace("@CPF", CPF); //"SELECT * FROM users";

      String query = "SELECT EXISTS(SELECT nome FROM DadosCliente WHERE CPFCNPJ = '@CPF') as Status".replace("@CPF",CPF); //.replace(".","").replace("-","")
        // execute the query, and get a java resultset
        try ( // create the java statement
                Statement st = conn.createStatement()) {
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);
            
            // iterate through the java resultset
            while (rs.next())
            {
                int id = rs.getInt("Status");
                
                /*String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                Date dateCreated = rs.getDate("date_created");
                boolean isAdmin = rs.getBoolean("is_admin");
                int numPoints = rs.getInt("num_points");*/
                //(id == 0 ? return false : return true);
                return (id == 1 ? true : false); //id != 0; // print the results
                //System.out.format("%s, %s, %s, %s, %s, %s\n", id, firstName, lastName, dateCreated, isAdmin, numPoints);
            } }
    }
    catch (ClassNotFoundException | SQLException e)
    {
      System.err.println("Got an exception! ");
      //System.err.println(e.getMessage());
      JOptionPane.showMessageDialog(null, e.getMessage(),"Erro", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    return false;
  }
    
    
    
    
    public boolean raw(String query){
    try
    {
      // create a mysql database connection
      String myDriver = BDDRIVER;
      String myUrl = BDURL;
      Class.forName(myDriver);
      Connection conn = DriverManager.getConnection(myUrl, BDUSER, BDPASSWORD);
    
      // create a sql date object so we can use it in our INSERT statement
      
      

      // the mysql insert statement
      /*String query = " insert into users (first_name, last_name, date_created, is_admin, num_points)"
        + " values (?, ?, ?, ?, ?)";*/

      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      /*preparedStmt.setString (1, "Barney");
      preparedStmt.setString (2, "Rubble");
      preparedStmt.setDate   (3, startDate);
      preparedStmt.setBoolean(4, false);
      preparedStmt.setInt    (5, 5000);*/

      // execute the preparedstatement
      preparedStmt.execute();
      
      conn.close();
      return true;
    }
    catch (Exception e)
    {
      System.err.println("Got an exception!");
      System.err.println(e.getMessage());
      error = e.getMessage();
      return false;
    }
  }
    
    
    public List<OcorrenciasOS> getOcorrencias(int idOS){
    List<OcorrenciasOS> ocorrencias = new ArrayList<>();
    try
    {
      // create our mysql database connection
      Class.forName(BDDRIVER);
      Connection conn = DriverManager.getConnection(BDURL, BDUSER, BDPASSWORD);
      
      
      
      // our SQL SELECT query. 
      // if you only need a few columns, specify them by name instead of using "*"
      String query = "select * from OcorrenciasOS where idOs = @OS order by idOcorrenciasOS asc".replace("@OS", String.valueOf(idOS));
        // execute the query, and get a java resultset
        try ( // create the java statement
                Statement st = conn.createStatement()) {
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);
            
            // iterate through the java resultset
            while (rs.next())
            {
                //int id = rs.getInt("Status");
                OcorrenciasOS ocorrencia = new OcorrenciasOS();
                ocorrencia.idOcorrencia = rs.getInt("idOcorrenciasOS");
                ocorrencia.idOS = idOS;
                ocorrencia.idStatus = rs.getInt("idStatus");
                ocorrencia.descricaoOcorrencia = rs.getString("descricaoOcorrencia");
                
                /*String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                Date dateCreated = rs.getDate("date_created");
                boolean isAdmin = rs.getBoolean("is_admin");
                int numPoints = rs.getInt("num_points");*/
                //(id == 0 ? return false : return true);
                /*if(id == 0)
                {
                return false;
                }
                else
                {
                return true;
                }*/
                // print the results
                //System.out.format("%s, %s, %s, %s, %s, %s\n", id, firstName, lastName, dateCreated, isAdmin, numPoints);
                ocorrencias.add(ocorrencia);
            } 
        }
      return ocorrencias;
    }
    catch (ClassNotFoundException | SQLException e)
    {
      System.err.println("Got an exception! ");
      //System.err.println(e.getMessage());
      JOptionPane.showMessageDialog(null, e.getMessage(),"Erro", JOptionPane.ERROR_MESSAGE);
      return null;
    }
    
    }
    
    public int getLastOS(){
    int lastOS = 0;
    try
    {
      // create our mysql database connection
      Class.forName(BDDRIVER);
      Connection conn = DriverManager.getConnection(BDURL, BDUSER, BDPASSWORD);
      
      
      
      // our SQL SELECT query. 
      // if you only need a few columns, specify them by name instead of using "*"
      String query = "select MAX(idOS) as lastID from DadosOS;";
        // execute the query, and get a java resultset
        try ( // create the java statement
                Statement st = conn.createStatement()) {
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);
            
            // iterate through the java resultset
            while (rs.next())
            {
                //int id = rs.getInt("Status");
                lastOS = rs.getInt("lastID");
                
                
                /*String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                Date dateCreated = rs.getDate("date_created");
                boolean isAdmin = rs.getBoolean("is_admin");
                int numPoints = rs.getInt("num_points");*/
                //(id == 0 ? return false : return true);
                /*if(id == 0)
                {
                return false;
                }
                else
                {
                return true;
                }*/
                // print the results
                //System.out.format("%s, %s, %s, %s, %s, %s\n", id, firstName, lastName, dateCreated, isAdmin, numPoints);
                //status.add(sts);
            } 
        }
      return lastOS;
    }
    catch (ClassNotFoundException | SQLException e)
    {
      System.err.println("Got an exception! ");
      //System.err.println(e.getMessage());
      JOptionPane.showMessageDialog(null, e.getMessage(),"Erro", JOptionPane.ERROR_MESSAGE);
      return 0;
    }
    
    }
    
    
    public List<Status> getStatus(){
    List<Status> status = new ArrayList<>();
    try
    {
      // create our mysql database connection
      Class.forName(BDDRIVER);
      Connection conn = DriverManager.getConnection(BDURL, BDUSER, BDPASSWORD);
      
      
      
      // our SQL SELECT query. 
      // if you only need a few columns, specify them by name instead of using "*"
      String query = "select * from DadosStatus;";
        // execute the query, and get a java resultset
        try ( // create the java statement
                Statement st = conn.createStatement()) {
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);
            
            // iterate through the java resultset
            while (rs.next())
            {
                //int id = rs.getInt("Status");
                Status sts = new Status();
                sts.idStatus = rs.getInt("idStatus");
                sts.descStatus = rs.getString("nome");
                
                
                /*String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                Date dateCreated = rs.getDate("date_created");
                boolean isAdmin = rs.getBoolean("is_admin");
                int numPoints = rs.getInt("num_points");*/
                //(id == 0 ? return false : return true);
                /*if(id == 0)
                {
                return false;
                }
                else
                {
                return true;
                }*/
                // print the results
                //System.out.format("%s, %s, %s, %s, %s, %s\n", id, firstName, lastName, dateCreated, isAdmin, numPoints);
                status.add(sts);
            } 
        }
      return status;
    }
    catch (ClassNotFoundException | SQLException e)
    {
      System.err.println("Got an exception! ");
      //System.err.println(e.getMessage());
      JOptionPane.showMessageDialog(null, e.getMessage(),"Erro", JOptionPane.ERROR_MESSAGE);
      return null;
    }
    
    }
    
    public List<OS> getOSs(String CPF){
    List<OS> OS = new ArrayList<>();
    try
    {
      // create our mysql database connection
      Class.forName(BDDRIVER);
      Connection conn = DriverManager.getConnection(BDURL, BDUSER, BDPASSWORD);
      
      
      
      // our SQL SELECT query. 
      // if you only need a few columns, specify them by name instead of using "*"
      String query = "select * from DadosOS where CPFCNPJ = '@CPFCNPJ'".replace("@CPFCNPJ", CPF);
        // execute the query, and get a java resultset
        try ( // create the java statement
                Statement st = conn.createStatement()) {
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);
            
            // iterate through the java resultset
            while (rs.next())
            {
                //int id = rs.getInt("Status");
                OS os = new OS();
                os.osN = rs.getInt("idOS");
                os.data = rs.getDate("dataCadastro");
                os.descricaoProblema = rs.getString("descricaoProblema");
                os.descricaoProduto = rs.getString("descricaoProduto");
                os.nSerie = rs.getString("nSerie");
                os.tipo = rs.getString("tipo");
                
                /*String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                Date dateCreated = rs.getDate("date_created");
                boolean isAdmin = rs.getBoolean("is_admin");
                int numPoints = rs.getInt("num_points");*/
                //(id == 0 ? return false : return true);
                /*if(id == 0)
                {
                return false;
                }
                else
                {
                return true;
                }*/
                // print the results
                //System.out.format("%s, %s, %s, %s, %s, %s\n", id, firstName, lastName, dateCreated, isAdmin, numPoints);
                OS.add(os);
            } 
        }
      return OS;
    }
    catch (ClassNotFoundException | SQLException e)
    {
      System.err.println("Got an exception! ");
      //System.err.println(e.getMessage());
      JOptionPane.showMessageDialog(null, e.getMessage(),"Erro", JOptionPane.ERROR_MESSAGE);
      return null;
    }
    
    }
    
    protected Cliente getClienteData(String CPF){
    try
    {
      // create our mysql database connection
      Class.forName(BDDRIVER);
      Connection conn = DriverManager.getConnection(BDURL, BDUSER, BDPASSWORD);
      Cliente cliente = new Cliente();
      
      
      // our SQL SELECT query. 
      // if you only need a few columns, specify them by name instead of using "*"
      String query = "select Nome, CPFCNPJ, Telefone, Email from DadosCliente where CPFCNPJ = '@CPF'".replace("@CPF", CPF); //"SELECT * FROM users";
        // execute the query, and get a java resultset
        try ( // create the java statement
                Statement st = conn.createStatement()) {
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);
            
            // iterate through the java resultset
            while (rs.next())
            {
                //int id = rs.getInt("Status");
                
                cliente.CPFCNPJ = rs.getString("CPFCNPJ");
                cliente.Email = rs.getString("Email");
                cliente.Telefone = rs.getString("Telefone");
                cliente.Nome = rs.getString("Nome");
                
                /*String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                Date dateCreated = rs.getDate("date_created");
                boolean isAdmin = rs.getBoolean("is_admin");
                int numPoints = rs.getInt("num_points");*/
                //(id == 0 ? return false : return true);
                /*if(id == 0)
                {
                return false;
                }
                else
                {
                return true;
                }*/
                // print the results
                //System.out.format("%s, %s, %s, %s, %s, %s\n", id, firstName, lastName, dateCreated, isAdmin, numPoints);
            } }
      return cliente;
    }
    catch (ClassNotFoundException | SQLException e)
    {
      System.err.println("Got an exception! ");
      //System.err.println(e.getMessage());
      JOptionPane.showMessageDialog(null, e.getMessage(),"Erro", JOptionPane.ERROR_MESSAGE);
      return null;
    }
    
  }
    
    
    protected OS getOSData(int OS){
    try
    {
      // create our mysql database connection
      Class.forName(BDDRIVER);
      Connection conn = DriverManager.getConnection(BDURL, BDUSER, BDPASSWORD);
      OS os = new OS();
      
      
      // our SQL SELECT query. 
      // if you only need a few columns, specify them by name instead of using "*"
      String query = "select *  from DadosOS where idOS = @OS".replace("@OS", String.valueOf(OS)); //"SELECT * FROM users";
        // execute the query, and get a java resultset
        try ( // create the java statement
                Statement st = conn.createStatement()) {
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);
            
            // iterate through the java resultset
            while (rs.next())
            {
                //int id = rs.getInt("Status");
                
                
                
                
                os.data = rs.getDate("dataCadastro");
                os.descricaoProblema = rs.getString("descricaoProblema");
                os.descricaoProduto = rs.getString("descricaoProduto");
                os.nSerie = rs.getString("nSerie");
                os.tipo = rs.getString(("tipo"));
                os.osN = OS;
                /*String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                Date dateCreated = rs.getDate("date_created");
                boolean isAdmin = rs.getBoolean("is_admin");
                int numPoints = rs.getInt("num_points");*/
                //(id == 0 ? return false : return true);
                /*if(id == 0)
                {
                return false;
                }
                else
                {
                return true;
                }*/
                // print the results
                //System.out.format("%s, %s, %s, %s, %s, %s\n", id, firstName, lastName, dateCreated, isAdmin, numPoints);
            } }
      return os;
    }
    catch (ClassNotFoundException | SQLException e)
    {
      System.err.println("Got an exception! ");
      //System.err.println(e.getMessage());
      JOptionPane.showMessageDialog(null, e.getMessage(),"Erro", JOptionPane.ERROR_MESSAGE);
      return null;
    }
    
  }
    
    }
    

