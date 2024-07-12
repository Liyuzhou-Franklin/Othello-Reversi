# Othello-Reversi
A platform for playing Reversi between two human players.

Java concepts used:
1. 2D Array: To model the Othello game board.
2. Java Collections: Set is used to keep track of the valid moves of both players; List is used to keep track of the history of the moves.
3. File I/O: The state of the game is written to the gameState file during the game. The information stored include the disk distribution on the game board, the historical moves, the valid next moves for both players, the number of black and white disks, and who the next player is. This allows players to resume the previous game even if the game window was closed.
4. JUnit Testable Component: The game logics (flipping the disks, checking the winner, and checking if the move is valid.) are unit testable.

Design model: Model-View-Controller.
1. The RunOthello class implements the controller part. It determines the layout of buttons and labels in the game window.
2. The GameBoard class implements the view part. It draws the game board and displays the game's internal state on the board. It bridges the RunOthello class and the Othello class.
3. The Othello class is the model part of Othello. It implements two important game logic: disk flipping and checking if a move is valid. It modifies the internal states (the 2D array storing the disks currently on the board and sets storing the valid moves) that store information of the game.
