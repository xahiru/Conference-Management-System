/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author xahiru
 */
@Entity
@Table(name = "tblGroup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblGroup.findAll", query = "SELECT t FROM UsersGroup t"),
    @NamedQuery(name = "UsersGroup.findAllDistinctGroupNames", query = "SELECT DISTINCT u.groupname FROM UsersGroup u"),
    @NamedQuery(name = "TblGroup.findByGroupId", query = "SELECT t FROM UsersGroup t WHERE t = :groupId"),
    @NamedQuery(name = "TblGroup.findByGroupname", query = "SELECT t FROM UsersGroup t WHERE t.groupname = :groupname")})
public class UsersGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "groupId")
    private Integer groupId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "groupname")
    private String groupname;
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private Users username;

    public UsersGroup() {
    }

    public UsersGroup(Integer groupId) {
        this.groupId = groupId;
    }

    public UsersGroup(Integer groupId, String groupname) {
        this.groupId = groupId;
        this.groupname = groupname;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public Users getUsername() {
        return username;
    }

    public void setUsername(Users username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupId != null ? groupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersGroup)) {
            return false;
        }
        UsersGroup other = (UsersGroup) object;
        if ((this.groupId == null && other.groupId != null) || (this.groupId != null && !this.groupId.equals(other.groupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return groupname;
    }

}
