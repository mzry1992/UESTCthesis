/**
 * Authentication aspect
 */
@Component
// 声明侧面
@Aspect
public class AuthenticationAspect {

  /**
   * Http request
   */
  private HttpServletRequest request;

  // 注入HttpServletRequest
  @Autowired(required = true)
  public void setRequest(HttpServletRequest request) {
    this.request = request;
  }

  // 声明该侧面的作用范围为包围在所有有LoginPermit注解的函数上
  @Around("@annotation(cn.edu.uestc.acmicpc.util.annotation.LoginPermit)")
  public Object checkAuth(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
    Method method = methodSignature.getMethod();
    // 获取函数上的LoginPermit注解
    LoginPermit permit = method.getAnnotation(LoginPermit.class);

    try {
      // 如果该函数需要登录后才能访问
      if (permit.NeedLogin()) {
        // 获得当前用户
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute("currentUser");
        // 如果当前未登录，那么就抛出权限异常
        if (userDTO == null) {
          throw new AppException("Permission denied");
        }
        // 如果权限不足，那么就抛出权限异常
        if (permit.value() != Global.AuthenticationType.NORMAL) {
          if (userDTO.getType() != permit.value().ordinal()) {
            throw new AppException("Permission denied");
          }
        }
      }
      // 至此，权限验证已经完毕，继续函数的运行
      return proceedingJoinPoint.proceed();
    } catch (AppException e) {
      // 捕获权限异常
      // 如果该方法返回值是视图地址，那么我们将其重定向到错误页面上
      if (method.getReturnType() == String.class) {
        return "redirect:/error/authenticationError";
      }
      // 否则这个方法返回的是一个Map，我们要做的是设置相应的错误信息
      else {
        Map<String, Object> json = new HashMap<>();
        json.put("result", "error");
        json.put("error_msg", e.getMessage());
        return json;
      }
    }
  }

}