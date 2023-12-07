package com.lypaka.clicky.Utils;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonBuilder;
import com.pixelmonmod.pixelmon.api.pokemon.species.Species;
import com.pixelmonmod.pixelmon.api.registries.PixelmonSpecies;

import java.util.ArrayList;
import java.util.List;

public class PokemonPool {

    public static List<Pokemon> pool;

    public static void load() {

        pool = new ArrayList<>();
        for (Species species : PixelmonSpecies.getAll()) {

            if (!species.isLegendary() && !species.isMythical() && !species.isUltraBeast()) {

                if (getEvoStage(species).equalsIgnoreCase("first")) {

                    Pokemon pokemon = PokemonBuilder.builder().species(species).level(1).build();
                    pool.add(pokemon);

                }

            }

        }

    }

    private static String getEvoStage (Species pokemon) {

        // Pokemon has no pre-evolutions and can evolve, Pokemon is baby-stage
        if (pokemon.getDefaultForm().getPreEvolutions().size() == 0 && pokemon.getDefaultForm().getEvolutions().size() != 0) {

            return "First";

        }

        // Pokemon has pre-evolutions and can evolve, Pokemon is middle-stage
        if (pokemon.getDefaultForm().getPreEvolutions().size() != 0 && pokemon.getDefaultForm().getEvolutions().size() != 0) {

            return "Middle";

        }

        // Pokemon has pre-evolutions and can not evolve, Pokemon is final-stage
        if (pokemon.getDefaultForm().getPreEvolutions().size() != 0 && pokemon.getDefaultForm().getEvolutions().size() == 0) {

            return "Final";

        }

        // Pokemon has no pre-evolutions and can not evolve, Pokemon is single-stage
        if (pokemon.getDefaultForm().getPreEvolutions().size() == 0 && pokemon.getDefaultForm().getEvolutions().size() == 0) {

            return "Single";

        }

        return "None";

    }

}
