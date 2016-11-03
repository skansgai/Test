package utill;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yss.viewpagerdemo.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/31.
 */
public class ImageViewPagerAdapter extends PagerAdapter {
    ArrayList<View> list;
    ArrayList<BannerModel> banner;
    public ImageViewPagerAdapter(ArrayList<View> list,ArrayList<BannerModel> banner){
        this.list=list;
        this.banner=banner;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view=list.get(position);
        ImageView  imageView = (ImageView) view.findViewById(R.id.banner_image);
        imageView.setImageResource(banner.get(position).getImgId());
        TextView textview= (TextView) view.findViewById(R.id.banner_title);
        textview.setText(banner.get(position).imageTitle);
        container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));
    }
}
