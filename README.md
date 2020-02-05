# The-Amazing-Labyrinth


## Description
Java implementation of the board game labyrinth.

## Objective
The objective of the game is to be the first player to collect the required treasures generated at the start of the game throughout the maze and to return to your starting position. 

## Rules
The four players are in each of the corners in a randomly generated maze. The only non-random tiles are ones that are on both an even row and column. 24 Treasures are randomly distributed to random tiles around the board. A deck of 24 cards containing images of each treasure is then shuffled and distributed equally among all four players face down. Turns are taken in a clockwise direction, starting with the top left player. 

In order to reach their objective, first the player must shift the walls of the labyrinth by pushing the extra tile into an odd row or column. This will also push another tile out, which becomes the new extra tile. You may not push an extra tile in the opposite direction of the previous . If a player is on the pushed out tile, the player is transferred to the newly inserted tile.  After shifting the maze the player may move in the maze as far as they like once. If the player lands on their objective they may look at their next objective. After it is the next player's turn. This continues until one player wins. 

## Screenshots
![main game](/src/resources/screenshots/mainGame.png)

## Authors
Jyaer<br/>
ronaldsin<br/>
