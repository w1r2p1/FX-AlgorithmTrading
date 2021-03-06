package ny2.ats.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ny2.ats.core.router.IEventRouter;
import ny2.ats.indicator.IIndicatorDataHolder;
import ny2.ats.information.IEventGenarator;
import ny2.ats.market.transport.IMarketManager;

/**
 * Entry Point for Application For TEST
 */
public class StartAppTest {

    private static final String SPRING_CONFIG_FILE = "classpath:applicationContext.xml";

    private static final Logger logger = LoggerFactory.getLogger(StartApp.class);

    public static void main(String[] args) {
        // For rotating error log
        logger.error("START Application.");

        try {
            // Context初期化
            ApplicationContext context = new ClassPathXmlApplicationContext(SPRING_CONFIG_FILE);

            // EventRouter設定
            IEventRouter eventRouter = context.getBean(IEventRouter.class);
            eventRouter.trimListenerLists();

            // Indicatorの計算対象設定
            IIndicatorDataHolder indicatorDataHolder = context.getBean(IIndicatorDataHolder.class);
            indicatorDataHolder.initialize();

            // 接続先にログイン
            IMarketManager marketManager = context.getBean(IMarketManager.class);
            // marketManager.loginMarket(MarketType.CENTRAL);


            //
            // テスト用
            //

            // Model


            // Model Start
            IEventGenarator eventGenarator = context.getBean(IEventGenarator.class);
            eventGenarator.sendModelStartEvent();

        } catch (Exception e) {
            logger.error("", e);
        }
    }
}
