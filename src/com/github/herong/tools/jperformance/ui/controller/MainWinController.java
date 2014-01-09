package com.github.herong.tools.jperformance.ui.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;

import com.github.herong.tools.jperformance.core.ConcurentCallableTest;
import com.github.herong.tools.jperformance.core.Environment;
import com.github.herong.tools.jperformance.core.model.InitParams;
import com.github.herong.tools.jperformance.core.model.TestResult;
import com.github.herong.tools.jperformance.ui.observable.GridResult;
import com.github.herong.tools.jperformance.ui.observable.RequestParam;
import com.github.herong.util.Util;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class MainWinController implements Initializable {

	@FXML
	private Button btn_start;
	@FXML
	private Button btn_stop;

	@FXML
	private TextField txt_path;
	@FXML
	private TextField txt_port;
	@FXML
	private TextField txt_protocol;
	@FXML
	private TextField txt_connect;
	@FXML
	private TextField txt_threadCnt;
	@FXML
	private TextField txt_encoding;
	@FXML
	private TextField txt_url;
	@FXML
	private TextField txt_response;
	@FXML
	private Label lab_threadCnt;
	@FXML
	private Label lab_success;
	@FXML
	private Label lab_faliure;
	@FXML
	private Label lab_tatoalTime;
	@FXML
	private Label lab_avgTime;
	@FXML
	private TextArea txta_body;

	@FXML
	private TableView<RequestParam> tab_param;

	@FXML
	private TableView<GridResult> tab_result;

	private int paramsId = 1;

	private ConcurentCallableTest threadGroup;

	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("初始化...");
		this.tab_param.setEditable(true);

		// c1.setCellFactory(TextFieldTableCell.forTableColumn());

		// 设置可编辑
		TableColumn c1 = this.tab_param.getColumns().get(1);
		c1.setCellFactory(TextFieldTableCell.forTableColumn());
		c1.setOnEditCommit(new EventHandler<CellEditEvent<RequestParam, String>>() {
			public void handle(CellEditEvent<RequestParam, String> t) {
				((RequestParam) t.getTableView().getItems()
						.get(t.getTablePosition().getRow())).setName(t
						.getNewValue());
			}
		});

		c1 = this.tab_param.getColumns().get(2);
		c1.setCellFactory(TextFieldTableCell.forTableColumn());
		c1.setOnEditCommit(new EventHandler<CellEditEvent<RequestParam, String>>() {
			public void handle(CellEditEvent<RequestParam, String> t) {
				((RequestParam) t.getTableView().getItems()
						.get(t.getTablePosition().getRow())).setValue(t
						.getNewValue());
			}
		});

		RequestParam reqParam = new RequestParam(String.valueOf(paramsId++),
				"Content-Type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		this.tab_param.getItems().add(reqParam);
		reqParam = new RequestParam(String.valueOf(paramsId++), "Accept",
				"application/json, text/plain, */*");
		this.tab_param.getItems().add(reqParam);
		reqParam = new RequestParam(String.valueOf(paramsId++),
				"X-Requested-With", "XMLHttpRequest");
		this.tab_param.getItems().add(reqParam);
		reqParam = new RequestParam(String.valueOf(paramsId++), "Referer",
				"http://sb-app-09.hnisi.com.cn:7001/web/ggfw/index.html#/wsyw");
		this.tab_param.getItems().add(reqParam);
		reqParam = new RequestParam(String.valueOf(paramsId++),
				"Accept-Language", "zh-cn");
		this.tab_param.getItems().add(reqParam);
		reqParam = new RequestParam(String.valueOf(paramsId++),
				"Accept-Encoding", "gzip, deflate");
		this.tab_param.getItems().add(reqParam);
		reqParam = new RequestParam(String.valueOf(paramsId++), "User-Agent",
				"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");
		this.tab_param.getItems().add(reqParam);
		reqParam = new RequestParam(String.valueOf(paramsId++), "Host",
				"sb-app-09.hnisi.com.cn:7001");
		this.tab_param.getItems().add(reqParam);
		reqParam = new RequestParam(String.valueOf(paramsId++), "Connection",
				"Keep-Alive");
		this.tab_param.getItems().add(reqParam);
		reqParam = new RequestParam(String.valueOf(paramsId++), "Pragma",
				"no-cache");
		this.tab_param.getItems().add(reqParam);

	}

	public void start(ActionEvent event) {
		doStart();
	}

	private void doStart() {
		try {
			initParams();
			threadGroup = new ConcurentCallableTest(Environment.initParams);
			threadGroup.run();
			displayResult();
			this.lab_tatoalTime.setText(String.valueOf(Environment.initParams
					.getTatoalTime()));
			this.lab_avgTime.setText(String.valueOf(Environment.initParams
					.getAvgTime()));
			this.lab_success.setText(String.valueOf(Environment.initParams
					.getSuccess()));
			this.lab_faliure.setText(String.valueOf(Environment.initParams
					.getFaliure()));
			this.lab_threadCnt.setText(String.valueOf(Environment.initParams
					.getThreadCnt()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void displayResult() {
		for (TestResult r : Environment.testResult.values()) {
			this.tab_result.getItems().add(
					new GridResult(r.getThreadName(), r.getState(), r
							.getStartTime(), r.getEndTime(), r.getCostTime()));
		}

	}

	private InitParams initParams() {
		InitParams initParams = Environment.initParams;
		initParams.setUrl(this.txt_url.getText().trim());
		initParams.setProtocol(this.txt_protocol.getText());
		initParams.setPath(this.txt_path.getText());
		initParams.setEncoding(this.txt_encoding.getText());

		if (!Util.isEmpty(this.txt_port.getText())) {
			initParams
					.setPort(Integer.parseInt(this.txt_port.getText().trim()));
		}

		if (!Util.isEmpty(this.txt_threadCnt.getText())) {
			initParams.setThreadCnt(Integer.parseInt(this.txt_threadCnt
					.getText().trim()));
		}

		if (!Util.isEmpty(this.txt_connect.getText())) {
			initParams.setConnect(Integer.parseInt(this.txt_connect.getText()
					.trim()));
		}

		if (!Util.isEmpty(this.txt_response.getText())) {
			initParams.setResponse(Integer.parseInt(this.txt_response.getText()
					.trim()));
		}

		for (Iterator<RequestParam> itor = this.tab_param.getItems().iterator(); itor
				.hasNext();) {
			RequestParam reqParam = itor.next();
			initParams.addReqParam(reqParam.getName(), reqParam.getValue());
		}

		initParams.setBody((String) Util.nvl(this.txta_body.getText()));
		return initParams;
	}

	public void stop(ActionEvent event) {
		threadGroup.stop();

	}

	public void openInitFile(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("选择配置文件");
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML文件", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showOpenDialog(null);
		if (file != null) {
			XStream xstream = getXStrem();
			InitParams initParam = (InitParams) xstream.fromXML(file);
			displayInitParam(initParam);
		}
	}

	private void displayInitParam(InitParams initParams) {
		this.txt_url.setText(initParams.getUrl());
		this.txt_protocol.setText(initParams.getProtocol());
		this.txt_path.setText(initParams.getPath());
		this.txt_encoding.setText(initParams.getEncoding());

		if (initParams.getPort() != 0) {
			this.txt_port.setText(String.valueOf(initParams.getPort()));
		}

		if (initParams.getThreadCnt() != 0) {
			this.txt_threadCnt
					.setText(String.valueOf(initParams.getThreadCnt()));
		}

		if (initParams.getConnect() != 0) {
			this.txt_connect.setText(String.valueOf(initParams.getConnect()));
		}

		if (initParams.getResponse() != 0) {
			this.txt_response.setText(String.valueOf(initParams.getResponse()));
		}

		for (Map.Entry<String, String> param : initParams.getReqParams()
				.entrySet()) {

			boolean flag = false;
			for (RequestParam oldParam : tab_param.getItems()) {
				if (param.getKey().equals(oldParam.getName())) {
					flag = true;
					break;
				}

			}

			try {
				if (!flag) {
					tab_param.getItems().add(
							new RequestParam(String.valueOf(paramsId++), param
									.getKey(), param.getValue()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		this.txta_body.setText(initParams.getBody());
	}

	public void saveInitFile(ActionEvent event) {
		initParams();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("保存配置文件");

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML文件", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		File file = fileChooser.showSaveDialog(null);
		if (file != null) {

			try {
				initParamSave(file);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		;

	}

	private XStream getXStrem() {
		XStream xstream = new XStream(new StaxDriver());
		xstream.alias("initParams", InitParams.class);
		return xstream;
	}

	private void initParamSave(File f) throws Exception {

		BufferedWriter bw = null;
		try {
			XStream xstream = getXStrem();
			String xml = xstream.toXML(Environment.initParams);
			bw = new BufferedWriter(new FileWriter(f, false));
			bw.write(xml);
			bw.flush();
			bw.close();
		} finally {
			if (bw != null) {
				bw.close();
			}
		}

	}

	/**
	 * 增加参数
	 * 
	 * @param event
	 */
	public void addParam(ActionEvent event) {
		System.out.println(event.getSource().toString());

		ObservableList<RequestParam> data = this.tab_param.getItems();
		data.add(new RequestParam(String.valueOf(paramsId++), "", ""));
	}

	/**
	 * 删除参数
	 * 
	 * @param event
	 */
	public void delParam(ActionEvent event) {
		int index = tab_param.getSelectionModel().getSelectedIndex();
		System.out.println("选中第" + index + "行!");
		tab_param.getItems().remove(index);
		int i = 1;
		// id 顺序重排
		for (Iterator<RequestParam> itor = tab_param.getItems().iterator(); itor
				.hasNext();) {
			RequestParam reqParam = itor.next();
			reqParam.setId(String.valueOf(i++));
		}
		paramsId--;
	}

	public void bodyClear(ActionEvent event) {
		this.txta_body.clear();
	}
}
