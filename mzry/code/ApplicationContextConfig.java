/**
 * Application Context Configuration.
 */
@Configuration
// 设置Spring需要扫描的目录
@ComponentScan(basePackages = {
    "cn.edu.uestc.acmicpc.db",
    "cn.edu.uestc.acmicpc.judge",
    "cn.edu.uestc.acmicpc.util",
    "cn.edu.uestc.acmicpc.service",
    "cn.edu.uestc.acmicpc.web.aspect"
})
// 设置resources文件的地址
@PropertySource("classpath:resources.properties")
// 开启事务管理
@EnableTransactionManagement
public class ApplicationContextConfig {

  @Autowired
  private Environment environment;

  /**
   * Bean: Judge service.
   *
   * JudgeService is a singleton instance and need start at first.
   *
   * @return judgeService bean
   */
  // 对Judge Service的配置
  @Bean(name = "judgeService")
  @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
  @Lazy(false)
  public JudgeService judgeService() {
    return new JudgeService();
  }

  /**
   * Bean: Data source
   *
   * We use BoneCP to manage connection poll.
   *
   * @return dataSource bean
   */
  // 对数据源的配置
  @Bean(name = "dataSource", destroyMethod = "close")
  public BoneCPDataSource dataSource() {
    BoneCPDataSource dataSource = new BoneCPDataSource();

    dataSource.setDriverClass(getProperty("db.driver"));
    dataSource.setJdbcUrl(getProperty("db.url"));
    dataSource.setUsername(getProperty("db.username"));
    dataSource.setPassword(getProperty("db.password"));
    dataSource.setMaxConnectionsPerPartition(Integer
        .parseInt(getProperty("db.maxConnectionsPerPartition")));
    dataSource.setMinConnectionsPerPartition(Integer
        .parseInt(getProperty("db.minConnectionsPerPartition")));
    dataSource.setPartitionCount(Integer.parseInt(getProperty("db.partitionCount")));
    dataSource.setAcquireIncrement(Integer.parseInt(getProperty("db.acquireIncrement")));
    dataSource.setStatementsCacheSize(Integer.parseInt(getProperty("db.statementsCacheSize")));
    return dataSource;
  }

  /**
   * Bean: session factory.
   *
   * @return sessionFactory bean
   */
  // Session Factory
  @Bean(name = "sessionFactory")
  @Lazy(false)
  public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();

    localSessionFactoryBean.setDataSource(this.dataSource());
    localSessionFactoryBean.setHibernateProperties(this.getHibernateProperties());
    localSessionFactoryBean.setAnnotatedClasses(Article.class,
        Code.class,
        CompileInfo.class,
        Contest.class,
        ContestProblem.class,
        ContestTeamInfo.class,
        ContestUser.class,
        Department.class,
        Language.class,
        Message.class,
        Problem.class,
        ProblemTag.class,
        Status.class,
        Tag.class,
        TrainingContest.class,
        TrainingStatus.class,
        TrainingUser.class,
        User.class,
        UserSerialKey.class);

    return localSessionFactoryBean;
  }

  /**
   * Bean: transaction manager.
   *
   * @return transactionManagerBean
   */
  // 事务管理工具
  @Bean(name = "transactionManager")
  public HibernateTransactionManager transactionManager() {
    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    transactionManager.setSessionFactory(this.sessionFactory().getObject());
    return transactionManager;
  }

  /**
   * Hibernate properties.
   *
   * @return properties
   */
  // Hibernate配置
  private Properties getHibernateProperties() {
    Properties properties = new Properties();
    properties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
    properties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
    properties.setProperty("hibernate.format", environment.getProperty("hibernate.format_sql"));
    properties.setProperty("hibernate.current_session_context_class",
        environment.getProperty("hibernate.current_session_context_class"));
    return properties;
  }

  /**
   * Simply get property in PropertySource.
   *
   * @param name property name
   * @return property value
   */
  private String getProperty(final String name) {
    return environment.getProperty(name);
  }
}
