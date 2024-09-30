package com.happy_travel.happy_travel.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@NotBlank(message = "El nombre no puede estar vacío")
@NonNull
@Column(nullable = false, unique = true)
private String username;

@NotBlank(message = "El mail no puede estar vacío")
@NonNull
@Column(nullable = false, unique = true)
private String email;

@NotBlank(message = "La contraseña no puede estar vacía")
@NonNull
@Column(nullable = false)
private String password;

@JsonIgnore
@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
private List<Destination> destinations;
    
}