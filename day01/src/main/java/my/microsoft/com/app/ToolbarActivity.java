package my.microsoft.com.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

public class ToolbarActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这是设置ActionBar隐藏的代码方法，一定在布局加载代码之前
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_toolbar);
        toolbar= (Toolbar) this.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("aaa","主图标点击");
            }
        });

        //toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle("title");
        //设置子标题
        toolbar.setSubtitle("Subtitle");
        //设置右侧菜单
        toolbar.inflateMenu(R.menu.toolbar_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item1:
                        Log.i("aaa","搜索1");
                    break;
                    case R.id.item2:
                        Log.i("aaa","搜索2");
                        break;
                    case R.id.item3:
                        Log.i("aaa","搜索3");
                        break;
                    case R.id.item4:
                        Log.i("aaa","搜索4");
                        break;
                }
                return true;
            }
        });


    }
}
