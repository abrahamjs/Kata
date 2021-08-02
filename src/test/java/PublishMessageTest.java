import org.junit.Assert;
import org.junit.Test;
import org.ocpsoft.prettytime.PrettyTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PublishMessageTest {
    @Test
    public void publishMessage(){

        User alice = new User();
        alice.setUserId("1");
        alice.setUserName("Alice");
        Assert.assertTrue(alice.getUserTimeline().getTimeLineMessages().isEmpty());

        //Create message
        String testMessage = "I love the weather today.";
        Message message = new Message();
        message.setUserName(alice.getUserName());
        message.setMessageContent(testMessage);
        message.setTimestamp(new Date().getTime());
        //Publish (Add message to Alice's timeline)
        alice.publishMessage(message);
        //Create Test Timeline with one message
        List<Message> testList = new ArrayList<>();
        testList.add(message);

        //Test if Alice's timeline matches our test
        Assert.assertTrue(alice.getUserTimeline().getTimeLineMessages().equals(testList));
        alice.getUserTimeline().getTimeLineMessagesWithTimeAgo().forEach(message1 -> {System.out.println(message1.viewMessage());});


    }
    @Test
    public void bobsTimeLine() {
        //"Alice" watches Bob's timeline
        //I use the PrettyTime library to compare the time of the publication with current time and get a "time ago" string representation
        PrettyTime p = new PrettyTime();
        //Create a time to make sure all the comparisons are made with the same timestamp
        Date date = new Date();

        User bob = new User();
        bob.setUserId("2");
        bob.setUserName("Bob");
        Message bobMessage1 = new Message();
        bobMessage1.setUserName(bob.getUserName());
        bobMessage1.setMessageContent("Darn! We lost!");
        bobMessage1.setTimestamp(date.getTime() - 1000 * 60 * 2);

        Message bobMessage2 = new Message();
        bobMessage2.setUserName(bob.getUserName());
        bobMessage2.setMessageContent("Good game though.");
        bobMessage2.setTimestamp(date.getTime() - 1000 * 60 );

        //Publish (Add message to Bob's timeline)
        bob.publishMessage(bobMessage1);
        bob.publishMessage(bobMessage2);
        //Create Mock List of messages that should be in Bob's timeline
        List<Message> bobsTimeLine = new ArrayList<>();
        bobMessage1.setTimeAgo(p.format(new Date(bobMessage1.getTimestamp())));
        bobMessage2.setTimeAgo(p.format(new Date(bobMessage2.getTimestamp())));
        bobsTimeLine.add(bobMessage2);
        bobsTimeLine.add(bobMessage1);






        //Validate that Bob's timeline matches our mock list
        Assert.assertTrue(bob.getUserTimeline().getTimeLineMessagesWithTimeAgo().equals(bobsTimeLine));
        bob.getUserTimeline().getTimeLineMessagesWithTimeAgo().forEach(message -> {System.out.println(message.viewMessage());});
    }
}
