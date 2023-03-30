package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.repository.PlayerRepository;
import edu.duke.ece651.team13.shared.enums.PlayerStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private final PlayerRepository repository;

    @Override
    public PlayerEntity createPlayer(String name) {
        PlayerEntity player = new PlayerEntity(name);
        return repository.save(player);
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
    public PlayerEntity updatePlayerStatus(Long Id, PlayerStatusEnum status){
        PlayerEntity player = getPlayer(Id);
        player.setStatus(status);
        return repository.save(player);
    }
}
