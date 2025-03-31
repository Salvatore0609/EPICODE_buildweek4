package it.epicode.progetto.mezzi;
import it.epicode.progetto.tratta.Tratta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mezzi")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Mezzo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tratta_id")
    private Tratta tratta;

    private int numeroTicketVidimati;

    private int volteTrattaPercorsa;



}


