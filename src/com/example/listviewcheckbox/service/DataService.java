package com.example.listviewcheckbox.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.listviewcheckbox.entity.SubjectItem;

public class DataService {
	
	/**
	 * 模拟从数据库表获取投票主题项目的数据源
	 * @return
	 */
	public static List<SubjectItem> getSubjectItems()
	{
		List<SubjectItem> list = new ArrayList<SubjectItem>();
		HashMap<String, Boolean> subjectMap=new HashMap<String, Boolean>();
		for (int i = 0; i < 3; i++) {
			
			for (int j = 0; j < 3; j++) {
				
				SubjectItem item = new SubjectItem();
				item.setSubjectId(i+"");
				if(subjectMap.containsKey(item.getSubjectId()))
				{
					item.setSubjectName("");
				}
				else
				{
					item.setSubjectName("投票主题"+i);
					subjectMap.put(item.getSubjectId(), true);
				}
				
				item.setItemId(i+""+j);
				item.setItemName("项目名称"+i+""+j);
				item.setIsMultiChoice(i%2==1?true:false);
				list.add(item);
				
			}
		}
		return list;
	}

}
