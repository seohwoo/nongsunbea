package nong.soon.bae.service;


import org.springframework.ui.Model;

public interface UserCheckService {
	public void userlist(int pageNum , Model model); //일반 유저 목록
	public int userstop(String username); //유저 정지하기
	public void findUser(Model model, int pageNum, String userSearch); //검색 	
	public int blacklistInsert(String username, String reason); //정지 회원 블랙리스트 테이블에 저장 
	public void blacklist(int pageNum , Model model);//정지회원목록
	public int reuser(String username); //정지회원복구하기
	public int deleteblacklist (String username); //블랙리스트에서 제거하기 
	public void blackFindUser(Model model, int pageNum, String userSearch);//정지회원검색
	
	public void showCate(Model model); //카테고리 분류하기
	public int maxNum();
	
	public int insertNewCate (int num, String addCate);
	public int addCateFile(String addCate, String realname);
	

	public void showSubCate (Model model, int cate1Select);
	public void showSelectCate1 (Model model, int cate1Select);
	
	public int subMaxNum(int cate1Select);
	public int insertSubCate(int cate1Select,int subMaxNum, String addSubCate);
	public int insertSubDetailCate(int cate1Select,int subMaxNum, int addCateNum1, String addCate1);
	
	public void showDetailCate(Model model,int cate1Select);
	public int subDetailMaxNum(int maxNum, int cate1Select);
	
	public int insertDetailCate(int cate1Select,int subMaxNum, int datailMaxNum, String addDetail);
	public int addDetailFile (String realname, String addDetail);
	
	public void showEtcCate (Model model,int cate1Select, int subMaxNum);
	public String findEtcName(int cate1Select,int subMaxNum);
	
	public int updateCateName (String newCateName,int cate1Select , int subMaxNum);
	public int updateEtcCate (int cate1Select, int etcNum, String etcName);
	
}
