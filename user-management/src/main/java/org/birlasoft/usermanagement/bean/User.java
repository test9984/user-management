package org.birlasoft.usermanagement.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(exclude = { "roles", "workspaces" })
@ToString(exclude = { "roles", "workspaces" })
@Entity
@Table(name = "thirdeye_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonIgnore
	@ColumnDefault(value = "false")
	@Column(name = "delete_status")
	private Boolean deleteStatus = Boolean.FALSE;;
	private String firstName;
	private String lastName;
	@Column(unique = true, updatable = false, nullable = false)
	private String emailAddress;
	private String password;
	private String organizationName;
	private String country;
	private String questionAnswer;
	@Column(name = "photo", length = 100000)
	private byte[] photo;
	private Boolean accountExpired = false;
	private Boolean accountLocked = false;
	private Boolean credentialExpired = false;
	private Boolean accountEnabled = false;
	private String createdBy;
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdDate;
	private String updatedBy;
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date updatedDate;
	private Boolean firstLogin = false;
	private Boolean isEditable = false;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Set<Role> roles = new HashSet<Role>();

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "user_workspace", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "workspace_id", referencedColumnName = "id"))
	private Set<WorkSpace> workspaces = new HashSet<WorkSpace>();

}
