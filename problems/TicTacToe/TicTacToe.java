package problems.TicTacToe;

import java.util.*;

public class TicTacToe {
    /**  */
    // 2 Human players
    // 3*3 game board
    // Alternating turns between X and O
    // move validation no wrong moves
    // Game end when a row, column, diagonal have same Symbol

    public static enum Symbol {
        X, O, EMPTY
    }

    public interface PlayerFactory {
        public Player createPlayer(Symbol symbol, PlayerStategy playerStategy);

    }

    public static class SimplePlayerFactory implements PlayerFactory {
        public Player createPlayer(Symbol symbol, PlayerStategy playerStategy) {
            return new Player(symbol, playerStategy);
        }

    }

    public static class Player {
        Symbol symbol;
        PlayerStategy playerStategy;

        public Player(Symbol symbol, PlayerStategy playerStategy) {
            this.symbol = symbol;
            this.playerStategy = playerStategy;
        }

        public Position makeMove(Board board) {
            return playerStategy.makeMove(board);
        }

    }

    public interface Listener {
        public void onMove(Board board);

    }

    public interface Publisher {
        public void addListener(Listener listener);

        public void removeListener(Listener listener);

        public void notifyListeners();

    }

    public static class GameEventListener implements Listener {

        @Override
        public void onMove(Board board) {
            board.printBoard();
        }

    }

    public static class Board implements Publisher {
        int rows, cols;
        private Symbol[][] grid;
        List<Listener> listeners;

        public Board(int rows, int cols) {
            this.rows = rows;
            this.cols = cols;
            grid = new Symbol[rows][cols];
            for (Symbol[] row : grid) {
                Arrays.fill(row, Symbol.EMPTY);
            }
            listeners = new ArrayList<>();

        }

        public boolean validateMove(Position move) {
            if (move.getRow() < 0 || move.getColumn() < 0 || move.getRow() >= rows || move.getColumn() >= rows)
                return false;
            if (grid[move.getRow()][move.getColumn()] != Symbol.EMPTY)
                return false;
            return true;
        }

        public void printBoard() {
            for (Symbol[] row : grid) {
                System.out.println(Arrays.toString(row));
            }
        }

        @Override
        public void addListener(Listener listener) {
            listeners.add(listener);
        }

        @Override
        public void removeListener(Listener listener) {
            listeners.remove(listener);
        }

        @Override
        public void notifyListeners() {
            for (Listener listener : listeners) {
                listener.onMove(this);
            }
        }

        public void makeMove(Position move, Symbol symbol) {
            grid[move.getRow()][move.getColumn()] = symbol;
            notifyListeners();
        }
        
        // public void checkState(GameContext gameContext, Player player){
        //     board.for()
        // }
    }

    public interface GameState {
        public void next(GameContext ctx, Player player, boolean hasWon);
        public boolean isGameOver();

    }

    // public static class InProgressState implements GameState {

    //     @Override
    //     public boolean isGameOver() {
    //         return false;
    //     }

    //     @Override
    //     public void next(GameContext ctx, Player player, boolean hasWon) {
    //         ctx.setGameState(ctx);
    //     }

    // }

    public static class XTurnState implements GameState {

        @Override
        public boolean isGameOver() {
            return false;
        }

        @Override
        public void next(GameContext ctx, Player player, boolean hasWon) {
            if (hasWon) ctx.setGameState(new OWonState());
            else ctx.setGameState(new OTurnState());
        }

    }

    public static class OTurnState implements GameState {

        @Override
        public boolean isGameOver() {
            return false;
        }

        @Override
        public void next(GameContext ctx, Player player, boolean hasWon) {
            if (hasWon) ctx.setGameState(new XWonState());
            else ctx.setGameState(new XTurnState());
        }

    }

    public static class XWonState implements GameState {
        @Override
        public boolean isGameOver() {
            return true;
        }

        @Override
        public void next(GameContext ctx, Player player, boolean hasWon) {
            
        }

    }

    public static class OWonState implements GameState {
        @Override
        public boolean isGameOver() {
            return true;
        }
        @Override
        public void next(GameContext ctx, Player player, boolean hasWon) {
            
        }

    }

    public static class DrawState implements GameState {
        @Override
        public boolean isGameOver() {
            return true;
        }

        @Override
        public void next(GameContext ctx, Player player, boolean hasWon) {
            
        }
    }

    public static class GameContext {
        GameState gameState = new XTurnState();

        public GameState getGameState() {
            return gameState;
        }

        public void setGameState(GameState gameState) {
            this.gameState = gameState;
        }

        public boolean isGameOver() {
            return gameState.isGameOver();
        }

        public void next() {
            gameState.next(this, null, isGameOver());

        }

    }

    public static class Position {
        int row, column;

        public Position(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getColumn() {
            return column;
        }

        public void setColumn(int column) {
            this.column = column;
        }

    }

    public interface PlayerStategy {
        public Position makeMove(Board board);

    }

    public static class HumanPlayerStrategy implements PlayerStategy {
        private Scanner scanner = new Scanner(System.in);


        public HumanPlayerStrategy() {
        }

        @Override
        public Position makeMove(Board board) {
            while (true) {
                try {
                    System.out.println("ENtry pos: r c");
                    int r = scanner.nextInt(), c = scanner.nextInt();
                    Position move = new Position(r, c);
                    if (board.validateMove(move)) {
                        return move;
                    }else{
                        System.out.println("Invalid move");
                    }

                } catch (Exception e) {
                    System.out.println("Invalid move");
                    scanner.nextInt();
                }
            }

        }

    }

    Board board;
    List<Player> players;
    int curentPlayerIndex;
    GameContext gameContext;

    

    public TicTacToe(int size, List<Player> players, PlayerFactory playerFactory, PlayerStategy playerStategy) {
        this.board = new Board(size, size);
        board.addListener(new GameEventListener());
        this.players =  new ArrayList<>(Arrays.asList(playerFactory.createPlayer(Symbol.X, playerStategy), playerFactory.createPlayer(Symbol.O, playerStategy)));
        this.curentPlayerIndex = 0;
        this.gameContext = new GameContext();
    }

    void play(){
        while (!gameContext.isGameOver()) {
            Player curPlayer = players.get(curentPlayerIndex);
            Position move = curPlayer.makeMove(board);
            board.makeMove(move, curPlayer.symbol);
            // board.checkState(gameContext, player)
            curentPlayerIndex = (curentPlayerIndex+1)%players.size();
            
        }
    }



    public static void main(String[] args) {
        TicTacToe game = new TicTacToe(3, null, new SimplePlayerFactory(), new HumanPlayerStrategy());
        game.play();
    }

}
