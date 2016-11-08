package view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by yang on 2016/11/5.
 */
public class HandPassword extends View {
    //选中点的数量
    private static  final int POINT_SIZE=5;
    //画笔
    Paint paint;
    //矩阵对象
    private Matrix matrix=new Matrix();
    //九个点的数组
    private Point[][] points=new Point[3][3];
    //监听器
    private OnPatterChengeListner onPatterChengeListner;
    //状态
    boolean isInit,isSelecte,isFinsh,moveNoPoint;
    private float screenWidth,screenHight;
    private float offsetX,offsetY,moveX,moveY;
    private Bitmap circleBlue,circleRed,circleGreen,lineBlue,lineRed;
    int bitMapR;
    //点亮点的集合
    private List<Point> pointList=new ArrayList<Point>();
    public HandPassword(Context context) {
        super(context);
    }

    public HandPassword(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HandPassword(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public HandPassword(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(!isInit){
            initPoints();
        }
        points2Canvas(canvas);
        if (pointList.size()>0){
            Point a=pointList.get(0);
            //绘制九宫格里面的点
            for (int i=0;i<pointList.size();i++){
                Point b=pointList.get(i);
                line2Canvas(canvas,a,b);
                a=b;
            }
            //绘制九宫格外的点
            if (moveNoPoint){
                line2Canvas(canvas,a,new Point(moveX,moveY));
            }
        }
    }
    //初始化方法
    private void points2Canvas(Canvas canvas) {
        for (int i=0;i<points.length;i++){
            for (int j=0;j<points[i].length;j++){
               Point point= points[i][j];
                if (point.state==Point.STATE_PRESS){
                    canvas.drawBitmap(circleBlue,point.x-bitMapR,point.y-bitMapR,paint);
                }else if (point.state==Point.STATE_ERROR){
                    canvas.drawBitmap(circleRed,point.x-bitMapR,point.y-bitMapR,paint);
                }else {
                    canvas.drawBitmap(circleGreen,point.x-bitMapR,point.y-bitMapR,paint);
                }
            }
        }
    }
    //画点方法
    private void initPoints() {
        //获取宽高
        screenWidth=getWidth();
        screenHight=getHeight();
        //判断横竖屏幕
        if (screenWidth>screenHight){
             //横屏
            offsetX=(screenWidth-screenHight)/2;
            screenWidth=screenHight;
        }else {
            //竖屏
            offsetY=(screenHight-screenWidth)/2;
            screenHight=screenWidth;
        }
        circleBlue=BitmapFactory.decodeResource(getResources(), R.drawable.press);
        circleGreen=BitmapFactory.decodeResource(getResources(), R.drawable.nomal);
        circleRed=BitmapFactory.decodeResource(getResources(),  R.drawable.error);
        lineBlue=BitmapFactory.decodeResource(getResources(), R.drawable.normal_line);
        lineRed=BitmapFactory.decodeResource(getResources(),  R.drawable.error_line);
        //点的坐标

        points[0][0]=new Point(offsetX+screenWidth/4,offsetY+screenHight/4);
        points[0][1]=new Point(offsetX+screenWidth/2,offsetY+screenHight/4);
        points[0][2]=new Point(offsetX+screenWidth-screenWidth/4,offsetY+screenHight/4);

        points[1][0]=new Point(offsetX+screenWidth/4,offsetY+screenWidth/2);
        points[1][1]=new Point(offsetX+screenWidth/2,offsetY+screenWidth/2);
        points[1][2]=new Point(offsetX+screenWidth-screenWidth/4,offsetY+screenWidth/2);

        points[2][0]=new Point(offsetX+screenWidth/4,offsetY+screenWidth-screenWidth/4);
        points[2][1]=new Point(offsetX+screenWidth/2,offsetY+screenWidth-screenWidth/4);
        points[2][2]=new Point(offsetX+screenWidth-screenWidth/4,offsetY+screenWidth-screenWidth/4);
            //资源半径
            bitMapR = circleRed.getHeight() / 2;
            //设置密码
        int index=1;
            for(Point[] points : this.points){
                for (Point point : points){
                    point.index=index;
                    index++;
                }
            }
            //设置初始化标志位
            isInit=true;
    }
    //画线
    private void line2Canvas(Canvas canvas,Point a,Point b){
        //线长度
        float lineLength= (float) Point.distance(a,b);
        float degrees=getDegrees(a,b);
        canvas.rotate(degrees,a.x,a.y);
        if (a.state==Point.STATE_PRESS){
            //缩放比例
            matrix.setScale(lineLength/lineBlue.getWidth(),1);
            matrix.postTranslate(a.x-lineBlue.getWidth()/2,a.y-lineBlue.getHeight()/2);//缩放
                canvas.drawBitmap(lineBlue,matrix,paint);
        }else {
            matrix.setScale(lineLength/lineBlue.getWidth(),1);
            matrix.postTranslate(a.x-lineBlue.getWidth()/2,a.y-lineBlue.getHeight()/2);//缩放
            canvas.drawBitmap(lineRed,matrix,paint);
        }
        canvas.rotate(-degrees,a.x,a.y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        moveNoPoint=false;
        isFinsh=false;
        //鼠标的坐标
        moveX=event.getX();
        moveY=event.getY();
        Point point=null;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (onPatterChengeListner!=null){
                    onPatterChengeListner.onPatterStart(true);
                }
                reSetPoint();
                point=checkSelectePoint();
                if (point!=null){
                isSelecte=true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (isSelecte){
                    point=checkSelectePoint();
                    if (point==null){
                        moveNoPoint=true;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                isFinsh=true;
                isSelecte=false;
                break;
        }
        //绘制没有结束，重复检查
        if (!isFinsh && isSelecte && point!=null){
            if (crossPoint(point)){
                moveNoPoint=true;
            }else {
                //新点
                point.state=Point.STATE_PRESS;
                pointList.add(point);
            }
        }
        //检查绘制结束
        if (isFinsh){
            //绘制不成立
            if (pointList.size()==1){
                reSetPoint();
                //绘制错误
            }else if (pointList.size()<5&&pointList.size()>0){
                errorPoint();
                if (onPatterChengeListner!=null){
                    onPatterChengeListner.onPatterChenge(null);
                }
            }else {
                //绘制成功
                if (onPatterChengeListner!=null){
                    String passwordStr="";
                    for (int i=0;i<pointList.size();i++){
                        passwordStr=passwordStr+pointList.get(i).index;
                    }
                    if (!TextUtils.isEmpty(passwordStr)){
                        onPatterChengeListner.onPatterChenge(passwordStr);
                    }
                }
            }
        }
        postInvalidate();
        return true;
    }
    //交叉点判断
    private boolean crossPoint(Point point){
        if (pointList.contains(point)){
            return true;
        }else {
           return false;
        }
    }
//绘制不成立清空集合
    public void reSetPoint(){
        for (int i=0;i<pointList.size();i++){
            Point point =pointList.get(i);
            point.state=Point.STATE_NORMAL;
        }
        pointList.clear();
    }
    public void errorPoint(){
        for (Point point: pointList){
            point.state=Point.STATE_ERROR;
        }
    }
    private Point checkSelectePoint(){
        for (int i=0;i<points.length;i++){
            for (int j=0;j<points[i].length;j++){
                Point point=points[i][j];
                if (Point.with(point.x,point.y,moveX,moveY,bitMapR)){
                    return point;
                }
            }
        }
        return  null;
    }
    public static class Point{
        //正常
        public  static int STATE_NORMAL=0;
        //选中
        public  static int STATE_PRESS=1;
        //错误
        public static int STATE_ERROR=2;
        float x,y;
        public int index=0,state=0;
        public Point(){};
        public Point(float offsetX, float offsetY) {
            this.x=offsetX;
            this.y=offsetY;
        }
        /*两点距离*/
        public static double distance(Point a,Point b){
            return Math.sqrt(Math.abs(a.x -b.x)*Math.abs(a.x -b.x)+Math.abs(a.y -b.y)*Math.abs(a.y -b.y));
        }
        /*判断是否重合*/
        public static boolean with(float pointX,float pointY,float moveX,float moveY,float r){
            return Math.sqrt((pointX-moveX)*(pointX-moveX)+(pointY-moveY)*(pointY-moveY))<r;
        }
    }
    public float getDegrees ( Point a , Point b ) {
        float degrees = 0;
        float ax = a.x;
        float ay = a.y;
        float bx = b.x;
        float by = b.y;
        if ( ax == bx ) {
            if ( by > ay ) {
                degrees = 90;
            }
            else {
                degrees = 270;
            }
        }
        else if ( by == ay ) {
            if ( ax > bx ) {
                degrees = 180;
            }
            else {
                degrees = 0;
            }
        }
        else {
            if ( ax > bx ) {
                if ( ay > by ) {// 第三象限
                    degrees = 180 + ( float ) ( Math.atan2( ay - by , ax - bx ) * 180 / Math.PI );
                }
                else {// 第二象限
                    degrees = 180 - ( float ) ( Math.atan2( by - ay , ax - bx ) * 180 / Math.PI );
                }
            }
            else {
                if ( ay > by ) {// 第四象限
                    degrees = 360 - ( float ) ( Math.atan2( ay - by , bx - ax ) * 180 / Math.PI );
                }
                else {// 第一象限
                    degrees = ( float ) ( Math.atan2( by - ay , bx - ax ) * 180 / Math.PI );
                }
            }
        }
        return degrees;

    }
    public static  interface OnPatterChengeListner{
        void onPatterChenge(String passwordStr);
        void onPatterStart(boolean isStart);
    }
    public void setPatterChangeListener(OnPatterChengeListner onPatterChengeListner){
            if (onPatterChengeListner!=null){
                this.onPatterChengeListner=onPatterChengeListner;
            }
    }
}
