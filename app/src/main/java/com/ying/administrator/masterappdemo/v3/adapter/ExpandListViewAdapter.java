package com.ying.administrator.masterappdemo.v3.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.Area2;
import com.ying.administrator.masterappdemo.entity.City2;
import com.ying.administrator.masterappdemo.entity.Province2;
import com.ying.administrator.masterappdemo.widget.CommonDialog_Home;

import java.util.List;

/**
 * author:Created by LiangSJ
 * date: 2017/9/7.
 * description:？
 */

public class ExpandListViewAdapter extends BaseExpandableListAdapter {
    private List<Province2> mListData;
    private LayoutInflater mInflate;
    private Context context;
    private int position;
    private CustomExpandableListView lv;

    public ExpandListViewAdapter(List<Province2> mListData, Context context) {
        this.mListData = mListData;
        this.context = context;
        this.mInflate = LayoutInflater.from(context);
    }

    public void addAllChild(int position, List<City2> mchild) {
//        group.get(position).getSon().addAll(mchild);
        if (mListData.get(position).getCities().size()>0){
            return;
        }else {
            mListData.get(position).getCities().addAll(mchild);
        }

        notifyDataSetChanged();
    }

    public void addAllChild2(int position,int pos, List<Area2> mchild) {
//        group.get(position).getSon().addAll(mchild);
        mListData.get(position).getCities().get(pos).getCounties().addAll(mchild);
        lv.expandGroup(pos);
        notifyDataSetChanged();
    }

    /**
     * 设置确定取消按钮的回调
     */
    public ExpandListViewAdapter.OnGroupClickListener OnGroupClickListener;
    public ExpandListViewAdapter OnGroupClickListener(OnGroupClickListener OnGroupClickListener) {
        this.OnGroupClickListener = OnGroupClickListener;
        return this;
    }

    public ExpandListViewAdapter.OnChildClickListener OnChildClickListener;
    public ExpandListViewAdapter OnChildClickListener (OnChildClickListener OnChildClickListener) {
        this.OnChildClickListener = OnChildClickListener;
        return this;
    }

    public interface OnGroupClickListener{
        /**
         * 点击确定按钮事件
         */
        public void onGropClick(ExpandableListView parent, View v, int groupPosition, long id,BaseExpandableListAdapter adapter);
    }

    public interface OnChildClickListener{
        /**
         * 点击确定按钮事件
         */
        public void OnChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id,BaseExpandableListAdapter adapter);
    }


    @Override
    public int getGroupCount() {
        return mListData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Province2 getGroup(int groupPosition) {
        return mListData.get(groupPosition);
    }

    @Override
    public City2 getChild(int groupPosition, int childPosition) {
        return mListData.get(groupPosition).getCities().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        FirstHolder holder = null;
        if (convertView == null) {
            holder = new FirstHolder();
            convertView = mInflate.inflate(R.layout.item_expand_lv_first, parent, false);
            holder.tv = ((TextView) convertView.findViewById(R.id.tv));
            holder.cb = ((CheckBox) convertView.findViewById(R.id.cb));
            convertView.setTag(holder);
        } else {
            holder = (FirstHolder) convertView.getTag();
        }
        holder.tv.setText(mListData.get(groupPosition).getProvince().getName());
        final FirstHolder finalHolder = holder;
        finalHolder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                boolean isChecked = finalHolder.cb.isChecked();
//                Log.d("bigname", "onclick: first:" + groupPosition + "," + isChecked);
//                mListData.get(groupPosition).setCheck(isChecked);
//                for (int i = 0; i < mListData.get(groupPosition).getCities().size(); i++) {
//                    City2 secondModel = mListData.get(groupPosition).getCities().get(i);
//                    secondModel.setCheck(isChecked);
//                    for (int j = 0; j < secondModel.getCounties().size(); j++) {
//                        Area2 thirdModel = secondModel.getCounties().get(j);
//                        thirdModel.setCheck(isChecked);
//                    }
//                }

                mListData.get(groupPosition).setCheck(!mListData.get(groupPosition).isCheck());
                for (int i = 0; i < mListData.get(groupPosition).getCities().size(); i++) {
                    mListData.get(groupPosition).getCities().get(i).setCheck(mListData.get(groupPosition).isCheck());
                }
                notifyDataSetChanged();
            }
        });
        finalHolder.cb.setChecked(mListData.get(groupPosition).isCheck());
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//        SecondHolder holder = null;
//        if (convertView == null) {
//            holder = new SecondHolder();
//            convertView = mInflate.inflate(R.layout.item_expand_lv_second, parent, false);
//            holder.tv = ((TextView) convertView.findViewById(R.id.tv));
//            holder.cb = ((CheckBox) convertView.findViewById(R.id.cb));
//            convertView.setTag(holder);
//        } else {
//            holder = (SecondHolder) convertView.getTag();
//        }
//        holder.tv.setText(mListData.get(groupPosition).getListSecondModel().get(childPosition).getTitle());
//        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                mListData.get(groupPosition).getListSecondModel().get(childPosition).setCheck(isChecked);
//            }
//        });
//        holder.cb.setChecked(mListData.get(groupPosition).getListSecondModel().get(childPosition).isCheck());
//        return convertView;
//        Object object= (Object) getChild(groupPosition, childPosition);
//        CustomExpandableListView subObjects= () convertView;;
//        if (convertView==null) {
//            subObjects= new CustomExpandableListView(activity);
//        }
//        Adapter2 adapter= new Adapter2(activity, object);
//        subObjects.setAdapter(adapter);
//
//        return subObjects
        lv = ((CustomExpandableListView) convertView);
        if (convertView == null) {
            lv = new CustomExpandableListView(context);
        }
        final SecondAdapter secondAdapter = new SecondAdapter(context, mListData.get(groupPosition).getCities());
        lv.setAdapter(secondAdapter);
        lv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Log.d("==","sssss");
                position = groupPosition;
                if ( OnGroupClickListener!= null) {
                    OnGroupClickListener.onGropClick(parent,v,groupPosition,id,secondAdapter);
                }

                return true;
            }
        });
        lv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if ( OnChildClickListener!= null) {
                    OnChildClickListener.OnChildClick(parent,v,groupPosition,childPosition,id,secondAdapter);
                }
                return false;
            }
        });
        lv.expandGroup(childPosition);
        return lv;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    /*
  *   第二层的适配器
  * */
   public class SecondAdapter extends BaseExpandableListAdapter {
        Context context;
        LayoutInflater inflater;
        List<City2> listSecondModel;

        public SecondAdapter(Context context,List<City2> listSecondModel) {
            this.context = context;
            this.listSecondModel = listSecondModel;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getGroupCount() {
            int size = listSecondModel.size();
            Log.d("bigname", "getGroupCount: "+size);
            return size;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return listSecondModel.get(groupPosition).getCounties().size();
        }

        public void addAllChild(int position,int pos, List<Area2> mchild) {
//        group.get(position).getSon().addAll(mchild);
            mListData.get(position).getCities().get(pos).getCounties().addAll(mchild);
            notifyDataSetChanged();
        }


        @Override
        public City2 getGroup(int groupPosition) {
            return listSecondModel.get(groupPosition);
        }

        @Override
        public Area2 getChild(int groupPosition, int childPosition) {
            return listSecondModel.get(groupPosition).getCounties().get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            SecondHolder holder = null;
            if (convertView == null) {
                holder = new SecondHolder();
                convertView = mInflate.inflate(R.layout.item_expand_lv_second, parent, false);
                holder.tv = ((TextView) convertView.findViewById(R.id.tv));
                holder.cb = ((CheckBox) convertView.findViewById(R.id.cb));
                convertView.setTag(holder);
            } else {
                holder = (SecondHolder) convertView.getTag();
            }
            holder.tv.setText(listSecondModel.get(groupPosition).getCity().getName());
            final SecondHolder secondHolder = holder;
            secondHolder.cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isChecked = secondHolder.cb.isChecked();
                    Log.d("bigname", "onCheckedChanged: second:" + groupPosition + "," + isChecked);
                    listSecondModel.get(groupPosition).setCheck(isChecked);
                    for (int i = 0; i < listSecondModel.get(groupPosition).getCounties().size(); i++) {
                        Area2 thirdModel = listSecondModel.get(groupPosition).getCounties().get(i);
                        thirdModel.setCheck(isChecked);
                    }
                    if(!isChecked) {

                        mListData.get(groupPosition).setCheck(!mListData.get(groupPosition).isCheck());
                    }
                    notifyDataSetChanged();
                }
            });
            secondHolder.cb.setChecked(listSecondModel.get(groupPosition).isCheck());
            return convertView;
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ThirdHolder holder = null;
            if (convertView == null) {
                holder = new ThirdHolder();
                convertView = mInflate.inflate(R.layout.item_expand_lv_third, parent, false);
                holder.tv = ((TextView) convertView.findViewById(R.id.tv));
                holder.cb = ((CheckBox) convertView.findViewById(R.id.cb));
                convertView.setTag(holder);
            } else {
                holder = (ThirdHolder) convertView.getTag();
            }
            holder.tv.setText(listSecondModel.get(groupPosition).getCounties().get(childPosition).getArea().getName());
            final ThirdHolder thirdHolder = holder;
            thirdHolder.cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isChecked = thirdHolder.cb.isChecked();
                    Log.d("bigname", "onCheckedChanged: third:" + groupPosition + "," + isChecked);
                    listSecondModel.get(groupPosition).getCounties().get(childPosition).setCheck(isChecked);
                }
            });
            thirdHolder.cb.setChecked(listSecondModel.get(groupPosition).getCounties().get(childPosition).isCheck());
            return convertView;
        }


        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }


    class FirstHolder {
        TextView tv;
        CheckBox cb;
    }

    class SecondHolder {
        TextView tv;
        CheckBox cb;
    }

    class ThirdHolder{
        TextView tv;
        CheckBox cb;
    }
}
