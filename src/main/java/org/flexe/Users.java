package org.flexe;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@Table(name = "users")
@AllArgsConstructor
@Data 
@NoArgsConstructor
public class Users  implements Serializable{

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "users_roles", //nom de la table de jointure en bd
        joinColumns = { @JoinColumn(name = "username") }, 
        inverseJoinColumns = { @JoinColumn(name = "code_role") }
    )
    private Set<Roles> roles = new HashSet<>();

	
	@Id
	@Basic(optional = false)
	@Column(name = "username")
	private String username;
	@Basic(optional = false)

	@Column(name = "password")
	private String password;

}
