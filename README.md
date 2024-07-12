# Othello-Reversi
A platform for playing Reversi between two human players.

Java concepts used:
1. 2D Array
2. Java Collections
3. File I/O
4. JUnit Testable Component

Design model: Model-View-Controller.
1. The RunOthello class implements the controller part. It determines the layout of buttons and labels in the game window.
2. The GameBoard class implements the view part. It draws the game board and displays the game's internal state on the board. It bridges the RunOthello class and the Othello class.
3. The Othello class is the model part of Othello. It implements two important game logic: disk flipping and checking if a move is valid. It modifies the internal states (the 2D array storing the disks currently on the board and sets storing the valid moves) that store information of the game.
