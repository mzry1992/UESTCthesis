/**
 * Spring MVC Configuration file resource.
 */
public class WebMVCResource {

  // 视图解析器配置
  public static ViewResolver viewResolver() {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

    viewResolver.setPrefix("/WEB-INF/views/");
    viewResolver.setSuffix(".jsp");

    return viewResolver;
  }

  // 设置FastJson为默认的JSON格式转换器
  public static HttpMessageConverter<?>[] messageConverters() {
    HttpMessageConverter<?>[] converters = new HttpMessageConverter<?>[1];
    FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
    List<MediaType> mediaTypes = new LinkedList<>();
    mediaTypes.add(MediaType.APPLICATION_JSON);
    fastJsonHttpMessageConverter.setSupportedMediaTypes(mediaTypes);
    converters[0] = fastJsonHttpMessageConverter;
    return converters;
  }

}