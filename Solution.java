package org.example;

import java.util.*;

public class Solution {
    /**
     * leetcode 888.公平的糖果棒交换
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
     * leetcode 剑指Offer42.连续子数组的最大和
     * @param nums
     * @return
     */
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

    /**
     * leetcode 剑指Offer58-II.左旋转字符串
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
     * leetcode 剑指Offer11.旋转数组的最小数字
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        Arrays.sort(numbers);
        return numbers[0];
    }

    /**
     * leetcode 剑指Offer57.和为s的两个数字
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
     * leetcode 剑指Offer53-I.在排序数组中查找数字 I
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
     * leetcode 剑指Offer39.数组中出现次数超过一半的数字
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    /**
     * leetcode 剑指Offer57-II.和为s的连续正数序列
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
                index =count;
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
     * leetcode 剑指Offer53-II.0～n-1中缺失的数字
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
     * leetcode 剑指Offer50.第一个只出现一次的字符
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
     * leetcode 剑指Offer62.圆圈中最后剩下的数字
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
}
