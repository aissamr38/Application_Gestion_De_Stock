
package Application;
import java.net.Socket;
import java.sql.*;




public class BDD {
    // Les déclaration 
    Connection connection;
    Statement statement;
    String url;
    String username; 
    String password;
    Socket client;
    int port;
    String Host;
    String SQL;
    // Constructeur :
    public BDD(String url,String username, String password, String Host, int port) {
        this.url=url;
        this.username = username;
        this.password = password;
        this.Host = Host;
        this.port = port;
    }
    
// Methode pour faire  connection Au B.D :   il Obligatoire
    public Connection connexionDatabase(){
        
        try{
        
        Class.forName("com.mysql.jdbc.Driver");
        connection=DriverManager.getConnection(url,username, password);
        
        
        
        
        }catch(Exception e){
            
            System.err.println(e.getMessage()); // e.getMessage() : pour afficher ou se trouve le proléme exactement 
                 // err affiche le erreur err
        }
        
        return connection;
        
    }// fin de la méthodes
    
    
    //fonction pour fermer la base de donnée :
    
    
    public Connection closeconnection(){
        
       try{
           connection.close();
       }catch(Exception e){
          System.err.println(e);}
       return connection;
           
    }
        
        
// methode pour exécuter les requet de la base de donnée
    
    public ResultSet executionQuery(String sql){
    connexionDatabase();
    ResultSet resultSet = null;
    try{
    
     statement =connection.createStatement();
     resultSet =statement.executeQuery(sql);
    }catch(SQLException ex){
        System.err.println(ex);
        
    }
        
    return resultSet;     
    
    } // Fin de la méthode 
    
    // Méthode pour exécuter les requete update
    
    public String executionUpdate(String sql){
        connexionDatabase();
        String result = "";
        try{
            statement =connection.createStatement();
            statement.executeUpdate(sql);
            result=sql;
        }catch(SQLException ex){
            result=ex.toString();
        }
        
        return result;
       } //fin de  la méthode.
    
    // Méthode pour afficher les table de notre  base de donnée/
    public ResultSet querySelectAll(String nomTable){
        connexionDatabase();
         SQL =  " SELECT * FROM " +nomTable;
        System.out.println(SQL);
        return this.executionQuery(SQL);
    }
    
    
    //Méthode pour afficher tous  avec de parametre " etat" :
    public ResultSet querySelectAll(String nomTable,String etat){
        connexionDatabase();
        SQL = " SELECT * FROM " +nomTable+ " WHERE " +etat;
        return this.executionQuery(SQL);
        
    }
    
  
    // 
    public ResultSet querySelect(String[] nomColonne,String nomTable){
        connexionDatabase(); 
        int i;
         SQL = " SELECT ";
         for(i=0;i<=nomColonne.length -1;i++){
             
             SQL += nomColonne[i];
             if(i<nomColonne.length-1){
                 SQL +=" , ";
             }
         }
        
        SQL +=" FROM " +nomTable;
        
        return this.executionQuery(SQL);
        }
    
    //methode de selection les colonnes avec condition :
    public ResultSet fcSelectCommand(String[] nomColonne,String nomTable,String etat){
        connexionDatabase(); 
         int i;
         SQL =" SELECT ";
         for(i=0;i<=nomColonne.length -1;i++){
             
             SQL += nomColonne[i];
             if(i<nomColonne.length-1){
                 SQL += " , ";
             }
         }
        
        SQL += " FROM " +nomTable+ " WHERE " +etat;
        
        return this.executionQuery(SQL);
        }
    
    
    //Méthode d'insertion de  données :
    public String queryInsert(String nomTable,String[] contenuTableau){
        connexionDatabase();
        int i;
        SQL = " INSERT INTO " +nomTable+ " VALUES( " ;
        for(i=0;i<=contenuTableau.length-1;i++){
            SQL += "'" + contenuTableau[i] + "'";
            if(i<contenuTableau.length-1){
                SQL += ",";
            }
        }
        
        SQL += ")"; 
        
        return this.executionUpdate(SQL);
    }
    
    //methode d'insertion dans toutes les  tables 
    public String queryInsert(String nomTable,String[] nomColonne,String[] contenuTable ){
        connexionDatabase();
        int i;
        SQL = " INSERT INTO " +nomTable+  "(";
        for(i=0;i<=nomColonne.length -1;i++){
            SQL += "'" + nomColonne[i] + "'";
            if(i<nomColonne.length -1){
                SQL += ",";
            }
        }
        SQL += ") VALUES( " ;
        for(i=0;i<contenuTable.length ;i++){
            SQL += "'" + contenuTable[i] + "'";
            if(i < contenuTable.length-1){
                SQL += ",";
            }
        }
        SQL += ")";
     return this.executionUpdate(SQL);   
    }
    
    // inserer les donnee on utilisant UPDATE
    public String queryUpdate(String nomTable,String[] nomColonne,String[] contenuTable,String etat ){
        connexionDatabase();
        int i;
        SQL = "UPDATE " + nomTable + " SET ";
        for(i=0;i<=nomColonne.length-1;i++){
        SQL += nomColonne[i] + "='" + contenuTable[i] + "'";
        if(i<nomColonne.length-1){
        SQL += " ,";}
        }
        SQL += " WHERE " +etat;
    
    return this.executionUpdate(SQL);
    }
    
    // Méthode pour la requete suprimer " delete " sans  paramétre:
    public String queryDelet(String nomTable){
        
        connexionDatabase();
        SQL = "DELETE FROM" + nomTable;
        return this.executionUpdate(SQL);
    }
    
    
    // Méthode pour la requete suprimer " delete " Avec  paramétre:
    public String queryDelet(String nomTable,String etat){
        
        connexionDatabase();
        SQL = "DELETE FROM" + nomTable + " WHERE " +etat;
        return this.executionUpdate(SQL);
    }
   

}
