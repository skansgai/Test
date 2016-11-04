package com.yss.timeviewdemo.zhihudaynews;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.yss.timeviewdemo.R;
import com.yss.timeviewdemo.utill.ZhiHuNewsModel;
import com.yss.timeviewdemo.utill.ZhihuAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/4.
 */
public class ZhiHuActivity extends Activity implements GestureDetector.OnGestureListener {
    View viewHead;
    ViewFlipper viewFlipper;
    ListView listView;
    RadioGroup radioGroup;
    private ArrayList<RadioButton> idlist;
    TextView rightMenu;
    boolean idShow;
    PopupWindow popupWindow;
    boolean viewEvent;
    boolean listviewEvent;
    int[] imgId={R.mipmap.img1,R.mipmap.img2,R.mipmap.img3,R.mipmap.img4,R.mipmap.img5};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhihu_news_listview);
        listView= (ListView) findViewById(R.id.news_listview);
        viewHead=getLayoutInflater().inflate(R.layout.zhihu_daynews_head,null);
        radioGroup= (RadioGroup) viewHead.findViewById(R.id.radio_group);
        rightMenu= (TextView) findViewById(R.id.right_menu);
        rightMenu.setOnClickListener(onClickListener);
        idlist=new ArrayList<RadioButton>();
        radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
        List<ZhiHuNewsModel>  list=createData();
        ZhihuAdapter zhiHuAdapter=new ZhihuAdapter(this,list);
        listView.setAdapter(zhiHuAdapter);
        addImage();
        listView.addHeaderView(viewHead);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.startFlipping();
        //
        viewFlipper.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (v instanceof FrameLayout){
                    int position=viewFlipper.getDisplayedChild();
                    idlist.get(position).setChecked(true);
                }
            }
        });
    }
    public void addImage(){
        viewFlipper= (ViewFlipper) viewHead.findViewById(R.id.viewflipper);
        for(int i=0;i<imgId.length;i++){
            ImageView imageView=createImageView();
            imageView.setImageResource(imgId[i]);
            viewFlipper.addView(imageView);
            RadioButton radioButton=createRadioButton();

            radioButton.setId(i);
            idlist.add(radioButton);
            radioGroup.addView(radioButton);
        }
    }
    public ImageView createImageView(){
        ImageView imageView=new ImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }
    public RadioButton createRadioButton(){
        RadioButton radioButton=new RadioButton(this);
        radioButton.setBackgroundResource(R.drawable.radiobutton);
        radioButton.setButtonDrawable(R.drawable.radiobutton);
        radioButton.setBackground(null);
        radioButton.setPadding(5,0,5,0);
        radioButton.setAlpha(0.5f);
        return radioButton;
    }
    /* @Override
    public boolean onTouchEvent(MotionEvent event) {
         return gestureDetector.onTouchEvent(event);
     }*/
    RadioGroup.OnCheckedChangeListener onCheckedChangeListener=new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            viewFlipper.setDisplayedChild(checkedId);
        }
    };
    public PopupWindow popupWindow(){
        LinearLayout linearLayout=new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                500,300
        ));
        linearLayout.setBackgroundColor(getResources().getColor(android.R.color.white));
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
        );
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        TextView textView1=new TextView(this);
        textView1.setLayoutParams(layoutParams);
        textView1.setPadding(15,15,0,15);
        textView1.setText("夜间模式");
        textView1.setTextColor(getResources().getColor(android.R.color.black));
        textView1.setTextSize(20);

        TextView textView2=new TextView(this);
        textView2.setLayoutParams(layoutParams);
        textView1.setPadding(15,15,0,15);
        textView1.setText("设置选项");
        textView1.setTextColor(getResources().getColor(android.R.color.black));
        textView1.setTextSize(20);
        linearLayout.addView(textView1);
        linearLayout.addView(textView2);
        PopupWindow popupWindow=new PopupWindow(linearLayout,150,230);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        return popupWindow;
    }
    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        switch (v.getId()){
            case R.id.right_menu:
                popupWindow= popupWindow();
                if (idShow){
                    popupWindow.dismiss();
                    idShow=false;
                }else{
                    popupWindow.showAsDropDown(rightMenu);
                    idShow=true;
                }
            break;
        }
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE,0,0,"夜间模式");
        menu.add(Menu.NONE,1,0,"相关设置");
        return super.onCreateOptionsMenu(menu);
    }
    List<ZhiHuNewsModel> list=new ArrayList<ZhiHuNewsModel>();
    public List<ZhiHuNewsModel> createData(){
    for(int i=0;i<20;i++){
    ZhiHuNewsModel zhiHuNewsModel=new ZhiHuNewsModel();
        zhiHuNewsModel.setImg(R.mipmap.img3);
        zhiHuNewsModel.setTitle("confont-国内功能很强大且图标内容很丰富的矢量图标库,提供矢量图标下载、在线存储"+i);
        int r=(int)Math.random();
        if(r==0){
            zhiHuNewsModel.setIsshowImg(true);
        }else if(r==1){
            zhiHuNewsModel.setIsshowImg(false);
        }
        list.add(zhiHuNewsModel);
    }
        return list;
    }
    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e1.getX() - e2.getX() > 120) {
            this.viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right));
            this.viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right));
            this.viewFlipper.showNext();

        } else if (e1.getX() - e2.getX() < -120) {
            this.viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right));
            this.viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right));
            this.viewFlipper.showPrevious();
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if( viewFlipper.onTouchEvent(event)){
            viewEvent=true;
            listviewEvent=false;
        }else{
            listView.onTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }
    public boolean onTouch(View arg0, MotionEvent arg1) {
        System.out.println("Touch::::::::::::::::::::");
        super.onTouchEvent(arg1);
        return viewFlipper.onTouchEvent(arg1);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // 先执行滑屏事件
        viewFlipper.onTouchEvent(ev);
        super.dispatchTouchEvent(ev);
        return viewFlipper.onTouchEvent(ev);
    }
}
