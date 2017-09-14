# Tic-tac-toe
A java console game for tic-tac-toe. 

# How to start 

In order to play this game, execute in following order 

  - mvn clean install 
  - java -jar target/tic-tac-toe-1.0-SNAPSHOT.jar

# How to play 

At first you will see three game mode. 

```sh
1. Bot (1st Player)  vs Human (Support only 3x3 board)
2. Human (1st Player) vs Bot (Support only 3x3 board)
3. Human  vs Human 
```
Please choose 1/2 if you want to play with My bot. I can almost gurantee you can never beat my bot in tic-tac-toe :p. If you want more challenge choose 1. May be you will lose many times. 

For playing with Human, it's more generic N*N board ( with a cap on N (currently 33) ) . But in order to win you need only 3 adjacent 'x' or 'o'. 

Hope you will enjoy. 

P.S : For building the bot. I used standard game theory algorithm while performing exhaustive search on the graph.
