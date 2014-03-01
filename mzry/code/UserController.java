// 声明控制器
@Controller
// 声明控制器的域名
@RequestMapping("/user")
public class UserController extends BaseController {

  // /user/login对应的方法
  @RequestMapping("login")
  // 登陆权限设置为无
  @LoginPermit(NeedLogin = false)
  public
  // 返回值为JSON数据
  @ResponseBody
  Map<String, Object> login(HttpSession session,
                            @RequestBody @Valid UserLoginDTO userLoginDTO,
                            BindingResult validateResult) {
    Map<String, Object> json = new HashMap<>();
    // 表单验证失败
    if (validateResult.hasErrors()) {
      json.put("result", "field_error");
      json.put("field", validateResult.getFieldErrors());
    } else {
      try {
        // 获取登陆用户的信息
        UserDTO userDTO = userService.getUserDTOByUserName(userLoginDTO.getUserName());
        // 密码验证
        if (userDTO == null || !userLoginDTO.getPassword().equals(userDTO.getPassword())) {
          throw new FieldException("password", "User or password is wrong, please try again");
        }
        // 更新用户
        userDTO.setLastLogin(new Timestamp(new Date().getTime() / 1000 * 1000));
        userService.updateUser(userDTO);

        // 将用户信息放入Session中
        session.setAttribute("currentUser", userDTO);

        // 构造返回数据
        json.put("userName", userDTO.getUserName());
        json.put("type", userDTO.getType());
        json.put("email", userDTO.getEmail());
        json.put("result", "success");
      } catch (FieldException e) {
        // 如果存在表单错误
        putFieldErrorsIntoBindingResult(e, validateResult);
        json.put("result", "field_error");
        json.put("field", validateResult.getFieldErrors());
      } catch (AppException e) {
        // 如果发生其它错误
        json.put("result", "error");
        json.put("error_msg", e.getMessage());
      }
    }
    // 返回结果
    return json;
  }

}