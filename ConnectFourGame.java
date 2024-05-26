import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ConnectFourGame {
    private final int ROW_SIZE = 6;
    private final int COL_SIZE = 7;
    private final int DIRECTION_LENGTH = 4;

    public ConnectFourGame() {}

    public boolean checkWin(int row, int col, Circle[][] circles) {
        Color color = (Color) circles[row][col].getFill();
        return checkDirection(row, col, color, 1, 0, circles) || // Horizontal
                checkDirection(row, col, color, 0, 1, circles) || // Vertical
                checkDirection(row, col, color, 1, 1, circles) || // Diagonal \
                checkDirection(row, col, color, 1, -1, circles);  // Diagonal /
    }

    private boolean checkDirection(int row, int col, Color color, int rowDir, int colDir, Circle[][] circles) {
        int count = 1;
        count += countInDirection(row, col, color, rowDir, colDir, circles);
        count += countInDirection(row, col, color, -rowDir, -colDir, circles);
        return count >= DIRECTION_LENGTH;
    }

    private int countInDirection(int row, int col, Color color, int rowDir, int colDir, Circle[][] circles) {
        int count = 0;
        for (int i = 1; i < DIRECTION_LENGTH; i++) {
            int r = row + i * rowDir;
            int c = col + i * colDir;
            if (r >= 0 && r < ROW_SIZE - 1 && c >= 0 && c < COL_SIZE && circles[r][c].getFill() == color) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

}
