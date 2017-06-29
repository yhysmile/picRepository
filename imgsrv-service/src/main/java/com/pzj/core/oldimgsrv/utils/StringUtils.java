package com.pzj.core.oldimgsrv.utils;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	private static final String COMMA_SEPARATOR = ",";
	private static final String DEFAULT_CHARSET_NAME = "UTF-8";
	private static final DecimalFormat intDF = new DecimalFormat("0");
	private static final int MOBILE_SHADOW_START = 3;
	private static final int MOBILE_SHADOW_END = 8;
	private static final int IDCARD_SHADOW_START = 6;
	private static final int IDCARD_SHADOW_END = 13;
	private static final char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	private static Pattern argPattern = Pattern.compile("\\{\\d\\}");

	public static String deleteOne(String origin, String sub) {
		int begin = origin.indexOf(sub);
		if (begin == -1) {
			return origin;
		}
		String result = origin.substring(0, begin)
				+ origin.substring(begin + sub.length(), origin.length());
		return result;
	}

	public static boolean contains(String str, String match) {
		for (int i = 0; i < match.length(); i++) {
			if (str.indexOf(match.charAt(i)) != -1) {
				return true;
			}
		}
		return false;
	}

	public static String arrayToString(Long[] arr) {
		String str = "";
		if ((arr == null) || (arr.length == 0))
			return str;
		StringBuffer sb = new StringBuffer();
		Long[] arrayOfLong = arr;
		int j = arr.length;
		for (int i = 0; i < j; i++) {
			Long element = arrayOfLong[i];
			sb.append(element);
			sb.append(",");
		}
		str = sb.toString();
		int index = str.lastIndexOf(",");
		return str.substring(0, index);
	}

	public static String arrayToString(String[] arr) {
		String str = null;
		if ((arr == null) || (arr.length == 0))
			return "";
		StringBuffer sb = new StringBuffer();
		String[] arrayOfString = arr;
		int j = arr.length;
		for (int i = 0; i < j; i++) {
			String element = arrayOfString[i];
			sb.append(element);
			sb.append(",");
		}
		str = sb.toString();
		int index = str.lastIndexOf(",");
		return str.substring(0, index);
	}

	public static String[] stringToArray(String str) {
		String[] arr = new String[0];
		if ((str != null) && (!str.trim().equals("")))
			arr = str.split(",");
		return arr;
	}

	public static String filtrateString(String resStr, String delStr) {
		String str = null;
		if ((resStr == null) || (delStr == null) || (resStr.trim().equals(""))
				|| (delStr.trim().equals("")))
			return str;
		String[] arr = resStr.split(",");
		StringBuffer sb = new StringBuffer();
		for (String element : arr) {
			if (!element.equals(delStr)) {
				sb.append(element);
				sb.append(",");
			}
		}
		str = sb.toString();
		int index = str.lastIndexOf(",");
		if (index == -1)
			return "";
		return str.substring(0, index);
	}

	public static String addString(String resStr, String addStr) {
		if ((resStr == null) || ("".equals(resStr))) {
			return addStr;
		}
		return resStr + "," + addStr;
	}

	public static boolean hasString(String resSts, String hasStr) {
		if ((resSts == null) || ("".equals(resSts))) {
			return false;
		}
		resSts = "," + resSts + ",";
		if (resSts.indexOf("," + hasStr + ",") > -1) {
			return true;
		}
		return false;
	}

	public static String mysqlEscape(String str) {
		if (str == null)
			return "";
		return str.replace("\\", "\\\\").replace("'", "\\'")
				.replace("\"", "\\\"");
	}

	public static String tlogEscape(String str) {
		if (str == null)
			return "";
		return str.replace("|", "丨");
	}

	public static String nameEscape(String str) {
		if (str == null)
			return "";
		return str.replace("$", "﹩");
	}

	public static boolean isGBK(char c) {
		String regEx = "[\\u4e00-\\u9fa5]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(String.valueOf(c));
		return m.find();
	}

	public static boolean isEmpty(String src) {
		if (src == null)
			return true;
		if (src.trim().equals(""))
			return true;
		if (src.trim().equalsIgnoreCase("null"))
			return true;
		return false;
	}

	public static int[] parseIntArray(String[] strs) {
		try {
			int[] result = new int[strs.length];
			for (int i = 0; i < strs.length; i++) {
				result[i] = Integer.parseInt(strs[i]);
			}
			return result;
		} catch (Exception localException) {
		}
		return new int[0];
	}

	public static String getUTF8Str(String str) {
		try {
			return new String(str.getBytes("UTF-8"), "UTF-8");
		} catch (Exception e) {
		}
		return str;
	}

	public static String getGBKStr(String str, String encoding) {
		try {
			return new String(str.getBytes(encoding), "GBK");
		} catch (Exception e) {
		}
		return str;
	}

	public static String getUTF8Str(String str, String encoding) {
		try {
			return new String(str.getBytes(encoding), "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		return str;
	}

	public static int getLength(String str) {
		if (str == null)
			return 0;
		int len = 0;

		char[] charArray = str.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char tempchar = charArray[i];
			if (Character.getType(tempchar) == 5)
				len += 2;
			else {
				len++;
			}
		}
		return len;
	}

	public static String splitString(String str, int num) {
		if ((str == null) || (getLength(str) <= num)) {
			return str;
		}
		char[] charArray = str.toCharArray();
		String subStr = "";
		int index = 0;
		int i = 0;
		for (int j = 0; i < num;) {
			if (j >= charArray.length) {
				break;
			}
			char tempchar = charArray[j];
			if (Character.getType(tempchar) == 5) {
				i += 2;
				if (i > num) {
					if (i == num + 1)
						continue;
				}
			} else {
				i++;
			}
			index++;
			j++;
		}
		subStr = str.substring(0, index);
		return subStr;
	}

	public static String join(String joiner, List objsvec) {
		if (objsvec == null)
			return "";
		return join(joiner, objsvec.toArray());
	}

	public static String join(String sep, Set set) {
		StringBuilder sb = new StringBuilder();
		for (Iterator localIterator = set.iterator(); localIterator.hasNext();) {
			Object o = localIterator.next();
			sb.append(sep);
			sb.append(o.toString());
		}

		return sb.substring(1, sb.length());
	}

	public static String join(String sep, Object[] objs) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < objs.length; i++) {
			sb.append(objs[i]);
			if (i != objs.length - 1) {
				sb.append(sep);
			}
		}
		return sb.toString();
	}

	public static String join(String sep, String[] objs) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < objs.length; i++) {
			sb.append(objs[i]);
			if (i != objs.length - 1) {
				sb.append(sep);
			}
		}
		return sb.toString();
	}

	public static String replaceArgs(String src, Object[] refs) {
		for (int i = 0; i < refs.length; i++) {
			src.indexOf("{" + (i + 1) + "}");

			src = src.replace("{" + (i + 1) + "}", refs[i].toString());
		}
		Matcher matcher = argPattern.matcher(src);
		StringBuilder sb = new StringBuilder();
		while (matcher.find()) {
			sb.append(matcher.group()).append(", ");
		}
		if (sb.length() > 0) {
			sb.append(" do not replaced in \"").append(src).append("\"");
			throw new IllegalArgumentException(sb.toString());
		}
		return src;
	}

	public static long parseScheduleTimer(String timerString) {
		String patternString = "(((\\d+)(d))?((\\d+)(h))?((\\d+)(m))?((\\d+)(s))?)*";

		Pattern pattern = Pattern.compile(patternString);

		Matcher matcher = pattern.matcher(timerString);
		int groupIndex = 3;
		long timer = 0L;

		if (matcher.find()) {
			for (int i = 0; i < 4; i++) {
				if (matcher.group(groupIndex) != null) {
					int count = Integer.valueOf(matcher.group(groupIndex))
							.intValue();
					String unit = matcher.group(groupIndex + 1);
					if ("d".equals(unit))
						timer += TimeUnit.DAYS.toMillis(count);
					else if ("h".equals(unit))
						timer += TimeUnit.HOURS.toMillis(count);
					else if ("m".equals(unit))
						timer += TimeUnit.MINUTES.toMillis(count);
					else if ("s".equals(unit))
						timer += TimeUnit.SECONDS.toMillis(count);
				}
				groupIndex += 3;
			}
		}
		return timer;
	}

	public static String filterOffUTF8MB4(String text) {
		try {
			byte[] bytes = text.getBytes("UTF-8");

			ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
			int i = 0;
			while (i < bytes.length) {
				short b = bytes[i];
				if (b > 0) {
					buffer.put(bytes[(i++)]);
				} else {
					b = (short) (b + 256);
					if ((b ^ 0xC0) >> 4 == 0) {
						buffer.put(bytes, i, 2);
						i += 2;
					} else if ((b ^ 0xE0) >> 4 == 0) {
						buffer.put(bytes, i, 3);
						i += 3;
					} else if ((b ^ 0xF0) >> 4 == 0) {
						i += 4;
					}
				}
			}
			buffer.flip();
			return new String(buffer.array(), "UTF-8");
		} catch (UnsupportedEncodingException ex) {
		}
		return text;
	}

	public static String replaceTextScript(String text) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while (i < text.length()) {
			if (text.charAt(i) == '$') {
				i++;
				String tmp = text.substring(i);
				System.out.println("tmp:" + tmp);

				if (tmp.startsWith("l")) {
					int idx = tmp.indexOf("$z");
					if (idx > 0) {
						tmp = tmp.substring(1, idx);
						i += 1 + tmp.length() + "$z".length();
						System.out.println("real tmp : " + tmp);
						String url = tmp.substring(0, tmp.indexOf("$"));
						String t1 = tmp.substring(tmp.lastIndexOf("$") + 1,
								tmp.length());
						System.out.println("URL:" + url);
						System.out.println("desc:" + t1);
						sb.append("<a href=\"").append(url).append("\">")
								.append(t1).append("</a>");
					} else {
						throw new IllegalArgumentException(
								"input text error, script format error :"
										+ text);
					}
				} else if (tmp.startsWith("i")) {
					int found = 0;
					while (i < text.length()) {
						if (text.charAt(i++) == '$')
							found++;
						if (found == 3)
							break;
					}
					if (found != 3)
						throw new IllegalArgumentException(
								"input text error, script format error :"
										+ text);
				} else if (tmp.startsWith("t")) {
					int idx = tmp.indexOf("$z");
					if (idx > 0) {
						tmp = tmp.substring(1, idx);
						i += 1 + tmp.length() + "$z".length();
						String tt = tmp.substring(tmp.indexOf("$") + 1,
								tmp.length());
						System.out.println(tt);
						sb.append(tt);
					} else {
						throw new IllegalArgumentException(
								"input text error, script format error :"
										+ text);
					}
				}
			} else {
				sb.append(text.charAt(i));
				i++;
			}
		}

		return sb.toString();
	}

	public static String[] splitFileName(String filename) {
		String[] str = new String[2];
		int lastIndex = filename.lastIndexOf(".");
		if (lastIndex >= 0) {
			str[0] = filename.substring(0, lastIndex);
			str[1] = filename.substring(lastIndex + 1, filename.length());
		} else {
			str[0] = filename;
			str[1] = "";
		}
		return str;
	}

	public static String getLoginMsg(String username, String passwd,
			Long loginId) {
		if ((loginId == null) || (loginId.longValue() <= 0L))
			return null;
		if ((username == null) || (passwd == null))
			return null;
		StringBuffer sb = new StringBuffer();
		username = username.toLowerCase();
		sb.append(username.substring(0, 1)).append(passwd.substring(0, 2))
				.append(username.substring(1, 2))
				.append(passwd.substring(2, 6)).append(loginId)
				.append(username.substring(2, 4))
				.append(passwd.substring(6, passwd.length()))
				.append(username.substring(4, username.length()));
		return sb.toString();
	}

	public static String phoneRandom() {
		boolean numberFlag = true;
		int length = 6;

		String retStr = "";
		String strTable = numberFlag ? "1234567890"
				: "1234567890abcdefghijkmnpqrstuvwxyz";
		int len = strTable.length();
		boolean bDone = true;
		do {
			retStr = "";
			int count = 0;
			for (int i = 0; i < length; i++) {
				double dblR = Math.random() * len;
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if (('0' <= c) && (c <= '9')) {
					count++;
				}
				retStr = retStr + strTable.charAt(intR);
			}
			if (count >= 2)
				bDone = false;
		} while (bDone);

		return retStr;
	}

	public static String parseNumber(Number num) {
		return intDF.format(num);
	}

	public static String getRandomNum(int num) {
		Random random = new Random();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < num; i++) {
			buf.append(random.nextInt(9));
		}
		return buf.toString();
	}

	public static boolean isLong(String longStr) {
		try {
			long l = Long.parseLong(longStr);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static String replace2(String mainStr, String oldStr, String newStr) {
		StringBuffer buffer = new StringBuffer(mainStr);
		int index = 0;
		int begin = 0;
		while ((index = buffer.toString().indexOf(oldStr, begin)) > -1) {
			buffer.replace(index, index + oldStr.length(), newStr);
			begin = index + newStr.length();
		}
		return buffer.toString();
	}

	public static boolean isInteger(String integerStr) {
		return true;
	}

	public static String shadowMobile(String mobile) throws Exception {
		if ((mobile == null) || (mobile.trim().length() != 11))
			throw new Exception("手机号码不正确");
		mobile = mobile.trim();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mobile.length(); i++) {
			if ((i >= 3) && (i <= 8))
				sb.append("*");
			else
				sb.append(mobile.charAt(i));
		}
		return sb.toString();
	}

	public static String shadowIdCard(String idcard) throws Exception {
		if ((idcard == null) || (idcard.trim().length() != 18))
			throw new Exception("身份证不正确");
		idcard = idcard.trim();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < idcard.length(); i++) {
			if ((i >= 6) && (i <= 13))
				sb.append("*");
			else
				sb.append(idcard.charAt(i));
		}
		return sb.toString();
	}

	private static char toHex(int nibble) {
		return hexDigit[(nibble & 0xF)];
	}

	public static String toEncodedUnicode(String theString, boolean escapeSpace) {
		int len = theString.length();
		int bufLen = len * 2;
		if (bufLen < 0) {
			bufLen = 2147483647;
		}
		StringBuffer outBuffer = new StringBuffer(bufLen);

		for (int x = 0; x < len; x++) {
			char aChar = theString.charAt(x);

			if ((aChar > '=') && (aChar < '')) {
				if (aChar == '\\') {
					outBuffer.append('\\');
					outBuffer.append('\\');
				} else {
					outBuffer.append(aChar);
				}
			} else
				switch (aChar) {
				case ' ':
					if ((x == 0) || (escapeSpace))
						outBuffer.append('\\');
					outBuffer.append(' ');
					break;
				case '\t':
					outBuffer.append('\\');
					outBuffer.append('t');
					break;
				case '\n':
					outBuffer.append('\\');
					outBuffer.append('n');
					break;
				case '\r':
					outBuffer.append('\\');
					outBuffer.append('r');
					break;
				case '\f':
					outBuffer.append('\\');
					outBuffer.append('f');
					break;
				case '!':
				case '#':
				case ':':
				case '=':
					outBuffer.append('\\');
					outBuffer.append(aChar);
					break;
				default:
					if ((aChar < ' ') || (aChar > '~')) {
						outBuffer.append('\\');
						outBuffer.append('u');
						outBuffer.append(toHex(aChar >> '\f' & 0xF));
						outBuffer.append(toHex(aChar >> '\b' & 0xF));
						outBuffer.append(toHex(aChar >> '\004' & 0xF));
						outBuffer.append(toHex(aChar & 0xF));
					} else {
						outBuffer.append(aChar);
					}
					break;
				}
		}
		return outBuffer.toString();
	}

	public static String fromEncodedUnicode(char[] in, int off, int len) {
		char[] out = new char[len];
		int outLen = 0;
		int end = off + len;
		while (off < end) {
			char aChar = in[(off++)];
			if (aChar == '\\') {
				aChar = in[(off++)];
				if (aChar == 'u') {
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = in[(off++)];
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - 48;
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 97;
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 65;
							break;
						case ':':
						case ';':
						case '<':
						case '=':
						case '>':
						case '?':
						case '@':
						case 'G':
						case 'H':
						case 'I':
						case 'J':
						case 'K':
						case 'L':
						case 'M':
						case 'N':
						case 'O':
						case 'P':
						case 'Q':
						case 'R':
						case 'S':
						case 'T':
						case 'U':
						case 'V':
						case 'W':
						case 'X':
						case 'Y':
						case 'Z':
						case '[':
						case '\\':
						case ']':
						case '^':
						case '_':
						case '`':
						default:
							throw new IllegalArgumentException(
									"Malformed \\uxxxx encoding.");
						}
					}
					out[(outLen++)] = ((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f') {
						aChar = '\f';
					}
					out[(outLen++)] = aChar;
				}
			} else {
				out[(outLen++)] = aChar;
			}
		}
		return new String(out, 0, outLen);
	}

	public static String toJSUnicode(String s) {
		return s.replaceAll("\\\\u", "\\%u");
	}

	public static String doubleToString(double d) {
		NumberFormat nf = new DecimalFormat("####");
		return nf.format(d);
	}

	public static String getWeekDate(String week) {
		StringBuffer buf = new StringBuffer();
		char[] arr = week.toCharArray();
		if ("1".equals(arr[0])) {
			buf.append("1").append(",");
		}
		if ("1".equals(arr[1])) {
			buf.append("2").append(",");
		}
		if ("1".equals(arr[2])) {
			buf.append("3").append(",");
		}
		if ("1".equals(arr[3])) {
			buf.append("4").append(",");
		}
		if ("1".equals(arr[4])) {
			buf.append("5").append(",");
		}
		if ("1".equals(arr[5])) {
			buf.append("6").append(",");
		}
		if ("1".equals(arr[6])) {
			buf.append("7");
		}
		String str = buf.toString();
		return str.length() > 0 ? str.substring(0, str.length() - 1) : "";
	}

	public static void main(String[] args) throws Exception {
	}
}