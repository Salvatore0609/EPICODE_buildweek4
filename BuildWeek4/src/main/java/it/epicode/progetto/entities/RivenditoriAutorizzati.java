package it.epicode.progetto.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

@Entity
public class RivenditoriAutorizzati extends Rivenditore {
	@Column(nullable = false)
	private long bigliettiEmessi;

	@Column(nullable = false)
	private long abbonamentiEmessi;
}
