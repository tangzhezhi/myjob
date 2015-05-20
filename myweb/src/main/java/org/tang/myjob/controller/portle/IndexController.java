package org.tang.myjob.controller.portle;

import org.tang.myjob.controller.utils.BaseController;

/**
 * Created by Administrator on 2015/3/23.
 */

//@Controller("IndexController")
public class IndexController extends BaseController  {

//
//    private static Logger logger = Logger.getLogger(IndexController.class.getName());
//
//    @Autowired
//    private IndexService indexService;
//
//    @Autowired
//    private LoginService loginService;
//
//    @Autowired
//    private RedisUtil redisUtil;
//
//
////    @Bean
////    public MyHandler myHandler() {
////        return new MyHandler();
////    }
//
////    @Autowired
////    private MyHandler myHandler;
//
////    //用于转发数据(sendTo)
//    @Autowired
//    private SimpMessagingTemplate template;
////
//    @Autowired
//    public IndexController(SimpMessagingTemplate t) {
//        template = t;
//    }
//
//    @RequestMapping(value = "index/loadIndexTopNews", method = {RequestMethod.POST , RequestMethod.GET})
//    @ResponseBody
//    public Map<String, Object> loadIndexTopNews() throws Exception {
//
//        Map<String ,Object> m = new HashMap<String ,Object>();
//
//        MessageDTO dto = null;
//        try {
//            dto = indexService.getMessage();
//            m.put("msg","success");
//        }
//        catch (Exception br){
//            logger.error(ExceptionType.product_msg, br);
//            throw new Exception(br);
//        }
//        m.put("result",dto);
//        return m;
//
//    }
//
//
//    @RequestMapping(value = "index/loadIndexProducts", method = {RequestMethod.POST , RequestMethod.GET})
//    @ResponseBody
//    public Map<String, Object> loadIndexProducts() throws Exception {
//
//        Map<String ,Object> m = new HashMap<String ,Object>();
//
//        List<ProductDTO> dtoList = null;
//        ProductDTO param = new ProductDTO();
//        param.setType(1);
//        try {
//            dtoList = indexService.getProduct(param);
//        } catch (Exception e) {
//            logger.error(ExceptionType.product_msg,e);
//            throw new Exception(ExceptionType.product_msg,e);
//        }
//        m.put("result",dtoList);
//        return m;
//
//    }
//
//    @RequestMapping(value = "index/loadIndexTopProduct", method = {RequestMethod.POST , RequestMethod.GET})
//    @ResponseBody
//    public Map<String, Object> loadIndexTopProduct() throws Exception {
//
//        Map<String ,Object> m = new HashMap<String ,Object>();
//
//        List<ProductDTO> dtoList = null;
//        ProductDTO param = new ProductDTO();
//        param.setType(0);
//        try {
//            dtoList = indexService.getProduct(param);
//        } catch (Exception e) {
//            logger.error(ExceptionType.product_msg,e);
//            throw new Exception(ExceptionType.product_msg,e);
//        }
//        m.put("result",dtoList);
//        return m;
//
//    }
//
//
//    @Auth
//    @RequestMapping(value={"/index"})
//    public ModelAndView index(){
//        ModelAndView mv = new ModelAndView("/index");
//        return mv;
//    }
//
//    @RequestMapping(value = "/login", method = {RequestMethod.POST , RequestMethod.GET})
//    @ResponseBody
//    public Map<String, Object> login(HttpSession session, String first) throws Exception {
//
//        if(StringUtils.hasText(first)){
//            Map<String ,Object> m = new HashMap<String ,Object>();
//            String user = Base64.getFromBase64(first);
//            UserDTO dto =  JacksonUtil.readValue(user, UserDTO.class);
//
//            boolean flag = false;
//
//            try {
//                //如果此用户已登陆，其他人使用该帐号在其他地方登陆,强制下线前一个
//                if(loginService.isOnline(dto.getUserName())){
//                    loginService.logout(dto.getUserName());
//                    this.repeatLoginMsg(dto.getUserName(),"测试消息");
//                }
//
//                UserDTO userDTO = loginService.queryUser(dto);
//                if(userDTO!=null){
//                    try {
//                        session.setAttribute(SESSION_USERID,userDTO.getUserName());
//                        logger.info("SESSION_USERID::"+session.getAttribute(SESSION_USERID));
//                        loginService.login(session.getId(),userDTO);
//
//                        //redis订阅用户频道
////                        redisSubscribeUser(dto.getUserName());
//
//                        m.put("user",userDTO);
//                        m.put("msg","success");
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            } catch (Exception e) {
//                logger.error(ExceptionType.login_msg,e);
//            }
//            return m;
//        }
//        return  null;
//    }
//
//
//
//    @RequestMapping(value = "/loginOut", method = {RequestMethod.POST , RequestMethod.GET})
//    @ResponseBody
//    public Map<String, Object> loginOut(HttpSession session, String first) throws Exception {
//
//        if(StringUtils.hasText(first)){
//            Map<String ,Object> m = new HashMap<String ,Object>();
//            String user = Base64.getFromBase64(first);
//            UserDTO dto =  JacksonUtil.readValue(user, UserDTO.class);
//            boolean flag = false;
//            try {
//                loginService.logout(session.getId(),dto.getUserName());
//                flag = redisUtil.delKey(session.getId());
//                if(flag){
//                    m.put("msg","success");
//                }
//            } catch (Exception e) {
//                logger.error(ExceptionType.login_out_msg,e);
//            }
//            return m;
//        }
//        return  null;
//    }
//
//
//    @RequestMapping(value = "/onlineUser", method = {RequestMethod.POST , RequestMethod.GET})
//    @ResponseBody
//    public Map<String, Object> onlineUser(HttpSession session) throws Exception {
//
//        boolean flag = false;
//        Map<String ,Object> m = new HashMap<String ,Object>();
//        try {
//            Set set = redisUtil.getConnection().smembers(SID_PREFIX);
//
//            int numbers = set.size();
//            List<UserDTO> userDTOList = null;
//            //好友列表
//
//            //在线好友列表
//
//            //系统在线用户
//            if(flag){
//                m.put("msg","success");
//            }
//        } catch (Exception e) {
//            logger.error(ExceptionType.login_out_msg,e);
//        }
//        return m;
//    }
//
//
//
//    public void repeatLoginMsg(String userid,String message) throws Exception {
//        logger.info("repeatLoginMsg:message:"+message);
//        MessageDTO messageDTO = new MessageDTO();
//        messageDTO.setTitle("您被挤下线");
//        messageDTO.setContent("此用户帐号在其他地方登陆");
//        messageDTO.setCreateDate(new Date());
//        template.convertAndSend("/topic/repeatLogin/"+userid,messageDTO);
//
//    }
//
//
////    @Bean
////    public SystemWebSocketHandler systemWebSocketHandler() {
////        return new SystemWebSocketHandler();
////    }
////
////
////    private void redisSubscribeUser(String channel){
////        try {
////            Jedis jedis = redisUtil.getConnection();
////            final RedisSubscribeListener redisSubscribeListener = new RedisSubscribeListener();
////            jedis.psubscribe(redisSubscribeListener, channel);
////            redisUtil.closeConnection(jedis);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }




}
