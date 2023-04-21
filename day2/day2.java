package day2;
//https://docs.qq.com/doc/DUGRwWXNOVEpyaVpG
public class day2 {

  class Solution1 {
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

  class Solution2 {
    /**
     nums[i] > 0
     find min len subarray nums[i:j] == target
     unsorted

     method 1: sliding windows

     i,j starting from left
     cur_sum keep track of current sum

     if cur_sum < target -> move j: cur_sum += nums[j]
     if cur_sum > target -> move i: cur_sum -= nums[i]
     if cur_sum = target -> store res(len of array), then move i and j forward

     how to find shortest?
     compare res with store res, always store MIN(res)

     time:o(n) space:o(1) only few pointers
     */
    public int minSubArrayLen(int target, int[] nums) {
      int len = nums.length;
      int left = 0, right = 0;
      int windowSum = 0; //store curr sum res
      int res = Integer.MAX_VALUE;

      while(right < nums.length){
        //expand the window
        windowSum += nums[right];
        right++;
        while(windowSum >=target && left < right) {
          //reach target, smaller window, update res
          res = Math.min(res, right-left);
          windowSum -= nums[left];
          left++;
        }

      }
      return res == Integer.MAX_VALUE ? 0 :res;
    }
  }


  class Solution3 {
    /**
     n = 1:
     1

     n=2:
     12
     43

     n=3:
     123
     894
     765

     n=4
     1234
     5678
     9012
     3456

     ....

     1.when to stop? total num of value is n*n
     2.how to move:
     left top -> right top -> right down -> left down -> left top -> right top

     time: o(n^2) total number is n*n. space:o(1)
     */
    public int[][] generateMatrix(int n) {
      int[][] matrix = new int[n][n];
      int col_start = 0, col_end = n-1;
      int row_start = 0, row_end = n-1;
      int count = 1; //count of element
      while(col_start <= col_end && row_start <= row_end){
        //left top -> right top
        for(int j=col_start; j<=col_end; j++){
          matrix[row_start][j] = count++;
        }

        //right top -> right down
        for(int i = row_start+1; i<=row_end; i++){
          matrix[i][col_end] = count++;
        }

        //right down -> left down
        for(int j=col_end-1; j>=col_start; j--){
          matrix[row_end][j] = count++;
        }

        //left down -> left up
        for(int i = row_end-1; i>row_start; i--){
          matrix[i][row_start] = count++;
        }

        //finished one loop, update corner point
        col_start++;
        col_end--;
        row_start++;
        row_end--;
      }
      return matrix;
    }
  }

}
