/**
 * <p>Description: 实现类</p>
 */
package com.lin.activitiServices.processTaskServicePackaging.historyService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.springframework.stereotype.Service;

/**
 * @author admin
 * <p>Description: 此类用于实现历史数据操作方法</p>
 */
@Service("HistroyOperatingInte")
public class HistoryOperatingImpl implements HistroyOperatingInte {
	/* (non-Javadoc)
	 * <p>Title: getProcessHistory</p>
	 * <p>Description: 此方法用于查询工作流历史数据</p> 
	 * @param historyService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param queryType 此字典标注查询方式类型并携带查询参数
	 * @return processHistory 返回查询所得的所有工作流历史的对象列表
	 * tips:
	 * 		queryType:{
	 * 			"all":"default"
	 * 			/"finished":"default"
	 * 			/"unfinished":"default"
	 * 			/"processDefinitionId":"processDefinitionId"
	 * 		}  
	 * @see com.lin.activitiServices.processTaskServicePackaging.historyService.HistoryServiceInte#getHistories(org.activiti.engine.HistoryService, java.util.Map)  
	 */
	public List<HistoricProcessInstance> getProcessHistory(HistoryService historyService, 
			Map<String, Object> queryType) {
		// TODO Auto-generated method stub

		List<HistoricProcessInstance> processHistory = new ArrayList<HistoricProcessInstance>();
		
		if(queryType.containsKey("all")) {	
			List<HistoricProcessInstance> finished = historyService.createHistoricProcessInstanceQuery().finished().list();
			List<HistoricProcessInstance> unfinished = historyService.createHistoricProcessInstanceQuery().finished().list();
			processHistory.addAll(finished);
			processHistory.addAll(unfinished);	
			
		}else if (queryType.containsKey("finished")) {	
			processHistory = historyService.createHistoricProcessInstanceQuery().finished().list();	
			
		}else if (queryType.containsKey("unfinished")) {
			processHistory = historyService.createHistoricProcessInstanceQuery().finished().list();	
			
		}else if (queryType.containsKey("processDefinitionId")) {
			processHistory = historyService.createHistoricProcessInstanceQuery().processDefinitionId((String) queryType.get("processDefinitionId")).list();
		
		}
		
		return processHistory;
	}

	/* (non-Javadoc)
	 * <p>Title: geTaskHistory</p>
	 * <p>Description: 此方法用于查询任务历史数据</p> 
	 * @param historyService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param queryType 此字典标注查询方式类型并携带查询参数
	 * @return taskHistories 返回查询所得的所有任务历史的对象列表
	 * tips:九选一
	 * 		queryType:{
	 * 			"all":"default"
	 * 			/"finished":"default"
	 * 			/"unfinished":"default"
	 * 			/"processDefinitionId":"processDefinitionId"
	 * 			/"processDefinitionName":"processDefinitionName"
	 * 			/"processInstanceId":"processInstanceId"
	 * 			/"taskDefinitionKey":"taskAssignee"
	 * 			/"taskDueBefore":"taskAssignee"
	 * 			/"taskDueAfter":"taskAssignee"
	 * 		} 
	 * @see com.lin.activitiServices.processTaskServicePackaging.historyService.HistoryServiceInte#geTaskHistory(org.activiti.engine.HistoryService, java.util.Map)  
	 */
	public List<HistoricTaskInstance> getTaskHistory(HistoryService historyService, 
			Map<String, Object> queryType) {
		// TODO Auto-generated method stub
		
		List<HistoricTaskInstance> taskHistory = new ArrayList<HistoricTaskInstance>();
		
		if(queryType.containsKey("all")) {
			List<HistoricTaskInstance> finished = historyService.createHistoricTaskInstanceQuery().finished().list();
			List<HistoricTaskInstance> unfinished = historyService.createHistoricTaskInstanceQuery().unfinished().list();
			taskHistory.addAll(finished);
			taskHistory.addAll(unfinished);
			
		}else if (queryType.containsKey("finished")) {
			taskHistory = historyService.createHistoricTaskInstanceQuery().finished().list();
			
		}else if (queryType.containsKey("unfinished")) {
			taskHistory = historyService.createHistoricTaskInstanceQuery().unfinished().list();
			
		}else if (queryType.containsKey("processDefinitionId")) {
			taskHistory = historyService.createHistoricTaskInstanceQuery().processDefinitionId((String)queryType.get("processDefinitionId")).list();
			
		}else if (queryType.containsKey("processDefinitionName")) {
			taskHistory = historyService.createHistoricTaskInstanceQuery().processDefinitionName((String)queryType.get("processDefinitionName")).list();
			
		}else if (queryType.containsKey("processInstanceId")) {
			taskHistory = historyService.createHistoricTaskInstanceQuery().processInstanceId((String)queryType.get("processInstanceId")).list();
			
		}else if (queryType.containsKey("taskDefinitionKey")) {
			taskHistory = historyService.createHistoricTaskInstanceQuery().taskDefinitionKey((String)queryType.get("taskDefinitionKey")).list();
			
		}else if (queryType.containsKey("taskDueBefore")) {
			taskHistory = historyService.createHistoricTaskInstanceQuery().taskDueBefore((Date)queryType.get("taskDueBefore")).list();
			
		}else if (queryType.containsKey("taskDueAfter")) {
			taskHistory = historyService.createHistoricTaskInstanceQuery().taskDueAfter((Date)queryType.get("taskDueAfter")).list();
		}
		
		return taskHistory;
	}

	/* (non-Javadoc)
	 * <p>Title: getActivityHistory</p>
	 * <p>Description: 此方法用于查询操作信息的历史数据</p> 
	 * @param historyService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param queryType 此字典标注查询方式类型并携带查询参数
	 * @return activityHistory 返回查询所得的所有操作历史的对象列表
	 * tips:六选一
	 * 		queryType:{
	 * 			"all":"default"
	 * 			/"finished":"default"
	 * 			/"unfinished":"default"
	 * 			/"activityId":"activityId"
	 * 			/"activityType":"activityType"
	 * 			/"processInstanceId":"processInstanceId"
	 * 			/"taskAssignee":"taskAssignee"
	 * 		}  
	 * @see com.lin.activitiServices.processTaskServicePackaging.historyService.HistoryServiceInte#getActivityHistory(org.activiti.engine.HistoryService, java.util.Map)  
	 */
	public List<HistoricActivityInstance> getActivityHistory(HistoryService historyService,
			Map<String, String> queryType) {
		// TODO Auto-generated method stub
		List<HistoricActivityInstance> activityHistory = new ArrayList<HistoricActivityInstance>();
		
		if(queryType.containsKey("all")) {
			List<HistoricActivityInstance> finished = historyService.createHistoricActivityInstanceQuery().finished().list();
			List<HistoricActivityInstance> unfinished = historyService.createHistoricActivityInstanceQuery().unfinished().list();
			activityHistory.addAll(finished);
			activityHistory.addAll(unfinished);
			
		}else if (queryType.containsKey("finished")) {
			activityHistory = historyService.createHistoricActivityInstanceQuery().finished().list();
			
		}else if (queryType.containsKey("unfinshed")) {
			activityHistory = historyService.createHistoricActivityInstanceQuery().unfinished().list();
			
		}else if (queryType.containsKey("activityId")) {
			activityHistory = historyService.createHistoricActivityInstanceQuery().activityId(queryType.get("activityId")).list();
			
		}else if (queryType.containsKey("activityType")) {
			activityHistory = historyService.createHistoricActivityInstanceQuery().activityType(queryType.get("activityType")).list();
			
		}else if (queryType.containsKey("processInstanceId")) {
			activityHistory = historyService.createHistoricActivityInstanceQuery().processDefinitionId(queryType.get("processDefinitionId")).list();
			
		}else if (queryType.containsKey("taskAssignee")) {
			activityHistory = historyService.createHistoricActivityInstanceQuery().taskAssignee(queryType.get("taskAssignee")).list();
		}
		
		return activityHistory;
	}

	/* (non-Javadoc)
	 * <p>Title: getDetailHistory</p>
	 * <p>Description: 此方法用于查询历史明细数据，包括流程变量的赋值过程</p> 
	 * @param historyService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param processInstanceId 待查询历史明细的流程定义Id
	 * @return detailHistory 返回查询所得的指定流程的历史明细对象列表 
	 * @see com.lin.activitiServices.processTaskServicePackaging.historyService.HistoryServiceInte#getDetailHistory(org.activiti.engine.HistoryService, java.lang.String)  
	 */
	public List<HistoricDetail> getDetailHistory(HistoryService historyService, String processInstanceId) {
		// TODO Auto-generated method stub
		
		List<HistoricDetail> detailHistory = new ArrayList<HistoricDetail>();
		
		detailHistory = historyService.createHistoricDetailQuery().processInstanceId(processInstanceId).list();
		
		return detailHistory;
	}

	/* (non-Javadoc)
	 * <p>Title: deleteHistory</p>
	 * <p>Description: 此方法用于删除历史数据</p> 
	 * @param historyService 为保证服务类的单例性，从业务函数获取服务实例 
	 * @param deleteType 此字典用于标注删除方式并携带删除所需的参数
	 * tips:
	 * 		deleteType:{
	 * 			"processInstance":"processInstanceId"
	 *			/"taskInstance":"taskInstanceId"
	 * 		}  
	 * @see com.lin.activitiServices.processTaskServicePackaging.historyService.HistoryServiceInte#deleteHistory(org.activiti.engine.HistoryService, java.util.Map)  
	 */
	public void deleteHistory(HistoryService historyService, Map<String, Object> deleteType) {
		// TODO Auto-generated method stub
		
		if(deleteType.containsKey("processInstance")) {
			historyService.deleteHistoricProcessInstance((String) deleteType.get("processInstanceId"));
			
		}else if(deleteType.containsKey("taskInstance")) {
			historyService.deleteHistoricTaskInstance((String) deleteType.get("taskInstanceId"));
			
		}
	}
}
