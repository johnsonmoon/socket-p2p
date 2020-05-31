package com.github.johnsonmoon.socket.p2p;

import com.github.johnsonmoon.socket.p2p.side.client.UIClientSide;
import com.github.johnsonmoon.socket.p2p.side.server.UIServerSide;
import com.github.johnsonmoon.socket.p2p.util.TryUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * Create by xuyh at 2020/5/29 17:10.
 */
public class UIEndPoint extends Application {
    private static Logger logger = LoggerFactory.getLogger(UIEndPoint.class);

    private AtomicBoolean shutdownSwitch = new AtomicBoolean(false);

    public static void main(String[] args) {
        Application.launch(UIEndPoint.class, args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = Thread.currentThread().getContextClassLoader().getResource("p2p-chat.fxml");
        Parent parent = FXMLLoader.load(url);
        initView(parent);
        initEvent();
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.setTitle("P2P chat");
        primaryStage.show();
    }

    private TextField txtIp;
    private TextField txtPort;
    private Button btnListen;
    private Button btnConnect;
    private TextArea txtAreaMessagePane;
    private TextArea txtAreaInputPane;
    private Button btnSend;
    private Button btnClear;
    private Button btnExit;

    private void initView(Parent parent) {
        txtIp = (TextField) parent.lookup("#txt_ip");
        txtPort = (TextField) parent.lookup("#txt_port");
        btnListen = (Button) parent.lookup("#btn_listen");
        btnConnect = (Button) parent.lookup("#btn_connect");
        txtAreaMessagePane = (TextArea) parent.lookup("#txt_area_message_pane");
        txtAreaInputPane = (TextArea) parent.lookup("#txt_area_input_pane");
        btnSend = (Button) parent.lookup("#btn_send");
        btnClear = (Button) parent.lookup("#btn_clear");
        btnExit = (Button) parent.lookup("#btn_exit");
    }

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private void initEvent() {
        btnListen.setOnAction(event -> {
            String port = txtPort.getText();
            if (port == null || port.isEmpty()) {
                logger.warn("Port is null or empty, please input port first.");
                txtAreaMessagePane.appendText("Port is null or empty, please input port first.\r\n");
                return;
            }
            Integer portI = TryUtil.tryOperation(() -> Integer.parseInt(port));
            if (portI == null) {
                logger.warn("Port is not a integer, please input correct port number.");
                txtAreaMessagePane.appendText("Port is not a integer, please input correct port number.\r\n");
                txtPort.clear();
                return;
            }
            txtAreaMessagePane.appendText("Listen port " + port + ".\r\n");
            executorService.submit(() -> new UIServerSide(shutdownSwitch, portI, txtAreaMessagePane, txtAreaInputPane, btnSend).start());
        });

        btnConnect.setOnAction(event -> {
            String ip = txtIp.getText();
            String port = txtPort.getText();
            if (ip == null || ip.isEmpty() || port == null || port.isEmpty()) {
                logger.warn("Ip or port is null or empty, please input ip and port first.");
                txtAreaMessagePane.appendText("Ip or port is null or empty, please input ip and port first.\r\n");
                return;
            }
            Integer portI = TryUtil.tryOperation(() -> Integer.parseInt(port));
            if (portI == null) {
                logger.warn("Port is not a integer, please input correct port number.");
                txtAreaMessagePane.appendText("Port is not a integer, please input correct port number.\r\n");
                txtPort.clear();
                return;
            }
            new UIClientSide(shutdownSwitch, ip, portI, txtAreaMessagePane, txtAreaInputPane, btnSend).start();
        });

        btnClear.setOnAction(event -> {
            txtAreaInputPane.clear();
        });

        btnExit.setOnAction(event -> System.exit(0));
    }
}
