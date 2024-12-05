package com.PokemonBattler.Encounters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.PokemonBattler.Builder.Pokemon.Pokemon;
import com.PokemonBattler.api.Parse.PokemonParser;
import com.PokemonBattler.api.PokemonApiClient;
import com.PokemonBattler.api.PokemonRepository;

public class StarterGenerator {
    public List<Pokemon> generateStarters(PokemonParser pokemonParser) {
        Random rand = new Random();
        int upperbound = 1026;
        List<Pokemon> starterList = new ArrayList<>();

        for(int i = 0; i < 3; i++) {
            int randomId = rand.nextInt(upperbound);
            starterList.add(pokemonParser.parse(PokemonApiClient.getPokemonData(String.valueOf(randomId))));
        }
        return starterList;
    }

    public Pokemon pickStarter(List<Pokemon> starterList) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose your starter Pokémon:");
        for (int i = 0; i < starterList.size(); i++) {
            System.out.println((i + 1) + ". " + starterList.get(i).getName());
        }

        int choice = -1;

        while (choice < 1 || choice > starterList.size()) {
            System.out.print("Enter the number of your choice: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice < 1 || choice > starterList.size()) {
                    System.out.println("Invalid choice. Please select a number between 1 and " + starterList.size());
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        }

        Pokemon selectedPokemon = starterList.get(choice - 1);
        PokemonRepository.savePokemon(selectedPokemon);
        System.out.println("You chose: " + selectedPokemon.getName());
        return selectedPokemon;

    }
}
