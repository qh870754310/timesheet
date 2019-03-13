package com.kcfy.techservicemarket.infra.json;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.dayatang.utils.BeanUtils;
import org.openkoala.common.facade.TreeKoalaFacade;

public class JsonTree {
	
	public static List<Map<String,Object>> generateMapObj(List<? extends TreeKoalaFacade> dataList) {
		return generateMapObj(dataList,null,true);
	}
	
	public static List<Map<String,Object>> generateMapObj(List<? extends TreeKoalaFacade> dataList, String[] otherNode) {
		return generateMapObj(dataList,otherNode,true);
	}
	
	/**
	 * 生成JSON树
	 * 
	 * @param dataList
	 *            读取层次数据结果集列表
	 * @param otherNode
	 *            其它节点key数组
	 * @param sort
	 *            是否进行排序(不排序是按id排序,否则按sn排序)
	 * @return json树的数据
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String,Object>> generateMapObj(List<? extends TreeKoalaFacade> dataList, String[] otherNode,
			Boolean sort) {
		// 节点哈希表<节点id，用于临时存储节点对象>
		Map<String, Map<String, Object>> nodeList = constructionMapObj(dataList, otherNode);
		// 根节点
		List<Map<String,Object>> rootNodeList = new ArrayList<Map<String,Object>>();
		// 构造无序的内存多叉树
		Set<Entry<String, Map<String, Object>>> set = nodeList.entrySet();
		for (Iterator<Entry<String, Map<String, Object>>> it = set.iterator(); it.hasNext();) {
			Map<String, Object> node = (Map<String, Object>) it.next().getValue();
			//默认全是叶子节点
			if (node.get("leaf") == null){
				node.put("leaf",true);
			}
			if (node.get("parentId") == null) {
				// 处理有多个父节点节点的
				rootNodeList.add(node);
			} else {
				if (nodeList.get(node.get("parentId")) != null) {
					nodeList.get(node.get("parentId")).put("leaf",false);
					if(nodeList.get(node.get("parentId")).get("children") == null){
						nodeList.get(node.get("parentId")).put("children", new ArrayList<Map<String,Object>>());
					}
					((ArrayList<Map<String,Object>>)nodeList.get(node.get("parentId")).get("children")).add(node);
				}
			}
		}
		
		if (sort) {// 也对父类进行排序
			sort(rootNodeList);
		}
		
		return rootNodeList;

	}

	@SuppressWarnings("unchecked")
	private static void sort(List<Map<String,Object>> rootNodeList){
		Collections.sort(rootNodeList, new MapSnDescComparator());
		for (Map<String, Object> map : rootNodeList) {
			if(!(boolean)map.get("leaf")){
				sort((ArrayList<Map<String,Object>>)map.get("children"));
			}
		}
	}
	
	/**
	 * 节点哈希表<节点id，用于临时存储节点对象>
	 * 
	 * @param dataList
	 *            读取层次数据结果集列表
	 * @param otherNode
	 *            其它节点key
	 * @return 节点哈希表
	 */
	private static Map<String, Map<String, Object>> constructionMapObj(List<? extends TreeKoalaFacade> dataList, String[] otherNode) {
		// 节点哈希表<节点id，用于临时存储节点对象>
		Map<String, Map<String, Object>> nodeMap = new HashMap<String, Map<String, Object>>();
		// 根据结果集构造节点列表（存入哈希表）
		for (Iterator<? extends TreeKoalaFacade> it = dataList.iterator(); it.hasNext();) {
			TreeKoalaFacade treeKoalaFacade = it.next();
			Map<String, Object> dataRecord = ((Map<String, Object>)new BeanUtils(treeKoalaFacade).getPropValues());
			Map<String, Object> node = new TreeMap<String, Object>();
			node.put("id", treeKoalaFacade.getId());
			node.put("name",treeKoalaFacade.getName());
			node.put("parentId",treeKoalaFacade.getParentId());
			node.put("sequence",treeKoalaFacade.getSequence());
			// 其它节点
			if (otherNode != null && otherNode.length > 0) {
				for (String key : otherNode) {
					node.put(key,dataRecord.get(key) == null ? "" : dataRecord.get(key));
				}
			}
			nodeMap.put(((String)node.get("id")), node);
		}
		return nodeMap;
	}
	
}
