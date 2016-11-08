package thememo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yss.thememo.R;

/**
 * Created by Administrator on 2016/11/7.
 */
public class ThememoAddActivity extends Activity {
    Button thememoAddBtn;
    EditText addContentEdit;
    int count=0;
    String editStr;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thememo_add);
        thememoAddBtn= (Button) findViewById(R.id.add_btn);
        addContentEdit= (EditText) findViewById(R.id.add_content);
        editStr=addContentEdit.getText().toString();
        createSharedPreferences();
        thememoAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                finish();
            }
        });
    }
    public void createSharedPreferences(){
        key=editStr.substring(0,editStr.length());
        SharedPreferences sharedPreferences=getSharedPreferences("Thememo", Context.MODE_WORLD_WRITEABLE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("Data",editStr);
        editor.commit();
    }
}
