package com.PokemonBattler.api;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;

import com.PokemonBattler.Builder.Pokemon;

public class PokemonService {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    EntityManager em = emf.createEntityManager();
    @Transactional
    public Pokemon savePokemon(Pokemon pokemon) {
        em.getTransaction().begin();
        em.persist(pokemon);
        em.getTransaction().commit();
        emf.close();
        return pokemon;
    }
}
