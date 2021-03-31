package com.yl.fegin;

import com.yl.api.member.service.MemberUserService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient("member")
public interface MemberServiceFegin extends MemberUserService {

}
