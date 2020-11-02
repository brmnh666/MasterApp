package com.m7.imkfsdk.chat.chatrow;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.m7.imkfsdk.chat.ChatActivity;
import com.m7.imkfsdk.chat.holder.BaseHolder;
import com.m7.imkfsdk.chat.holder.CardViewHolder;
import com.m7.imkfsdk.chat.holder.ViewHolderTag;
import com.moor.imkf.model.entity.CardInfo;
import com.moor.imkf.model.entity.FromToMessage;
import com.moor.imkf.model.parser.HttpParser;
import com.ying.administrator.masterappdemo.R;

/**
 * Created by pangw on 2018/6/25.
 */

public class CardRxChatBox extends BaseChatRow {
    public CardRxChatBox(int type) {
        super(type);
    }

    @Override
    public boolean onCreateRowContextMenu(ContextMenu contextMenu, View targetView, FromToMessage detail) {
        return false;
    }

    @Override
    protected void buildChattingData(final Context context, BaseHolder baseHolder, final FromToMessage detail, int position) {
        CardViewHolder holder = (CardViewHolder) baseHolder;
        final FromToMessage message = detail;
        if(message != null) {

            final CardInfo cardInfo = HttpParser.getCardInfo(detail.cardInfo,0);
            if (cardInfo.title==null){
                holder.getRl_chat().setVisibility(View.GONE);
            }else {
                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .placeholder(R.drawable.kf_pic_thumb_bg)
                        .error(R.drawable.kf_image_download_fail_icon);
                Glide.with(context).load(cardInfo.icon)
                        .apply(options)
                        .into(holder.getIcon());

                holder.getMame().setText(cardInfo.name);

                holder.getTitle().setText(cardInfo.title);

                holder.getContent().setText(cardInfo.concent);

                holder.getRelativeLayout().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //商品点击事件
                        try{
                            String str=cardInfo.url;
//                            str=str.replace("http://seller.xigyu.com/product/detail/","");
//                            Intent intent = new Intent(context, GoodsDetailActivity.class);
//                            intent.putExtra("id",str);
//                            context.startActivity(intent);

                        }catch (Exception e){

                        }
                    }
                });

                View.OnClickListener listener = ((ChatActivity) context).getChatAdapter().getOnClickListener();
                ViewHolderTag holderTag = ViewHolderTag.createTag(message, ViewHolderTag.TagType.TAG_SEND_MSG, position);
                holder.getSend().setTag(holderTag);
                holder.getSend().setOnClickListener(listener);
            }


        }
    }

    @Override
    public View buildChatView(LayoutInflater inflater, View convertView) {
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.kf_chat_row_card, null);
            CardViewHolder holder = new CardViewHolder(mRowType);
            convertView.setTag(holder.initBaseHolder(convertView, true));
        }
        return convertView;
    }

    @Override
    public int getChatViewType() {
        return ChatRowType.CARDINFO_ROW_TRANSMIT.ordinal();
    }
}

