package kr.co.teada.ex82jsonhttprequestdb_veryimportant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //아답터 3종 세트
    RecyclerView recyclerView;
    ItemsAdapter itemsAdapter;
    ArrayList<ItemsVO> items=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recycler);
        itemsAdapter=new ItemsAdapter(items, this);
        recyclerView.setAdapter(itemsAdapter);

        //필수!! 리사이클러뷰는 레이아웃 선택 가능 : 리니어(수직, 수평), 그리드, 스택어브(불규칙격자 ex. 핀터레스트)
        //리사이클러뷰의 아이템뷰들 배치 관리자 설정 : LayoutManager
        LinearLayoutManager layoutManager=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

    }//end of onCreate

    public void clickLoadData(View view) {
        //데이터 가져오기 : Volley+ 라이브러리 사용하여 서버의 "loadDBtoJson.php" 파일에 접속하여 결과 받기!
        String serverURl="http://ateat.dothome.co.kr/Android/loadDBtoJson.php";

        //결과를 JSONArray 로 받을거니까 JSONArrayRequest 요청 객체 생성
        //이 객체가 서버에 요청하고 JSONArray 로 결과를 받을꺼야
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(serverURl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //응답을 성공적으로 받았을 때..
                //서버로부터 echo 된 데이터..: 매개변수로 온 JsonArray

                try{

                    items.clear(); //ArrayList 배열의 모든 요소 삭제: 싹 다 지워
                    itemsAdapter.notifyDataSetChanged(); //데이터 바꼈다~

                    for(int i=0; i<response.length(); i++){
                        JSONObject jsonObject=response.getJSONObject(i);

                        String num=jsonObject.getString("num");
                        String name=jsonObject.getString("name");
                        String msg=jsonObject.getString("msg");
                        String filePath=jsonObject.getString("filepath");
                        String date=jsonObject.getString("date");

                        //(이미지) 파일 경로의 경우, 서버 IP 가 제외된 주소(uploads/xxx.jpg)로 전달되어 옴
                        //그래서 바로 사용할 수 없음
                        filePath="http://ateat.dothome.co.kr/Android/"+filePath;

                        //아이템들을 가지고 있는 ArrayList 에 추가
                        //0번 칸에 add 해줘! 새로운게 맨 위로 오게!!                                     ********중요!!
                        items.add(0,new ItemsVO(num, name, msg, filePath, date));
                        //화면갱신
                        itemsAdapter.notifyItemInserted(0);

                    }
                }catch (Exception e){
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //위에서 만든 JsonArrayRequest 요청 객체를 동작되도록 하기 위해 우체통( RequestQueue )에 넣기!!
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);



    }//end of clickLoadData




}//end of  MainActivity
