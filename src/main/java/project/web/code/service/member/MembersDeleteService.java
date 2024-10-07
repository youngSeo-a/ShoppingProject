package project.web.code.service.member;

import lombok.RequiredArgsConstructor;
import project.web.code.mapper.member.MemberMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembersDeleteService {

   private final MemberMapper memberMapper;
    public void execute(String memDels[]){
        memberMapper.membersDelete(memDels);
    }
}
