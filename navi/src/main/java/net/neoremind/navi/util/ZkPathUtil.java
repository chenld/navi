package net.neoremind.navi.util;

import net.neoremind.navi.constant.NaviCommonConstant;

public class ZkPathUtil {

	/**
	 * Try to assamble paths into a string without duplicated / character problem
	 * By default, every beginner path should startWith /
	 * 
	 * @param paths in array
	 * @return path
	 */
	public static String buildPath(String... paths) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < paths.length; i++) {
			String cur = paths[i];
			int curLen = cur.length();
			String next = null;
			if (i < paths.length - 1) {
				next = paths[i + 1];
			}
			if (cur.endsWith(NaviCommonConstant.ZK_PATH_SEPARATOR)) {
				if (next != null) {
					if (next.startsWith(NaviCommonConstant.ZK_PATH_SEPARATOR)) {
						result.append(cur.substring(0, curLen - 1));
					} else {
						result.append(cur.substring(0, curLen));
					}
				} else {
					result.append(cur.substring(0, curLen - 1));
				}
			} else {
				if (next != null) {
					if (next.startsWith(NaviCommonConstant.ZK_PATH_SEPARATOR)) {
						result.append(cur.substring(0, curLen));
					} else {
						result.append(cur.substring(0, curLen));
						result.append(NaviCommonConstant.ZK_PATH_SEPARATOR);
					}
				} else {
					result.append(cur.substring(0, curLen));
				}
			}
		}
		return result.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(ZkPathUtil.buildPath(new String[]{"/a", "/b", "/c"}));
		System.out.println(ZkPathUtil.buildPath(new String[]{"/a/", "/b", "/c"}));
		System.out.println(ZkPathUtil.buildPath(new String[]{"/a", "b", "/c"}));
		System.out.println(ZkPathUtil.buildPath(new String[]{"/a/", "b", "/c"}));
		
		System.out.println(ZkPathUtil.buildPath(new String[]{"/a/x/", "/b", "/c"}));
		System.out.println(ZkPathUtil.buildPath(new String[]{"/a/x", "/b", "c"}));
		System.out.println(ZkPathUtil.buildPath(new String[]{"/a/x/", "b", "c"}));
		System.out.println(ZkPathUtil.buildPath(new String[]{"/a/x", "b", "/c"}));
	}

}
