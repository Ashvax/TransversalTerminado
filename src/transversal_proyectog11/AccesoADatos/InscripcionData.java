
package transversal_proyectog11.AccesoADatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import transversal_proyectog11.entidades.Inscripcion;

public class InscripcionData {
    
    private Connection con = null;
    
    public InscripcionData(){
        this.con = Conexion.getConexion();
    }
    
    public void guardarInscripcion(Inscripcion inscrip){
        String sql="INSERT INTO inscripcion(nota, idAlumno, idMateria) VALUES(?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, inscrip.getAlumno().getIdAlumno());
            ps.setInt(2,inscrip.getMateria().getIdMateria());
            ps.setDouble(3,inscrip.getNota());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                inscrip.setIdInscripcion(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Inscripcion Registrada");
            }
            ps.close();
            
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");             
        }
        
    }
    
    
}
