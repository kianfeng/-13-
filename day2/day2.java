package day2;
//https://docs.qq.com/doc/DUGRwWXNOVEpyaVpG
public class day2 {
  class Solution {
    /**
     input: sorted(1,2,3,4)
     output:sorted(1,4,9,16)

     cases 1: original is posive nubmer -> double and keep same spot
     cases 2: zero -> keep same
     cases 3: negative number -> need to re-sorted it

     method 1: loop to double each ele in array and then resort it
     time: o(n+ nlogn) depends on the quick sort algo space:o(logn) depends on the quick sort

     public int[] sortedSquares(int[] nums) {
     for(int i =0; i< nums.length; i++){
     int x = nums[i];
     nums[i] = x*x;
     }

     Arrays.sort(nums);
     return nums;
     }

     [STUDY]method 2: sorted array -> use two pointers
     time :O(n) space o(n)
     */
    public int[] sortedSquares(int[] nums) {
      int len = nums.length;
      int[] result = new int[len];
      int index = len-1; // new array last ele index

      int left = 0;
      int right = len-1;

      //why equal, cuz when left < right we are missing one ele in the mid
      while (left<=right){
        if(nums[left]* nums[left] > nums[right]*nums[right]){
          result[index--] = nums[left] * nums[left];
          left++;
        }else {
          result[index--] = nums[right] * nums[right];
          right--;
        }
      }
      return result;
    }
  }
}
