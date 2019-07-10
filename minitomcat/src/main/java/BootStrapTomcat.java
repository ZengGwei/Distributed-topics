import com.cat.core.http.NRequest;
import com.cat.core.http.NRespones;
import com.cat.core.http.NServlet;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 〈基于Netty 实现山寨tomcat〉
 * create by zgw on 2019/7/9
 */
public class BootStrapTomcat {

    private int port = 8080;

    private Map<String, NServlet> servletMap  = new HashMap<String, NServlet>();//url 与servlet 映射

    private Properties webxml = new Properties();

    private  void  init(){
        try{
            String web_info = this.getClass().getResource("/").getPath();
            FileInputStream fis = new FileInputStream(web_info + "web.properties");
            webxml.load(fis);
            for (Object obj:webxml.keySet()){
                String k=  obj.toString();
                System.out.println(k);
                if (k.endsWith(".url")){
                    String servletName = k.replaceAll("\\.url$", "");
                    String url = webxml.getProperty(k);
                    String className = webxml.getProperty(servletName + ".className");
                    System.out.println("put : url==="+url+"\n className=== "+className);
                    NServlet servlet = (NServlet)Class.forName(className).newInstance();
                    servletMap.put(url,servlet);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void start(){
        this.init();

        EventLoopGroup bossGroup = new NioEventLoopGroup();//Boss线程
        EventLoopGroup workerGroup = new NioEventLoopGroup();//Worker线程

        try{
            ServerBootstrap server = new ServerBootstrap();//netty 服务

            server.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel client) throws Exception {
                            client.pipeline().addLast("http-encoder",new HttpRequestEncoder());
                            client.pipeline().addLast("http-decoder",new HttpRequestDecoder());
                            client.pipeline().addLast("handler",new NTomcatHandler());
                        }
                    }).option(ChannelOption.SO_BACKLOG,128)   //主线程配置 最大线程128
                     .childOption(ChannelOption.SO_KEEPALIVE,true); //子线程配置 保持长链接

            ChannelFuture sync = server.bind(port).sync();
            System.out.println("mini Tomcat 启动，监听端口号："+port);
            sync.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
    public  class NTomcatHandler extends ChannelInboundHandlerAdapter{
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            if (msg instanceof HttpRequest){
                HttpRequest req = (HttpRequest) msg;
                NRequest nRequest = new NRequest(ctx, req);
                NRespones nRespones = new NRespones(ctx, req);

                String url = nRequest.getURL();
                if (servletMap.containsKey(url)){
                    servletMap.get(url).server(nRequest,nRespones);
                }else {
                    nRespones.write("404 - Not Found");
                }
            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            super.exceptionCaught(ctx, cause);
        }
    }

    public static void main(String[] args) {
        new BootStrapTomcat().start();
    }
}
