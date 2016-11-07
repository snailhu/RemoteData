package DataAn.log.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String goSystem(){		
		//return "/secondStyle/system" ;
		return "/admin/ftltojsp/systemLog" ;
	}
	
	/*@RequestMapping(value = "showSystemLog", method = RequestMethod.GET)
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
			sDto.setLogoutTime("");
			sDto.setOperateJob("");
			sLDtos.add(sDto);
		}
		return sLDtos;				
	}*/
	
	@RequestMapping(value = "showSystemLog/{beginTime}/{endTime}")
	@ResponseBody
	public List<SystemLogDto> getSystemLogByTime(
			@PathVariable String beginTime, 
			@PathVariable String endTime
			)
	{
		//System.out.println(beginTime+"()"+endTime);
		/*if((beginTime==null) || (endTime==null))		
		{
			beginTime="1950-01-01 00:00:01";
			endTime="9999-01-01 00:00:01";
		}
		else{
			beginTime =beginTime+" 00:00:01";
			endTime=endTime+" 23:59:59";
		}*/
		List<SystemLogDto> sLDtos = new ArrayList<SystemLogDto>();
		List<SystemLog> sLogs;
		try {	
			sLogs = systemLogService.getSyetemLogsByTime(beginTime, endTime);
			for(SystemLog sl:sLogs){
				SystemLogDto sDto = new SystemLogDto();
				sDto.setLoginIp(sl.getLoginIp());
				//sDto.setLoginTime(changeDateStyle(sl.getLoginTime()));
				sDto.setUserName(sl.getUserName());
				//sDto.setLogoutTime(sl.getLogoutTime().toString());
				sDto.setOperateTime(changeDateStyle(sl.getOperateTime()));
				sDto.setOperateJob(sl.getOperateJob());
				sLDtos.add(sDto);
			}
			return sLDtos;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String changeDateStyle(Date date){
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
		String stringtime = dateFormat.format( date );
		return stringtime;
	}
	
	/**
	* 日期转换成字符串
	* @param date 
	* @return str
	*/
	public  String DateToStr(Date date) {
		  
		   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   String str = format.format(date);
		   return str;
		} 

	/**
	* 字符串转换成日期
	* @param str
	* @return date
	*/
	public  Date StrToDate(String str) {
	  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   Date date = null;
	   try {
	    date = format.parse(str);
	   } catch (ParseException e) {
	    e.printStackTrace();
	   }
	   return date;
	}
}
