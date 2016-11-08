package utill;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yss.thememo.R;

import java.util.List;

/**
 * Created by Administrator on 2016/11/7.
 */
public class ThememoAdpater extends BaseAdapter {
    Context context;
    List<ThememoModel> list;
    LayoutInflater layoutInflater;
    public ThememoAdpater(Context context, List<ThememoModel> list){
        this.context=context;
        this.list=list;
        layoutInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView=layoutInflater.inflate(R.layout.thememo_listview_item,null);
        }
        TextView thememoTitle= (TextView) convertView.findViewById(R.id.thememo_title);
        TextView thememoContent= (TextView) convertView.findViewById(R.id.thememo_content);
        TextView thememoTime= (TextView) convertView.findViewById(R.id.thememo_time);
        ThememoModel thememoModel=list.get(position);
        thememoTitle.setText(thememoModel.getThememoTitle());
        thememoContent.setText(thememoModel.getThememoContent());
        thememoTime.setText(thememoModel.getThememoTime());
        return convertView;
    }
}
