/*
 * @#FMPPacketType.java - 2015
 * Copyright bitDubai.com., All rights reserved.
 * You may not modify, use, reproduce or distribute this software.
 * BITDUBAI/CONFIDENTIAL
 */
package com.bitdubai.fermat_p2p_api.layer.p2p_communication.commons.enums;

/**
 * The enum <code>com.bitdubai.fermat_p2p_api.layer.p2p_communication.commons.enums.FermatPacketType</code> define
 * all types of Fermat Packet cam be
 * <p/>
 *
 * Created by Roberto Requena - (rart3001@gmail.com) on 01/09/15.
 *
 * @version 1.0
 * @since Java JDK 1.7
 */
public enum FermatPacketType {

    // Definition types

    /**
     * This type is use when a server handshake respond
     */
    SERVER_HANDSHAKE_RESPOND       ("SRV_HS_RESP"),

    /**
     * This type is use when a vpn server handshake respond
     */
    VPN_SERVER_HANDSHAKE_RESPOND   ("VPN_SRV_HS_RESP"),

    /**
     * This type is use when a component registration request
     */
    COMPONENT_REGISTRATION_REQUEST ("COMP_REG_REQ"),

    /**
     * This type is use when only a update actor request
     */
    UPDATE_ACTOR_REQUEST ("UPD_ACT_REQ"),

    /**
     * This type is use when only a update actor complete
     */
    COMPLETE_UPDATE_ACTOR ("COMPL_UPD_ACT"),

    /**
     * This type is use when a failure is detected on component registration request
     */
    FAILURE_COMPONENT_REGISTRATION_REQUEST ("FAIL_COMP_REG_REQ"),

    /**
     * This type is use when only a update actor request
     */
    FAILURE_UPDATE_ACTOR_REQUEST ("FAIL_UPD_ACT_REQ"),

    /**
     * This type is use when a component registration request is complete
     */
    COMPLETE_COMPONENT_REGISTRATION("COMPL_COMP_REG"),

    /**
     * This type is use when need a list the components registered in the server
     */
    REQUEST_LIST_COMPONENT_REGISTERED("REQ_LIST_COMP_REG"),

    /**
     * This type is use when NOTIFICATE to All client to register in other Platform Cloud Server
     */

    REGISTER_SERVER_REQUEST("REG_SERV_REQ"),

    /**
     * This type is use when a requested list is not available
     */
    FAILURE_REQUESTED_LIST_NOT_AVAILABLE("REQ_LIST_NOT_AVA"),

    /**
     * This type is use when a component disconnect request is made
     */
    COMPONENT_DISCONNECT_REQUEST   ("COMP_DISC_REQ"),

    /**
     * This type is use when a message delivery notification is made
     */
    MESSAGE_DELIVERY_NOTIFICATION  ("MSG_DEL_NOT"),

    /**
     * This type is use when a component connection request is made
     */
    COMPONENT_CONNECTION_REQUEST   ("COMP_CONNECT_REQ"),

    /**
     * This type is use when a component connection request is made
     */
    DISCOVERY_COMPONENT_CONNECTION_REQUEST   ("DIS_COMP_CONNECT_REQ"),

    /**
     * This type is use when a component connection respond is made
     */
    COMPONENT_CONNECTION_RESPOND   ("COMP_CONNECT_RES"),

    /**
     * This type is use when a discovery component connection respond is made
     */
    DISCOVERY_COMPONENT_CONNECTION_RESPOND   ("DIS_COMP_CONNECT_RES"),

    /**
     * This type is use when a component connection request is complete, are
     * participants are connected to the vpn, and is ready to send message
     */
    COMPLETE_COMPONENT_CONNECTION_REQUEST("COMPL_COMP_CONNECT_REQ"),

    /**
     * This type is use when a component connection request is failure, are
     * participants are not connected to the vpn, and they cant send message
     */
    FAILURE_COMPONENT_CONNECTION_REQUEST("FAIL_COMP_CONNECT_REQ"),

    /**
     * This type is use whe an component send a message to another component
     */
    MESSAGE_TRANSMIT ("MSG_TRANS"),

    /**
     * This type is use whe an component send a message to another component and occur a failure
     */
    FAILURE_MESSAGE_TRANSMIT ("FMSG_TRANS"),

    /**
     * This type is use when client connection is successfully reconnect
     */
    CLIENT_CONNECTION_SUCCESSFULLY_RECONNECT ("CCSR"),

    /**
     * This type is use when the remote participant of the vpn is disconnected
     */
    VPN_REMOTE_PARTICIPANT_DISCONNECTED ("VRPD"),

    /**
     * This type is use when the remote participant of the vpn is reconnected
     */
    VPN_REMOTE_PARTICIPANT_RECONNECTED ("VRPR"),

    /**
     * This type is use when vpn connection is successfully reconnect
     */
    VPN_CONNECTION_SUCCESSFULLY_RECONNECT ("VCSR");

    /**
     * Represent the code
     */
    private String code;

    /**
     * Constructor whit parameter
     *
     * @param code
     */
    private FermatPacketType(String code){
        this.code = code;
    }

    /**
     * Return the FermatPacketType represented by the code pass as parameter
     *
     * @param code
     * @return FermatPacketType
     */
    public static FermatPacketType getByCode(final String code){

        switch (code){
            case "SRV_HS_RESP"            : return FermatPacketType.SERVER_HANDSHAKE_RESPOND;
            case "VPN_SRV_HS_RESP"        : return FermatPacketType.VPN_SERVER_HANDSHAKE_RESPOND;
            case "COMP_REG_REQ"           : return FermatPacketType.COMPONENT_REGISTRATION_REQUEST;
            case "UPD_ACT_REQ"            : return FermatPacketType.UPDATE_ACTOR_REQUEST;
            case "FAIL_COMP_REG_REQ"      : return FermatPacketType.FAILURE_COMPONENT_REGISTRATION_REQUEST;
            case "FAIL_UPD_ACT_REQ"       : return FermatPacketType.FAILURE_UPDATE_ACTOR_REQUEST;
            case "COMPL_COMP_REG"         : return FermatPacketType.COMPLETE_COMPONENT_REGISTRATION;
            case "COMPL_UPD_ACT"          : return FermatPacketType.COMPLETE_UPDATE_ACTOR;
            case "REQ_LIST_COMP_REG"      : return FermatPacketType.REQUEST_LIST_COMPONENT_REGISTERED;
            case "REQ_LIST_NOT_AVA"       : return FermatPacketType.FAILURE_REQUESTED_LIST_NOT_AVAILABLE;
            case "REG_SERV_REQ"           : return FermatPacketType.REGISTER_SERVER_REQUEST;
            case "COMP_DISC_REQ"          : return FermatPacketType.COMPONENT_DISCONNECT_REQUEST;
            case "MSG_DEL_NOT"            : return FermatPacketType.MESSAGE_DELIVERY_NOTIFICATION;
            case "COMP_CONNECT_REQ"       : return FermatPacketType.COMPONENT_CONNECTION_REQUEST;
            case "DISC_COMP_CONNECT_REQ"  : return FermatPacketType.DISCOVERY_COMPONENT_CONNECTION_REQUEST;
            case "COMP_CONNECT_RES"       : return FermatPacketType.COMPONENT_CONNECTION_RESPOND;
            case "DIS_COMP_CONNECT_RES"   : return FermatPacketType.DISCOVERY_COMPONENT_CONNECTION_RESPOND;
            case "COMPL_COMP_CONNECT_REQ" : return FermatPacketType.COMPLETE_COMPONENT_CONNECTION_REQUEST;
            case "FAIL_COMP_CONNECT_REQ"  : return FermatPacketType.FAILURE_COMPONENT_CONNECTION_REQUEST;
            case "MSG_TRANS"              : return FermatPacketType.MESSAGE_TRANSMIT;
            case "FMSG_TRANS"             : return  FermatPacketType.FAILURE_MESSAGE_TRANSMIT;
            case "CCSR"                   : return  FermatPacketType.CLIENT_CONNECTION_SUCCESSFULLY_RECONNECT;
            case "VRPD"                   : return  FermatPacketType.VPN_REMOTE_PARTICIPANT_DISCONNECTED;
            case "VRPR"                   : return  FermatPacketType.VPN_REMOTE_PARTICIPANT_RECONNECTED;
            case "VCSR"                   : return  FermatPacketType.VPN_CONNECTION_SUCCESSFULLY_RECONNECT;

            default: throw new IllegalArgumentException();
        }
    }

}
