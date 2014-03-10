/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author xahiru
 */
@Entity
@Table(name = "ads")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ads.findAll", query = "SELECT a FROM Ads a"),
    @NamedQuery(name = "Ads.findByIdads", query = "SELECT a FROM Ads a WHERE a.idads = :idads"),
    @NamedQuery(name = "Ads.findByPhoto", query = "SELECT a FROM Ads a WHERE a.photo = :photo"),
    @NamedQuery(name = "Ads.findByVideo", query = "SELECT a FROM Ads a WHERE a.video = :video"),
    @NamedQuery(name = "Ads.findByDate", query = "SELECT a FROM Ads a WHERE a.date = :date")})
public class Ads implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idads")
    private Integer idads;
    @Size(max = 255)
    @Column(name = "photo")
    private String photo;
    @Size(max = 255)
    @Column(name = "video")
    private String video;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    public Ads() {
    }

    public Ads(Integer idads) {
        this.idads = idads;
    }

    public Integer getIdads() {
        return idads;
    }

    public void setIdads(Integer idads) {
        this.idads = idads;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idads != null ? idads.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ads)) {
            return false;
        }
        Ads other = (Ads) object;
        if ((this.idads == null && other.idads != null) || (this.idads != null && !this.idads.equals(other.idads))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Ads[ idads=" + idads + " ]";
    }
    
}
