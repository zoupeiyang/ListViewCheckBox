package com.example.listviewcheckbox.adapter;

import java.util.HashMap;
import java.util.List;

import com.example.listviewcheckbox.R;
import com.example.listviewcheckbox.entity.SubjectItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

public class SubjectAdapter extends BaseAdapter {
	
	private List<SubjectItem> list;
	private Context context;
	//存储所有主题的项目的选中状态，遍历这个容器可以获取选中的项目信息
    private HashMap<String,Boolean> subjectItemMap;
    private LayoutInflater inflater;
	
	public class ViewHolder{
	   //投票主题id控件
	   public TextView tvSubjectId;
	   //投票主题名称控件
	   public TextView tvSubjectName;
	   //投票项目名称控件
	   public TextView tvSubjectItemName;
	   //投票项目id控件
	   public TextView tvSubjectItemId;
	   //投票主题类型（单选或多选）控件
	   public TextView tvIsMultiChoice;
	   //选中CheckBox控件（主题类型为多选时显示）
	   public CheckBox cbSubjectItem;
	   //选中RadioButton控件（主题类型为单选时显示）
	   public RadioButton rbSubjectItem;
		
	}
	
	public SubjectAdapter(List<SubjectItem> list,Context context)
	{
		this.list=list;
		this.context=context;
		 inflater = LayoutInflater.from(context);
		this.subjectItemMap=new HashMap<String, Boolean>();
		//初始化subjectItemMap，默认所有项目为未选中状态
		for (int i = 0; i < list.size(); i++) {
			this.subjectItemMap.put(list.get(i).getItemId(), false);
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
    	SubjectItem item = list.get(position);
		if(convertView!=null&&convertView.getId()==R.id.lv_subject)
		{
			viewHolder=(ViewHolder)convertView.getTag();
		}
		else {
			viewHolder = new ViewHolder();
			convertView=inflater.inflate(R.layout.listview_subject_item, null);
			viewHolder.tvSubjectId=(TextView)convertView.findViewById(R.id.tv_subject_id);
			viewHolder.tvSubjectName=(TextView) convertView.findViewById(R.id.tv_subject_name);
			viewHolder.tvSubjectItemId = (TextView) convertView.findViewById(R.id.tv_subject_item_id);
			viewHolder.tvSubjectItemName = (TextView) convertView.findViewById(R.id.tv_subject_item_name);
			viewHolder.cbSubjectItem = (CheckBox) convertView.findViewById(R.id.cb_subject_item);
			viewHolder.rbSubjectItem = (RadioButton) convertView.findViewById(R.id.rb_subject_item);
			viewHolder.tvIsMultiChoice = (TextView) convertView.findViewById(R.id.tv_is_multi_choice );
			
		}
		
		//如果项目名称为空就隐藏当前项的产品名称，即所有子项目只允许第一个子项目出现产品名称
		if(item.getSubjectName().equals(""))
		{
			viewHolder.tvSubjectName.setVisibility(View.GONE);
		}
		else {
			viewHolder.tvSubjectName.setText(item.getSubjectName());
		}
		
		viewHolder.tvSubjectItemId.setText(item.getItemId());
		viewHolder.tvSubjectId.setText(item.getSubjectId());
		viewHolder.tvSubjectItemName.setText(item.getItemName());
		viewHolder.tvIsMultiChoice.setText(item.getIsMultiChoice().toString());
		//当前项目为多选项目
		if(item.getIsMultiChoice().toString().equals("true"))
		{
			viewHolder.cbSubjectItem.setVisibility(View.VISIBLE);
			viewHolder.rbSubjectItem.setVisibility(View.GONE);
			viewHolder.cbSubjectItem.setChecked(this.subjectItemMap.get(item.getItemId()));
			
		}
		//当前项目为单选项目
		else {
			viewHolder.cbSubjectItem.setVisibility(View.GONE);
			viewHolder.rbSubjectItem.setVisibility(View.VISIBLE);
			viewHolder.rbSubjectItem.setChecked(this.subjectItemMap.get(item.getItemId()));
		}
		convertView.setTag(viewHolder);
        return convertView;
	}
	
	/**
	 * 获取所有主题的项目的选中状态容器
	 * @return
	 */
	 public  HashMap<String,Boolean> getSubjectItemMap() {
	        return this.subjectItemMap;
	    }


}
