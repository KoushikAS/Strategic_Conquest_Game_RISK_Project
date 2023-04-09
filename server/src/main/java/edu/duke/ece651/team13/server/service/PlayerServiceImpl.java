package edu.duke.ece651.team13.server.service;

import edu.duke.ece651.team13.server.entity.GameEntity;
import edu.duke.ece651.team13.server.entity.PlayerEntity;
import edu.duke.ece651.team13.server.entity.UserEntity;
import edu.duke.ece651.team13.server.enums.PlayerStatusEnum;
import edu.duke.ece651.team13.server.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private final PlayerRepository repository;

    @Override
    public PlayerEntity createPlayer(String name, GameEntity game) {
        PlayerEntity player = new PlayerEntity(name);
        player.setGame(game);
        game.getPlayers().add(player);
        return repository.save(player);
    }

    @Override
    public PlayerEntity getPlayer(Long Id) {
        Optional<PlayerEntity> player = repository.findById(Id);
        if (player.isPresent()) {
            return player.get();
        } else {
            log.error("Did not find Player Id " + Id);
            throw new NoSuchElementException("Player with Id " + Id + " does not exists");
        }
    }

    @Override
    public List<PlayerEntity> getPlayersByUser(UserEntity user) {
        return repository.findByUser(user);
    }

    @Override
    public PlayerEntity updatePlayerStatus(PlayerEntity player, PlayerStatusEnum status) {
        player.setStatus(status);
        return repository.save(player);
    }

    @Override
    public PlayerEntity updatePlayerTechResource(PlayerEntity player, int techResource) {
        player.setTechResource(techResource);
        return repository.save(player);
    }

    @Override
    public PlayerEntity updatePlayerFoodResource(PlayerEntity player, int foodResource) {
        player.setFoodResource(foodResource);
        return repository.save(player);
    }

    @Override
    public PlayerEntity updatePlayerUser(PlayerEntity player, UserEntity userEntity) {
        player.setUser(userEntity);
        return repository.save(player);
    }
}
