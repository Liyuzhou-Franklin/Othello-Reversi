# Othello-Reversi
A platform for playing Reversi between two human players

========================
=: Core Concepts Used :=
========================

  1. 2D Array
        I model the Othello game board with a 8*8 2D array. The values stored in
        the 2D array (except for the center 4 blocks) will initially be 0. This is
        done in the reset() function. If a black disk appears on the board, I set
        the value of the corresponding entry in the 2D array to 1, as well as change
        the flipped disks' corresponding entries from 2 to 1. Similar if a white disk
        appears on the board. Using integers to represent colors is clean and precise,
        though I could also create an enumeration of colors. I use for loops and while
        loops to iterate through all or a part of the elements in the array. It is
        appropriate to use 2D array because we do not need to resize the game board.

  2. Java Collections
        I use the Set in Java Collections to keep track of the valid moves (empty cells
        with flippable opponents' disks next to it) of the two players. blackValidMove
        records the valid moves for the black player; whiteValidMove records the valid
        moves for the white player. I number the grids from 1 to 64 (the top left grid
        is set to 1 to be consistent with the java coordinate system). This number can
        be quickly converted into the location of the grid through division and modulus
        functions. I store this number instead of the grid coordinates in the TreeSet. I
        used the add and remove functions of set, and I used iterator to iterate through
        elements in the set to find the elements I want to remove.
        Set is justified because the order of the valid moves do not matter, and the valid
        moves are all distinct from each other. I need to modify the valid moves often,
        so I cannot use an array.
        I use the List in Java Collections to keep track of the history of moves made by
        the two players. blackMoves records the moves made by the black player; whiteMoves
        records the valid moves for the white player. Again, these moves are denoted by
        numbers from 1 to 64. I store this number instead of the grid coordinates in the
        ArrayList used to instantiate list. I used the add and clear functions of list to
        add moves to the list and clear moves when the game is reset.
        List is justified because the order of the historical moves does matter. Again, I
        need to modify these moves, so an array is unhandy.

  3. File I/O
        File I/O is implemented in my Othello class. I write the state of the game to the
        gameState file using File I/O. It stores all the status of the game, including the
        game board, the historical moves, the valid next moves for both players, the number
        of black and white disks, and who the next player is. To write the previous game
        state back to the game board displayed in the game window, users only need to click
        the "resume" button, which triggers my loadGameFromFile function in the Othello class.
        loadGameFromFile will change the number of disks and the next player to the same as
        that at the end of last game. The historical moves and valid moves will also be
        resumed in the internal state of the game.

  4. JUnit Testable Component
        Othello's game logic is unit-testable. I tested three game logic: flipping disks,
        checking winner, and checking if the move is valid. I wrote a total of 18 tests.
        They include edge cases and normal cases. I checked that no two tests examine the
        same thing. There is always a possibility that one of my test does not pass while
        others do.

===============================
=: File Structure Screenshot :=
===============================

- Include a screenshot of your project's file structure. This should include
  all of the files in your project, and the folders they are in. You can
  upload this screenshot in your homework submission to gradescope, named
  "file_structure.png".

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
        I employ the Model-View-Controller model in designing Othello.

        RunOthello: The RunOthello class implements the controller part. It determines the
        layout of buttons and labels in the game window.

        GameBoard: The GameBoard class implements the view part. It draws the game board and
        displays the game's internal state on the board. It bridges the RunOthello class and
        the Othello class.

        Othello: The Othello class is the model part of Othello. It implements two important
        game logic: disk flipping and checking if a move is valid. It modifies the internal
        states (the 2D array storing the disks currently on the board and sets storing the
        valid moves) that store information of the game.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
        The disk-flip logic is not simple, and required quite some debugging efforts.
        I initially updated variables and put the break keyword at the wrong place, which
        resulted in weird bugs. Same issue with putting the right grids into sets that
        stored valid moves.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
        I feel that the separation of functionality is good. I encapsulated the board
        with the private keyword in the Othello class. Methods in other classes cannot
        make any modifications to the game board.
        I would make my game logic less redundant if given the chance. Currently, the
        functions that check if a grid is a valid move and the disk flipping functions
        are very similar in their logic (screening the eight directions and flipping
        disks/deciding if the move is valid). I will think about how to refactor this if
        I have more time.

========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.
        I used Wikipedia's explanation of Othello's rules in my instruction window.
        https://en.wikipedia.org/wiki/Reversi
        Fun Fact: The testTie test case in my CheckWinnerTest class was adopted from a game
        played in the 2003 World Othello Championship, which is recorded in Brian Rose's book
        "Othello: A Minute to Learn, A Lifetime to Master." :)
