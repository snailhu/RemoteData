package DataAn.sys.dto;

public enum StormSysStatus {

	NORMAL{

		@Override
		public String getName() {
			return "正常";
		}

		@Override
		public String getValue() {
			return "1";
		}
		
	},
	SHUTDOWN{

		@Override
		public String getName() {
			return "宕机";
		}

		@Override
		public String getValue() {
			return "0";
		}
		
	};
	public abstract String getName();
	
	public abstract String getValue();
	
	public static StormSysStatus getStormSysStatus(String value){
		if(value.equals("1")){
			return StormSysStatus.NORMAL;
		}else{
			return StormSysStatus.SHUTDOWN;
		}
	}
}
