package com.example.listviewcheckbox;

import java.util.HashMap;
import java.util.List;
import com.example.listviewcheckbox.adapter.SubjectAdapter;
import com.example.listviewcheckbox.adapter.SubjectAdapter.ViewHolder;
import com.example.listviewcheckbox.entity.SubjectItem;
import com.example.listviewcheckbox.service.DataService;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class SubjectActivity extends Activity {

	private ListView lvSubject;
	private SubjectAdapter subjectAdapter;
	private List<SubjectItem> list;
	private Button btnAdd;
	// 用来保存单选主题当前选中的项目，这样用户在切换选择同一个主题下其它选项时能够将之前选中的项目的状态设置为未选状态
	private HashMap<String, String> radioButtonSelectedMaps;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_subject_activity);
		lvSubject = (ListView) findViewById(R.id.lv_subject);
		btnAdd = (Button) findViewById(R.id.btn_add);
		//从数据源获取投票主题和项目信息
		list = DataService.getSubjectItems();
		subjectAdapter = new SubjectAdapter(list, this);
		lvSubject.setAdapter(subjectAdapter);
		radioButtonSelectedMaps = new HashMap<String, String>();
		// 提交投票事件处理
		btnAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String selectValues="选中信息:";
				//遍历用户选中项目，可以根据实际需求获取选中项目的任何信息
				for (int i = 0; i < list.size(); i++) {
					if(subjectAdapter.getSubjectItemMap().get(list.get(i).getItemId()))
					{
						selectValues+="项目ID:"+list.get(i).getItemId()+"项目名称:"+list.get(i).getItemName();
					}
					
				}
				Toast.makeText(SubjectActivity.this, selectValues.equals("选中信息:")?"未选中任何信息":selectValues, Toast.LENGTH_LONG).show();
			}
		});

		// ListView控件每一行点击事件处理
		lvSubject.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				ViewHolder viewHolder = (ViewHolder) view.getTag();
				// 如果当前行是多选项目
				if (viewHolder.tvIsMultiChoice.getText().equals("true")) {
					viewHolder.cbSubjectItem.toggle();
					subjectAdapter.getSubjectItemMap().put(viewHolder.tvSubjectItemId.getText().toString(),viewHolder.cbSubjectItem.isChecked());

				} 
				
				//如果当前行为单选项目，注意单选项目选中后需要将同一主题下已经选中的项目设置为未选中状态
				else {
					String currentSubjectIdSelected=viewHolder.tvSubjectId.getText().toString();
					String currentSubjectItemId=viewHolder.tvSubjectItemId.getText().toString();
					//判断该单选主题是否有已经选中项目，如果有需要将它的选中状态设置为未选中
					if (radioButtonSelectedMaps.containsKey(currentSubjectIdSelected)) {
						subjectAdapter.getSubjectItemMap().put(radioButtonSelectedMaps.get(currentSubjectIdSelected),false);

					}
					//将当前选中的项目设置为该单选主题的选中项目
					radioButtonSelectedMaps.put(currentSubjectIdSelected,currentSubjectItemId);
					viewHolder.rbSubjectItem.toggle();
					subjectAdapter.getSubjectItemMap().put(currentSubjectItemId,viewHolder.rbSubjectItem.isChecked());
					//更新ListView
					updateListView();

				}
			
			}});

	}
	
	/**
	 * 更新ListView
	 */
	private void updateListView()
	{
		subjectAdapter.notifyDataSetChanged();
	}

}
