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

public abstract class Mezzo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;




    private Tratta tratta;


    private int numeroTicketVidimati;

    private int volteTrattaPercorsa;



}


