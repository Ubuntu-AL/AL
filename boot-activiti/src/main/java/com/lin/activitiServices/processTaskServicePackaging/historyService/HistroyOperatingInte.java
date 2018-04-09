/**
 * <p>Description: 接口类</p>
 */
package com.lin.activitiServices.processTaskServicePackaging.historyService;

import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;

/**
 * @author admin
 * <p>Description: 此类负责定义历史数据操作方法</p>
 */
public interface HistroyOperatingInte {

	/**
	 * <p>Title: getProcessHistory</p>  
	 * <p>Description: 此方法用于查询工作流历史数据</p> 
	 * @param historyService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param queryType 此字典标注查询方式类型并携带查询参数
	 * @return processHistories 返回查询所得的所有工作流历史的对象列表
	 * tips:四选一
	 * 		queryType:{
	 * 			"all":"default"
	 * 			/"finished":"default"
	 * 			/"unfinished":"default"
	 * 			/"processDefinitionId":"processDefinitionId"
	 * 		}
	 */
	public List<HistoricProcessInstance> getProcessHistory(HistoryService historyService, Map<String, Object> queryType);
	
	/**
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
	 */
	public List<HistoricTaskInstance> getTaskHistory(HistoryService historyService, Map<String, Object> queryType);
	
	/**
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
	 */
	public List<HistoricActivityInstance> getActivityHistory(HistoryService historyService, Map<String, String> queryType);
	
	
	/**
	 * <p>Title: getDetailHistory</p>  
	 * <p>Description: 此方法用于查询历史明细数据，包括流程变量的赋值过程</p> 
	 * @param historyService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param processInstanceId 待查询历史明细的流程定义Id
	 * @return detailHistory 返回查询所得的指定流程的历史明细对象列表
	 */
	public List<HistoricDetail> getDetailHistory(HistoryService historyService, String processInstanceId);
	
	/**
	 * <p>Title: deleteHistory</p>  
	 * <p>Description: 此方法用于删除历史数据</p> 
	 * @param historyService 为保证服务类的单例性，从业务函数获取服务实例 
	 * @param map 此字典用于标注删除方式并携带删除所需的参数
	 * tips:
	 * 		deleteType:{
	 * 			"processInstance":"processInstanceId"
	 *			/"taskInstance":"taskInstanceId"
	 * 		}
	 */
	public void deleteHistory(HistoryService historyService, Map<String, Object> map);
}
