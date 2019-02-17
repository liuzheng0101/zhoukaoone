package com.example.mature.myapplication;

import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class MainActivity extends AppCompatActivity {
    private SimpleDraweeView simpleDraweeView;
    private Button button1,button2,button3,button4,button5,button6,button7,button8;
    private Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simpleDraweeView=findViewById(R.id.sview);
        uri=Uri.parse("http://img5.imgtn.bdimg.com/it/u=161362436,3591415293&fm=26&gp=0.jpg");
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        button4=findViewById(R.id.button4);
        button5=findViewById(R.id.button5);
        button6=findViewById(R.id.button6);
        button7=findViewById(R.id.button7);
        button8=findViewById(R.id.button8);
        //圆角
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RoundingParams roundingParams = RoundingParams.fromCornersRadius(30f);
                //roundingParams.setOverlayColor(Color.RED);
                simpleDraweeView.getHierarchy().setRoundingParams(roundingParams);
            }
        });
        //圆形
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
                //roundingParams.setBorder(Color.RED, 2);
                roundingParams.setRoundAsCircle(true);
                simpleDraweeView.getHierarchy().setRoundingParams(roundingParams);
            }
        });
        //比例1:2
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleDraweeView.setAspectRatio(2f);
                Toast.makeText(MainActivity.this,"比例1:2",Toast.LENGTH_SHORT).show();
            }
        });
        //渐进式
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                        .setProgressiveRenderingEnabled(true)
                        .build();
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setImageRequest(request)
                        .setOldController(simpleDraweeView.getController())
                        .build();
                simpleDraweeView.setController(controller);
            }
        });
        simpleDraweeView.setImageURI(uri);
    }
}
