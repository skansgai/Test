package utill;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/31.
 */
public class MyViewPagerAdapter extends PagerAdapter {
    ArrayList<View> arrayList;
    public MyViewPagerAdapter(ArrayList<View> arrayList){
        this.arrayList=arrayList;
    }
    @Override
    public int getCount() {//返回数据源总条数
        return arrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
/*获得每个Item下标*/
    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
/*设置页面宽度*/
   /* @Override
    public float getPageWidth(int position) {
        return 50;
    }*/
/**/
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(arrayList.get(position));
        return arrayList.get(position);
    }
/*销毁*/
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(arrayList.get(position));
    }
}
