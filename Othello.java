package org.cis1200.othello;

import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.io.*;

public class Othello {

    private int[][] board;
    private boolean nextPlayer;
    private Set<Integer> blackValidMove = new TreeSet<>();
    private Set<Integer> whiteValidMove = new TreeSet<>();
    private List<Integer> blackMoves = new ArrayList<>();
    private List<Integer> whiteMoves = new ArrayList<>();
    private int blackDisk;
    private int whiteDisk;

    public Othello() {
        reset();
    }

    public void reset() {
        board = new int[8][8];
        board[3][3] = 1;
        board[4][4] = 1;
        board[3][4] = 2;
        board[4][3] = 2;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (! (i == 3 || i == 4) && (j == 3 || j == 4)) {
                    board[i][j] = 0;
                }
            }
        }
        nextPlayer = true;
        blackValidMove.add(21);
        blackValidMove.add(30);
        blackValidMove.add(35);
        blackValidMove.add(44);
        whiteValidMove.add(20);
        whiteValidMove.add(27);
        whiteValidMove.add(38);
        whiteValidMove.add(45);
        blackMoves.clear();
        whiteMoves.clear();
        blackDisk = 2;
        whiteDisk = 2;
    }

    public boolean playTurn(int r, int c) {
        if (!validMove(r, c)) {
            return false;
        } else if (nextPlayer) {
            board[r][c] = 1;
            blackMoves.add(8 * r + c + 1);
        } else {
            board[r][c] = 2;
            whiteMoves.add(8 * r + c + 1);
        }
        pieceFlip(r, c);
        updateValidMoves();

        if (checkWinner() == 0) {
            nextPlayer = !nextPlayer;
        }
        if ((nextPlayer && blackValidMove.isEmpty()) ||
                (!nextPlayer && whiteValidMove.isEmpty())) {
            nextPlayer = !nextPlayer;
        }
        return true;
    }

    public boolean validMove(int r, int c) {
        if (checkWinner() != 0) {
            return false;
        } else if (board[r][c] != 0) {
            return false;
        } else {
            return (blackValidMove.contains(8 * r + c + 1) || !nextPlayer) &&
                    (whiteValidMove.contains(8 * r + c + 1) || nextPlayer);
        }
    }

    public void pieceFlip(int r, int c) {
        pieceFlipHelperDiagonal(r, c);
        pieceFlipHelperVertical(r, c);
        pieceFlipHelperHorizontal(r, c);
        if (nextPlayer) {
            blackDisk++;
        } else {
            whiteDisk++;
        }
    }

    public void updateValidMoves() {
        deleteMoves(blackValidMove, 1);
        deleteMoves(whiteValidMove, 2);
        addMoves(blackValidMove, 1);
        addMoves(whiteValidMove, 2);
    }

    public void pieceFlipHelperDiagonal(int r, int c) {
        int row = r;
        int col = c;
        if (nextPlayer) {
            while (row > 0 && col > 0) {
                row--;
                col--;
                if (board[row][col] == 0) {
                    break;
                }
                if (board[row][col] == 1) {
                    if (row == r - 1) {
                        break;
                    }
                    if (row < r - 1) {
                        for (int i = 1; i < r - row; i++) {
                            board[row + i][col + i] = 1;
                            blackDisk++;
                            whiteDisk--;
                        }
                        break;
                    }
                }
            }
            row = r;
            col = c;
            while (row > 0 && col < 7) {
                row--;
                col++;
                if (board[row][col] == 0) {
                    break;
                }
                if (board[row][col] == 1) {
                    if (row == r - 1) {
                        break;
                    }
                    if (row < r - 1) {
                        for (int i = 1; i < r - row; i++) {
                            board[row + i][col - i] = 1;
                            blackDisk++;
                            whiteDisk--;
                        }
                        break;
                    }
                }
            }
            row = r;
            col = c;
            while (row < 7 && col < 7) {
                row++;
                col++;
                if (board[row][col] == 0) {
                    break;
                }
                if (board[row][col] == 1) {
                    if (row == r + 1) {
                        break;
                    }
                    if (row > r + 1) {
                        for (int i = 1; i < row - r; i++) {
                            board[row - i][col - i] = 1;
                            blackDisk++;
                            whiteDisk--;
                        }
                        break;
                    }
                }
            }
            row = r;
            col = c;
            while (row < 7 && col > 0) {
                row++;
                col--;
                if (board[row][col] == 0) {
                    break;
                }
                if (board[row][col] == 1) {
                    if (row == r + 1) {
                        break;
                    }
                    if (row > r + 1) {
                        for (int i = 1; i < row - r; i++) {
                            board[row - i][col + i] = 1;
                            blackDisk++;
                            whiteDisk--;
                        }
                        break;
                    }
                }
            }
        } else {
            while (row > 0 && col > 0) {
                row--;
                col--;
                if (board[row][col] == 0) {
                    break;
                }
                if (board[row][col] == 2) {
                    if (row == r - 1) {
                        break;
                    }
                    if (row < r - 1) {
                        for (int i = 1; i < r - row; i++) {
                            board[row + i][col + i] = 2;
                            whiteDisk++;
                            blackDisk--;
                        }
                        break;
                    }
                }
            }
            row = r;
            col = c;
            while (row > 0 && col < 7) {
                row--;
                col++;
                if (board[row][col] == 0) {
                    break;
                }
                if (board[row][col] == 2) {
                    if (row == r - 1) {
                        break;
                    }
                    if (row < r - 1) {
                        for (int i = 1; i < r - row; i++) {
                            board[row + i][col - i] = 2;
                            whiteDisk++;
                            blackDisk--;
                        }
                        break;
                    }
                }
            }
            row = r;
            col = c;
            while (row < 7 && col < 7) {
                row++;
                col++;
                if (board[row][col] == 0) {
                    break;
                }
                if (board[row][col] == 2) {
                    if (row == r + 1) {
                        break;
                    }
                    if (row > r + 1) {
                        for (int i = 1; i < row - r; i++) {
                            board[row - i][col - i] = 2;
                            whiteDisk++;
                            blackDisk--;
                        }
                        break;
                    }
                }
            }
            row = r;
            col = c;
            while (row < 7 && col > 0) {
                row++;
                col--;
                if (board[row][col] == 0) {
                    break;
                }
                if (board[row][col] == 2) {
                    if (row == r + 1) {
                        break;
                    }
                    if (row > r + 1) {
                        for (int i = 1; i < row - r; i++) {
                            board[row - i][col + i] = 2;
                            whiteDisk++;
                            blackDisk--;
                        }
                        break;
                    }
                }
            }
        }
    }

    public void pieceFlipHelperVertical(int r, int c) {
        int row = r;
        if (nextPlayer) {
            while (row > 0) {
                row--;
                if (board[row][c] == 0) {
                    break;
                }
                if (board[row][c] == 1) {
                    if (row == r - 1) {
                        break;
                    }
                    if (row < r - 1) {
                        for (int i = 1; i < r - row; i++) {
                            board[row + i][c] = 1;
                            blackDisk++;
                            whiteDisk--;
                        }
                        break;
                    }
                }
            }
            row = r;
            while (row < 7) {
                row++;
                if (board[row][c] == 0) {
                    break;
                }
                if (board[row][c] == 1) {
                    if (row == r + 1) {
                        break;
                    }
                    if (row > r + 1) {
                        for (int i = 1; i < row - r; i++) {
                            board[row - i][c] = 1;
                            blackDisk++;
                            whiteDisk--;
                        }
                        break;
                    }
                }
            }
        } else {
            while (row > 0) {
                row--;
                if (board[row][c] == 0) {
                    break;
                }
                if (board[row][c] == 2) {
                    if (row == r - 1) {
                        break;
                    }
                    if (row < r - 1) {
                        for (int i = 1; i < r - row; i++) {
                            board[row + i][c] = 2;
                            whiteDisk++;
                            blackDisk--;
                        }
                        break;
                    }
                }
            }
            row = r;
            while (row < 7) {
                row++;
                if (board[row][c] == 0) {
                    break;
                }
                if (board[row][c] == 2) {
                    if (row == r + 1) {
                        break;
                    }
                    if (row > r + 1) {
                        for (int i = 1; i < row - r; i++) {
                            board[row - i][c] = 2;
                            whiteDisk++;
                            blackDisk--;
                        }
                        break;
                    }
                }
            }
        }
    }

    public void pieceFlipHelperHorizontal(int r, int c) {
        int col = c;
        if (nextPlayer) {
            while (col > 0) {
                col--;
                if (board[r][col] == 0) {
                    break;
                }
                if (board[r][col] == 1) {
                    if (col == c - 1) {
                        break;
                    }
                    if (col < c - 1) {
                        for (int i = 1; i < c - col; i++) {
                            board[r][col + i] = 1;
                            blackDisk++;
                            whiteDisk--;
                        }
                        break;
                    }
                }
            }
            col = c;
            while (col < 7) {
                col++;
                if (board[r][col] == 0) {
                    break;
                }
                if (board[r][col] == 1) {
                    if (col == c + 1) {
                        break;
                    }
                    if (col > c + 1) {
                        for (int i = 1; i < col - c; i++) {
                            board[r][col - i] = 1;
                            blackDisk++;
                            whiteDisk--;
                        }
                        break;
                    }
                }
            }
        } else {
            while (col > 0) {
                col--;
                if (board[r][col] == 0) {
                    break;
                }
                if (board[r][col] == 2) {
                    if (col == c - 1) {
                        break;
                    }
                    if (col < c - 1) {
                        for (int i = 1; i < c - col; i++) {
                            board[r][col + i] = 2;
                            whiteDisk++;
                            blackDisk--;
                        }
                        break;
                    }
                }
            }
            col = c;
            while (col < 7) {
                col++;
                if (board[r][col] == 0) {
                    break;
                }
                if (board[r][col] == 2) {
                    if (col == c + 1) {
                        break;
                    }
                    if (col > c + 1) {
                        for (int i = 1; i < col - c; i++) {
                            board[r][col - i] = 2;
                            whiteDisk++;
                            blackDisk--;
                        }
                        break;
                    }
                }
            }
        }
    }

    public void deleteMoves(Set<Integer> s, int color) {
        Iterator<Integer> iter = s.iterator();
        while (iter.hasNext()) {
            int k = iter.next();
            int r = (k - 1) / 8;
            int c = (k - 1) % 8;
            if (board[r][c] != 0) {
                iter.remove();
            } else {
                boolean check = true;
                int d = 0;
                while (c + d < 7) {
                    d++;
                    if (board[r][c + d] == 0) {
                        break;
                    }
                    if (board[r][c + d] == color) {
                        if (d == 1) {
                            break;
                        }
                        if (d > 1) {
                            check = false;
                            break;
                        }
                    }
                }
                d = 0;
                if (check) {
                    while (c - d > 0) {
                        d++;
                        if (board[r][c - d] == 0) {
                            break;
                        }
                        if (board[r][c - d] == color) {
                            if (d == 1) {
                                break;
                            }
                            if (d > 1) {
                                check = false;
                                break;
                            }
                        }
                    }
                }
                d = 0;
                if (check) {
                    while (r + d < 7) {
                        d++;
                        if (board[r + d][c] == 0) {
                            break;
                        }
                        if (board[r + d][c] == color) {
                            if (d == 1) {
                                break;
                            }
                            if (d > 1) {
                                check = false;
                                break;
                            }
                        }
                    }
                }
                d = 0;
                if (check) {
                    while (r - d > 0) {
                        d++;
                        if (board[r - d][c] == 0) {
                            break;
                        }
                        if (board[r - d][c] == color) {
                            if (d == 1) {
                                break;
                            }
                            if (d > 1) {
                                check = false;
                                break;
                            }
                        }
                    }
                }
                d = 0;
                if (check) {
                    while (r + d < 7 && c - d > 0) {
                        d++;
                        if (board[r + d][c - d] == 0) {
                            break;
                        }
                        if (board[r + d][c - d] == color) {
                            if (d == 1) {
                                break;
                            }
                            if (d > 1) {
                                check = false;
                                break;
                            }
                        }
                    }
                }
                d = 0;
                if (check) {
                    while (r + d < 7 && c + d < 7) {
                        d++;
                        if (board[r + d][c + d] == 0) {
                            break;
                        }
                        if (board[r + d][c + d] == color) {
                            if (d == 1) {
                                break;
                            }
                            if (d > 1) {
                                check = false;
                                break;
                            }
                        }
                    }
                }
                d = 0;
                if (check) {
                    while (r - d > 0 && c + d < 7) {
                        d++;
                        if (board[r - d][c + d] == 0) {
                            break;
                        }
                        if (board[r - d][c + d] == color) {
                            if (d == 1) {
                                break;
                            }
                            if (d > 1) {
                                check = false;
                                break;
                            }
                        }
                    }
                }
                d = 0;
                if (check) {
                    while (r - d > 0 && c - d > 0) {
                        d++;
                        if (board[r - d][c - d] == 0) {
                            break;
                        }
                        if (board[r - d][c - d] == color) {
                            if (d == 1) {
                                break;
                            }
                            if (d > 1) {
                                check = false;
                                break;
                            }
                        }
                    }
                }
                if (check) {
                    iter.remove();
                }
            }
        }
    }

    public void addMoves(Set<Integer> s, int color) {
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                if (board[i][j] == 0 && !s.contains(8 * i + j + 1)) {
                    boolean check = false;
                    int d = 0;
                    while (j + d < 7) {
                        d++;
                        if (board[i][j + d] == 0) {
                            break;
                        }
                        if (board[i][j + d] == color) {
                            if (d == 1) {
                                break;
                            }
                            if (d > 1) {
                                check = true;
                                break;
                            }
                        }
                    }
                    d = 0;
                    if (!check) {
                        while (j - d > 0) {
                            d++;
                            if (board[i][j - d] == 0) {
                                break;
                            }
                            if (board[i][j - d] == color) {
                                if (d == 1) {
                                    break;
                                }
                                if (d > 1) {
                                    check = true;
                                    break;
                                }
                            }
                        }
                    }
                    d = 0;
                    if (!check) {
                        while (i + d < 7) {
                            d++;
                            if (board[i + d][j] == 0) {
                                break;
                            }
                            if (board[i + d][j] == color) {
                                if (d == 1) {
                                    break;
                                }
                                if (d > 1) {
                                    check = true;
                                    break;
                                }
                            }
                        }
                    }
                    d = 0;
                    if (!check) {
                        while (i - d > 0) {
                            d++;
                            if (board[i - d][j] == 0) {
                                break;
                            }
                            if (board[i - d][j] == color) {
                                if (d == 1) {
                                    break;
                                }
                                if (d > 1) {
                                    check = true;
                                    break;
                                }
                            }
                        }
                    }
                    d = 0;
                    if (!check) {
                        while (i + d < 7 && j - d > 0) {
                            d++;
                            if (board[i + d][j - d] == 0) {
                                break;
                            }
                            if (board[i + d][j - d] == color) {
                                if (d == 1) {
                                    break;
                                }
                                if (d > 1) {
                                    check = true;
                                    break;
                                }
                            }
                        }
                    }
                    d = 0;
                    if (!check) {
                        while (i + d < 7 && j + d < 7) {
                            d++;
                            if (board[i + d][j + d] == 0) {
                                break;
                            }
                            if (board[i + d][j + d] == color) {
                                if (d == 1) {
                                    break;
                                }
                                if (d > 1) {
                                    check = true;
                                    break;
                                }
                            }
                        }
                    }
                    d = 0;
                    if (!check) {
                        while (i - d > 0 && j + d < 7) {
                            d++;
                            if (board[i - d][j + d] == 0) {
                                break;
                            }
                            if (board[i - d][j + d] == color) {
                                if (d == 1) {
                                    break;
                                }
                                if (d > 1) {
                                    check = true;
                                    break;
                                }
                            }
                        }
                    }
                    d = 0;
                    if (!check) {
                        while (i - d > 0 && j - d > 0) {
                            d++;
                            if (board[i - d][j - d] == 0) {
                                break;
                            }
                            if (board[i - d][j - d] == color) {
                                if (d == 1) {
                                    break;
                                }
                                if (d > 1) {
                                    check = true;
                                    break;
                                }
                            }
                        }
                    }
                    if (check) {
                        s.add(8 * i + j + 1);
                    }
                }
            }
        }
    }

    public int checkWinner() {
        if (blackDisk + whiteDisk == 64 || (blackValidMove.isEmpty() &&
                whiteValidMove.isEmpty())) {
            if (blackDisk > whiteDisk) {
                return 1;
            } else if (blackDisk < whiteDisk) {
                return 2;
            } else {
                return 3;
            }
        } else {
            return 0;
        }
    }

    public int getCell(int r, int c) {
        return board[r][c];
    }

    public boolean getPlayer() {
        return nextPlayer;
    }

    public int getNumberOfBlack() {
        return blackDisk;
    }

    public int getNumberOfWhite() {
        return whiteDisk;
    }

    public void writeGameToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    writer.write(Integer.toString(board[i][j]));
                    writer.write(" ");
                }
                writer.newLine();
            }

            writer.newLine();
            writer.write("History of moves made:");
            writer.newLine();
            for (Integer move : blackMoves) {
                writer.write(move.toString());
                writer.write(" ");
            }
            writer.newLine();
            for (Integer move : whiteMoves) {
                writer.write(move.toString());
                writer.write(" ");
            }

            writer.newLine();
            writer.write("Valid moves for black and white:");
            writer.newLine();
            for (Integer move : blackValidMove) {
                writer.write(move.toString());
                writer.write(" ");
            }
            writer.newLine();
            for (Integer move : whiteValidMove) {
                writer.write(move.toString());
                writer.write(" ");
            }

            writer.newLine();
            writer.write("Number of black disks:");
            writer.newLine();
            writer.write(String.valueOf(blackDisk));
            writer.newLine();
            writer.write("Number of white disks:");
            writer.newLine();
            writer.write(String.valueOf(whiteDisk));

            writer.newLine();
            if (nextPlayer && checkWinner() == 0) {
                writer.write("Black's turn");
            } else if (!nextPlayer && checkWinner() == 0) {
                writer.write("White's turn");
            } else if (checkWinner() == 1) {
                writer.write("Black wins!");
            } else if (checkWinner() == 2) {
                writer.write("White wins!");
            } else {
                writer.write("It's a tie!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadGameFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            for (int i = 0; i < 8; i++) {
                String[] rowValues = reader.readLine().split(" ");
                for (int j = 0; j < 8; j++) {
                    board[i][j] = Integer.parseInt(rowValues[j]);
                }
            }

            reader.readLine();
            reader.readLine();
            String[] blackMovesHistory = reader.readLine().split(" ");
            String[] whiteMovesHistory = reader.readLine().split(" ");
            for (String move : blackMovesHistory) {
                blackMoves.add(Integer.parseInt(move));
            }
            for (String move : whiteMovesHistory) {
                whiteMoves.add(Integer.parseInt(move));
            }

            reader.readLine();
            String[] blackMovesResumed = reader.readLine().split(" ");
            String[] whiteMovesResumed = reader.readLine().split(" ");

            try {
                blackValidMove.clear();
                whiteValidMove.clear();
                for (String move : blackMovesResumed) {
                    blackValidMove.add(Integer.parseInt(move));
                }
                for (String move : whiteMovesResumed) {
                    whiteValidMove.add(Integer.parseInt(move));
                }
            } catch (NumberFormatException e) { }

            reader.readLine();
            blackDisk = Integer.parseInt(reader.readLine());
            reader.readLine();
            whiteDisk = Integer.parseInt(reader.readLine());

            if (reader.readLine().equals("Black's turn")) {
                nextPlayer = true;
            } else {
                nextPlayer = false;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
