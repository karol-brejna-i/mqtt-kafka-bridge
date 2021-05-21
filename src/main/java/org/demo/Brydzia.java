package org.demo;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import java.nio.charset.StandardCharsets;

@ApplicationScoped
public class Brydzia {

    private static final Logger LOG = Logger.getLogger(Brydzia.class);

    @Incoming("source")
    @Outgoing("sink")
    public String publish(byte[] payload) {
        String message = new String(payload, StandardCharsets.UTF_8);
        if (LOG.isDebugEnabled()) {
            LOG.debug("Forwarding: " + message);
        }
        return message;
    }
}
