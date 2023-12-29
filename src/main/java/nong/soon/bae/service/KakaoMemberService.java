package nong.soon.bae.service;

import nong.soon.bae.bean.KakaoUsersDTO;

public interface KakaoMemberService {
	
	public void kakaoJoin(KakaoUsersDTO usersDTO);
	public KakaoUsersDTO kakaoLogin(String username);
	public String findAuthBy(String userid);
	public String findUserIdBy2(String username);
	public KakaoUsersDTO findByUserId(String userid);
}
