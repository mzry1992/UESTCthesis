// 建立一个浏览器模拟对象
MockMvc mockMvc = standaloneSetup(indexController)
        .setViewResolvers(WebMVCResource.viewResolver())
        .setMessageConverters(WebMVCResource.messageConverters())
        .build();

// 模拟Get操作，访问/地址
// 期望结果为：
//    返回状态为OK
//    视图名字是index/index
//    且模型中有期望的消息
mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(view().name("index/index"))
        .andExpect(model().attribute("message", "home page."));