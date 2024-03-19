package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.TboardDao;
import com.javaex.vo.TboardVo;

@Service
public class TboardService {

	@Autowired
	private TboardDao tboardDao;

	// 리스트(검색X,페이징 X)
	public List<TboardVo> exeList() {
		System.out.println("TboardService.exeList()");

		List<TboardVo> boardList = tboardDao.boardSelectList();

		return boardList;

	}

	// 리스트(검색X,페이징 0)
	public Map<String, Object> exeList2(int crtPage) {
		System.out.println("TboardService.exeList2()");

		// crtPage가 음수로 들어왓을때 1로 처리->삼항연산자
		crtPage = (crtPage < 0) ? crtPage = 1 : crtPage;

		// 한페이지당 출력 글 개수
		int listCnt = 10;

		// 페이지당 시작할 글 번호
		int startRowNo = (crtPage - 1) * 10;

		// listCnt와 startRowNo를 맵으로 묶는다
		Map<String, Integer> limitMap = new HashMap<String, Integer>();
		limitMap.put("startRowNo", startRowNo);
		limitMap.put("listCnt", listCnt);

		List<TboardVo> boardList = tboardDao.boardSelectList2(limitMap);
		///// 여기까지 리스트 가져오기

		//// 여기부터 페이징계산
		// 페이지당 버튼 개수
		int pageBtnCnt = 5;

		// 전체 글개수
		int totalCnt = tboardDao.totalCount();

		// 마지막 버튼 번호
		int endPageBtnNo = (int) Math.ceil(crtPage / (double) pageBtnCnt) * pageBtnCnt;

		// 시작 버튼 번호
		int startPageNo = (endPageBtnNo - pageBtnCnt) + 1;

		// 다음 화살표 유무
		boolean next = false;
		if (listCnt * endPageBtnNo < totalCnt) {
			next = true;
		}else {
			endPageBtnNo = (int)Math.ceil(totalCnt/(double)listCnt);
		}

		// 이전 화살표 유무
		boolean pre = false;
		if (startPageNo != 1) {
			pre = true;
		}

		// Map으로 묶어서 보내기
		Map<String, Object> tboardMap = new HashMap<String, Object>();
		tboardMap.put("boardList", boardList);
		tboardMap.put("startPageNo", startPageNo);
		tboardMap.put("endPageBtnNo", endPageBtnNo);
		tboardMap.put("pre", pre);
		tboardMap.put("next", next);

		System.out.println(tboardMap);

		return tboardMap;

	}
	
	// 리스트(검색O,페이징 O)
		public Map<String, Object> exeList3(int crtPage, String keyword) {
			System.out.println("TboardService.exeList3()");

			// crtPage가 음수로 들어왓을때 1로 처리->삼항연산자
			crtPage = (crtPage < 0)? crtPage = 1 : crtPage;

			// 한페이지당 출력 글 개수
			int listCnt = 10;

			// 페이지당 시작할 글 번호
			int startRowNo = (crtPage - 1) * 10;

			// listCnt와 startRowNo, keyword를 맵으로 묶는다
			Map<String, Object> limitMap = new HashMap<String, Object>();
			limitMap.put("startRowNo", startRowNo);
			limitMap.put("listCnt", listCnt);
			limitMap.put("keyword", keyword);

			List<TboardVo> boardList = tboardDao.boardSelectList3(limitMap);
			///// 여기까지 리스트 가져오기

			//// 여기부터 페이징계산
			// 페이지당 버튼 개수
			int pageBtnCnt = 5;

			// 전체 글개수
			int totalCnt = tboardDao.totalCount3(keyword);

			// 마지막 버튼 번호
			int endPageBtnNo = (int) Math.ceil(crtPage / (double) pageBtnCnt) * pageBtnCnt;

			// 시작 버튼 번호
			int startPageNo = (endPageBtnNo - pageBtnCnt) + 1;

			// 다음 화살표 유무
			boolean next = false;
			if (listCnt * endPageBtnNo < totalCnt) {
				next = true;
			}else {
				endPageBtnNo = (int)Math.ceil(totalCnt/(double)listCnt);
			}

			// 이전 화살표 유무
			boolean pre = false;
			if (startPageNo != 1) {
				pre = true;
			}

			// Map으로 묶어서 보내기
			Map<String, Object> tboardMap = new HashMap<String, Object>();
			tboardMap.put("boardList", boardList);
			tboardMap.put("startPageNo", startPageNo);
			tboardMap.put("endPageBtnNo", endPageBtnNo);
			tboardMap.put("pre", pre);
			tboardMap.put("next", next);

			System.out.println(tboardMap);

			return tboardMap;

		}

}
