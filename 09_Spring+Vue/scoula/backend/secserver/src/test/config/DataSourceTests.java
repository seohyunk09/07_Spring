@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= {RootConfig.class})
@Log4j2
class RootConfigTest {
    @Autowired
    private DataSource dataSource;
    @Test
    @DisplayName("DataSource 연결이 된다.")
    public void dataSource() throws SQLException {
        try(Connection con = dataSource.getConnection()){
            log.info("DataSource 준 비 완 료");

            log.info(con);
        }
    }
}