package com.sourcey.materiallogindemo;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by Belal on 1/23/2018.
 */

public class HelpFragment extends Fragment {
    private ListView lv;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_layout, container, false);
        lv = (ListView) view.findViewById(R.id.tab_listview);	//实例化

        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new SimpleAdapter(getActivity(), getData(), R.layout.tab_listview_item,
                new String[]{"img", "title"},
                new int[]{R.id.itemimg, R.id.itemtitle});      //配置适配器，并获取对应Item中的ID
        lv.setAdapter(adapter);
               lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

               // Toast.makeText(getActivity(), String.valueOf(arg2), Toast.LENGTH_LONG).show();
                switch (arg2) {
                    case 0:
                        Toast.makeText(getActivity(), "1", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(getActivity(),AboutUs_Activity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Toast.makeText(getActivity(), "2", Toast.LENGTH_LONG).show();
                        Intent intent2=new Intent(getActivity(),About_use_Activity.class);
                        startActivity(intent2);
                        break;
                    case 2:
                        Intent intent1 = new Intent();
                        intent1.setData(Uri.parse("https://baike.baidu.com/item/%E8%82%BA%E7%BB%93%E8%8A%82/8554561?fr=aladdin"));
                        intent1.setAction(Intent.ACTION_VIEW);
                        HelpFragment.this.startActivity(intent1); //启动浏览器

                        Toast.makeText(getActivity(), "3", Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Intent intent3=new Intent(getActivity(),FeedBcakActivity.class);
                        startActivity(intent3);
                    default:
                        break;
                }
            }
        });
}



    //数据的获取@！
    private List<? extends Map<String, ?>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

//将需要的值传入map中
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "关于我们");

        map.put("img", R.drawable.ic_menu_18pt);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "关于软件");

        map.put("img", R.drawable.ic_shouye);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "肺结节常识");

        map.put("img", R.drawable.ic_add);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "我要反馈");

        map.put("img", R.drawable.ic_help );
        list.add(map);

        return list;
    }
    /*@Override
    public void onItemWidgetClickListener(View v) {
        //item上有多个可点击的控件时，根据控件id找到相应事件（这里只有一个控件）
        switch (v.getId()) {
            case R.id.tab_listview:

                break;

            default:
                break;
        }
    }*/



}
