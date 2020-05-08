package org.birlasoft.usermanagement.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(exclude= {"roles"})
@Entity
@Data
@Table(name = "thirdeye_permission")
public class Permission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "permission_name",nullable = false, updatable = false)
	private String permissionName;
	@Column(name = "permission_desc",nullable = false, updatable = false)
	private String permissionDesc;
	@JsonIgnore
	@ColumnDefault(value="false")
	@Column(name = "delete_status")
	private Boolean deleteStatus=Boolean.FALSE;
	@JsonIgnore
	@ManyToMany(mappedBy = "permissions")
	private Set<Role> roles = new HashSet<>();
}
