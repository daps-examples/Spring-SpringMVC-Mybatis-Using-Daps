package bestwaiting;

import cn.bjca.daps.driver.DapsDriver;
import cn.bjca.daps.infra.util.elf.Io;
import com.mysql.jdbc.Driver;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import static cn.bjca.daps.driver.jdbc.core.driver.DriverDataSourceCache.DAPS_CONF;

public class Init implements InitializingBean {
    private Logger logger = Logger.getLogger(this.getClass());

    static {
        // System.setProperty(DAPS_CONF, "raw:" + Io.toString(Io.readClasspath("daps/daps.json")));
        try {
            Class.forName(DapsDriver.class.getName());
            Class.forName(Driver.class.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void afterPropertiesSet() throws Exception {
        logger.info("DAPS DAPS_CONF dapsConf 规则 加载到的配置是 " + System.getProperty(DAPS_CONF));
    }
}
