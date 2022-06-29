package org.flexe;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;

@Entity
@Table(name = "roles")
@AllArgsConstructor
public class Roles implements Serializable{
	@JsonIdentityInfo(scope = Roles.class,generator = ObjectIdGenerators.PropertyGenerator.class, property = "codeRole")

 
	private static final long serialVersionUID = 1L;



	@ManyToMany(mappedBy = "roles")
    private Set<Users> users = new HashSet<>();
    
	

	@Valid
	@Id
	@Type(type = "pg-uuid")
	@Basic(optional = false)
	@GeneratedValue(generator = "uuid1Roles")
    @GenericGenerator(name = "uuid1Roles", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name="code_role")
	@NotNull
	private UUID codeRole;
	@Column(name = "role")
	@NotNull
	private String role;
	@Column(name="description")
	@NotNull
	private String description;

	
	public Roles(UUID codeRole) {
		this.codeRole=codeRole;
	}
	
	public Roles() {}
	public Roles(String role) {
		this.role=role;
	}
	public Roles(UUID codeRole, String role, String description) {
		this.codeRole=codeRole;
		this.description=description;
		this.role=role;
	}

	public Roles(String roles, String description) {
		this.role=roles;
		this.description=description;
	}
	public String getRole() {
		return role;
	}
	
	public UUID getCodeRole() {
		return codeRole;
	}

	public void setCodeRole(UUID codeRole) {
		this.codeRole = codeRole;
	}

	public void setRole(String role) {
		this.role = role;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public int hashCode() {
		return Objects.hash(description, role);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Roles other = (Roles) obj;
		return Objects.equals(description, other.description) && Objects.equals(role, other.role);
	}
	@Override
	public String toString() {
		return "Roles [role=" + role + ", description=" + description + "]";
	}


	
	
	
	
	
}
