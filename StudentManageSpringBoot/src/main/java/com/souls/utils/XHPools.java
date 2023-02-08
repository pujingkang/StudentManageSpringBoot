package com.souls.utils;

class XHNode {
    String xh;
    String sessionID;
    Long enrollTime;
    XHNode next;

    public XHNode(String xh, String sessionID, Long enrollTime) {
        this.xh = xh;
        this.sessionID = sessionID;
        this.enrollTime = enrollTime;
    }
}

public class XHPools {
    private static XHNode head = null;
    public static Integer max = 0;

    //查询相同sessionID的请求是否已被分配学号
    private static XHNode checkExist(String sessionID) {
        XHNode node = XHPools.head;
        while (node != null && (!node.sessionID.equals(sessionID)))
            node = node.next;
        return node;
    }

    //查询sessionID已经失效的，重新利用学号
    private static XHNode repeatExist(Long currentTime) {
        XHNode node = XHPools.head;
        while (node != null && (currentTime - node.enrollTime) < (5 * 60 * 1000))
            node = node.next;
        return node;
    }

    public static String getXH(String sessionID) {
        Long currentTime = System.currentTimeMillis();
        XHNode node = checkExist(sessionID);
        if (node != null)
            return node.xh;
        node = repeatExist(currentTime);
        if (node != null) {
            node.sessionID = sessionID;
            node.enrollTime = currentTime;
            return node.xh;
        }
        XHPools.max++;
        String xh = getString(max);
        node = new XHNode(xh, sessionID, currentTime);
        node.next = head;
        head = node;
        return xh;
    }

    private static String getString(int max) {
        if (max < 10) return "XH000" + max;
        if (max < 100) return "XH00" + max;
        if (max < 1000) return "XH0" + max;
        return "HX" + max;
    }

    public static boolean setXHNodeInvalidte(String sessionID) {
        XHNode node = XHPools.head;
        while (node != null && (!node.sessionID.equals(sessionID)))
            node = node.next;
        if (node == null) return false;
        node.enrollTime = 0L;
        return true;
    }
}
