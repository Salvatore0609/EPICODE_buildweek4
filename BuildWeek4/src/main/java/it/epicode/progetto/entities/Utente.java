package it.epicode.progetto.entities;

import it.epicode.progetto.enums.Ruolo;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "utenti")
public class Utente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUtente;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String cognome;

	@Column(nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Ruolo ruolo;

	@Column
	private boolean attivo = true;

	@OneToMany(mappedBy = "utente")
	private List<Biglietto> biglietti;

	public Utente(String username, String nome, String cognome, String password, Ruolo ruolo, boolean attivo) {
		this.username = username;
		this.nome = nome;
		this.cognome = cognome;
		this.password = password;
		this.ruolo = ruolo;
		this.attivo = attivo;
	}
}
