# Strategic Conquest Game (RISK) Project (Java Spring Boot, Postgress & React with Docker)

## Overview

This project is an implementation of a board game inspired by "RISK", with unique rules and gameplay, developed using Java Spring Boot for the backend, React for the frontend, and Docker for containerization. The game is networked, allowing 2-4 players to play from different devices.

## Game Description

The Strategic Conquest Game is a turn-based strategy game where players aim to conquer territories on a game board. Each player starts with a set number of territories and armies. The objective is to strategically move armies, attack opponents, and defend territories to ultimately control the entire map. The game incorporates a card drawing mechanism, adding an element of chance and strategy that can significantly impact the gameplay.

### Key Elements:
- **Territories:** The board is divided into various territories that players can conquer and defend.
- **Armies:** Players use armies to attack and defend territories.
- **Turns:** Players take turns to make strategic decisions, move armies, and engage in battles.

## Features

### Game Mechanics
- **Strategic Gameplay:** Inspired by the classic game "RISK", but with distinct rules and mechanics.
- **Networked Multiplayer:** 2-4 players can join and play from separate devices.
- **Dynamic Game Board:** A map divided into territories, each owned and controlled by players.

### Enhanced Gameplay with Card System
- **Card Drawing Mechanism:** Players can draw cards each turn, affecting gameplay with positive or negative impacts.
- **Card Types:**
  - **CARD_CONQUERING_WARRIORS:** Adds a Level-6 unit to every territory owned by the player.
  - **CARD_FAMINE:** Halts tech/food resource creation for the player for the next round.
  - **CARD_UNBREAKABLE_DEFENCE:** Defends one territory completely for a turn.
  - **CARD_NO_LUCK:** No effect, representing a missed opportunity.

### Tech Stack
- **Backend:** Java Spring Boot.
- **Frontend:** React.
- **Database:** Postgress.
- **Containerization:** Docker setup for easy deployment and environment consistency.

## Installation and Running the Game

### Prerequisites
- Docker

### Installation
1. Clone the repository to your local machine:

```sh
git clone https://github.com/KoushikAS/Strategic_Conquest_Game_RISK_Project.git
cd Strategic_Conquest_Game_RISK_Project
```

### Running the Game with Docker
1. Use Docker to build and run the containers for both the backend and frontend services.

```sh
docker-compose up --build
```
   
## Accessing the Application

- Access the game through the frontend service's exposed port. http://127.0.0.1:3000/

## Database Management with pgAdmin

For easier view of DB I have enabled PG Admin to connect and view DB.

- **Access pgAdmin**: Navigate to `http://127.0.0.1:5050/` in your web browser to manage the PostgreSQL database.
- **Login Credentials**: Use the email `651@duke.edu` and the password `651651651` for access.
- **Connecting to the Database**:
  - Click on "Add New Server" to establish a new database connection.
  - Under the "General tab:
    - Name: any value like `db`.
  - Under the "Connection" tab:
    - Host name/address: `db`
    - Username: `postgres`
    - Password: `drew@ece`

## How to Play

1. **Join the Game:** Players join the game from their devices.
2. **Strategic Moves:** Players make move and attack orders to conquer territories.
3. **Draw Cards:** Utilize the card drawing system for strategic advantages or encounter challenges.
4. **Winning the Game:** The game ends when one player conquers all territories or all other players lose their territories.

## Contributions

This project was developed as part of an academic assignment in 3 different evolution as provided in requiremnets folder. Contributions were made solely by Koushik Annareddy Sreenath, Wenting Yang, Aimin Yang, Yushun Chen, adhering to the project guidelines and requirements set by the course ECE-651 Software Engineering.

## License

This project is licensed under the [MIT License](LICENSE).

## Acknowledgments

- Gratitude to Andrew Hilton and the course staff for their invaluable guidance.
- This project is inspired by the board game "RISK" and adapted with unique rules and gameplay mechanics.

