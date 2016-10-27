package DataAn.Analysis.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 一个组中所选的所有参数
 * @author snailHU
 *
 */
public class ParamBatchDto implements Serializable {
	
		private String nowSeries;
		
		private String nowStar;
		
		private  String component;
		
		private  String startTime;
		
		private String  endTime;
		
		private List<ParamAttributeDto> paramAttribute;

		public String getNowSeries() {
			return nowSeries;
		}

		public void setNowSeries(String nowSeries) {
			this.nowSeries = nowSeries;
		}

		public String getNowStar() {
			return nowStar;
		}

		public void setNowStar(String nowStar) {
			this.nowStar = nowStar;
		}

		public String getComponent() {
			return component;
		}

		public void setComponent(String component) {
			this.component = component;
		}

		public String getStartTime() {
			return startTime;
		}

		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}

		public String getEndTime() {
			return endTime;
		}

		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}

		public List<ParamAttributeDto> getParamAttribute() {
			return paramAttribute;
		}

		public void setParamAttribute(List<ParamAttributeDto> paramAttribute) {
			this.paramAttribute = paramAttribute;
		}

		
		
		
		
}
