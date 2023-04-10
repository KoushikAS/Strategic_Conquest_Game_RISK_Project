package edu.duke.ece651.team13.server.repository;

import edu.duke.ece651.team13.server.entity.PlayerEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Slf4j
public class CustomPlayerRepositoryImpl implements CustomPlayerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PlayerEntity getDetachedPlayer(Long id) {
        final PlayerEntity player = (PlayerEntity) entityManager.createQuery("FROM PlayerEntity p WHERE p.id = :id")
                .setParameter("id", id)
                .getSingleResult();
        log.info("Just after Entity get" +entityManager.contains(player));
        entityManager.detach(player);
        log.info("Just after detach Entity get" +entityManager.contains(player));
        return player;
    }
}
