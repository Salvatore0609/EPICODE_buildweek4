package it.epicode.progetto.entities;
import it.epicode.progetto.dao.TrattaDAO;
import it.epicode.progetto.dao.ViaggioTrattaDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.Duration;
import java.time.LocalDateTime;
import static it.epicode.progetto.utils.Input.scanner;

public class GestioneViaggioTratte {
	public static void eseguiViaggioTratta() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
		EntityManager em = emf.createEntityManager();
		ViaggioTrattaDao viaggioTrattaDAO = new ViaggioTrattaDao(em);
		TrattaDAO trattaDAO = new TrattaDAO(em);

		System.out.println("Inserisci il numero di tratta da far partire.");
		Long numeroTratta = scanner.nextLong();
		System.out.print("Scelta: ");
		System.out.println("Inserisci il tempo previsto di percorrenza della tratta:");
		int tempoPrevisto = scanner.nextInt();
		System.out.print("Scelta: ");
		System.out.println("Inserisci il tempo effettivo di percorrenza della tratta:");
		int tempoEffettivo = scanner.nextInt();
		System.out.print("Scelta: ");
		Tratta numeroTrattaScanner = trattaDAO.findById(numeroTratta);

		LocalDateTime tempoPrevistoOr = LocalDateTime.now().plusMinutes(tempoPrevisto);
		LocalDateTime tempoEffettivoOr = LocalDateTime.now().plusMinutes(tempoEffettivo);

		Long differenzaTempo = (Duration.between(tempoPrevistoOr, tempoEffettivoOr)).toMinutes();

		int numeroViaggioTratte = viaggioTrattaDAO.ritornaUltimoViaggio();

		ViaggioTratte viaggioTratte = new ViaggioTratte(null, numeroTrattaScanner, tempoPrevistoOr, tempoEffettivoOr,
				differenzaTempo, numeroViaggioTratte + 1);

		System.out.println("Viaggio delle tratte eseguito con successo.");
	}
}
