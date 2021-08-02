import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.ocpsoft.prettytime.PrettyTime;

import java.util.*;
import java.util.stream.Collectors;

public class ViewWallTest {

    @Before
    public void setUp(){

    }

    @Test
    public void followUser(){
        User alice = new User();
        alice.setUserId("1");
        alice.setUserName("Alice");

        User bob = new User();
        bob.setUserId("2");
        bob.setUserName("Bob");

        User charlie  = new User();
        charlie.setUserId("3");
        charlie.setUserName("Charlie");

        Date date = new Date();
        Message aliceMessage1 = new Message();
        aliceMessage1.setUserName(alice.getUserName());
        aliceMessage1.setMessageContent("I love the weather today.");
        aliceMessage1.setTimestamp(date.getTime() - 1000 * 60 * 5);
        alice.publishMessage(aliceMessage1);

        Message bobMessage1 = new Message();
        bobMessage1.setUserName(bob.getUserName());
        bobMessage1.setMessageContent("Darn! We lost!");
        bobMessage1.setTimestamp(date.getTime() - 1000 * 60);
        bob.publishMessage(bobMessage1);

        Message bobMessage2 = new Message();
        bobMessage2.setUserName(bob.getUserName());
        bobMessage2.setMessageContent("Good game though.");
        bobMessage2.setTimestamp(date.getTime() - 1000 * 60 * 2);
        bob.publishMessage(bobMessage2);

        Message charlieMessage1 = new Message();
        charlieMessage1.setUserName(charlie.getUserName());
        charlieMessage1.setMessageContent("I'm in New York today! Anyone wants to have a coffee?");
        charlieMessage1.setTimestamp(date.getTime());
        charlie.publishMessage(charlieMessage1);
        //Check if charlie has no followers
        Assert.assertTrue (charlie.getFollowers().isEmpty());

        //Charlie follows Alice and Bob
        charlie.followUser(alice);
        charlie.followUser(bob);
        //Check that charlie is following 2 people now
        Assert.assertEquals (charlie.getFollowers().size(), 2);

        PrettyTime p = new PrettyTime();
        List<Message> messagesInWall = new ArrayList<>();
        messagesInWall.add(charlieMessage1);
        messagesInWall.add(bobMessage1);
        messagesInWall.add(bobMessage2);
        messagesInWall.add(aliceMessage1);

        //Add time ago to the mock messages list
        messagesInWall.forEach(message -> {
                        message.setTimeAgo(p.format(new Date(message.getTimestamp())));});
        //Check if Charlie's wall has all aggregated messages in the correct order
        Assert.assertEquals(messagesInWall, charlie.getUserWall().retrieveWallWithTimeAgo());

        charlie.getUserWall().retrieveWallWithTimeAgo().forEach(message -> {
            System.out.println(message.viewMessage());
        });
    }



}
