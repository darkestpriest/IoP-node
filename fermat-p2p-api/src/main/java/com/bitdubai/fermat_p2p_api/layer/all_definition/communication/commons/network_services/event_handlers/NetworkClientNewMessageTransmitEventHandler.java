package com.bitdubai.fermat_p2p_api.layer.all_definition.communication.commons.network_services.event_handlers;

import com.bitdubai.fermat_api.FermatException;
import com.bitdubai.fermat_api.layer.all_definition.events.interfaces.FermatEvent;
import com.bitdubai.fermat_api.layer.all_definition.events.interfaces.FermatEventHandler;
import com.bitdubai.fermat_p2p_api.layer.all_definition.communication.commons.clients.events.NetworkClientNewMessageTransmitEvent;
import com.bitdubai.fermat_p2p_api.layer.all_definition.communication.commons.network_services.abstract_classes.AbstractNetworkService;

/**
 * The Class <code>com.bitdubai.fermat_p2p_api.layer.all_definition.communication.commons.network_services.event_handlers.NetworkClientConnectionLostEventHandler</code>
 * implements the handling of the event <code>com.bitdubai.fermat_p2p_api.layer.all_definition.communication.commons.clients.events.NetworkClientConnectionLostEvent</code>
 * reference: <code>P2pEventType.NETWORK_CLIENT_NEW_MESSAGE_TRANSMIT</code>
 * <p/>
 *
 * Created by Leon Acosta - (laion.cj91@gmail.com) on 12/05/2016.
 *
 * @author  lnacosta
 * @version 1.0
 * @since   Java JDK 1.7
 */
public class NetworkClientNewMessageTransmitEventHandler implements FermatEventHandler<NetworkClientNewMessageTransmitEvent> {

   /**
    * Represent the networkService
    */
    private AbstractNetworkService networkService;

    /**
     * Constructor with parameter
     *
     * @param networkService
     */
    public NetworkClientNewMessageTransmitEventHandler(AbstractNetworkService networkService) {
        this.networkService = networkService;
    }

    /**
     * (non-Javadoc)
     *
     * @see FermatEventHandler#handleEvent(FermatEvent)
     *
     * @param fermatEvent instance of NetworkClientConnectionLostEvent
     *
     * @throws FermatException if something goes wrong.
     */
    @Override
    public void handleEvent(NetworkClientNewMessageTransmitEvent fermatEvent) throws FermatException {

        if (this.networkService.isStarted() &&
                this.networkService.getProfile().getNetworkServiceType().equals(fermatEvent.getNetworkServiceTypeSource()))
            networkService.onMessageReceived(fermatEvent.getContent());

    }
}
