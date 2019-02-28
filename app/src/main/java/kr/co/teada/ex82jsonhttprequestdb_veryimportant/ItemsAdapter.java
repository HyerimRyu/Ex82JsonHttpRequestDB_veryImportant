package kr.co.teada.ex82jsonhttprequestdb_veryimportant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter {

    ArrayList<ItemsVO> items;
    Context context;

    //alt + insert
    public ItemsAdapter(ArrayList<ItemsVO> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        //int viewType 는 아이템 하나하나 모양 다르게 주고 싶을 때 쓰는거야

        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.recycler_items, viewGroup, false);

        VH holder=new VH(itemView);
        return holder;

    }//end of onCreateViewHolder

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        //값들 연결
        //매개변수로 뷰홀더 오는걔가 위에서 리턴시킨 홀더야.
        VH vh= (VH)viewHolder;

        //연결 시작
        //현재 번째(position 번째:끝에 매개변수)
        ItemsVO item=items.get(position);

        vh.tvName.setText(item.getName());
        vh.tvMsg.setText(item.getMsg());
        vh.tvDate.setText(item.getDate());

        //이미지는 라이브러리 쓰자 : glide
        Glide.with(context).load(item.getFilePath()).into(vh.iv);

    }//end of onBindViewHolder

    @Override
    public int getItemCount() {
        return items.size();
    }//end of getItemCount


    //이너클래스 : ViewHolder 클래스 설계
    class VH extends RecyclerView.ViewHolder{

        TextView tvName;
        TextView tvMsg;
        TextView tvDate;
        ImageView iv;

        public VH(@NonNull View itemView) {
            super(itemView);

            tvName=itemView.findViewById(R.id.tv_name);
            tvMsg=itemView.findViewById(R.id.tv_msg);
            tvDate=itemView.findViewById(R.id.tv_date);
            iv=itemView.findViewById(R.id.iv);
        }
    }//end of VH




}//end of ItemsAdapter
