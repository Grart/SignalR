// Copyright (c) .NET Foundation. All rights reserved.
// Licensed under the Apache License, Version 2.0. See License.txt in the project root for license information.

package com.microsoft.aspnet.signalr;

public class HubConnectionBuilder {
    private String url;
    private Transport transport;
    private Logger logger;
    private boolean skipNegotiate;

    public HubConnectionBuilder withUrl(String url) {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("A valid url is required.");
        }

        this.url = url;
        return this;
    }

    public HubConnectionBuilder withUrl(String url, Transport transport) {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("A valid url is required.");
        }
        this.url = url;
        this.transport = transport;
        return this;
    }

    public HubConnectionBuilder withUrl(String url, HttpConnectionOptions options) {
        this.url = url;
        this.transport =  options.getTransport();
        this.logger =  new ConsoleLogger(options.getLoglevel());
        this.skipNegotiate = options.getSkipNegotiate();
        return this;
    }

    public HubConnectionBuilder configureLogging(LogLevel logLevel) {
        this.logger = new ConsoleLogger(logLevel);
        return this;
    }

    public HubConnectionBuilder configureLogging(Logger logger) {
        this.logger = logger;
        return this;
    }

    public HubConnection build() {
        if (this.url == null) {
            throw new RuntimeException("The 'HubConnectionBuilder.withUrl' method must be called before building the connection.");
        }
        return new HubConnection(url, transport, logger, skipNegotiate);
    }
}