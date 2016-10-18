package DataAn.galaxyManager.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import DataAn.common.dao.Pager;
import DataAn.common.pageModel.EasyuiDataGridJson;
import DataAn.galaxyManager.dto.ParameterDto;
import DataAn.galaxyManager.service.IParameterService;
import DataAn.sys.dto.RoleDto;

@Controller
@RequestMapping("/admin/parameter")
public class ParameterController {

	@Resource
	private IParameterService parameterService;
	
	// 返回参数管理主页
	@RequestMapping()
	public String toParameterIndex() {
		return "redirect:/admin/parameter/index";
	}
	
	@RequestMapping("index")
	public String parameterIndex() {
		return "admin/galaxy/parameterList";
	}
	
	@RequestMapping(value = "/getList", method = RequestMethod.POST)
	@ResponseBody
	public EasyuiDataGridJson getRoleList(int page, int rows) {
//		System.out.println("getRoleList..");
//		System.out.println("pageIndex: " + page);
//		System.out.println("pageSize: " + rows);
		EasyuiDataGridJson json = new EasyuiDataGridJson();
		Pager<ParameterDto> pager = parameterService.getParameterList(page, rows);
		json.setRows(pager.getDatas());
		json.setTotal(pager.getTotalCount());
		return json;
	}
}
