module foobar.main {
    requires java.net.http;
    requires java.sql;

    requires kotlin.stdlib;
    requires kotlinx.html.jvm;
    requires kotlinx.coroutines.core;

    requires ktor.server.core;
    requires ktor.server.netty;
    requires ktor.server.host.common;
    requires ktor.html.builder;
    requires ktor.http.jvm;
    requires ktor.utils.jvm;
    requires ktor.network.tls;
    requires java.desktop;
}