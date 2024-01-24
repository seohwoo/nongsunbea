package nong.soon.bae.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import nong.soon.bae.bean.KakaoApproveResponse;
import nong.soon.bae.bean.KakaoReadyResponse;
import nong.soon.bae.data.ApiKeys;
import nong.soon.bae.data.FileRoot;


@Service
@RequiredArgsConstructor
@Transactional
public class KaKaoPayServiceImpl implements KaKaoPayService{
	
	static final String cid = "TC0ONETIME"; // ������ �׽�Ʈ �ڵ�
    static final String admin_Key = ApiKeys.getApiKeys().getKakaoPayKey(); // ���� ����! ���� ���ø����̼��� ���� Ű�� �־��ּ���
    private KakaoReadyResponse kakaoReady;
	@Override
	public KakaoReadyResponse kakaoPayReady() {
		 // īī������ ��û ���
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("partner_order_id", "������ �ֹ� ��ȣ");	// ������ �ֹ� ��ȣ
        parameters.add("partner_user_id", "������ ȸ�� ID");	// ������ ȸ�� ID
        parameters.add("item_name", "��ǰ��");				// ��ǰ��
        parameters.add("item_code", "��ǰ�ڵ�");				// ��ǰ�ڵ�
        parameters.add("quantity", "1");					// ��ǰ����
        parameters.add("created_at", "");					// ������(��������)
        parameters.add("approved_at", "");					// ������(��������)
        parameters.add("total_amount", "50000");			// ��������
        parameters.add("tax_free_amount", "45000");			// ���ΰ���
        parameters.add("approval_url", "http://"+FileRoot.getIp()+":8080/test/success"); // ���� �� redirect url
        parameters.add("cancel_url", "http://"+FileRoot.getIp()+":8080/test/cancel"); 	// ��� �� redirect url
        parameters.add("fail_url", "http://"+FileRoot.getIp()+":8080/test/fail"); 		// ���� �� redirect url
        
        
        // �Ķ����, ���
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
        
        // �ܺο� ���� url
        RestTemplate restTemplate = new RestTemplate();

        kakaoReady = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/ready",
                requestEntity,
                KakaoReadyResponse.class);
                
        return kakaoReady;
	}
	
	/**
     * īī�� �䱸 �����
     */
    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();

        String auth = "KakaoAK " + admin_Key;

        httpHeaders.set("Authorization", auth);
        httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return httpHeaders;
    }
	
	@Override
	public KakaoApproveResponse ApproveResponse(String pgToken) {
		 // īī�� ��û
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("tid", kakaoReady.getTid());
        parameters.add("partner_order_id", "������ �ֹ� ��ȣ");
        parameters.add("partner_user_id", "������ ȸ�� ID");
        parameters.add("pg_token", pgToken);

        // �Ķ����, ���
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
        
        // �ܺο� ���� url
        RestTemplate restTemplate = new RestTemplate();
        
        KakaoApproveResponse approveResponse = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/approve",
                requestEntity,
                KakaoApproveResponse.class);
                
        return approveResponse;
	}
    
    
    
	

}