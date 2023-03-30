package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.repository.PlayerRepository;
import edu.duke.ece651.team13.shared.enums.PlayerStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    PlayerRepository repository;

    @Override
    public void createPlayer(String name) {
        PlayerEntity player = new PlayerEntity(name);
        repository.save(player);
    }

    @Override
    public PlayerEntity getPlayer(Long Id) {
        Optional<PlayerEntity> player = repository.findById(Id);
        if (player.isPresent()) {
            return player.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void updatePlayerStatus(Long Id, PlayerStatusEnum status){
        PlayerEntity player = getPlayer(Id);
        player.setStatus(status);
        repository.save(player);
    }
}
