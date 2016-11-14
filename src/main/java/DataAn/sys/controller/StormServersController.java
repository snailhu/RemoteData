package DataAn.sys.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import DataAn.common.controller.BaseController;
import DataAn.sys.service.IStormServersService;

@Controller
@RequestMapping(value = "/admin/stormServers")
public class StormServersController extends BaseController{

	@Resource
	private IStormServersService stormServersService;
	
	@RequestMapping("index")
	public String stormServersIndex(){
		
		return "admin/stormServers/index";
	}
}
