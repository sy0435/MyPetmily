package common;

public class PageVO {
	private int pageList = 10;		//페이지당 보여질 목록 수
	private int blockPage = 10;		//블럭당 보여질 페이지 수
	private int totalList;			//총 목록수
	//624건 : 63페이지
	private int totalPage;			//총 페이지수
	//63페이지 : 7블럭
	private int totalBlock;			//총 블럭수
	private int curPage;			//현재페이지
	private int curBlock;			//현재 블럭
	private int beginList, endList;	//현재 페이지에 보여질 목록 시작/ 끝
	private int beginPage, endPage;	//현재 페이지에 보여질 페이지 시작/ 끝

	private String search, search2, search3, search4, keyword;		//검색조건, 검색어
	private String board_subject, board_city, board_region;
	private String viewType="list";
	

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
	public String getViewType() {
		return viewType;
	}
	public void setViewType(String viewType) {
		this.viewType = viewType;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getSearch2() {
		return search2;
	}
	public void setSearch2(String search2) {
		this.search2 = search2;
	}
	public String getSearch3() {
		return search3;
	}
	public void setSearch3(String search3) {
		this.search3 = search3;
	}
	public String getSearch4() {
		return search4;
	}
	public void setSearch4(String search4) {
		this.search4 = search4;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getTotalBlock() {
		return totalBlock;
	}
	public void setTotalBlock(int totalBlock) {
		this.totalBlock = totalBlock;
	}
	public int getCurBlock() {
		return curBlock;
	}
	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}
	public int getPageList() {
		return pageList;
	}
	public void setPageList(int pageList) {
		this.pageList = pageList;
	}
	public int getBlockPage() {
		return blockPage;
	}
	public void setBlockPage(int blockPage) {
		this.blockPage = blockPage;
	}
	public int getTotalList() {
		return totalList;
	}
	public void setTotalList(int totalList) {
		this.totalList = totalList;
		//DB에서 조회해온 총 목록수에 따라 총 페이지 수가 결정됨
		totalPage = totalList / pageList;
		if(totalList % pageList > 0 ) ++totalPage;
		
		//총 페이지수에 따라서 총 블럭수가 결정 됨
		totalBlock = totalPage / blockPage;
		if( totalPage % blockPage > 0) ++totalBlock;
		
		//현재 페이지에 따라 보여질 목록의 번호가 결정됨
		//624건 : 1페이지 : 615-624, 
		//		  2페이지 : 603-614,
		endList = totalList - (curPage -1)*pageList;
		beginList = endList - (pageList -1 );
		
		//현재 블럭에 따라 보여질 페이지 번호가 결정됨
		curBlock = curPage / blockPage;
		if ( curPage % blockPage > 0) ++curBlock;
		//1블럭 : 1-10페이지
		//2블럭 : 11-20페이지
		endPage = blockPage * curBlock;
		beginPage = endPage - (blockPage - 1);
		//endPage 는 총 페이지 수보다 클 수 없다.
		if( endPage > totalPage) endPage = totalPage;
		
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getBeginList() {
		return beginList;
	}
	public void setBeginList(int beginList) {
		this.beginList = beginList;
	}
	public int getEndList() {
		return endList;
	}
	public void setEndList(int endList) {
		this.endList = endList;
	}
	public int getBeginPage() {
		return beginPage;
	}
	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
}
