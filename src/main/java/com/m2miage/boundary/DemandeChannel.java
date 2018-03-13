package com.m2miage.boundary;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;


public interface DemandeChannel {
    @Input
    SubscribableChannel input();
}
