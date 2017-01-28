package com.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Stream;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.model.enuns.Authorities;
import com.model.enuns.Status;
import com.util.StaticDB;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = { "id" })
@ToString(callSuper = false, of = { "id", "name", "username", "status", "description" })
@Entity
@Table(name = StaticDB.TABLE_USER, uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
public class User extends EntityJpa implements UserDetails {

	private static final long serialVersionUID = 442738873666572571L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@NotNull(message = "error.empty.name")
	@NotEmpty(message = "error.empty.name")
	private String name;

	@Column
	@NotNull(message = "error.empty.username")
	@NotEmpty(message = "error.empty.username")
	private String username;

	@JsonIgnore
	@Column
	private String password;

	@Enumerated(EnumType.STRING)
	private Status status;

	@Column
	private String description;

	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	private Collection<Authorities> roles;

	@Transient
	@JsonIgnore
	private Collection<UserAuthority> authorities;

	@Override
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	@JsonIgnore
	public Collection<UserAuthority> getAuthorities() {
		if (roles != null) {
			roles.forEach(r -> {
				addAuth(r);
			});
		}

		return authorities;
	}

	private void addAuth(Authorities role) {
		if (authorities == null) {
			authorities = new HashSet<>();
		}
		authorities.add(new UserAuthority(role.getRole()));
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		if (Status.ACTIVE.equals(status)) {
			return true;
		}
		return false;
	}

	@JsonIgnore
	public boolean isUser() {
		if (roles != null) {
			try (Stream<String> auths = roles.stream().map(Authorities::getRole)) {
				return auths.filter(a -> a.equals(Authorities.USER.getRole())).findAny().isPresent();
			} catch (Exception e) {

			}
		}
		return false;
	}

	@JsonIgnore
	public boolean isAdmin() {
		if (roles != null) {
			try (Stream<String> auths = roles.stream().map(Authorities::getRole)) {
				return auths.filter(a -> a.equals(Authorities.ADMIN.getRole())).findAny().isPresent();
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}

	public String toJson() {
		StringBuilder sb = new StringBuilder("[");
		String sep = "";
		if (roles != null && !roles.isEmpty()) {
			for (Authorities r : roles) {
				sb.append(sep + "'" + r.getRole() + "'");
				sep = ",";
			}
		}
		sb.append("]");

		return "{\"id\":" + id + ", \"name\":\"" + name + "\", \"username\":\"" + username + "\", \"status\":\""
				+ status + "\", \"description\":\"" + description + "\"}";
	}
}