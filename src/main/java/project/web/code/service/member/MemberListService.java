package project.web.code.service.member;

import lombok.RequiredArgsConstructor;
import project.web.code.dto.MemberDTO;
import project.web.code.mapper.member.MemberMapper;
import org.springframework.stereotype.Service;
import project.web.code.utils.PagingVO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberListService {
    private final MemberMapper memberMapper;
    private final PagingVO paging;
    String searchWord;

    public MemberDTO.DateResponse selectAll(Map<String, Object> param) throws Exception {

        // 결과 데이터를 저장할 객체 선언
        MemberDTO.DateResponse response = new MemberDTO.DateResponse();
        //파라메터에서 현재페이지 꺼내기
        int nowPage = (int) param.get("nowPage");
        String searchWord = (String) param.get("searchWord");
        //전체 게시글 수 가져오기
        int total = memberMapper.memberCount(searchWord);
        //페이지 처리를 위해 데이터 입력
        this.paging.setPageData(nowPage, total, searchWord);
        //페이지에 해당하는 게시글 가져오는 객체 선언
        List<MemberDTO.Response> list = new ArrayList<>();


        //개수가 존재할 때만 리스트 호출한다.
        if (total > 0) {
            if(searchWord != null ) { // searchWord가 null이 아닌 경우에만
                this.searchWord = searchWord.trim();
            }
            //페이징처리를 위한 파라메터 입력
            param.put("start", paging.getStart());
            param.put("end", paging.getEnd());
            list = memberMapper.selectAll(param);
        }
        //결과 객체에 데이터 삽입
        response.setTotal(total);
        response.setMemList(list);
        response.setPageHTML(this.paging.pageHTML());
        response.setPageNum(nowPage);

        return response;
    }
}
