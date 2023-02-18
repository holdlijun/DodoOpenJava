package io.github.mcchampions.DodoOpenJava.Event;

import io.github.mcchampions.DodoOpenJava.Api.Version;
import io.github.mcchampions.DodoOpenJava.Event.events.V2.GiftSendEvent;
import io.github.mcchampions.DodoOpenJava.Event.events.V2.IntegralChangeEvent;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.ByteString;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 事件触发
 * @author qscbm187531
 */
@Slf4j
public class EventTrigger {
    private static EventTrigger mInstance = null;
    public static String wssLo = "";
    public OkHttpClient okHttpClient ;
    private boolean isConnect = false;
    private final static int MAX_NUM = 5;       // 最大重连数
    private final static int MILLIS = 5000;
    public  OkHttpClient wss;
    private int connectNum = 0;
    public static String ad;
    public WebSocket mWebSocket;
    public static EventTrigger getInstance() {
        if (null == mInstance) {
            synchronized (EventTrigger.class) {
                if (mInstance == null) {
                    mInstance = new EventTrigger();
                }
            }
        }
        System.out.println(mInstance);
        return mInstance;
    }

    public void init(@NotNull String Authorization,@NotNull Version version) {
        wss = new OkHttpClient.Builder()
                .pingInterval(15, TimeUnit.SECONDS) //保活心跳
                .build();
        okHttpClient = new OkHttpClient();
        if (Objects.equals(version.getVersion(), "v2")) {
            v2(Authorization);
        }
    }

    public void v2(String Authorization) {
        if (isConnect){
            return;
        }
        ad = Authorization;
        Request request = new Request.Builder().url("https://botopen.imdodo.com/api/v2/websocket/connection").addHeader("Content-Type", "application/json").addHeader("Authorization", ad)
                .post(RequestBody.create(MediaType.parse("application/json"), "{}"))
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                wssLo = new JSONObject(Objects.requireNonNull(response.body()).string()).getJSONObject("data").getString("endpoint");
                response.close();
                try {
                    Request request = new Request.Builder()
                            .url(wssLo)
                            .build();
                    mWebSocket = wss.newWebSocket(request, createListenerV2(Authorization));
                }catch (Exception e){
                    log.error("链接中断",e);
                }
            }
        });
    }


    public void reconnect(String Authorization) {
        log.warn("连接重试");
        if (connectNum <= MAX_NUM) {
            try {
                Thread.sleep(MILLIS);
                v2(Authorization);
                connectNum++;
            } catch (Exception e) {
                log.error("reconnect",e);
            }
        } else {
            log.info( "reconnect over " + MAX_NUM + ",please check url or network");
        }
    }
    /**
     * 是否连接
     */
    public boolean isConnectFun() {
        return mWebSocket != null && isConnect;
    }

    private WebSocketListener createListenerV2(String Authorization){
        return new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                super.onOpen(webSocket, response);
                isConnect = response.code() == 101;
                if (!isConnectFun()) {
                    reconnect(Authorization);
                } else {
                    log.info("WebSocket 连接成功");
                }

            }

            @Override
            public void onMessage(WebSocket webSocket, ByteString bytes) {
                super.onMessage(webSocket, bytes);
                JSONObject jsontext = new JSONObject(bytes.utf8());
                switch (jsontext.getJSONObject("data").getString("eventType")) {
                    case "1001":
                        try {
                            EventManage.fireEvent(new io.github.mcchampions.DodoOpenJava.Event.events.V2.PersonalMessageEvent(jsontext));
                        } catch (Exception e) {
                            log.error("1001",e);
                        }
                        break;

                    case "2001":
                        try {
                            EventManage.fireEvent(new io.github.mcchampions.DodoOpenJava.Event.events.V2.MessageEvent(jsontext));
                        } catch (Exception e) {
                            log.error("2001",e);
                        }
                        break;
                    case "3001":
                        try {
                            EventManage.fireEvent(new io.github.mcchampions.DodoOpenJava.Event.events.V2.MessageReactionEvent(jsontext));
                        } catch (Exception e) {
                            log.error("3001",e);
                        }
                        break;
                    case "3002":
                        try {
                            EventManage.fireEvent(new io.github.mcchampions.DodoOpenJava.Event.events.V2.CardMessageButtonClickEvent(jsontext));
                        } catch (Exception e) {
                            log.error("3002",e);
                        }
                        break;
                    case "3003":
                        try {
                            EventManage.fireEvent(new io.github.mcchampions.DodoOpenJava.Event.events.V2.CardMessageFormSubmitEvent(jsontext));
                        } catch (Exception e) {
                            log.error("3003",e);
                        }
                        break;
                    case "3004":
                        try {
                            EventManage.fireEvent(new io.github.mcchampions.DodoOpenJava.Event.events.V2.CardMessageListSubmitEvent(jsontext));
                        } catch (Exception e) {
                            log.error("3004",e);
                        }
                        break;
                    case "4001":
                        try {
                            EventManage.fireEvent(new io.github.mcchampions.DodoOpenJava.Event.events.V2.MemberJoinEvent(jsontext));
                        } catch (Exception e) {
                            log.error("4001",e);
                        }
                        break;
                    case "4002":
                        try {
                            EventManage.fireEvent(new io.github.mcchampions.DodoOpenJava.Event.events.V2.MemberLeaveEvent(jsontext));
                        } catch (Exception e) {
                            log.error("4002",e);
                        }
                        break;
                    case "5001":
                        try {
                            EventManage.fireEvent(new io.github.mcchampions.DodoOpenJava.Event.events.V2.ChannelVoiceMemberJoinEvent(jsontext));
                        } catch (Exception e) {
                            log.error("5001",e);
                        }
                        break;
                    case "5002":
                        try {
                            EventManage.fireEvent(new io.github.mcchampions.DodoOpenJava.Event.events.V2.ChannelVoiceMemberLeaveEvent(jsontext));
                        } catch (Exception e) {
                            log.error("5002",e);
                        }
                        break;
                    case "6001":
                        try {
                            EventManage.fireEvent(new io.github.mcchampions.DodoOpenJava.Event.events.V2.ChannelArticleEvent(jsontext));
                        } catch (Exception e) {
                            log.error("6001",e);
                        }
                        break;
                    case "6002":
                        try {
                            EventManage.fireEvent(new io.github.mcchampions.DodoOpenJava.Event.events.V2.ChannelArticleCommentEvent(jsontext));
                        } catch (Exception e) {
                            log.error("6002",e);
                        }
                        break;
                    case "7001":
                        try {
                            EventManage.fireEvent(new GiftSendEvent(jsontext));
                        } catch (Exception e) {
                            log.error("7001",e);
                        }
                        break;
                    case "8001":
                        try {
                            EventManage.fireEvent(new IntegralChangeEvent(jsontext));
                        } catch (Exception e) {
                            log.error("8001",e);
                        }
                        break;
                    case "4003":
                        try {
                            EventManage.fireEvent(new io.github.mcchampions.DodoOpenJava.Event.events.V2.MemberInviteEvent(jsontext));
                        } catch (Exception e) {
                            log.error("4003",e);
                        }
                        break;
                    default:
                        log.warn("未知的事件！");
                }
            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                super.onClosing(webSocket, code, reason);
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                super.onClosed(webSocket, code, reason);
                mWebSocket.close(1000,"正常关闭");
                mWebSocket = null;
                isConnect = false;
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, @Nullable Response response) {
                super.onFailure(webSocket, t, response);
                if (response != null) {
                    log.error("WebSocket 连接失败：{}",response.message());
                }
                log.error("WebSocket 连接失败异常原因：{}",t.getMessage());
                isConnect = false;
                if (!StringUtils.isEmpty(t.getMessage())){
                    log.warn("进行重试");
                    reconnect(Authorization);
                }

            }
        };
    }
}
