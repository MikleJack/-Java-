package com.example.demo.controller;

import com.example.demo.entity.VerifyCode;
import com.example.demo.service.IVerifyCodeGen;
import com.example.demo.service.impl.SimpleCharVerifyCodeGenImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
@RestController
@RequestMapping("verifycode")
public class VertifyCodeController {

    private IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();

    @ApiOperation(value = "验证码")
    @GetMapping("/getVertifyCodebyId")
    public IVerifyCodeGen getVertifyCodebyId(HttpServletRequest request, HttpServletResponse response){
        verifyCode(request, response);
        return  iVerifyCodeGen;
    }

    //返回验证码的字符串
    @ApiOperation(value = "验证码")
    @GetMapping("/getStringOfVertifyCode")
    public String getVertifyCodebyId(){
        return iVerifyCodeGen.getCode();
    }

    @ApiOperation(value = "验证码")
    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
//        this.iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        try {
            //设置长宽
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
            String code = verifyCode.getCode();
            LOGGER.info(code);
            //将VerifyCode绑定session
            request.getSession().setAttribute("VerifyCode", code);
            //设置响应头
            response.setHeader("Pragma", "no-cache");
            //设置响应头
            response.setHeader("Cache-Control", "no-cache");
            //在代理服务器端防止缓冲
            response.setDateHeader("Expires", 0);
            //设置响应内容类型
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
        } catch (IOException e) {
            LOGGER.info("验证发生错误");
        }
    }

}
