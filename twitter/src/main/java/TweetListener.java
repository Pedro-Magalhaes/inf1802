import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

import java.util.logging.Logger;

public class TweetListener implements StatusListener {

    private static final Logger logger = Logger.getLogger(TweetListener.class.getName());

    @Override
    public void onStatus(Status status) {
        Tweet t = new Tweet(status.getUser().getName(),status.getText(),status.getCreatedAt());
        logger.info("Tweet criado: " + t.toString());
    }

    @Override
    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
        logger.info("delete");
    }

    @Override
    public void onTrackLimitationNotice(int i) {
        logger.info("track");
    }

    @Override
    public void onScrubGeo(long l, long l1) {

    }

    @Override
    public void onStallWarning(StallWarning stallWarning) {
        logger.info("stall");
    }

    @Override
    public void onException(Exception e) {
        e.printStackTrace();
        logger.info("exeption");
    }
}
