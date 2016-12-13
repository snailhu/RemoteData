package DataAn.mongo;

public class Result {
	private String datetime;
	private String sequence_00428;
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getSequence_00428() {
		return sequence_00428;
	}
	public void setSequence_00428(String sequence_00428) {
		this.sequence_00428 = sequence_00428;
	}
	@Override
	public String toString() {
		return "Result [datetime=" + datetime + ", sequence_00428="
				+ sequence_00428 + "]";
	}
}
