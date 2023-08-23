package com.jiangc.practice.heartbeat.test.sort;
import java.util.Arrays;
public class ArrSortTest {
    /*** 10个排序 ***/

    /*** 直接选择排序 ***/

    private static void directChooseSort ( int[] array )

    { for ( int i = 0; i < array.length; i++ )
    {  int index = i;
        for ( int j = i + 1; j < array.length; j++ )
        {
            if (array[index] > array[j])
            {
                index = j;
            }
        }
        if (i != index)
        {
            int temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
    }
    }

    /** * 堆排序 */
    private static void heapSort ( int[] array, int start, int len )
    {
        int pos = ( len - 1 ) / 2;
        for ( int i = pos; i >= 0; i-- )
        {
            int tmp = array[start + i];
            int index = i * 2 + 1;
            while (index < len)
            {
                if (index + 1 < len && array[start + index] < array[start + index + 1]) // 从小到大
                {
                    index += 1;
                }
                if (tmp < array[start + index]) // 从小到大
                {
                    array[start + i] = array[start + index];
                    i = index;
                    index = i * 2 + 1;
                }
                else
                {
                    break;
                }
            }
            array[start + i] = tmp;
        }
        for ( int i = 0; i < len; i++ )
        {
            int temp = array[start];
            array[start] = array[start + len - 1 - i];
            array[start + len - 1 - i] = temp;
            // 再一次
            int post = 0;
            int tmp = array[start + post];
            int index = 2 * post + 1;
            while (index < len - 1 - i)
            {
                if (index + 1 < len - 1 - i && array[start + index] < array[start + index + 1]) // 从小到大
                {
                    index += 1;
                }
                if (tmp < array[start + index]) // 从小到大
                {
                    array[start + post] = array[start + index];
                    post = index;
                    index = post * 2 + 1;
                }
                else
                {
                    break;
                }
            }
            array[start + post] = tmp;
        }
    }

    /*** 冒泡排序 */
    private static void bubbleSort ( int[] array )
    {
        for ( int i = 0; i < array.length; i++ )
        {
            for ( int j = i + 1; j < array.length; j++ )
            {
                if (array[i] > array[j])
                {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
    /** 快速排序 */
    private static void quickSort ( int[] array, int start, int end )
    {
        if (start < end)
        {
            int key = array[start];
            int i = start;
            for ( int j = start + 1; j < end + 1; j++ )
            {
                if (key > array[j])
                {
                    int temp = array[j];
                    array[j] = array[i + 1];
                    array[i + 1] = temp;
                    i++;
                }
            }
            array[start] = array[i];
            array[i] = key;
            quickSort (array, start, i - 1);
            quickSort (array, i + 1, end);
        }
    }
    /*** 直接插入排  */
    private static void directInsertSort ( int[] array )
    {
        for ( int i = 1; i < array.length; i++ )
        {
            int j;
            int temp = array[i];
            for ( j = i; j > 0; j-- )
            {
                if (array[j - 1] > temp)
                {
                    array[j] = array[j - 1];
                }
                else
                {
                    break;
                }
            }
            array[j] = temp;
        }
    }

    /*** 折半插入排序  */
    private static void binaryInsertSort ( int[] array )
    {
        for ( int i = 1; i < array.length; i++ )
        {
            // if (array[i] > array[i - 1]) // 从大到小
            if (array[i] < array[i - 1]) // 从小到大
            {
                int tmp = array[i];
                int low = 0;
                int high = i - 1;
                while (low <= high)
                {
                    int mid = ( low + high ) >>> 1;
                    // if (array[mid] > tmp) // 从大到小
                    if (array[mid] < tmp)// 从小到大
                    {
                        low = mid + 1;
                    }
                    else
                    {
                        high = mid - 1;
                    }
                }
                for ( int j = i; j > low; j-- )
                {
                    array[j] = array[j - 1];
                }
                array[low] = tmp;
            }
        }
    }

    /*** 希尔排序 */
    private static void shellSort ( int[] array, int start, int len )
    {
        int power = 1;
        while (( power + 1 ) * 2 < len)
        {
            power = ( power + 1 ) * 2 - 1;
        }
        for ( int k = power; k >= 1; k = ( k + 1 ) / 2 - 1 )
        {
            for ( int i = 0; i < k; i++ )
            {
                if (len - i <= 1)
                {
                    break;
                }
                int tmp;
                for ( int j = start + i + k; j < start + len; j += k )
                {
                    tmp = array[j];
                    int m = j;
                    for ( ; m > start + i; m -= k )
                    {
                        if (array[m - k] > tmp) // 从小到大
                        {
                            array[m] = array[m - k];
                        }
                        else
                        {
                            break;
                        }
                    }
                    array[m] = tmp;
                }
            }
        }
    }

    /*** 归并排序 */
    private static void mergeSort ( int[] array, int start, int end, int[] tempArray )
    {
        if (end <= start)
        {
            return;
        }
        int middle = ( start + end ) / 2;
        mergeSort (array, start, middle, tempArray);
        mergeSort (array, middle + 1, end, tempArray);
        int k = 0, leftIndex = 0, rightIndex = end - start;
        System.arraycopy (array, start, tempArray, 0, middle - start + 1);
        for ( int i = 0; i < end - middle; i++ )
        {
            tempArray[end - start - i] = array[middle + i + 1];
        }
        while (k < end - start + 1)
        {
            if (tempArray[rightIndex] > tempArray[leftIndex]) // 从小到大
            {
                array[k + start] = tempArray[leftIndex++];
            }
            else
            {
                array[k + start] = tempArray[rightIndex--];
            }
            k++;
        }
    }
    /*** 桶式排序 */
    private static void bucketSort ( int[] array, int min, int max )
    {
        int[] tmp = new int[array.length];
        int[] buckets = new int[max - min];
        for ( int i = 0; i < array.length; i++ )
        {
            buckets[array[i] - min]++;
        }
        // 从小到大
        // for ( int i = 1; i < max - min; i++ )
        // {
        // buckets[i] = buckets[i] + buckets[i - 1];
        // }
        // 从大到小
        for ( int i = max - min - 1; i > 0; i-- )
        {
            buckets[i - 1] = buckets[i] + buckets[i - 1];
        }
        System.arraycopy (array, 0, tmp, 0, array.length);
        for ( int k = array.length - 1; k >= 0; k-- )
        {
            array[--buckets[tmp[k] - min]] = tmp[k];
        }
    }

    /*** 基数排序 */
    private static void radixSort ( int[] array, int from, int radix, int d )
    {
        int len = array.length;
        int[] temporary = new int[len];
        int[] count = new int[radix];
        int R = 1;
        for ( int i = 0; i < d; i++ )
        {
            System.arraycopy (array, from, temporary, 0, len);
            Arrays.fill (count, 0);
            for ( int k = 0; k < len; k++ )
            {
                int subkey = ( temporary[k] / R ) % radix;
                count[subkey]++;
            }
            // 从小到大
            // for ( int j = 1; j < radix; j++ )
            // {
            // count[j] = count[j] + count[j - 1];
            // }
            // 从大到小
            for ( int j = 1; j < radix; j++ )
            {
                count[j - 1] = count[j] + count[j - 1];
            }
            for ( int m = len - 1; m >= 0; m-- )
            {
                int subkey = ( temporary[m] / R ) % radix;
                --count[subkey];
                array[from + count[subkey]] = temporary[m];
            }
            R *= radix;
        }
    }
    public static void main ( String[] args )
    {
        int[] array = new int[] { 11, 213, 134, 65, 77, 78, 23, 43 };
        directChooseSort (array);
        System.out.println (Arrays.toString (array));
        array = new int[] { 11, 213, 134, 65, 77, 78, 23, 43 };

        heapSort (array, 0, array.length);
        System.out.println (Arrays.toString (array));

        array = new int[] { 11, 213, 134, 65, 77, 78, 23, 43 };
        bubbleSort (array);
        System.out.println (Arrays.toString (array));
        array = new int[] { 11, 213, 134, 65, 77, 78, 23, 43 };
        quickSort (array, 0, array.length - 1);
        System.out.println (Arrays.toString (array));

        array = new int[] { 11, 213, 134, 65, 77, 78, 23, 43 };
        directInsertSort (array);
        System.out.println (Arrays.toString (array));

        array = new int[] { 11, 213, 134, 65, 77, 78, 23, 43 };
        binaryInsertSort (array);
        System.out.println (Arrays.toString (array));
        array = new int[] { 11, 213, 134, 65, 77, 78, 23, 43 };
        shellSort (array, 0, array.length);
        System.out.println (Arrays.toString (array));
        array = new int[] { 11, 213, 134, 65, 77, 78, 23, 43 };
        mergeSort (array, 0, array.length - 1, new int[array.length]);
        System.out.println (Arrays.toString (array));
        array = new int[] { 11, 213, 134, 65, 77, 78, 23, 43, 321 };
        bucketSort (array, 11, 322);
        System.out.println (Arrays.toString (array));
        array = new int[] { 111, 213, 134, 65, 77, 78, 23, 43, 321 };
        radixSort (array, 0, 2, array.length);
        System.out.println (Arrays.toString (array));
    }
}

