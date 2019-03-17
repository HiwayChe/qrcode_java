package com.cheersson.qrcode.util;

import com.cheersson.qrcode.dao.BaseDao;
import com.cheersson.qrcode.exception.BizException;
import com.cheersson.qrcode.model.Item;
import com.cheersson.qrcode.model.ItemExample;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeUtil {

    public static final char leftSeparator = '«';
    public static final char rightSeparator = '»';

    public static final String yearWeek = "{yearWeek}";
    public static final String customerItemCode = "{customerItemCode}";
    public static final String yearMonthDay = "{yearMonthDay}";

    public static List<String> split(String code) {
        List<String> resultList = new ArrayList<>();
        boolean left = false;
        int start = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == leftSeparator) {
                resultList.add(code.substring(start, i));
                start = i;
                left = true;
            } else if (code.charAt(i) == rightSeparator) {
                if (!left) {
                    throw new BizException(MessageUtil.getMessage("第{}位置的{}没有与之匹配的{}", i + 1, rightSeparator, leftSeparator));
                }
                resultList.add(code.substring(start, i + 1));
                start = i + 1;
                left = false;
            } else {

            }
        }
        if (left) {

        }
        if (start < code.length()) {
            resultList.add(code.substring(start));
        }
        resultList.removeIf(""::equalsIgnoreCase);
        return resultList;
    }

    public static void validateYearWeek(String date, String yearWeekStr) {
        int yearWeek = DateUtil.getYearWeek(DateUtil.parse(date, DateUtil.YYYYMMDD), true);
        int yearWeek2 = Integer.parseInt(yearWeekStr.substring(2));
        AssertUtil.isTrue(yearWeek == yearWeek2, "年周不匹配:{}应该是第{}周", date, yearWeek);
    }

    public static void validate(String code, String rule, Long categoryId) {
        List<String> codeSplit = split(code);
        List<String> ruleSplit = split(rule);
        if (codeSplit.size() != ruleSplit.size()) {
            throw new BizException("扫描结果长度不符");
        }
        Map<String, String> placeholderMap = new HashMap<>();
        for (int i = 0; i < codeSplit.size(); i++) {
            String c = codeSplit.get(i);
            String r = ruleSplit.get(i);
            if (c.equalsIgnoreCase(r)) {
                continue;
            }
            if (c.startsWith(leftSeparator + "") || r.startsWith(leftSeparator + "")) {
                throw new BizException("分隔符不匹配");
            }
            if (StringUtils.equals(r, c)) {
                continue;
            }

            if (r.contains("{")) {
                //占位符
                placeholderMap.putAll(extractPlaceHolder(c, r));
            }
            throw new BizException("验证不通过:{},{}", r, c);
        }

        for (Map.Entry<String, String> entry : placeholderMap.entrySet()) {
            if (entry.getKey().equals(yearWeek)) {
                validateYearWeek(placeholderMap.get(yearMonthDay), entry.getValue());
            } else if (entry.getKey().equals(customerItemCode)) {
                //从数据库中查记录
                String customerItemCode = entry.getValue();
                ItemExample itemExample = new ItemExample();
                itemExample.createCriteria().andCategoryidEqualTo(categoryId);
                List<Item> itemList = SpringContextHolder.getApplicationContext().getBean(BaseDao.class).selectByExample(itemExample);
                boolean found = false;
                for (Item item : itemList) {
                    if (item.getCustomercode().equals(customerItemCode)) {
                        found = true;
                        break;
                    }
                }
                AssertUtil.isTrue(found, "客户料号不存在:{}", customerItemCode);
            } else {
                throw new BizException("非法占位符:{}", entry.getKey());
            }
        }
    }

    private static Map<String, String> extractPlaceHolder(String c, String r) {
        int leftBrace = r.indexOf("{");
        int rightBrace = r.lastIndexOf("}");
        if (leftBrace != -1) {
            AssertUtil.isEqual(c.substring(0, leftBrace), r.substring(0, leftBrace));
        }
        if (rightBrace != -1) {
            AssertUtil.isEqual(c.substring(rightBrace + 1), r.substring(rightBrace + 1));
        }

        return (Map<String, String>) com.cheersson.qrcode.util.MapUtils.newMap(r.substring(leftBrace == -1 ? 0 : leftBrace, rightBrace == -1 ? r.length() : rightBrace),
                c.substring(leftBrace == -1 ? 0 : leftBrace, rightBrace == -1 ? c.length() : rightBrace));
    }
}
