package com.PokemonBattler.api;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import com.PokemonBattler.Builder.Pokemon.Pokemon;
public class PokemonRepository {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    EntityManager em = emf.createEntityManager();
    public Pokemon savePokemon(Pokemon pokemon) {
        em.getTransaction().begin();
        em.persist(pokemon);
        em.getTransaction().commit();
        return pokemon;
    }
    public List<Pokemon> findAll() {
       return em.createQuery("SELECT p FROM Pokemon p", Pokemon.class).getResultList();
    }
    public Optional<Pokemon> findById(Long id) {
        return Optional.ofNullable(em.find(Pokemon.class, id));
    }
    public void delete(Long id) {
        var pokemon = findById(id);
        em.remove(pokemon);
    }
    public Pokemon update(Pokemon pokemon) {
        return em.merge(pokemon);
    }

}
