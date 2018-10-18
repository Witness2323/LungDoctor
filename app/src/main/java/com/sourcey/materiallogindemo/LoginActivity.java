package com.sourcey.materiallogindemo;

import android.app.ProgressDialog;
import android.content.EntityIterator;
import android.database.DefaultDatabaseErrorHandler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.text.style.AbsoluteSizeSpan;
import android.text.SpannableString;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.text.*;
import android.text.Spanned;
import butterknife.BindView;
import butterknife.ButterKnife;
import android.os.Handler;
import android.os.Message;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import android.*;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private  EditText hint;


    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_login) Button _loginButton;
    @BindView(R.id.btn_login1)Button _loginButton1;
    @BindView(R.id.link_signup) TextView _signupLink;

    public void init(){
        hint= (EditText) findViewById(R.id.input_ID);
        // 新建一个可以添加属性的文本对象
        SpannableString ss = new SpannableString("请输入用户名");
        // 新建一个属性对象,设置文字的大小
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(15,true);
        // 附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 设置hint
        hint.setHint(new SpannedString(ss)); // 一定要进行转换,否则属性会消失
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);



        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                login();

            }
        });
        _loginButton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                //setClass函数的第一个参数是一个Context对象
                //Context是一个类，Activity是Context类的子类，也就是说，所有的Activity对象，都可以向上转型为Context对象
                //setClass函数的第二个参数是一个Class对象，在当前场景下，应该传入需要被启动的Activity类的class对象
                intent.setClass(LoginActivity.this,MainActivity.class);
                startActivity(intent);

            }});

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }


    public static final int SHOW_RESPONSE=1;
    public Handler handler=new Handler() {
        public void handleMessage(Message msg)
        {
            switch (msg.what){
                case SHOW_RESPONSE:
                    String response=(String)msg.obj;

                    Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                    if(response.equals("true")){
                        onLoginSuccess();}else{
                        onLoginFailed();}
                    break;
                default:
                    break;
            }
        }
    };

    public void login() {
        Log.d(TAG, "Login");


        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();


        String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        EditText uid=(EditText)findViewById(R.id.input_ID);
                        EditText pwd=(EditText)findViewById(R.id.input_password);
                        String id=uid.getText().toString().trim();
                        String pw=pwd.getText().toString().trim();
                        SendByHttpClient(id,pw);

                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        Toast.makeText(getBaseContext(), "登录成功！", Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        //setClass函数的第一个参数是一个Context对象
        //Context是一个类，Activity是Context类的子类，也就是说，所有的Activity对象，都可以向上转型为Context对象
        //setClass函数的第二个参数是一个Class对象，在当前场景下，应该传入需要被启动的Activity类的class对象
        intent.setClass(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "登录失败，请核对用户名和密码", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;


        String password = _passwordText.getText().toString();



        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
    public   static  String result;
    public void SendByHttpClient(final String id, final String pw){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    HttpClient httpclient=new DefaultHttpClient();
                    HttpPost httpPost=new HttpPost("http://192.168.191.1:8080/LungDoctor/login");//服务器地址，指向Servlet
                    List<NameValuePair> params=new ArrayList<NameValuePair>();//将id和pw装入list

                    params.add(new BasicNameValuePair("ID",id));
                    params.add(new BasicNameValuePair("PW",pw));
                    final UrlEncodedFormEntity entity=new UrlEncodedFormEntity(params,"utf-8");//以UTF-8格式发送
                    httpPost.setEntity(entity);
                    HttpResponse httpResponse= httpclient.execute(httpPost);
                    if(httpResponse.getStatusLine().getStatusCode()==200)//在200毫秒之内接收到返回值
                    {
                        HttpEntity entity1=httpResponse.getEntity();
                        String response=EntityUtils.toString(entity1, "utf-8");//以UTF-8格式解析
                        result =response;

                        Message message=new Message();
                        message.what=SHOW_RESPONSE;
                        message.obj=response;
                        handler.sendMessage(message);
                        handler.handleMessage(message);//使用Message传递消息给线程
                    }
                    else{
                        result="连接失败";
                        Toast.makeText(getBaseContext(), "连接失败，请检查网络设置！", Toast.LENGTH_LONG).show();
                    }

                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
