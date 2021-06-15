package com.kosmo.touchevent19.view;

public class Dot {
    //[터치한 지점의 x,y좌표 저장]
    private int xPos,yPos;
    //그리기 여부 판단용
    private boolean isDrawing;
    public Dot() { }
    public Dot(int xPos, int yPos, boolean isDrawing) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.isDrawing = isDrawing;
    }
    //게터.세터]
    public int getxPos() {
        return xPos;
    }
    public void setxPos(int xPos) {
        this.xPos = xPos;
    }
    public int getyPos() {
        return yPos;
    }
    public void setyPos(int yPos) {
        this.yPos = yPos;
    }
    public boolean isDrawing() {
        return isDrawing;
    }
    public void setDrawing(boolean drawing) {
        isDrawing = drawing;
    }
}
