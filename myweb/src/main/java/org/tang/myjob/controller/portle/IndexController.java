package org.tang.myjob.controller.portle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.TextMessage;
import org.tang.myjob.controller.Interceptor.Auth;
import org.tang.myjob.controller.utils.BaseController;
import org.tang.myjob.controller.websocket.handler.MyHandler;
import org.tang.myjob.dto.message.MessageDTO;
import org.tang.myjob.dto.product.ProductDTO;
import org.tang.myjob.dto.system.UserDTO;
import org.tang.myjob.service.LoginService;
import org.tang.myjob.service.exception.ExceptionType;
import org.tang.myjob.service.portle.IndexService;
import org.tang.myjob.service.redis.RedisUtil;
import org.tang.myjob.utils.json.JacksonUtil;
import org.tang.myjob.utils.secret.Base64;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2015/3/23.
 */

@Controller("IndexController")
public class IndexController extends BaseController  {
    public static final String SESSION_USERID = "tangUSERID";
    public static final String SESSION_AUTHS = "tangAUTHS";
    //KEY值根据SessionID生成
    private static final String SID_PREFIX = "online:sid:";
    private static final String UID_PREFIX = "online:uid:";

    private static Logger logger = Logger.getLogger(IndexController.class.getName());

    @Autowired
    private IndexService indexService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private RedisUtil redisUtil;


    @Bean
    public MyHandler myHandler() {
        return new MyHandler();
    }

//    @Autowired
//    private MyHandler myHandler;

//    //用于转发数据(sendTo)
//    @Autowired
//    private SimpMessagingTemplate template;
//
//    @Autowired
//    public IndexController(SimpMessagingTemplate t) {
//        template = t;
//    }

//    /**
//     * WebSocket聊天的相应接收方法和转发方法
//     * @param userDTO  重复登录验证
//     */
//    @MessageMapping("/isHadLogin")
//    public void isHadLogin(UserDTO userDTO) {
//        //找到需要发送的地址
//        String dest = "/isHadLogin/msg" + userDTO.getUserName();
//        this.template.convertAndSend(dest, "用户在其他地方登录");
//    }


    @RequestMapping(value = "index/loadIndexTopNews", method = {RequestMethod.POST , RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> loadIndexTopNews() throws Exception {

        Map<String ,Object> m = new HashMap<String ,Object>();

        MessageDTO dto = null;
        try {
            dto = indexService.getMessage();
            m.put("msg","success");
        }
        catch (Exception br){
            logger.error(ExceptionType.product_msg, br);
            throw new Exception(br);
        }
        m.put("result",dto);
        return m;

    }


    @RequestMapping(value = "index/loadIndexProducts", method = {RequestMethod.POST , RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> loadIndexProducts() throws Exception {

        Map<String ,Object> m = new HashMap<String ,Object>();

        List<ProductDTO> dtoList = null;
        ProductDTO param = new ProductDTO();
        param.setType(1);
        try {
            dtoList = indexService.getProduct(param);
        } catch (Exception e) {
            logger.error(ExceptionType.product_msg,e);
            throw new Exception(ExceptionType.product_msg,e);
        }
        m.put("result",dtoList);
        return m;

    }

    @RequestMapping(value = "index/loadIndexTopProduct", method = {RequestMethod.POST , RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> loadIndexTopProduct() throws Exception {

        Map<String ,Object> m = new HashMap<String ,Object>();

        List<ProductDTO> dtoList = null;
        ProductDTO param = new ProductDTO();
        param.setType(0);
        try {
            dtoList = indexService.getProduct(param);
        } catch (Exception e) {
            logger.error(ExceptionType.product_msg,e);
            throw new Exception(ExceptionType.product_msg,e);
        }
        m.put("result",dtoList);
        return m;

    }

    @Auth
    @RequestMapping(value={"/index"})
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("/index");
        return mv;
    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST , RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> login(HttpSession session, String first) throws Exception {

        if(StringUtils.hasText(first)){
            Map<String ,Object> m = new HashMap<String ,Object>();
            String user = Base64.getFromBase64(first);
            UserDTO dto =  JacksonUtil.readValue(user, UserDTO.class);

            boolean flag = false;

            try {
                //如果此用户已登陆，其他人使用该帐号在其他地方登陆,强制下线前一个
                if(loginService.isOnline(dto.getUserName())){
                    loginService.logout(dto.getUserName());
                    myHandler().sendMessageToUser(dto.getUserName(),new TextMessage("用户在其他地方登陆"));
//                    systemWebSocketHandler().sendMessageToUser(dto.getUserName(), new TextMessage("用户在其他地方登陆"));
                }

                UserDTO userDTO = loginService.queryUser(dto);
                if(userDTO!=null){
                    try {
                        session.setAttribute(SESSION_USERID,userDTO.getUserName());
                        logger.info("SESSION_USERID::"+session.getAttribute(SESSION_USERID));
                        loginService.login(session.getId(),userDTO);

                        //redis订阅用户频道
//                        redisSubscribeUser(dto.getUserName());

                        m.put("user",userDTO);
                        m.put("msg","success");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                logger.error(ExceptionType.login_msg,e);
            }
            return m;
        }
        return  null;
    }



    @RequestMapping(value = "/loginOut", method = {RequestMethod.POST , RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> loginOut(HttpSession session, String first) throws Exception {

        if(StringUtils.hasText(first)){
            Map<String ,Object> m = new HashMap<String ,Object>();
            String user = Base64.getFromBase64(first);
            UserDTO dto =  JacksonUtil.readValue(user, UserDTO.class);
            boolean flag = false;
            try {
                loginService.logout(session.getId(),dto.getUserName());
                flag = redisUtil.delKey(session.getId());
                if(flag){
                    m.put("msg","success");
                }
            } catch (Exception e) {
                logger.error(ExceptionType.login_out_msg,e);
            }
            return m;
        }
        return  null;
    }


    @RequestMapping(value = "/onlineUser", method = {RequestMethod.POST , RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> onlineUser(HttpSession session) throws Exception {

        boolean flag = false;
        Map<String ,Object> m = new HashMap<String ,Object>();
        try {
            Set set = redisUtil.getConnection().smembers(SID_PREFIX);

            int numbers = set.size();
            List<UserDTO> userDTOList = null;
            //好友列表

            //在线好友列表

            //系统在线用户
            if(flag){
                m.put("msg","success");
            }
        } catch (Exception e) {
            logger.error(ExceptionType.login_out_msg,e);
        }
        return m;
    }

//    @Bean
//    public SystemWebSocketHandler systemWebSocketHandler() {
//        return new SystemWebSocketHandler();
//    }
//
//
//    private void redisSubscribeUser(String channel){
//        try {
//            Jedis jedis = redisUtil.getConnection();
//            final RedisSubscribeListener redisSubscribeListener = new RedisSubscribeListener();
//            jedis.psubscribe(redisSubscribeListener, channel);
//            redisUtil.closeConnection(jedis);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }




}
