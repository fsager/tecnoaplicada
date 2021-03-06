package ar.com.tecnologiaaplicada.domain;
// Generated 15-ene-2011 15:36:58 by Hibernate Tools 3.4.0.Beta1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(name="USUARIO"
    , uniqueConstraints = @UniqueConstraint(columnNames="USR_NOMBRE") 
)
public class Usuario  implements java.io.Serializable {


     private Long usrId;
     private String usrNombre;
     private String usrClave;
     private Date usrUltimoAcceso;
     private String usrHabilitadoSn;
     private String deletedSn;

    public Usuario() {
    }

    public Usuario(String usrNombre, String usrClave, Date usrUltimoAcceso, String usrHabilitadoSn, String deletedSn) {
       this.usrNombre = usrNombre;
       this.usrClave = usrClave;
       this.usrUltimoAcceso = usrUltimoAcceso;
       this.usrHabilitadoSn = usrHabilitadoSn;
       this.deletedSn = deletedSn;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="USR_ID", unique=true, nullable=false)
    public Long getUsrId() {
        return this.usrId;
    }
    
    public void setUsrId(Long usrId) {
        this.usrId = usrId;
    }

    
    @Column(name="USR_NOMBRE", unique=true, nullable=false, length=200)
    public String getUsrNombre() {
        return this.usrNombre;
    }
    
    public void setUsrNombre(String usrNombre) {
        this.usrNombre = usrNombre;
    }

    
    @Column(name="USR_CLAVE", nullable=false, length=20)
    public String getUsrClave() {
        return this.usrClave;
    }
    
    public void setUsrClave(String usrClave) {
        this.usrClave = usrClave;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="USR_ULTIMO_ACCESO",length=19)
    public Date getUsrUltimoAcceso() {
        return this.usrUltimoAcceso;
    }
    
    public void setUsrUltimoAcceso(Date usrUltimoAcceso) {
        this.usrUltimoAcceso = usrUltimoAcceso;
    }

    
    @Column(name="USR_HABILITADO_SN", nullable=false, length=1)
    public String getUsrHabilitadoSn() {
        return this.usrHabilitadoSn;
    }
    
    public void setUsrHabilitadoSn(String usrHabilitadoSn) {
        this.usrHabilitadoSn = usrHabilitadoSn;
    }

    
    @Column(name="DELETED_SN", nullable=false, length=1)
    public String getDeletedSn() {
        return this.deletedSn;
    }
    
    public void setDeletedSn(String deletedSn) {
        this.deletedSn = deletedSn;
    }




}


