package com.rami.nia.base.adapter;

public enum NiaApi implements UrlInterface {

	/**
	 * KT LDAP 조회(KTS 사원 인증)
	 * /nims/rest/mobileOssLogin/ossKtLogin
	 */
//	KTS_AUTH_CHECK("/nims/rest/mobileOssLogin/ossKtLogin"),

	/**
	 * 안심통보 조회
	 * /nims/rest/mobileChgCsm/searchSubsTelInfo
	 */
//	SUB_SERVICE_TEL_INFO_SELECT("/nims/rest/mobileChgCsm/searchSubsTelInfo"),

	/**
	 * 메인아이디 수정
	 *
	 */
//	UPDATE_MAIN_ID("/nims/rest/mobileChgCsm/saveBiznaruContractInfo")
	;

	private String baseUrl = ApiConstants.Nia;
	private String url;

	NiaApi(String url) {
		this.url = url;
	}

	@Override
	public String getUrl() {
		return baseUrl + url;
	}
}
