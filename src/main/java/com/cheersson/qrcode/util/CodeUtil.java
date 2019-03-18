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

    public static final String leftSeparator = "«";
    public static final String rightSeparator = "»";

    public static final String leftPlaceholderSign = "{";
    public static final String rightPlaceholderSign = "}";

    public static final String yearWeek = "yearWeek";
    public static final String customerItemCode = "customerItemCode";
    public static final String yearMonthDay = "yearMonthDay";

    public static List<String> split(String code) {
        List<String> resultList = new ArrayList<>();
        boolean left = false;
        int start = 0;
        for (int i = 0; i < code.length(); i++) {
            if (leftSeparator.equalsIgnoreCase(String.valueOf(code.charAt(i)))) {
                resultList.add(code.substring(start, i));
                start = i;
                left = true;
            } else if (rightSeparator.equalsIgnoreCase(String.valueOf(code.charAt(i)))) {
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

    /**
     *
     * @param code 扫描结果
     * @param rule 验证规则
     * @param categoryId
     * @return
     */
    public static boolean validate(String code, String rule, Long categoryId) {
        List<String> codeSplit = split(code);
        List<String> ruleSplit = split(rule);
        if (codeSplit.size() != ruleSplit.size()) {
            throw new BizException("扫描结果分组长度不符");
        }
        Map<String, String> placeholderMap = new HashMap<>();
        for (int i = 0; i < codeSplit.size(); i++) {
            String c = codeSplit.get(i);
            String r = ruleSplit.get(i);
            if (StringUtils.equals(r, c)) {
                continue;
            }
            if (c.startsWith(leftSeparator) || r.startsWith(leftSeparator)) {
                throw new BizException("分隔符不匹配,扫描结果是{}，验证规则是{}", c, r);
            }

            if (r.contains(leftPlaceholderSign) && r.contains(rightPlaceholderSign)) {
                //占位符
                placeholderMap.putAll(extractPlaceHolder(c, r));
            }else{
                throw new BizException("验证不通过，验证规则是{}", r);
            }
        }

        for (Map.Entry<String, String> entry : placeholderMap.entrySet()) {
            if (entry.getKey().equals(yearWeek)) {
                validateYearWeek(placeholderMap.get(yearMonthDay), entry.getValue());
            } else if (entry.getKey().equals(customerItemCode)) {
                //从数据库中查记录
                String customerItemCode = entry.getValue();
                ItemExample itemExample = new ItemExample();
                itemExample.createCriteria().andCategoryIdEqualTo(categoryId);
                List<Item> itemList = SpringContextHolder.getApplicationContext().getBean(BaseDao.class).selectByExample(itemExample);
                boolean found = false;
                for (Item item : itemList) {
                    if (item.getCustomerCode().equals(customerItemCode)) {
                        found = true;
                        break;
                    }
                }
                AssertUtil.isTrue(found, "客户料号不存在:{}", customerItemCode);
            } else {
                //throw new BizException("非法占位符:{}", entry.getKey());
            }
        }
        return true;
    }

    private static Map<String, String> extractPlaceHolder(String c, String r) {
        int from = r.indexOf(leftPlaceholderSign);
        int to = r.lastIndexOf(rightPlaceholderSign);

        if(from != 0){
            AssertUtil.isEqual(c.substring(0, from), r.substring(0, from), "参数不匹配{} vs {}", c, r);
        }
        if(to != r.length() - 1){
            AssertUtil.isEqual(c.substring(r.length() - to), r.substring(to -1), "参数不匹配{} vs {}", c, r);
        }

        Map<String, String> map = new HashMap<>();
        map.put(r.substring(from + 1, to), c.substring(from, c.length() - (r.length() - to - 1)));
        return map;
    }
}
