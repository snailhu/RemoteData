package DataAn.reportManager.option;

public enum ReportDataType {

	/**DOC文件类型*/
	DOC {
		@Override
		public String getName() {
			return "doc";
		}
	};
	
	public abstract String getName();

	public static ReportDataType getType(String type) {
		if ("doc".equals(type)) {
			return ReportDataType.DOC;
		}
		return null;
	}
}
