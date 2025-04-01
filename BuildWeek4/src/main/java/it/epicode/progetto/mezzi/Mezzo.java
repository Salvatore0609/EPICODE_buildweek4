package it.epicode.progetto.mezzi;
import it.epicode.progetto.periodo_di_servizio.Stato;
import it.epicode.progetto.tratta.Tratta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mezzi")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Mezzo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @ManyToOne
    private Tratta tratta;

    private int numeroTicketVidimati;

    private int volteTrattaPercorsa;


    @Enumerated(EnumType.STRING)
    private Stato stato;

    public Stato getStatoEnum() {
        return Stato.valueOf(String.valueOf(stato));
    }


    public void setStatoEnum(Stato statoEnum) {
        this.stato = Stato.valueOf(statoEnum.name());
    }







}


