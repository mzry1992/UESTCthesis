/**
 * DTO post from user login form.
 */
public class UserLoginDTO {

  /**
   * Input: user name
   */
  // 非空验证
  @NotNull(message = "Please enter your user name.")
  // 正则表达式验证
  @Pattern(regexp = "\\b^[a-zA-Z0-9_]{4,24}$\\b",
      message = "Please enter 4-24 characters consist of A-Z, a-z, 0-9 and '_'.")
  private String userName;

  /**
   * Input: password
   */
  // 非空验证
  @NotNull(message = "Please enter your password.")
  // 长度验证
  @Length(min = 40, max = 40, message = "Please enter your password.")
  private String password;

}