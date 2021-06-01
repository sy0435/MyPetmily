package community;

public class CommunityVO {
	
	private int board_num;
	private String member_id;
	private String board_date;
	private String board_subject, board_city, board_region;
	private String board_title, board_content, board_imagepath;

	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getBoard_date() {
		return board_date;
	}
	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}
	public String getBoard_subject() {
		return board_subject;
	}
	public void setBoard_subject(String board_subject) {
		this.board_subject = board_subject;
	}
	public String getBoard_city() {
		return board_city;
	}
	public void setBoard_city(String board_city) {
		this.board_city = board_city;
	}
	public String getBoard_region() {
		return board_region;
	}
	public void setBoard_region(String board_region) {
		this.board_region = board_region;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public String getBoard_imagepath() {
		return board_imagepath;
	}
	public void setBoard_imagepath(String board_imagepath) {
		this.board_imagepath = board_imagepath;
	}
	
}