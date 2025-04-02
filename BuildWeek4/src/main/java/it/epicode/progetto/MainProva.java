package it.epicode.progetto;

import java.time.LocalDateTime;

public class MainProva {
    public static void main(String[] args) {
        LocalDateTime tempo_X = LocalDateTime.now();
        LocalDateTime tempo_Y = LocalDateTime.now().plusMinutes(30);

        Integer differenza = (tempo_X.getMinute() - tempo_Y.getMinute());
        System.out.println(differenza);
    }
}
