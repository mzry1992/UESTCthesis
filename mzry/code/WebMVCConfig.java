/**
 * Spring MVC configuration file.
 */
@Configuration
// 开启Web MVC模式
@EnableWebMvc
// 指定Controller包位置
@ComponentScan(basePackages = {
    "cn.edu.uestc.acmicpc.web.oj.controller"
})
// 开启AspectJ代理
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class WebMVCConfig extends WebMvcConfigurerAdapter {

  // 注册静态资源代理
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/images/**").addResourceLocations("/images/**");
    registry.addResourceHandler("/plugins/**").addResourceLocations("/plugins/**");
    registry.addResourceHandler("/scripts/**").addResourceLocations("/scripts/**");
    registry.addResourceHandler("/styles/**").addResourceLocations("/styles/**");
    registry.addResourceHandler("/font/**").addResourceLocations("/font/**");
  }

  // 开启ServletHandler
  @Override
  public void configureDefaultServletHandling(
      DefaultServletHandlerConfigurer defaultServletHandlerConfigurer) {
    defaultServletHandlerConfigurer.enable();
  }

  // 注册消息转换器
  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.addAll(Arrays.asList(WebMVCResource.messageConverters()));
  }

  // 视图解析器
  @Bean
  public ViewResolver viewResolver() {
    return WebMVCResource.viewResolver();
  }

  // 启用多文件上传
  @Bean
  public MultipartResolver multipartResolver() {
    return new CommonsMultipartResolver();
  }

}
