package cn.edu.gdmec.android.android_scrollview;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

import cn.edu.gdmec.android.android_scrollview.http.HttpUtils;

import static android.text.Html.fromHtml;

public class MainActivity extends AppCompatActivity {

    private LinearLayout layout;
    private ProgressDialog dialog;
    private final String HTML_PATH = "https://www.lookmw.cn/ganwu/117914.html";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView() {

        dialog = new ProgressDialog(this);
        dialog.setTitle("提示");
        dialog.setMessage("Loading.........");
        layout = (LinearLayout) this.findViewById(R.id.line4);
        new MyTask().execute(HTML_PATH);
      /*  for (int i=0;i<10;i++){
            ImageView imageView = new ImageView(this);

            imageView.setImageDrawable(getResources().getDrawable(R.drawable.bb));
            layout.addView(imageView,i);
        }*/
    }

    class MyTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String result = HttpUtils.sendPostMethod(params[0],"","utf-8");
            return result;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(String strings) {
            super.onPostExecute(strings);
            TextView textView = new TextView(MainActivity.this);
            Spanned spanned = Html.fromHtml(strings);
            //  处理html中超链接的事件
            textView.setMovementMethod(new LinkMovementMethod());
            textView.setText(spanned);
            layout.addView(textView);
            dialog.dismiss();
        }
    }
}
