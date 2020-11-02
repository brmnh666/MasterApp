package com.m7.imkfsdk.chat.chatrow;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.m7.imkfsdk.chat.ChatActivity;
import com.m7.imkfsdk.chat.holder.BaseHolder;
import com.m7.imkfsdk.chat.holder.RichViewHolder;
import com.moor.imkf.model.entity.CardInfo;
import com.moor.imkf.model.entity.FromToMessage;
import com.moor.imkf.model.parser.HttpParser;
import com.ying.administrator.masterappdemo.R;

/**
 * Created by pangw on 2018/6/26.
 */

public class RichTxChatBox extends BaseChatRow {
    public RichTxChatBox(int type) {
        super(type);
    }

    @Override
    public boolean onCreateRowContextMenu(ContextMenu contextMenu, View targetView, FromToMessage detail) {
        return false;
    }

    @Override
    protected void buildChattingData(final Context context, BaseHolder baseHolder, final FromToMessage detail, int position) {
        RichViewHolder holder = (RichViewHolder) baseHolder;
        final FromToMessage message = detail;
        if(message != null) {
                final CardInfo ci = HttpParser.getCardInfo(message.cardInfo,0);
                holder.getWithdrawTextView().setVisibility(View.GONE);
                holder.getContainer().setVisibility(View.VISIBLE);

                holder.getTitle().setText(ci.title);
                holder.getContent().setText(ci.concent);
                holder.getName().setText(ci.name);

//                if(ci.icon.equals("")||ci.icon==null){
//                    holder.getImageView().setVisibility(View.GONE);
//                }else{
//                    holder.getImageView().setVisibility(View.VISIBLE);
//                }
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.kf_pic_thumb_bg)
                    .error(R.drawable.kf_image_download_fail_icon);
                Glide.with(context).load(ci.icon)
                        .apply(options)
                        .into(holder.getImageView());
                holder.getKf_chat_rich_lin().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
//                            String str=ci.url;
//                            str=str.replace("http://seller.xigyu.com/product/detail/","");
//                            Intent intent = new Intent(context, GoodsDetailActivity.class);
//                            intent.putExtra("id",str);
//                            context.startActivity(intent);

                        }catch (Exception e){

                        }
//                        Toast.makeText(context, "6666666666666666666", Toast.LENGTH_SHORT).show();

                    }
                });
            View.OnClickListener listener = ((ChatActivity) context).getChatAdapter().getOnClickListener();
            getMsgStateResId(position, holder, message, listener);
        }
    }

    @Override
    public View buildChatView(LayoutInflater inflater, View convertView) {
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.kf_chat_row_rich_tx, null);
            RichViewHolder holder = new RichViewHolder(mRowType);
            convertView.setTag(holder.initBaseHolder(convertView, true));
        }
        return convertView;
    }

    @Override
    public int getChatViewType() {
        return ChatRowType.RICHTEXT_ROW_TRANSMIT.ordinal();
    }
}
