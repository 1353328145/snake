package jesing.snake;

import java.awt.*;
import java.util.Random;

public class HandleSnake {
    private int[][]map;
    private int direction;//左0 右1 上2 下3
    private Random random;
    private static int [][]arr={
            {1,0},
            {0,1},
            {-1,0},
            {0,-1},
    };
    private int xLen=20;//调整棋盘大小
    private int yLen=20;
    private int wide;
    private int hight;
    int hx=0,hy=0;

    public int[][] getMap() {
        return map;
    }

    public HandleSnake(){
        this.map=new int[xLen][yLen];
        this.random=new Random();
        this.direction=1;
        map[0][0]=1;
        this.wide=800/xLen;
        this.hight=800/yLen;
        creatFood();
    }
    public void creatFood(){
        int x,y;
        while (map[x=random.nextInt(xLen)][y=random.nextInt(yLen)]!=0);
        map[x][y]=-1;
    }
    public void draw(Graphics g){
        final Color color = g.getColor();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j]==-1){
                    g.setColor(Color.RED);
                    g.fillRect(50+j*hight,50+i*wide,wide,hight);
                    g.setColor(color);
                }else if(i==hx&&j==hy){
                    g.setColor(Color.ORANGE);
                    g.fillRect(50+j*hight,50+i*wide,wide,hight);
                    g.setColor(color);
                }else if (map[i][j]>0){
                    g.setColor(Color.PINK);
                    g.fillRect(50+j*hight,50+i*wide,wide,hight);
                    g.setColor(color);
                }
            }
        }
    }

    public void top() {
        if (direction!=3){
            this.direction=2;
        }
    }

    public void down() {
        if (direction!=2){
            this.direction=3;
        }
    }

    public void left() {
        if (direction!=1){
            this.direction=0;
        }
    }

    public void right() {
        if (direction!=0){
            this.direction=1;
        }
    }

    public boolean go(){
        int nextX=0,nextY=0;
        switch (direction){
            case 1: nextX=hx;nextY=(hy+1)%yLen;
            break;
            case 0: nextX=hx;
                if (hy-1<0){nextY=(hy-1)%yLen+yLen;}else{
                    nextY=hy-1;
                }
                break;
            case 2:
                if (hx-1<0){nextX=(hx-1)%xLen+xLen;}else{
                    nextX=hx-1;
                }
                nextY=hy;
                break;
            case 3: nextX=(hx+1)%xLen;nextY=hy;
            break;
        }
        if (map[nextX][nextY]==-1){
            map[nextX][nextY]=map[hx][hy]+1;
            hx=nextX;
            hy=nextY;
            creatFood();
        }else if (map[nextX][nextY]>0){
            return false;
        }else{
            map[nextX][nextY]=map[hx][hy];
            dfs(hx,hy);
            hx=nextX;
            hy=nextY;
        }

        return true;
    }
    public void dfs(int x,int y){
        map[x][y]--;
        for (int i = 0; i < arr.length; i++) {
            int xx=(x+arr[i][0])%xLen;
            int yy=(y+arr[i][1])%yLen;
            if (xx<0){xx+=xLen;}
            if (yy<0){yy+=yLen;}
            if (map[xx][yy]!=0&&map[xx][yy]==map[x][y]){
                dfs(xx,yy);
            }
        }
    }
}
