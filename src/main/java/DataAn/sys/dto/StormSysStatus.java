package DataAn.sys.dto;

public enum StormSysStatus {

	NORMAL{

		@Override
		public String getName() {
			return "正常";
		}
		
	},
	SHUTDOWN{

		@Override
		public String getName() {
			return "宕机";
		}
		
	};
	public abstract String getName();
}
