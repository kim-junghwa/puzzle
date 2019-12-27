package vo;

public class Report {
	private Member member;
	private String reportId;
	private String reportDate;
	private int count;
	private int timer;
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getTimer() {
		return timer;
	}
	public void setTimer(int timer) {
		this.timer = timer;
	}
	@Override
	public String toString() {
		return "Report [meber=" + member + ", reportId=" + reportId + ", reportDate=" + reportDate + ", count=" + count
				+ ", timer=" + timer + "]";
	}
}
