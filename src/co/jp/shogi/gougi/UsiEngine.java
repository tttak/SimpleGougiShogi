package co.jp.shogi.gougi;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * USIエンジンクラス
 */
public class UsiEngine {

	/** 実行ファイル （例）「C:/将棋/Gikou/gikou.exe」 */
	private File exeFile;
	/** USI名 （例）「Gikou 20160606」　 */
	private String usiName;
	/** エンジン番号（1〜3のいずれか） */
	private int engineNumber;

	/** プロセス */
	private Process process;
	/** inputスレッド */
	private InputStreamThread inputThread;
	/** outputスレッド */
	private OutputStreamThread outputThread;

	/** USIオプションのSet */
	private Set<String> optionSet = new HashSet<String>();

	/** bestmoveコマンド （例）「bestmove 3i4h ponder 3c8h+」 */
	private String bestmoveCommand;
	/** 直近の読み筋 （例）「info depth 3 seldepth 4 multipv 1 score cp 502 nodes 774 nps 154800 time 5 pv 2g3g 4h3g 2c3d S*3f」 */
	private String lastPv;

	/**
	 * プロセスの終了
	 */
	public void destroy() {
		if (inputThread != null) {
			inputThread.close();
		}
		if (outputThread != null) {
			outputThread.close();
		}

		Utils.destroy(process);
	}

	/**
	 * このエンジンの表示用文字列を返す
	 * （例）「Engine1」
	 * 
	 * @return
	 */
	public String getEngineDisp() {
		return "Engine" + getEngineNumber();
	}

	/**
	 * このエンジンのUSIオプションのプレフィックスを返す
	 * （例）「E1_」
	 * 
	 * @return
	 */
	public String getOptionNamePrefix() {
		return "E" + getEngineNumber() + "_";
	}

	/**
	 * bestmoveコマンド（ponder部分を除く）を返す
	 * （例）「bestmove 3i4h」
	 * 
	 * @return
	 */
	public String getBestmoveCommandExceptPonder() {
		if (bestmoveCommand == null) {
			return null;
		}

		int index = bestmoveCommand.indexOf("ponder");
		if (index >= 0) {
			// （例）「bestmove 3i4h ponder 3c8h+」→「bestmove 3i4h」
			return bestmoveCommand.substring(0, index).trim();
		} else {
			return bestmoveCommand.trim();
		}
	}

	/**
	 * bestmove、直近の読み筋のクリア
	 */
	public void clearBestmoveLastPv() {
		this.bestmoveCommand = null;
		this.lastPv = null;
	}

	/**
	 * 直近の評価値（文字列）を取得
	 * 
	 * @return
	 */
	public String getLastStrScore() {
		return ShogiUtils.getStrScoreFromInfoPv(lastPv);
	}

	// ------------------------------ 単純なGetter&Setter START ------------------------------

	public File getExeFile() {
		return exeFile;
	}

	public void setExeFile(File exeFile) {
		this.exeFile = exeFile;
	}

	public String getUsiName() {
		return usiName;
	}

	public void setUsiName(String usiName) {
		this.usiName = usiName;
	}

	public int getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(int engineNumber) {
		this.engineNumber = engineNumber;
	}

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	public InputStreamThread getInputThread() {
		return inputThread;
	}

	public void setInputThread(InputStreamThread inputThread) {
		this.inputThread = inputThread;
	}

	public OutputStreamThread getOutputThread() {
		return outputThread;
	}

	public void setOutputThread(OutputStreamThread outputThread) {
		this.outputThread = outputThread;
	}

	public String getBestmoveCommand() {
		return bestmoveCommand;
	}

	public void setBestmoveCommand(String bestmoveCommand) {
		this.bestmoveCommand = bestmoveCommand;
	}

	public Set<String> getOptionSet() {
		return optionSet;
	}

	public void setOptionSet(Set<String> optionSet) {
		this.optionSet = optionSet;
	}

	public String getLastPv() {
		return lastPv;
	}

	public void setLastPv(String lastPv) {
		this.lastPv = lastPv;
	}

	// ------------------------------ 単純なGetter&Setter END ------------------------------

}
