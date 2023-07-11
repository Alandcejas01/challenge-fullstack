package com.ar.backend.entities;

import com.ar.backend.enums.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/**
 *  Esta clase hace referencia a la entidad Usuario de la bases de datos.
 *  implementa la interfaz {@link UserDetails} para poseer los
 *  metodos necesarios para la autenticación en JWT.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String fullName;

  @NotNull
  private String email;

  @NotNull
  private String dni;

  @NotNull
  private String password;

  @Enumerated(EnumType.STRING)
  private Role role;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "account_id")
  private Account account;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getUsername() {
    return dni;
  }

  @Override
  public @NotNull String getPassword() {
    return password;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }


  /**
   * Dto para la autenticación del usuario.
   */
  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class UserAuthentication {
    private String fullName;
    private String email;
    private String dni;
    private String password;
    private Role role;
  }

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class UserDto {
    private Long id;
    private String fullName;
    private String email;
    private String dni;
    private Account.AccountDto account;
  }

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class UserInfoDto {
    private String fullName;
    private String dni;
  }

}
