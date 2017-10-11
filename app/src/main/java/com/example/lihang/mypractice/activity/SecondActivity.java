package com.example.lihang.mypractice.activity;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lihang.mypractice.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(R.style.CrazyTheme);
        setContentView(R.layout.activity_second);

        context=SecondActivity.this;

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.btn_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder sb=new StringBuilder("");
                XmlResourceParser xrp=getResources().getXml(R.xml.books);
                try{

                    while (xrp.getEventType()!= XmlPullParser.END_DOCUMENT){

                        if(xrp.getEventType()==XmlPullParser.START_TAG){
                            String tagName=xrp.getName();
                            if(tagName.equals("book")){
                                String bookPrice=xrp.getAttributeValue(0);
                                sb.append("价格：");
                                sb.append(bookPrice);
                                sb.append("\t");
                                sb.append("出版日期：");
                                String bookDate=xrp.getAttributeValue(1);
                                sb.append(bookDate+"\t");
                                sb.append("书名：");
                                sb.append(xrp.nextText());
                            }
                            sb.append("\n\n");
                        }
                        xrp.next();
                    }
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//
                EditText et= (EditText) findViewById(R.id.et_books);
                et.setText(sb.toString());

                Toast.makeText(SecondActivity.this,"sdf",Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

        }
        return super.onOptionsItemSelected(item);
    }
}
