/*import Entities.ActorContext.ActorContext;
import Entities.ActorProxy.ActorProxy;
import Entities.DynamicProxy.DynamicProxy;
import Entities.EncryptionDecorator.EncryptionDecorator;
import Entities.FirewallDecorator.FirewallDecorator;
import Entities.HelloWorldActor.HelloWorldActor;
import Entities.InsultActor.InsultActor;
import Entities.InsultService.InsultService;
import Entities.InsultService.InsultServiceInterface;
import Entities.Message.Message;*/

import Api.HttpServer.HttpServer;
import Entities.ActorContext.ActorContext;
import Entities.ActorListener.ActorListener;
import Entities.ActorProxy.ActorProxy;
import Entities.DynamicProxy.DynamicProxy;
import Entities.EncryptionDecorator.EncryptionDecorator;
import Entities.Enums.TrafficLevel;
import Entities.FirewallDecorator.FirewallDecorator;
import Entities.HelloWorldActor.HelloWorldActor;
import Entities.Insult.AddInsultMessage;
import Entities.Insult.GetInsultMessage;
import Entities.InsultActor.InsultActor;
import Entities.InsultService.InsultService;
import Entities.InsultService.InsultServiceInterface;
import Entities.LambdaFirewallDecorator.LambdaFirewallDecorator;
import Entities.Message.Message;
import Entities.MoninorService.MonitorService;
import Entities.PingPongActor.TestPingPongActor;
import Entities.RingActor.RingActor;
import Entities.RingActor.TestRingActor;

import java.util.function.Predicate;

public class Main {
    private final static Integer PORT = 9000;
    public static void main(String[] args) throws Throwable {
        HttpServer httpServer = new HttpServer(PORT);
        httpServer.startServer();

        testingFunctionsBack();
    }

    public static void testingFunctionsBack() {
        /*Part 1*/

        ActorProxy hello = ActorContext.getInstance().spawnActor("Test 1 ", new HelloWorldActor());
        hello.sendMessage(new Message(null, "Hello World"));
        hello.stop();

        /*Part 2*/

        ActorProxy insult = ActorContext.getInstance().spawnActor("Test 2", new InsultActor());
        insult.sendMessage(new GetInsultMessage(insult));
        Message result = insult.receive();
        System.out.println(result.getBody());
        insult.sendMessage(new AddInsultMessage(insult, "Bardzo"));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        insult.sendMessage(new GetInsultMessage(insult));
        result = insult.receive();
        System.out.println(result.getBody());
        insult.stop();

        /*Part 3*/

        LambdaFirewallDecorator lambdaFirewallDecorator = new LambdaFirewallDecorator(new FirewallDecorator(new HelloWorldActor()));
        Predicate<String> containsA = x -> x.startsWith("A");
        lambdaFirewallDecorator.addClosureMessage(containsA);
        ActorProxy firewall = ActorContext.getInstance().spawnActor("Test 3", new EncryptionDecorator(lambdaFirewallDecorator));
        /*The message won't arrive because it doesn't start with A*/
        firewall.sendMessage(new Message(hello, "Hola"));
        /*This message will be printed because it starts with A*/
        firewall.sendMessage(new Message(hello, "AAHola"));
        firewall.stop();

        /*Part 4*/

        try {
            ActorProxy insultService = ActorContext.getInstance().spawnActor("Test 4", new InsultActor());
            InsultServiceInterface insulter = DynamicProxy.getInstance().intercept(new InsultService(), insultService);
            insulter.addInsult("stupid");
            insulter.addInsult("dd");
            insulter.addInsult("e");
            System.out.println(insulter.getAllInsults());
            insultService.stop();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        /*Part 5*/
        ActorProxy monitor1 = ActorContext.getInstance().spawnActor("Test 5 ", new HelloWorldActor());
        ActorProxy monitor2 = ActorContext.getInstance().spawnActor("Test 6 ", new HelloWorldActor());
        ActorProxy monitor3 = ActorContext.getInstance().spawnActor("Test 7 ", new HelloWorldActor());
        ActorProxy monitor4 = ActorContext.getInstance().spawnActor("Test 8 ", new HelloWorldActor());
        monitor1.stop();
        monitor2.stop();
        monitor3.stop();
        monitor4.stop();
        MonitorService.getInstance().monitorAllActors();

        monitor1.start();
        monitor2.start();
        monitor3.start();
        monitor4.start();

        monitor1.stop();
        monitor2.stop();
        monitor3.stop();
        monitor4.stop();

        monitor1.sendMessage(new Message(hello, "hola"));

        System.out.println(MonitorService.getInstance().getTraffic().get(TrafficLevel.LOW).toString());
        System.out.println(MonitorService.getInstance().getReceivedMessages().get("Test 5 ").get(0).getBody());
        System.out.println(MonitorService.getInstance().getNumberOfMessages().get("Test 5 "));
        System.out.println(MonitorService.getInstance().getReceivedMessages().get("Test 6 "));
        System.out.println(MonitorService.getInstance().getNumberOfMessages().get("Test 6 "));

        /*Part 6*/
        RingActor ringActor = new RingActor();
        ringActor.setName("Test 9");
        ringActor.getListeners().add(MonitorService.getInstance());
        TestRingActor testRingActor = new TestRingActor(ringActor);
        testRingActor.start();

        new TestPingPongActor();
    }
}
