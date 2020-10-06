package ar.edu.unq.cookitbackend.service.initial;

import ar.edu.unq.cookitbackend.model.Step;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class InitialStep {
    public List<Step> createSteps() {
        return new ArrayList<>(Arrays.asList(
                Step.builder()
                        .description("En un bol ponemos dos tazas de harina integral, le agregamos una cucharadita de polvo para hornear y otra cucharadita de bicarbonato y sal a gusto.")
                        .build(),
                Step.builder()
                        .description("Le agregamos el agua tibia y lo vamos amansando hasta crear una masa homogénea unos 10 minutos después de amasar la dejamos reposar por unos 30 minutos y la ponemos en la pizera y la mandamos al horno por unos 20 minutos hasta que la masa se dore.")
                        .build(),
                Step.builder()
                        .description("Le agregamos el agua tibia y lo vamos amansando hasta crear una masa homogénea unos 10 minutos después de amasar la dejamos reposar por unos 30 minutos y la ponemos en la pizera y la mandamos al horno por unos 20 minutos hasta que la masa se dore.")
                        .build(),
                Step.builder()
                        .description("Le agregamos el agua tibia y lo vamos amansando hasta crear una masa homogénea unos 10 minutos después de amasar la dejamos reposar por unos 30 minutos y la ponemos en la pizera y la mandamos al horno por unos 20 minutos hasta que la masa se dore.")
                        .build(),
                Step.builder()
                        .description("Le agregamos el agua tibia y lo vamos amansando hasta crear una masa homogénea unos 10 minutos después de amasar la dejamos reposar por unos 30 minutos y la ponemos en la pizera y la mandamos al horno por unos 20 minutos hasta que la masa se dore.")
                        .build()
        ));
    }
}
