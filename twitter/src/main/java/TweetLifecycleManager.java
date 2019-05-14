import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.Serializable;
import java.util.logging.Logger;

public class TweetLifecycleManager implements LifecycleManager, Serializable {
    private static final Logger logger = Logger.getLogger(TweetLifecycleManager.class.getName());
    private ConfigurationBuilder configurationBuilder;
    private TwitterStream twitterStream;
    private TwitterStreamFactory tf;


    public TweetLifecycleManager() {

        String _consumerKey = System.getenv().get("TWITTER_CONSUMER_KEY");
        String _consumerSecret = System.getenv().get("TWITTER_CONSUMER_SECRET");
        String _accessToken = System.getenv().get("TWITTER_ACCESS_TOKEN");
        String _accessTokenSecret = System.getenv().get("TWITTER_ACCESS_TOKEN_SECRET");

        this.configurationBuilder = new ConfigurationBuilder();

        configurationBuilder.setOAuthConsumerKey(_consumerKey)
                .setOAuthConsumerSecret(_consumerSecret)
                .setOAuthAccessToken(_accessToken)
                .setOAuthAccessTokenSecret(_accessTokenSecret);

        this.tf = new TwitterStreamFactory( configurationBuilder.build() );

        this.twitterStream = this.tf.getInstance();

        this.twitterStream.addListener(new TweetListener());
    }

    @Override
    public void start() {
        this.twitterStream.filter(new FilterQuery("rio de janeiro"));
        logger.info("started the stream");
    }

    @Override
    public void stop() {
        this.twitterStream.cleanUp();
        this.twitterStream.clearListeners();
    }


}
