package co.jp.shogi.gougi;

import java.util.ArrayList;
import java.util.List;

/**
 * èè`
 */
public class Constants {

	/** usiR}hÉÔ·uid namev */
	public static final String USI_ID_NAME = "SimpleGougiShogi_20170115";
	/** usiR}hÉÔ·uid authorv */
	public static final String USI_ID_AUTHOR = "t";

	/** Ýèt@CÌt@C¼ */
	public static final String CONFIG_FILENAME = "SimpleGougiShogi.config";

	/** USIGWXgÌÅå */
	public static final int MAX_ENGINE_COUNT = 10;
	/** ½ci3ÒjÌêÌUSIGWXgÌ */
	public static final int TASUUKETSU_3_ENGINE_COUNT = 3;

	/** yc^Cvz½ci3Òj */
	public static final String GOUGI_TYPE_TASUUKETSU_3 = "½ci3Òj";
	/** yc^CvzyÏc */
	public static final String GOUGI_TYPE_RAKKAN = "yÏc";
	/** yc^CvzßÏc */
	public static final String GOUGI_TYPE_HIKAN = "ßÏc";
	/** yc^CvzyÏcÆßÏcððÝ */
	public static final String GOUGI_TYPE_RAKKAN_HIKAN_BYTURNS = "yÏcÆßÏcððÝ";

	/** c^CvÌXg */
	public static final List<String> GOUGI_TYPE_LIST;
	static {
		GOUGI_TYPE_LIST = new ArrayList<String>();
		GOUGI_TYPE_LIST.add(GOUGI_TYPE_TASUUKETSU_3);
		GOUGI_TYPE_LIST.add(GOUGI_TYPE_RAKKAN);
		GOUGI_TYPE_LIST.add(GOUGI_TYPE_HIKAN);
		GOUGI_TYPE_LIST.add(GOUGI_TYPE_RAKKAN_HIKAN_BYTURNS);
	}

	/** ]¿l¢Ýè */
	public static final int SCORE_NONE = Integer.MIN_VALUE + 1;
	/** MateÌ]¿l */
	public static final int SCORE_MATE = 100000;

}
