package it.epicode.progetto.entities;

import it.epicode.progetto.enums.StatoDistributori;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity
public class DistributoriAutomatici extends Rivenditore {
	@Enumerated(EnumType.STRING)
	@Column
	private StatoDistributori stato;

	@Column(nullable = false)
	private long bigliettiEmessi;

	@Column(nullable = false)
	private long abbonamentiEmessi;

}
