class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int oldcolor=image[sr][sc];
        if(oldcolor==color)
        return image;
        dfs(image,sr,sc,color,oldcolor);
        return image;

    }
    private void dfs(int[][] image,int r,int c,int newColor,int oldcolor)
    {
        if(r<0||c<0|| r>=image.length || c>=image[0].length)
        {
            return ;

        }
        if(image[r][c]!=oldcolor)
        {
            return;
        }
        image[r][c]=newColor;
        dfs(image,r-1,c,newColor,oldcolor);
        dfs(image,r,c-1,newColor,oldcolor);
        dfs(image,r+1,c,newColor,oldcolor);
        dfs(image,r,c+1,newColor,oldcolor);

    }
}