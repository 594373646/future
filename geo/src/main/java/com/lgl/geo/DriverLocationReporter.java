package com.lgl.geo;

import com.lgl.geo.common.Location;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Timer;
import java.util.TimerTask;

public class DriverLocationReporter {
    private static final String KAFKA_TOPIC = "driver_location";

    public static void main(String[] args) {
        // 创建Netty客户端
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new DriverLocationHandler());
                    }
                });

        try {
            // 连接到服务器
            ChannelFuture future = bootstrap.connect("localhost", 8888).sync();
            Channel channel = future.channel();

            // 定时器，每隔5秒上报司机位置信息
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    // 获取司机位置信息
                    Location location = getLocation();

                    // 构建消息并发送到Kafka
                    String message = buildMessage(location);
                    sendToKafka(message, KAFKA_TOPIC);
                }
            }, 0, 5000);

            // 等待直到连接关闭
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭Netty客户端
            group.shutdownGracefully();
        }
    }

    private static Location getLocation() {
        // 实现获取司机位置的逻辑，可以使用定位功能或其他方式获取位置信息
        // 返回司机位置对象
        return null;
    }

    private static String buildMessage(Location location) {
        // 构建消息内容，将位置信息转换为字符串格式
        // 返回消息字符串
        return null;
    }

    private static void sendToKafka(String message, String topic) {
        // 实现将消息发送到Kafka的逻辑
    }

    private static class DriverLocationHandler extends SimpleChannelInboundHandler<String> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
            // 处理服务器返回的消息
        }
    }
}
