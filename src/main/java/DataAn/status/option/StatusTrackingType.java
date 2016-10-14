package DataAn.status.option;

public enum StatusTrackingType {

	/** 开始 */
	START {
		@Override
		public String getName() {
			return "开始";
		}

		@Override
		public String getValue() {
			return "1";
		}
	},
	/** 文件上传 */
	FILEUPLOAD {
		@Override
		public String getName() {
			return "文件上传";
		}

		@Override
		public String getValue() {
			return "2";
		}
	},
	/** 数据导入 */
	IMPORT {
		@Override
		public String getName() {
			return "数据导入";
		}

		@Override
		public String getValue() {
			return "3";
		}
	},
	/** 数据预处理 */
	PREHANDLE {
		@Override
		public String getName() {
			return "预处理";
		}

		@Override
		public String getValue() {
			return "4";
		}
	},
	/** 结束 */
	END {
		@Override
		public String getName() {
			return "结束";
		}

		@Override
		public String getValue() {
			return "5";
		}
	};
	public abstract String getName();

	public abstract String getValue();

	public static StatusTrackingType getStatusTrackingType(String value) {
		switch (value) {
		case "1":
			return StatusTrackingType.START;

		case "2":
			return StatusTrackingType.FILEUPLOAD;

		case "3":
			return StatusTrackingType.IMPORT;

		case "4":
			return StatusTrackingType.PREHANDLE;

		case "5":
			return StatusTrackingType.END;

		default:
			return null;
		}
	}
}
