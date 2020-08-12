package com.sufeng.algorithm;

public class Solution1 {
    public static void main(String[] args) {
        new Solution1().test();
    }
    /**
     * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，
     * 每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。
     *
     * 例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
     *
     * [["a","b","c","e"],
     * ["s","f","c","s"],
     * ["a","d","e","e"]]
     *
     * 但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
     *
     * 输入：
     * board =    [["A","B","C","E"],
     *             ["S","F","C","S"],
     *             ["A","D","E","E"]],
     * word = "ABCCED"
     *
     * 输出：true
     */
    public void test(){
        /*
            ABCE
            SFCS
            ADEE
         */
        char[] matrix = "ABCESFCSADEE".toCharArray();
        char[] str = "ABCCE".toCharArray();
        System.out.println(hasPath(matrix, 3, 4, str));
    }

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str)
    {
        for(int i = 0;i < rows;i++){
            for(int j = 0;j < cols;j++){
                if(dfs(matrix,rows,cols,str,i,j,0))
                    return true;
            }
        }
        return false;
    }

    public boolean dfs(char[] matrix,int rows, int cols, char[] str,int i,int j,int index){
        if(i<0 || i>=rows || j<0 || j>=cols || matrix[i*cols+j] != str[index]){
            return false;
        }
        if(index == str.length-1){
            return true;
        }
        char temp = matrix[i*cols+j];
        matrix[i*cols+j] = '.';
        boolean result = dfs(matrix,rows,cols,str,i+1,j,index+1) || dfs(matrix,rows,cols,str,i-1,j,index+1) || dfs(matrix,rows,cols,str,i,j-1,index+1) || dfs(matrix,rows,cols,str,i,j+1,index+1);
        matrix[i*cols+j] = temp;
        return result;
    }
}
