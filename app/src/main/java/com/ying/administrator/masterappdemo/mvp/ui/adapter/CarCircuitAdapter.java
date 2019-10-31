package com.ying.administrator.masterappdemo.mvp.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ying.administrator.masterappdemo.R;
import com.ying.administrator.masterappdemo.entity.Category;
import com.ying.administrator.masterappdemo.entity.MySkills;

import java.util.ArrayList;
import java.util.List;

public class CarCircuitAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<MySkills> group = new ArrayList<>(); //父级


    public CarCircuitAdapter(Context context) {
        this.context = context;

    }

    public void addGroupList(List<MySkills> mgroup) {
        if (mgroup != null) {
            group.addAll(mgroup);
            notifyDataSetChanged();
        }

    }

    public List<MySkills> getGroup() {
        return group;
    }

    public void addAllChild(int position, List<Category> mchild) {
//        group.get(position).getSon().addAll(mchild);
        group.get(position).getCategoryArrayList().addAll(mchild);
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return group.size();
    }

    @Override
    public MySkills getGroup(int groupPosition) {
        return group.get(groupPosition);
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return group.get(groupPosition).getCategoryArrayList().size();
    }


    @Override
    public Category getChild(int groupPosition, int childPosition) {
        return group.get(groupPosition).getCategoryArrayList().get(childPosition);
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

    /**
     * 获取显示指定组的视图对象
     *
     * @param groupPosition 组位置
     * @param isExpanded    该组是展开状态还是伸缩状态
     * @param convertView   重用已有的视图对象
     * @param parent        返回的视图对象始终依附于的视图组
     * @return
     * @see android.widget.ExpandableListAdapter#getGroupView(int, boolean,
     * View, ViewGroup)
     */
    @SuppressLint("SetTextI18n")
    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_kills, null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.tv_kill_name);
            holder.iv_choose = (ImageView) convertView.findViewById(R.id.iv_choose);
            holder.iv_updown = (ImageView) convertView.findViewById(R.id.iv_updown);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(group.get(groupPosition).getCategory().getFCategoryName());
        holder.iv_choose.setSelected(group.get(groupPosition).isSelected());
        holder.iv_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                group.get(groupPosition).setSelected(!group.get(groupPosition).isSelected());
                for (int i = 0; i < group.get(groupPosition).getCategoryArrayList().size(); i++) {
                    group.get(groupPosition).getCategoryArrayList().get(i).setSelected(group.get(groupPosition).isSelected());
                }
                notifyDataSetChanged();
            }
        });

        //判断isExpanded就可以控制是按下还是关闭，同时更换图片
        if (isExpanded) {
            holder.iv_updown.setImageResource(R.drawable.tree_ex);
            holder.iv_choose.setVisibility(View.VISIBLE);
        } else {
            holder.iv_updown.setImageResource(R.drawable.tree_ec);
            holder.iv_choose.setVisibility(View.GONE);
        }

        return convertView;
    }

    /**
     * 获取一个视图对象，显示指定组中的指定子元素数据。
     *
     * @param groupPosition 组位置
     * @param childPosition 子元素位置
     * @param isLastChild   子元素是否处于组中的最后一个
     * @param convertView   重用已有的视图(View)对象
     * @param parent        返回的视图(View)对象始终依附于的视图组
     * @return
     * @see android.widget.ExpandableListAdapter#getChildView(int, int, boolean,
     * View, ViewGroup)
     */
    @SuppressLint("SetTextI18n")
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_skill2, null);
            holder = new ViewHolder();
            holder.tv_car_circuit = (TextView) convertView.findViewById(R.id.tv_kill_name);
            holder.iv_choose = (ImageView) convertView.findViewById(R.id.iv_choose);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Category child = getChild(groupPosition, childPosition);


            holder.tv_car_circuit.setText( child.getFCategoryName());
            holder.iv_choose.setSelected(child.isSelected());

        return convertView;
    }

    /**
     * 是否选中指定位置上的子元素。
     * 让isChildSelectable方法返回true,解决ExpandableListView中子元素无法点击，OnChildClickListener无效
     *
     * @param groupPosition
     * @param childPosition
     * @return
     * @see android.widget.ExpandableListAdapter#isChildSelectable(int, int)
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ViewHolder {
        TextView textView, is_new;


        TextView tv_car_circuit;
        ImageView iv_choose;
        ImageView iv_updown;
    }
}


