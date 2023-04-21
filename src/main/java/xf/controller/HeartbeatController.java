package xf.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/")
public class HeartbeatController {

    @ResponseBody
    @RequestMapping("/hb")
    public String hb(HttpServletResponse response) {
        return "1";
    }

}
