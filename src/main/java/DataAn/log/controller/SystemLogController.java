package DataAn.log.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import DataAn.log.domain.SystemLog;
import DataAn.log.dto.SystemLogDto;
import DataAn.log.service.SystemLogService;


@Controller
@RequestMapping("/admin")
public class SystemLogController {
	
	@Resource 
	private SystemLogService systemLogService;
	
	
	@RequestMapping("/systemLog")
	public String goSystem(
			HttpServletResponse response,
			HttpServletRequest request
			){				
		return "/secondStyle/system" ;				
	}
	
	@RequestMapping(value = "/showSystemLog", method = RequestMethod.GET)
	@ResponseBody
	public List<SystemLogDto> getSystemLog(
			HttpServletResponse response,
			HttpServletRequest request
			){
		List<SystemLogDto> sLDtos = new ArrayList<SystemLogDto>();
		List<SystemLog> sLogs = systemLogService.getSystemLogs();
		for(SystemLog sl:sLogs){
			SystemLogDto sDto = new SystemLogDto();
			sDto.setLoginIp(sl.getLoginIp());
			sDto.setLoginTime(changeDateStyle(sl.getLoginTime()));
			sDto.setUserName(sl.getUserName());
			sLDtos.add(sDto);
		}
		return sLDtos;				
	}
	
	
	public String changeDateStyle(Date date){
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
		String stringtime = dateFormat.format( date );
		return stringtime;
	}
}
