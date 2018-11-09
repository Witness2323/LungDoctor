package com.sourcey.materiallogindemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;


public class RealHomeFragment extends Fragment implements OnBannerListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ListView listViewBasic = null;
    private String[] listViewData = new String[]{
            "肺结节新闻","肺结节新闻","肺结节新闻",
            "肺结节新闻","肺结节新闻"
    };

    //private RecyclerView mRecyclerView;

    private List<News> NewsList = new ArrayList<News>();  //创建集合保存水果信息
    //  private ContentAdapter mContentAdapter;
    View v;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

   // private OnFragmentInteractionListener mListener;

    public RealHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RealHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RealHomeFragment newInstance(String param1, String param2) {
        RealHomeFragment fragment = new RealHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_real_home2, container, false);
        final List<String> images = new ArrayList<>();
        images.add("https://img.chinatimes.com/newsphoto/2018-10-30/656/20181030002291.png");
        images.add("https://goss.veer.com/creative/vcg/veer/800water/veer-303833897.jpg");
        images.add("https://goss.veer.com/creative/vcg/veer/800water/veer-157010965.jpg");
        images.add("https://goss.veer.com/creative/vcg/veer/800water/veer-150082536.jpg");
        images.add("https://goss.veer.com/creative/vcg/veer/800water/veer-108666928.jpg");

        final List<String> titles= new ArrayList<>();
        titles.add("肺部有节结就是肺癌？医师：危险度看这三种尺寸"); titles.add("沪首家肺部结节中西医结合MDT诊疗中心在曙光医院成立"); titles.add("点内创始人葛亮：筛查与鉴别诊断后，AI 肺结节产品的下一步是什么？"); titles.add("【健康科普】不重视肺部小结节？变成恶性肿瘤后悔都来不及！"); titles.add("体检查出肺结节莫惊慌 专家提醒：它≠肺癌但需警惕");
        Banner banner = (Banner) v.findViewById(R.id.banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.setOnBannerListener(this);
        banner.start();

        listViewBasic = (ListView)v.findViewById(R.id.list_view);

        //设置listview中的内容
     //  listViewBasic.setAdapter(new ArrayAdapter<String>(v.getContext(),android.R.layout.simple_list_item_1,listViewData));
        getData();
       NewsAdapter fruitAdapter = new NewsAdapter(v.getContext(),
                R.layout.item_news, NewsList);          //关联数据和子布局
        listViewBasic.setAdapter(fruitAdapter);                   //绑定数据和适配器
        /*lvFruits.setOnItemClickListener(new OnItemClickListener() {  //点击每一行的点击事件

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position,
                                    long id) {
                Fruit fruit=fruitList.get(position);         //获取点击的那一行
                Toast.makeText(MainActivity.this,fruit.getImageName(),Toast.LENGTH_LONG).show();//使用吐司输出点击那行水果的名字
            }
        });*/


       //banner.setOnBannerListener(v.getContext());





   /*     BannerLayout bannerLayout = (BannerLayout) v.findViewById(R.id.banner);
        if(Build.VERSION.SDK_INT >= 11) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
        }

        final List<String> urls = new ArrayList<>();
        urls.add("https://goss.veer.com/creative/vcg/veer/800water/veer-108666928.jpg");
        urls.add("https://goss.veer.com/creative/vcg/veer/800water/veer-108666928.jpg");
        urls.add("https://goss.veer.com/creative/vcg/veer/800water/veer-108666928.jpg");
        urls.add("https://goss.veer.com/creative/vcg/veer/800water/veer-108666928.jpg");
        urls.add("https://goss.veer.com/creative/vcg/veer/800water/veer-108666928.jpg");
        bannerLayout.setViewUrls(urls);

//设置加载器
        bannerLayout.setImageLoader(new GlideImageLoader());


//添加点击监听
        bannerLayout.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(v.getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });*/








       // mContentLv = v.findViewById(R.id.lv_content);
     //   mEngine = App.getInstance().getEngine();
  //      initListView();


    //    loadBannerData();
     //   loadContentData();
    /*    mContentBanner = v.findViewById(R.id.banner);
        mContentBanner.setDelegate(new BGABanner.Delegate<CardView, String>() {
            @Override
            public void onBannerItemClick(BGABanner banner, CardView itemView, String model, int position) {
                Toast.makeText(banner.getContext(), "点击了第" + (position + 1) + "页", Toast.LENGTH_SHORT).show();
            }
        });
        mContentBanner.setAdapter(new BGABanner.Adapter<CardView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, CardView itemView, String model, int position) {
                SimpleDraweeView simpleDraweeView = itemView.findViewById(R.id.sdv_item_fresco_content);
                simpleDraweeView.setImageURI(Uri.parse(model));
            }
        });

        App.getInstance().getEngine().fetchItemsWithItemCount(5).enqueue(new Callback<BannerModel>() {
            @Override
            public void onResponse(Call<BannerModel> call, Response<BannerModel> response) {
                BannerModel bannerModel = response.body();
//                mContentBanner.setData(R.layout.item_fresco, bannerModel.imgs, bannerModel.tips);
                mContentBanner.setData(R.layout.item_fresco, bannerModel.imgs, null);
            }

            @Override
            public void onFailure(Call<BannerModel> call, Throwable t) {
                Toast.makeText(App.getInstance(), "网络数据加载失败", Toast.LENGTH_SHORT).show();
            }
        });*/

        return v;
    }
    private void getData() {
        int[] imageIds = { R.drawable.news1, R.drawable.news2 };
        String[] newstitles = { "拿什么来对付你，我的小结节","体检发现肺部小结节勿慌！并不等于肺癌" };
        String[] neirongs={"随着低剂量CT的普及，以及雾霾的加重，现在发现肺部小结节及磨玻璃影的患者越来……","有人形容体检是“不查不放心，查了更担心”。近些年，肺部结节检出率越来越高的原因在于……"};
        String[] newstime={"2018-08-01","2018-08-22"};
        for(int i=0;i<imageIds.length;i++){                  //将数据添加到集合中
            NewsList.add(new News(imageIds[i],newstitles[i],neirongs[i],newstime[i]));  //将图片id和对应的name存储到一起
        }
    }
    @Override
    public void OnBannerClick(int position) {
        switch(position){
            case 0:
                Intent intent1 = new Intent();
                intent1.setData(Uri.parse("https://www.chinatimes.com/cn/realtimenews/20181030002247-260405"));
                intent1.setAction(Intent.ACTION_VIEW);
                RealHomeFragment.this.startActivity(intent1); //启动浏览器
                break;
            case 1:
                Intent intent2 = new Intent();
                intent2.setData(Uri.parse("http://news.sina.com.cn/o/2018-10-21/doc-ifxeuwws6513198.shtml"));
                intent2.setAction(Intent.ACTION_VIEW);
                RealHomeFragment.this.startActivity(intent2); //启动浏览器
                break;
            case 2:
                Intent intent3 = new Intent();
                intent3.setData(Uri.parse("https://baijiahao.baidu.com/s?id=1614541309006332154&wfr=spider&for=pc"));
                intent3.setAction(Intent.ACTION_VIEW);
                RealHomeFragment.this.startActivity(intent3); //启动浏览器
                break;
            case 3:
                Intent intent4 = new Intent();
                intent4.setData(Uri.parse("https://baijiahao.baidu.com/s?id=1614541309006332154&wfr=spider&for=pc"));
                intent4.setAction(Intent.ACTION_VIEW);
                RealHomeFragment.this.startActivity(intent4); //启动浏览器
                break;
            case 4:
                Intent intent5 = new Intent();
                intent5.setData(Uri.parse("https://js.ifeng.com/a/20180723/6745888_0.shtml"));
                intent5.setAction(Intent.ACTION_VIEW);
                RealHomeFragment.this.startActivity(intent5); //启动浏览器
                break;

        }

    }



    /**
     * 初始化ListView
     */
    /*
    private void initListView() {
        // 初始化HeaderView
        View headerView = View.inflate(v.getContext(), R.layout.layout_header, null);
        mBanner = headerView.findViewById(R.id.banner);
        mBanner.setAdapter(this);
        mBanner.setDelegate(this);

        // 初始化ListView
        mContentLv.addHeaderView(headerView);
        mContentAdapter = new ContentAdapter(v.getContext());
        mContentLv.setAdapter(mContentAdapter);
    }

    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
        Glide.with(this)
                .load(model)
                .apply(new RequestOptions().placeholder(R.drawable.holder).error(R.drawable.holder).dontAnimate().centerCrop())
                .into(itemView);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, ImageView imageView, String model, int position) {
        Toast.makeText(v.getContext(), "点击了第" + (position + 1) + "页", Toast.LENGTH_SHORT).show();
    }

    /**
     * 加载头部广告条的数据
     */
    /*
    private void loadBannerData() {
        mEngine.fetchItemsWithItemCount(5).enqueue(new Callback<BannerModel>() {
            @Override
            public void onResponse(Call<BannerModel> call, Response<BannerModel> response) {
                BannerModel bannerModel = response.body();
                mBanner.setData(bannerModel.imgs, bannerModel.tips);
            }

            @Override
            public void onFailure(Call<BannerModel> call, Throwable t) {
                Toast.makeText(App.getInstance(), "加载广告条数据失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 加载内容列表数据
     */
   /* private void loadContentData() {
        mEngine.loadContentData("http://bgashare.bingoogolapple.cn/refreshlayout/api/defaultdata.json").enqueue(new Callback<List<RefreshModel>>() {
            @Override
            public void onResponse(Call<List<RefreshModel>> call, Response<List<RefreshModel>> response) {
                mContentAdapter.setData(response.body());
            }

            @Override
            public void onFailure(Call<List<RefreshModel>> call, Throwable t) {
                Toast.makeText(App.getInstance(), "加载内容数据失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class ContentAdapter extends BGAAdapterViewAdapter<RefreshModel> {

        public ContentAdapter(Context context) {
            super(context, R.layout.item_normal);
        }

        @Override
        protected void fillData(BGAViewHolderHelper helper, int position, RefreshModel model) {
            helper.setText(R.id.tv_item_normal_title, model.title).setText(R.id.tv_item_normal_detail, model.detail);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
  /*  public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
