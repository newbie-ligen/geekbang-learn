package io.github.kimmking.gateway.outbound.myclient;

import io.github.kimmking.gateway.filter.HeaderHttpResponseFilter;
import io.github.kimmking.gateway.filter.HttpRequestFilter;
import io.github.kimmking.gateway.filter.HttpResponseFilter;
import io.github.kimmking.gateway.router.HttpEndpointRouter;
import io.github.kimmking.gateway.router.RandomHttpEndpointRouter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpClientHandler {

    private List<String> backendUrls;

    HttpEndpointRouter router = new RandomHttpEndpointRouter();
    HttpResponseFilter responseFilter = new MyFilter();


    public HttpClientHandler(List<String> backends) {
        this.backendUrls = backends;
    }

    public HttpClientHandler(List<String> backendUrls, HttpEndpointRouter router) {
        this.backendUrls = backendUrls;
        this.router = router;
    }

    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, HttpRequestFilter requestFilter) {
        String backendUrl =router.route(backendUrls);
        final String url = backendUrl + fullRequest.uri();
        requestFilter.filter(fullRequest, ctx);
        HttpEntity httpEntity = get(url);
        response(httpEntity,ctx);
    }

    public void response(   HttpEntity httpEntity, ChannelHandlerContext ctx ){
        try {
            final byte[] bytes = EntityUtils.toString(httpEntity, "utf-8").getBytes();
            FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(bytes));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", (int) httpEntity.getContentLength());
            responseFilter.filter(response);
            ctx.write(response);
        }catch (Exception e) {
            e.printStackTrace();
            exceptionCaught(ctx,e);
        }
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }


    public static HttpEntity get(String url) {
        CloseableHttpClient httpclient = null;
        try {
            httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(httpget);

            HttpEntity entity = response.getEntity();
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(httpclient!=null) {
                try {
                    httpclient.close();
                }catch (Exception e) {
                    httpclient = null;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        //System.out.println(get("http://www.baidu.com"));
        System.out.println(get("http://127.0.0.1:8081"));

    }
}
