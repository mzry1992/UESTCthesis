@Bean
@Primary
public UserService mockUserService() {
  return mock(UserService.class);
}