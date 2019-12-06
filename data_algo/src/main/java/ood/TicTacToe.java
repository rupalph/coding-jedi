package ood;

/**
 * Created by rupalph on 11/3/19.
 */
public class TicTacToe {

    class GameBoard {
        char[][] board;
        Status status;
        GameBoard(int size){
            board = new char[size][size];
        }

        void putPiece(char symbol, Move move) {
            board[move.row][move.col] = symbol;
        }

        public Status getStatus() {
            return status;
        }
    }

    enum Status { WIN, LOST, IN_PROGRESS, DRAW};

    class GameStatus {
        Status status;
        Player player;

        public GameStatus(Status win, Player player1) {
        }
    }
    class Player {
        char symbol;
        Status status;

        Player(char sym){
            symbol = sym;
        }

        public void setWin() {
            status = Status.WIN;
        }

        public boolean win() {
            if(status==Status.WIN) return true;
            return false;
        }
    }

    class Move{
        int row, col;
    }

    class Game {
        Player player1, player2;
        GameBoard board;
        Player createPlayer(char symbol){
            return new Player(symbol);
        }
        void startGame(Player p1, Player p2, int size) {

            player1 = p1;
            player2 = p2;

            board = new GameBoard(size);


        }

        void takeTurn(Player p, Move move){
            board.putPiece(p.symbol, move);
            if(board.getStatus() == Status.WIN) {
                p.setWin();
            }
        }

         GameStatus checkStatus() {
            if (player1.win()) return new GameStatus(Status.WIN, player1);
            else if (player2.win()) return new GameStatus(Status.WIN, player2);
            else if (board.getStatus()==Status.DRAW) return new GameStatus(Status.DRAW, null);
            return new GameStatus(Status.IN_PROGRESS, null);
        }
    }
}
