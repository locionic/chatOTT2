package com.example.chatott2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chatott2.MessageActivity;
import com.example.chatott2.Model.Chat;
import com.example.chatott2.Model.User;
import com.example.chatott2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;
    private Context mContext;
    private List<Chat> mChats;
    private String imageurl;
    FirebaseUser firebaseUser;
    public MessageAdapter(Context mContext, List<Chat> mChats, String imageurl){
        this.mContext = mContext;
        this.mChats = mChats;
        this.imageurl = imageurl;
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(i == MSG_TYPE_LEFT){
            View v = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, viewGroup, false);
            return new MessageAdapter.ViewHolder(v);
        }else{
            View v = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, viewGroup, false);
            return new MessageAdapter.ViewHolder(v);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder viewHolder, int i) {
        Chat chat = mChats.get(i);
        viewHolder.show_message.setText(chat.getMessage());
        if(imageurl.equals("default"))
            viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
        else
            Glide.with(mContext).load(imageurl).into(viewHolder.imageView);
        if (i == mChats.size()-1) {
            if (chat.isIsseen())
                viewHolder.txt_seen.setText("Seen");
            else
                viewHolder.txt_seen.setText("Delivered");
        }else
            viewHolder.txt_seen.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return mChats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView show_message;
        public ImageView imageView;
        public TextView txt_seen;
        public ViewHolder(View v){
            super(v);
            show_message = v.findViewById(R.id.show_message);
            imageView = v.findViewById(R.id.profile_image);
            txt_seen = v.findViewById(R.id.txt_seen);
        }
    }

    @Override
    public int getItemViewType(int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(mChats.get(position).getSender().equals(firebaseUser.getUid()))
            return MSG_TYPE_RIGHT;
        else
            return MSG_TYPE_LEFT;
    }
}
