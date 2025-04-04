package it.epicode.progetto.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "autisti")
public class Autista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cognome;

    private LocalDate dataNascita;

    private String patenteCategoria;

    @OneToMany(mappedBy = "autista", cascade = CascadeType.ALL)
    private List<Turno> turni;

    @OneToMany(mappedBy = "autista", cascade = CascadeType.ALL)
    private List<Tratta> storicoTratte;

    @OneToMany(mappedBy = "autista", cascade = CascadeType.ALL)
    private List<NotaDisciplinare> noteDisciplinari;

    @ManyToOne
    @JoinColumn(name = "mezzo_id")
    private Mezzo mezzoAttuale;

    public Autista(String nome, String cognome, LocalDate dataNascita, String patenteCategoria) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.patenteCategoria = patenteCategoria;
    }

    public boolean isDisponibile(LocalDate data) {
        if (turni == null) return true;
        return turni.stream().noneMatch(turno -> turno.getData().isEqual(data.atStartOfDay()));
    }
}


