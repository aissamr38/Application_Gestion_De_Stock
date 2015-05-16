
package Application;
import java.sql.SQLException;
import java.sql.*;
import javax.swing.table.AbstractTableModel;

public class ResultSetTableModel extends AbstractTableModel{
    
    private ResultSet rs;
    
    public ResultSetTableModel(ResultSet rs){
        this.rs=rs;
        fireTableDataChanged();
        
    }
    
    
  // méthode getCountcolonne :
    @Override
    public int getColumnCount(){
     try{  
         if(rs == null){
           return 0;
       }
       else {
           return rs.getMetaData().getColumnCount();
       }
        
        }catch(SQLException ex){
         
         System.out.println(" getColumncount resultset generation error while getting column count");
         System.out.println(ex.getMessage());
         return 0;
     }
    }
    
  //Méthode GetRowCount:
    @Override
     public int getRowCount(){
     try{  
         if(rs == null){
           return 0;
       }
       else {
             rs.last();
           return rs.getRow();
       }
        
        }catch(SQLException ex){
         
         System.out.println(" getrowcount resultset generation error while getting rows count");
         System.out.println(ex.getMessage());
         return 0;
     }
    }
    
 // getValue
     @Override
     public Object getValueAt(int rowIndex, int columnIndex){
         if(rowIndex < 0 || rowIndex > getRowCount() || columnIndex < 0 
                 || columnIndex > getColumnCount() )
         {  return null;}
         try{
             
             if(rs == null){
                 return null;}
                 else {
                     rs.absolute(rowIndex + 1);
                     return rs.getObject(columnIndex + 1);
                     
                     }
         }catch(SQLException ex){
         
         System.out.println(" getvalue resultset generation error while fetching rows");
         System.out.println(ex.getMessage());
         return null;
     }
         }
         
//getColumn name:
     @Override
    public String getColumnName(int columnIndex){
        
        try{
            return rs.getMetaData().getColumnName(columnIndex +1);
        }catch(SQLException ex){
         
         System.out.println(" getcolumnname resultset generation error while fetching column name");
         System.out.println(ex.getMessage());
      }
        return super.getColumnName(columnIndex);
     
     }


}