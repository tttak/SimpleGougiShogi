package co.jp.shogi.gougi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 入力用スレッド
 */
public class InputStreamThread extends Thread {

	/** Logger */
	protected static Logger logger = Logger.getLogger(InputStreamThread.class.getName());

	/** BufferedReader */
	private BufferedReader br;
	/** 表示名 */
	private String dispName;

	/** コマンドリスト */
	private List<String> commandList = new ArrayList<String>();

	/**
	 * コンストラクタ
	 * 
	 * @param is
	 */
	public InputStreamThread(InputStream is) {
		br = new BufferedReader(new InputStreamReader(is));
	}

	/**
	 * コンストラクタ
	 * 
	 * @param is
	 * @param dispName
	 */
	public InputStreamThread(InputStream is, String dispName) {
		this(is);
		this.dispName = dispName;
	}

	/* (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run() */
	@Override
	public void run() {
		try {
			String line;
			while ((line = br.readLine()) != null) {
				logger.info("in[" + dispName + "]コマンド受信!!" + line);

				// 受信したコマンドをコマンドリストに追加
				// ・スレッドの排他制御
				synchronized (this) {
					commandList.add(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			Utils.close(br);
		}
	}

	/**
	 * 特定のコマンドを受信するまで待つ
	 * 
	 * @param command
	 */
	public void waitUntilCommand(String command) {
		try {
			while (!commandList.contains(command)) {
				Thread.sleep(10);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * クローズ処理
	 */
	public void close() {
		Utils.close(br);
	}

	// ------------------------------ 単純なGetter&Setter START ------------------------------

	public List<String> getCommandList() {
		return commandList;
	}

	public String getDispName() {
		return dispName;
	}

	public void setDispName(String dispName) {
		this.dispName = dispName;
	}

	// ------------------------------ 単純なGetter&Setter END ------------------------------

}
