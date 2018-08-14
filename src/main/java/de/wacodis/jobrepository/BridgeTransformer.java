package de.wacodis.jobrepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.GenericMessage;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Marius Bogoevici
 * @author Soby Chacko
 */
@EnableBinding(Processor.class)
public class BridgeTransformer {

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public Object transform(Object payload) {
        return payload;
    }

    //Following source is used as test producer.
    @EnableBinding(TestSource.class)
    static class TestProducer {

        private AtomicBoolean semaphore = new AtomicBoolean(true);

        @Bean
        @InboundChannelAdapter(channel = TestSource.OUTPUT, poller = @Poller(fixedDelay = "1000"))
        public MessageSource<String> sendTestData() {
            return ()
                    -> new GenericMessage<>(this.semaphore.getAndSet(!this.semaphore.get()) ? "foo" : "bar");

        }
    }

    //Following sink is used as test consumer for the above processor. It logs the data received through the processor.
    @EnableBinding(TestSink.class)
    static class TestConsumer {

        private final Log logger = LogFactory.getLog(getClass());

        @StreamListener(TestSink.INPUT)
        public void receive(String data) {
            logger.info("Data received..." + data);
        }
    }

    interface TestSink {

        String INPUT = "input1";

        @Input(INPUT)
        SubscribableChannel input1();

    }

    interface TestSource {

        String OUTPUT = "output1";

        @Output(TestSource.OUTPUT)
        MessageChannel output();

    }
}
