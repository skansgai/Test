package bitmap;

import android.Manifest;
import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;

import com.yss.viewpagerdemo.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/11/7.
 */
public class BitMapActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
        checkUserPremission();
        createBitmap();
    }
    //创建BitMap对象
    public void createBitmap(){
        //bitmap是个静态类不能直接new出来，可以通过BitmapFactory创建
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.img2);//将一个资源文件转换成BitMap对象
        Bitmap bitmap1=BitmapFactory.decodeFile(Environment.getDataDirectory()+"/13407624.html");//将一个指定文件路径名的文件转换成BitMap对象
        //将assets文件目录下的文件转换成bitMap对象
        AssetManager assetManager=getAssets();//得到一个assetManage对象
        try {
            InputStream inputStream=assetManager.open("13407624.html");
            Bitmap bitmap2=BitmapFactory.decodeStream(inputStream);//将一个输入流转换成Bitmap对象
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //获取动态权限
    public void checkUserPremission(){
        if (Build.VERSION.SDK_INT>23){
            ActivityCompat.requestPermissions(this,//上下文
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },
                    1001 );//请求码
        }
    }
    //动态权限回调函数
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        createBitmap();
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
