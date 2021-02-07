package org.example;

import java.util.*;

public class Solution {
    /**
     * leetcode 888. 公平的糖果棒交换
     * @param A
     * @param B
     * @return
     */
    /*
    public int[] fairCandySwap(int[] A, int[] B) {
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        float flag = (sumA - sumB) / 2.0f;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int temp = A[i] - B[j];
                if (temp == flag){
                    return new int[]{A[i], B[j]};
                }
            }
        }
        return null;
    }
    */
    public int[] fairCandySwap(int[] A, int[] B) {
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        int flag = (sumA - sumB);
        for (int i = 0; i < A.length; i++) {
            A[i] = A[i] * 2;
        }
        Arrays.sort(A);
        for (int j = 0; j < B.length; j++) {
            int target = flag + 2 * B[j];
            int i = Arrays.binarySearch(A, target);
            if (i >= 0){
                return new int[]{A[i]/2, B[j]};
            }
        }
        return null;
    }

    /**
     * leetcode 剑指 Offer 42. 连续子数组的最大和
     * @param nums
     * @return
     */
    /*
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            temp += nums[i];
            if (temp > maxSum){
                maxSum = temp;
            }
            if (temp < 0){
                temp = 0;
            }
        }
        return maxSum;
    }
    */
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] <= 0){
                dp[i] = nums[i];
                if (dp[i] > max){
                    max = dp[i];
                }
            }else {
                dp[i] = dp[i - 1] + nums[i];
                if (dp[i] > max){
                    max = dp[i];
                }
            }
        }
        return Math.max(max,dp[0]);
    }

    /**
     * leetcode 剑指 Offer 58 - II. 左旋转字符串
     * @param s
     * @param n
     * @return
     */
    public String reverseLeftWords(String s, int n) {
        char[] c = s.toCharArray();
        char[] pre = new char[n];
        StringBuilder sb = new StringBuilder();
        if (n > s.length()){
            n = s.length();
        }
        for (int i = 0; i < s.length(); i++) {
            if (i < n){
                pre[i] = c[i];
            }else {
                sb.append(c[i]);
            }
        }
        for (char item : pre) {
            sb.append(item);
        }
       return sb.toString();
    }

    /**
     * leetcode 剑指 Offer 11. 旋转数组的最小数字
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        Arrays.sort(numbers);
        return numbers[0];
    }

    /**
     * leetcode 剑指 Offer 57. 和为s的两个数字
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        for (int n : nums) {
            int searchTarget = target - n;
            int xb = Arrays.binarySearch(nums, searchTarget);
            if (xb >= 0){
                return new int[]{nums[xb], n};
            }
        }
        return null;
    }

    /**
     * leetcode 剑指 Offer 03. 数组中重复的数字
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        Map map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])){
                return nums[i];
            }else {
                map.put(nums[i],null);
            }
        }
        return -1;
    }

    /**
     * leetcode 剑指 Offer 53 - I. 在排序数组中查找数字 I
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int count = 0;
        for (int item : nums) {
            if (item == target){
                count++;
            }
        }
        return count;
    }

    /**
     * leetcode 剑指 Offer 39. 数组中出现次数超过一半的数字
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    /**
     * leetcode 剑指 Offer 57 - II. 和为s的连续正数序列
     * @param target
     * @return
     */
    public int[][] findContinuousSequence(int target) {
        int sum = 0;
        int count = 1;
        int index = 1;
        List list = new ArrayList();
        Deque queue = new ArrayDeque();
        while (index < target){
            sum += index;
            if (sum < target){
                queue.add(index);
                index++;
            }
            if (sum > target){
                count++;
                index = count;
                queue.clear();
                sum = 0;
            }
            if (sum == target){
                queue.add(index);
                int[] temp = new int[queue.size()];
                for (int i = 0; i < temp.length; i++) {
                    temp[i] = (int)queue.poll();
                }
                list.add(temp);
                queue.clear();
                count++;
                index = count;
                sum = 0;
            }
        }
        int[][] A = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            A[i] = (int[]) list.get(i);
        }
        return A;
    }

    /**
     * leetcode 剑指 Offer 53 - II. 0～n-1中缺失的数字
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int i = 0;
        for (; i < nums.length; i++) {
            if (i != nums[i]){
                return i;
            }
        }
        return i;
    }

    /**
     * leetcode 剑指 Offer 50. 第一个只出现一次的字符
     * @param s
     * @return
     */
    /*
    public char firstUniqChar(String s) {
        char[] c = s.toCharArray();
        for (char item : c) {
            int xb0 = s.indexOf(item);
            int xb1 = s.lastIndexOf(item);
            if (xb0 != xb1){
                continue;
            }else {
                return item;
            }
        }
        return ' ';
    }
    */
    public char firstUniqChar(String s) {
        //使用字典方式，记录字符串s中每个字母出现的次数。
        int[] dictionary = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            dictionary[index]++;
        }
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if (dictionary[index] == 1){
                return s.charAt(i);
            }
        }
        return ' ';
    }

    /**
     * leetcode 剑指 Offer 62. 圆圈中最后剩下的数字
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining(int n, int m) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int index = (m - 1) % list.size();//第一个删除元素的索引
        while (list.size() > 1){
            list.remove(index);//remove后列表的长度会减少1，下一个开始元素的索引相当于原来删除元素的索引
            index = (m - 1 + index) % list.size();//下一个删除元素的索引
        }
        return list.get(0);
    }

    /**
     * leetcode 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
     * @param nums
     * @return
     */
    public int[] exchange(int[] nums) {
        List<Integer> oddList = new ArrayList<>();
        List<Integer> evenList = new ArrayList<>();
        for (int item : nums) {
            if (item % 2 == 1){
                oddList.add(item);
            }else {
                evenList.add(item);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i < oddList.size()){
                nums[i] = oddList.get(i);
            }else {
                nums[i] = evenList.get(i - oddList.size());
            }
        }
        return nums;
    }

    /**
     * leetcode 1480. 一维数组的动态和
     * @param nums
     * @return
     */
    public int[] runningSum(int[] nums) {
        int[] sum = new int[nums.length];
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            temp += nums[i];
            sum[i] = temp;
        }
        return sum;
    }

    /**
     * leetcode 1588. 所有奇数长度子数组的和
     * @param arr
     * @return
     */
    public int sumOddLengthSubarrays(int[] arr) {
        //先按数组长度划分子数组，保存每个数组对应的数组和
        int oddLen = 1;
        int sum = 0;
        while (oddLen <= arr.length){
            int index = 0;
            int[] oddArray = new int[oddLen];
            while (index < (arr.length - oddLen + 1)){
                for (int i = 0; i < oddArray.length; i++) {
                    oddArray[i] = arr[index + i];
                }
                for (int i = 0; i < oddArray.length; i++) {
                    sum += oddArray[i];
                }
                index++;
            }
            oddLen += 2;

        }
        return sum;
    }

    /**
     * leetcode 893. 特殊等价字符串组
     * @param A
     * @return
     */
    public int numSpecialEquivGroups(String[] A) {
        Set<String> set = new HashSet<>();
        for (String s : A) {
            int[] odd = new int[26];
            int[] even = new int[26];
            for (int i = 0; i < s.length(); i++) {
                if (i % 2 == 1){
                    odd[(s.charAt(i) - 'a')]++;
                }else {
                    even[(s.charAt(i) - 'a')]++;
                }
            }
            set.add(Arrays.toString(odd)+Arrays.toString(even));
        }
        return set.size();
    }

    /**
     * leetcode 1. 两数之和
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer,Object> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])){
                return new int[]{i, (int)map.get(nums[i])};
            }else {
                map.put(target - nums[i],i);
            }
        }
        return null;
    }

    /**
     * 2. 两数相加
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        StringBuilder sb1 = new StringBuilder();//记录l1对应的数字
        StringBuilder sb2 = new StringBuilder();//记录l2对应的数字
        ListNode tempNode = l1;
        while (tempNode != null){
            sb1.append(tempNode.val);
            tempNode = tempNode.next;
        }
        tempNode = l2;
        while (tempNode != null){
            sb2.append(tempNode.val);
            tempNode = tempNode.next;
        }
//        System.out.println(sb1);
//        System.out.println(sb2);
        StringBuilder temp = new StringBuilder();//记录方法返回值对应的字符串
        int tempSum = 0;//记录对应位置的两个数之和
        int index = 0;
        boolean isUp = false;
        while (index < sb1.length() && index < sb2.length()){
            String str1 = sb1.substring(index,index+1);
            String str2 = sb2.substring(index,index+1);
            if (isUp){
                tempSum = Integer.parseInt(str1) + Integer.parseInt(str2) + 1;
                isUp = false;
            }else {
                tempSum = Integer.parseInt(str1) + Integer.parseInt(str2);
            }
//            System.out.println("index:" + index + "  " + str1 + "+" + str2 + "=" + tempSum);
            if (tempSum >= 10){
                isUp = true;
                tempSum = tempSum % 10;
            }
            temp.append(tempSum);
            index++;
        }
        while (index < sb1.length()){
            String str1 = sb1.substring(index,index+1);
            String str2 = "0";
            if (isUp){
                tempSum = Integer.parseInt(str1) + Integer.parseInt(str2) + 1;
                isUp = false;
            }else {
                tempSum = Integer.parseInt(str1) + Integer.parseInt(str2);
            }
//            System.out.println("index:" + index + "  " + str1 + "+" + str2 + "=" + tempSum);
            if (tempSum >= 10){
                isUp = true;
                tempSum = tempSum % 10;
            }
            temp.append(tempSum);
            index++;
        }
        while (index < sb2.length()){
            String str1 = "0";
            String str2 = sb2.substring(index,index+1);
            if (isUp){
                tempSum = Integer.parseInt(str1) + Integer.parseInt(str2) + 1;
                isUp = false;
            }else {
                tempSum = Integer.parseInt(str1) + Integer.parseInt(str2);
            }
//            System.out.println("index:" + index + "  " + str1 + "+" + str2 + "=" + tempSum);
            if (tempSum >= 10){
                isUp = true;
                tempSum = tempSum % 10;
            }
            temp.append(tempSum);
            index++;
        }
        if (isUp){
            temp.append("1");
            isUp = false;
        }
//        System.out.println(temp.toString());
        //将temp转为链表存储
        ListNode headNode = new ListNode();
        tempNode = headNode;
        for (int i = 0; i < temp.length(); i++) {
            int val = Integer.parseInt(temp.substring(i,i+1));
            tempNode.val = val;
            if (i < temp.length() - 1){
                ListNode aNode = new ListNode();
                tempNode.next = aNode;
                tempNode = aNode;
            }
        }
        return headNode;
    }

    /**
     * leetcode 3. 无重复字符的最长子串
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0){
            return 0;
        }
        int maxSub = countNoRepeatedCharacter(s);
        while (maxSub > 0){
            int index = 0;
            while (index + maxSub - 1 < s.length()){
                String str = s.substring(index, index + maxSub - 1 + 1);
                if (countNoRepeatedCharacter(str) != maxSub){
                    index++;
                }else {
                    return maxSub;
                }
            }
            maxSub -= 1;
        }
        return -1;
    }
    public int countNoRepeatedCharacter(String s){
        int[] dictionary = new int[128];
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i);
            dictionary[index]++;
        }
        int count = 0;//记录不重复元素的个数
        for (int n : dictionary) {
            if (n != 0 ){
                count++;
            }
        }
        return count;
    }

    /**
     * leetcode 4. 寻找两个正序数组的中位数
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] temp = new int[m+n];
        int i = 0;//nums1的初始下标
        int j = 0;//nums2的初始下标
        int index = 0;//temp的初始下标
        while (i < m && j < n){
            if (nums1[i] <= nums2[j]){
                temp[index++] = nums1[i];
                i++;
            }else {
                temp[index++] = nums2[j];
                j++;
            }
        }
        while (i < m){
            temp[index++] = nums1[i];
            i++;
        }
        while (j < n){
            temp[index++] = nums2[j];
            j++;
        }
//        System.out.println(Arrays.toString(temp));
        if (temp.length % 2 == 1){
            return temp[temp.length / 2];
        }else {
            return (temp[temp.length / 2] + temp[temp.length / 2 - 1]) / 2.0f;
        }
    }

    /**
     * leetcode 5. 最长回文子串
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int maxLen = s.length();
        while (maxLen > 0){
            int start = 0;
            while (start + maxLen <= s.length()){
                String str = s.substring(start, start + maxLen - 1 + 1);
                if (isPalindrome(str)){
                    return str;
                }else {
                    start++;
                }
            }
            maxLen--;
        }
        return null;
    }
    public boolean isPalindrome(String s){
        int mid = s.length() / 2;
        int index = 0;
        while (index < mid){
            char i = s.charAt(index);
            char j = s.charAt(s.length() - 1 - index);
            if (i == j ){
                index++;
            }else {
                return false;
            }
        }
        return true;
    }

    /**
     * leetcode 10. 正则表达式匹配
     * @param s
     * @param p
     * @return
     */
    /*
    public boolean isMatch(String s, String p) {
        int i = 0;
        int index = 0;
        while (index < p.length()){
            char now = p.charAt(index);//当前规律字符
            if (index + 1 < p.length()){//如果为true则当前规律字符存在下一个字符
                char next = p.charAt(index+1);//当前规律字符的下一个字符
                if (next != '*'){
                    //此时只匹配1个当前规律字符
                    if (s.charAt(i) == now || now == '.'){
                        i++;
                        index++;
                    }else {
                        return false;
                    }
                }else {
                    //此时匹配0~多个当前规律字符
                    while (i < s.length() && (s.charAt(i) == now || now == '.')){
                        i++;
                    }
                    index += 2;
                }
            }else {
                if (index >= 2 && p.charAt(index) == p.charAt(index-2)){
                    return true;
                }else {
                    if (i < s.length() && (now == s.charAt(i) || now == '.')){
                        index++;
                        i++;
                    }
                }
            }
        }
        if (i == s.length() && index == p.length()){
            return true;
        }else {
            return false;
        }
    }
    */    //超时
    public boolean isMatch(String s, String p) {
        //待填坑
        return java.util.regex.Pattern.matches(p,s);
    }

    /**
     * leetcode 121. 买卖股票的最佳时机
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice){
                minPrice = prices[i];
            }else {
                if (prices[i] - minPrice > maxProfit){
                    maxProfit = prices[i] - minPrice;
                }
            }
        }
        return maxProfit;
    }

}
