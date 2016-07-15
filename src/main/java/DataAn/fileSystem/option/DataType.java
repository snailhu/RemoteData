package DataAn.fileSystem.option;

/**
* Title: DataType
* @Description: 文件数据类型 dat csv
* @author  Shewp
* @date 2016年7月13日
*/
public enum DataType {

	/**dat文件类型*/
	DAT {
		@Override
		public String getName() {
			return "dat";
		}
	},
	/**cav文件类型*/
	CSV {
		@Override
		public String getName() {
			return "csv";
		}
	};
	public abstract String getName();

	public static DataType getType(String type) {
		if ("dat".equals(type)) {
			return DataType.DAT;
		} else {
			return DataType.CSV;
		}
	}
}
