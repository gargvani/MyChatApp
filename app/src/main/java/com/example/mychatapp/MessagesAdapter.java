package com.example.mychatapp;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MessagesAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<Messages> messagesarraylist;

    int ITEM_SENT=1;
    int ITEM_RECIEVE=2;

    public MessagesAdapter(Context context, ArrayList<Messages> messagesarraylist) {
        this.context = context;
        this.messagesarraylist = messagesarraylist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       if(viewType==ITEM_SENT)
       {
           View view = LayoutInflater.from(context).inflate(R.layout.senderchatlayout,parent,false);
           return new SenderViewHolder(view);
       }
       else

       {
           View view = LayoutInflater.from(context).inflate(R.layout.recieverchatlayout,parent,false);
           return new RecieverViewHolder(view);
       }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Messages messages=messagesarraylist.get(position);
        if(holder.getClass()==SenderViewHolder.class)
        {
            SenderViewHolder viewHolder=(SenderViewHolder)holder;
            viewHolder.textviewmwssage.setText(messages.getMessage());
            viewHolder.timeofmessage.setText(messages.getCurrenttime());
        }
        else
        {
            RecieverViewHolder viewHolder=(RecieverViewHolder) holder;
            viewHolder.textviewmwssage.setText(messages.getMessage());
            viewHolder.timeofmessage.setText(messages.getCurrenttime());
        }


    }

    @Override
    public int getItemViewType(int position) {
       Messages messages=messagesarraylist.get(position);
       if(FirebaseAuth.getInstance().getCurrentUser().getUid().equals(messages.getSenderID()))
       {
           return ITEM_SENT;
       }
       else
       {
           return ITEM_RECIEVE;
       }
    }

    @Override
    public int getItemCount() {
        return messagesarraylist.size();
    }







    class SenderViewHolder extends RecyclerView.ViewHolder
    {
        TextView textviewmwssage;
        TextView timeofmessage;

        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            textviewmwssage=itemView.findViewById(R.id.sendermessg);
            timeofmessage=itemView.findViewById(R.id.timeodmssg);
        }
    }

    class RecieverViewHolder extends RecyclerView.ViewHolder
    {
        TextView textviewmwssage;
        TextView timeofmessage;

        public RecieverViewHolder(@NonNull View itemView) {
            super(itemView);
            textviewmwssage=itemView.findViewById(R.id.sendermessg);
            timeofmessage=itemView.findViewById(R.id.timeodmssg);
        }
    }

}
