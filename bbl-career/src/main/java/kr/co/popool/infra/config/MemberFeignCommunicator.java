package kr.co.popool.infra.config;

import kr.co.popool.bblcommon.error.model.ResponseFormat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "bbl-member",url = "localhost:8001")

public interface MemberFeignCommunicator {

  @RequestMapping(
      method= RequestMethod.GET,
      value="/members/identity/info")
   ResponseFormat getMemberIdentity();



}
