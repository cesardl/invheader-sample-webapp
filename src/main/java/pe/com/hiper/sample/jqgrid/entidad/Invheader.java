/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.hiper.sample.jqgrid.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author pdiaz
 */
@Entity
@Table(name = "invheader")
@NamedQueries({
    @NamedQuery(name = "Invheader.findAll", query = "SELECT i FROM Invheader i ORDER BY i.invid"),
    @NamedQuery(name = "Invheader.findByInvid", query = "SELECT i FROM Invheader i WHERE i.invid = :invid"),
    @NamedQuery(name = "Invheader.findByInvdate", query = "SELECT i FROM Invheader i WHERE i.invdate = :invdate"),
    @NamedQuery(name = "Invheader.findByClientId", query = "SELECT i FROM Invheader i WHERE i.clientId = :clientId"),
    @NamedQuery(name = "Invheader.findByAmount", query = "SELECT i FROM Invheader i WHERE i.amount = :amount"),
    @NamedQuery(name = "Invheader.findByTax", query = "SELECT i FROM Invheader i WHERE i.tax = :tax"),
    @NamedQuery(name = "Invheader.findByTotal", query = "SELECT i FROM Invheader i WHERE i.total = :total"),
    @NamedQuery(name = "Invheader.findByNote", query = "SELECT i FROM Invheader i WHERE i.note = :note")})
public class Invheader implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "invid")
    private Integer invid;
    @Basic(optional = false)
    @Column(name = "invdate")
    @Temporal(TemporalType.DATE)
    private Date invdate;
    @Basic(optional = false)
    @Column(name = "client_id")
    private int clientId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "amount")
    private BigDecimal amount;
    @Basic(optional = false)
    @Column(name = "tax")
    private BigDecimal tax;
    @Basic(optional = false)
    @Column(name = "total")
    private BigDecimal total;
    @Column(name = "note")
    private String note;
    
    public Invheader() {
    }
    
    public Invheader(Integer invid) {
        this.invid = invid;
    }
    
    public Invheader(Integer invid, Date invdate, int clientId, BigDecimal amount, BigDecimal tax, BigDecimal total) {
        this.invid = invid;
        this.invdate = invdate;
        this.clientId = clientId;
        this.amount = amount;
        this.tax = tax;
        this.total = total;
    }
    
    public Integer getInvid() {
        return invid;
    }
    
    public void setInvid(Integer invid) {
        this.invid = invid;
    }
    
    public Date getInvdate() {
        return invdate;
    }
    
    public void setInvdate(Date invdate) {
        this.invdate = invdate;
    }
    
    public int getClientId() {
        return clientId;
    }
    
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    public BigDecimal getTax() {
        return tax;
    }
    
    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }
    
    public BigDecimal getTotal() {
        return total;
    }
    
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
    public String getNote() {
        return note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invid != null ? invid.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Invheader)) {
            return false;
        }
        Invheader other = (Invheader) object;
        if ((this.invid == null && other.invid != null) || (this.invid != null && !this.invid.equals(other.invid))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "pe.com.hiper.sample.jqgrid.entidad.Invheader[ invid=" + invid + " ]";
    }
}
