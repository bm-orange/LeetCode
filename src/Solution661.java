/**
 * 661. Image Smoother
 * Created by man on 2018/11/1.
 */
public class Solution661 {
    /*
    描述:
    Given a 2D integer matrix M representing the gray
    scale of an image, you need to design a smoother to
    make the gray scale of each cell becomes the average
    gray scale (rounding down) of all the 8 surrounding
    cells and itself. If a cell has less than 8 surrounding
    cells, then use as many as you can.

    测试用例:
    Example1
    Input:
    [[1,1,1],
     [1,0,1],
     [1,1,1]]
    Output:
    [[0, 0, 0],
     [0, 0, 0],
     [0, 0, 0]]
    Explanation:
    For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
    For the point (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
    For the point (1,1): floor(8/9) = floor(0.88888889) = 0

    Note:
    + The value in the given matrix is in the range of [0, 255].
    + The length and width of the given matrix are in the range of [1, 150].

     */

    public int[][] imageSmoother(int[][] M) {
        /*
        许多代码使用了位掩码bitmask
        bitmask = new int[][] {{-1, 0, 1}, {-1, 0, 1}}
         */
        int[][] smootherResult = new int[M.length][M[0].length];
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                smootherResult[i][j] = averageValue(i, j, M);
            }
        }
        return smootherResult;
    }

    public int averageValue(int r, int c, int[][] M) {
        int sum = 0;
        int cnt = 0;
        int rConstraintDown = r - 1 >= 0 ? r - 1 : r;
        int rConstraintUp = r + 1 < M.length ? r + 1 : r;
        int cConstraintDown = c - 1 >= 0 ? c - 1 : c;
        int cConstraintUp = c + 1 < M[0].length ? c + 1 : c;
        for (int i = rConstraintDown; i <= rConstraintUp; i++) {
            for (int j = cConstraintDown; j <= cConstraintUp; j++) {
                sum += M[i][j];
                cnt++;
            }
        }

        return Math.floorDiv(sum, cnt);
    }
}
