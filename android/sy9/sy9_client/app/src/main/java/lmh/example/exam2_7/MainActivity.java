package lmh.example.exam2_7;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLUtils;
import android.os.Bundle;
import android.view.View;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends Activity
{
                         //保存开始时间

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // 创建一个GLSurfaceView，用于显示OpenGL绘制的图形

        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chooseIntent = new Intent(Intent.ACTION_SET_WALLPAPER);
                // 启动系统选择应用
                Intent intent = new Intent(Intent.ACTION_CHOOSER);
                intent.putExtra(Intent.EXTRA_INTENT, chooseIntent);
                intent.putExtra(Intent.EXTRA_TITLE, "选择动态壁纸");
                startActivity(intent);
            }
        });
    }


}
