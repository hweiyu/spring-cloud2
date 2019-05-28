package com.imin.example.controller;

import com.imin.example.dto.DemoProto;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author code
 * @version V1.0
 * @Title: 基础代码
 * @Description: Protobuf用例
 **/
@Controller
@RequestMapping("${api.url.prefix}")
public class ProtobufController extends BaseController {

    @RequestMapping(value = "/proto/demo", method= RequestMethod.POST, produces = "application/x-protobuf")
    public ResponseEntity<DemoProto.Demo> protoTest(RequestEntity<DemoProto.Demo> requestEntity) {
        DemoProto.Demo demo =  requestEntity.getBody();
        return ResponseEntity.ok(demo);
    }

}
