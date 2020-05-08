package org.birlasoft.usermanagement.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@Entity
@EqualsAndHashCode
@Table(name = "thireye_workspace")
public class WorkSpace {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "workspace_name" ,nullable = false, updatable = false)
	private String workspaceName;
	@Column(name = "workspace_desc",nullable = false, updatable = false)
	private String workspaceDesc;
	@JsonIgnore
	@ColumnDefault(value="false")
	@Column(name = "delete_status")
	private Boolean deleteStatus =Boolean.FALSE;
	
	private String createdBy;
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdDate;
	private String updatedBy;
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date updatedDate;
	@JsonIgnore
	@ManyToMany(mappedBy = "workspaces",fetch=FetchType.LAZY)
	private Set<User> users = new HashSet<>();

}
